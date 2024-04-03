package dev.urner.volodb.service;

import dev.urner.volodb.dao.VolunteerDAO;
import dev.urner.volodb.entity.Gender;
import dev.urner.volodb.entity.Volunteer;
import dev.urner.volodb.entity.VolunteerInvalidFormatException;
import dev.urner.volodb.entity.VolunteerNotFoundException;
import dev.urner.volodb.entity.Enums.OngoingLegalProceedingsState;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;

import lombok.RequiredArgsConstructor;

import org.apache.commons.io.FilenameUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class VolunteerService {

  private final VolunteerDAO volunteerDAO;

  // private final PersonService ps;
  private final VolunteerStatusService vss;
  private final CountryService cs;
  private final HealthInsuranceService hs;
  private final ReligionService rs;
  private final SchoolEduService ses;
  private final VocationalEduService ves;
  private final MinioClient minioClient;

  public List<Volunteer> findAll() {
    return volunteerDAO.findAll();
  }

  public Volunteer findById(int theId) {
    return volunteerDAO.findById(theId);
  }

  @Transactional
  public Volunteer save(Volunteer theVolunteer) {
    return volunteerDAO.save(theVolunteer);
  }

  @Transactional
  public void deleteById(int theId) {
    volunteerDAO.deleteById(theId);
  }

  @Transactional
  public Volunteer update(int theVolunteerId, Map<String, Object> fields) {
    Volunteer dbVolunteer = volunteerDAO.findById(theVolunteerId);

    if (dbVolunteer == null)
      throw new VolunteerNotFoundException("VolunteerId " + theVolunteerId + " not found.");

    fields.forEach((key, value) -> {

      // if User trys to set id- or created-field -> ignore
      if (key.toLowerCase() == "id" || key.toLowerCase() == "created")
        return;

      // Person
      if (key.toLowerCase() == "person") {
        Map<String, Object> personMap = (Map<String, Object>) value;
        personMap.forEach((pKey, pValue) -> {
          switch (pKey) {
            case "lastname":
              dbVolunteer.getPerson().setLastname(pValue.toString());
              return;

            case "firstname":
              dbVolunteer.getPerson().setFirstname(pValue.toString());
              return;

            case "gender":
              dbVolunteer.getPerson().setGender(new Gender(pValue.toString()));
              return;
          }
        });
        return;

      }

      // VolunteerStatus
      if (key.toLowerCase().equals("status")) {
        try {
          dbVolunteer.setStatus(vss.findByName(value.toString()));
        } catch (Exception e) {
          throw new VolunteerInvalidFormatException("Volunteer-Status '" + value + "' not found.");
        }
        return;
      }

      // Nationality
      if (key.toLowerCase().equals("nationality")) {
        try {
          dbVolunteer.setNationality(cs.findByNationalityName(value.toString()));
        } catch (Exception e) {
          throw new VolunteerInvalidFormatException("Nationality '" + value + "' not found.");
        }
        return;
      }

      // HealthInsurance
      if (key.toLowerCase().equals("healthinsurance")) {
        try {
          dbVolunteer.setHealthInsurance(hs.findByName(value.toString()));
        } catch (Exception e) {
          throw new VolunteerInvalidFormatException("Insurance with Name '" + value + "' not found.");
        }
        return;
      }

      // Religion
      if (key.toLowerCase().equals("religion")) {
        try {
          dbVolunteer.setReligion(rs.findByName(value.toString()));
        } catch (Exception e) {
          throw new VolunteerInvalidFormatException("Religion with Name '" + value + "' not found.");
        }
        return;
      }

      // SchoolEdu
      if (key.toLowerCase().equals("levelofschooledu")) {
        try {
          dbVolunteer.setLevelOfSchoolEdu(ses.findByName(value.toString()));
        } catch (Exception e) {
          throw new VolunteerInvalidFormatException("Schooleducation with Name '" + value + "' not found.");
        }
        return;
      }

      // VocationalEdu
      if (key.toLowerCase().equals("levelofvocationaledu")) {
        try {
          dbVolunteer.setLevelOfVocationalEdu(ves.findByName(value.toString()));
        } catch (Exception e) {
          throw new VolunteerInvalidFormatException("Vocational education with Name '" + value + "' not found.");
        }
        return;
      }

      // All other Cases:
      Field field = ReflectionUtils.findField(Volunteer.class, key);

      // if fieldname does not exist in Object
      if (field == null)
        return;

      field.setAccessible(true);

      if (field.getName().toLowerCase().equals("birthday")) {
        ReflectionUtils.setField(field, dbVolunteer, LocalDate.parse(value.toString()));
        return;
      }

      if (field.getName().toLowerCase().equals("ongoinglegalproceedings")) {
        OngoingLegalProceedingsState state;

        if (value == null) {
          ReflectionUtils.setField(field, dbVolunteer, OngoingLegalProceedingsState.NOT_SET);
          return;
        }

        switch (value.toString().toLowerCase()) {
          case "yes":
            state = OngoingLegalProceedingsState.YES;
            break;
          case "true":
            state = OngoingLegalProceedingsState.YES;
            break;
          case "no":
            state = OngoingLegalProceedingsState.NO;
            break;
          case "false":
            state = OngoingLegalProceedingsState.NO;
            break;
          case "not_set":
            state = OngoingLegalProceedingsState.NOT_SET;
            break;
          case "null":
            state = OngoingLegalProceedingsState.NOT_SET;
            break;

          default:
            throw new VolunteerInvalidFormatException(
                "ongoingLegalProceedings has to be 'yes', 'true', 'no', 'false', 'not_set' or 'null'");
        }

        ReflectionUtils.setField(field, dbVolunteer, state);
        return;
      }

      ReflectionUtils.setField(field, dbVolunteer, value);
    });

    return volunteerDAO.save(dbVolunteer);
  }

  public String SetAvatar(MultipartFile avatar, int volunteerId) {
    Volunteer dbVolunteer = volunteerDAO.findById(volunteerId);
    if (dbVolunteer == null) {
      throw new VolunteerNotFoundException("Volunteer with Id: '" + volunteerId + "' not found.");
    }

    String fileExtension = FilenameUtils.getExtension(avatar.getOriginalFilename());
    if (!fileExtension.toLowerCase().matches("jpg|jpeg|png")) {
      throw new VolunteerInvalidFormatException("only .jpg or .png files allowed");
    }

    String bucketName = "volunteer-" + volunteerId;
    String objectName = "avatar/avatar-" + volunteerId + "." + fileExtension;

    try {
      // If Bucket doesn't exist: Create it!
      if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
        minioClient.makeBucket(
            MakeBucketArgs.builder()
                .bucket(bucketName)
                .build());
      }

      // Store Avatar in Bucket
      minioClient.putObject(PutObjectArgs.builder()
          .bucket(bucketName)
          .object(objectName)
          .stream(avatar.getInputStream(), avatar.getSize(), -1)
          .build());

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }

    dbVolunteer.setAvatar(bucketName + "/" + objectName);
    dbVolunteer = volunteerDAO.save(dbVolunteer);

    return dbVolunteer.getAvatar();
  }

  // public String GetAvatarLink(int volunteerId) {
  // Volunteer dbVolunteer = volunteerDAO.findById(volunteerId);
  // if (dbVolunteer == null) {
  // throw new VolunteerNotFoundException("Volunteer with Id: '" + volunteerId +
  // "' not found.");
  // }

  // // Set the Avatar-Field in Volunteer-Entity @JsonIgnore
  // // -> then provide over Getter-Method the Bucket- and ObjectName
  // // String bucketName = getBucketNameByVoloId(volunteerId);
  // // String objectName = "avatar/avatar-" + volunteerId + "." + fileExtension;

  // try {
  // // Get sharable Link
  // String avatarLink =
  // minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
  // .method(Method.GET)
  // .bucket(bucketName)
  // .object(objectName)
  // .expiry(1, TimeUnit.DAYS)
  // .build());

  // dbVolunteer.setAvatar(avatarLink);

  // } catch (Exception e) {
  // throw new RuntimeException(e.getMessage());
  // }

  // return "";
  // }

}
