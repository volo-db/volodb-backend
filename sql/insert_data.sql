/* Delete DB and set it up again to clear all data */

DROP DATABASE  IF EXISTS `volo_db`;
CREATE DATABASE `volo_db`;
USE `volo_db`;

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `gender` INT NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `firstname` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `gender`;
CREATE TABLE `gender` (
  `id` INT  NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person` INT NOT NULL,
  `status` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `careof` VARCHAR(50),
  `country` INT NOT NULL,
  `state` VARCHAR(50),
  `street` VARCHAR(50) NOT NULL,
  `postal_code` VARCHAR(10) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person` INT NOT NULL,
  `type` INT NOT NULL,
  `val` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `contact_type`;
CREATE TABLE `contact_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` VARCHAR(50) NOT NULL,
  `person` INT NOT NULL,
  `secret` VARCHAR(100) NOT NULL,
  `organisational_role` VARCHAR(50),
  PRIMARY KEY (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user_role_mapping`;
CREATE TABLE `user_role_mapping` (
  `user` VARCHAR(50) NOT NULL,
  `user_role` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `legal_guardian`;
CREATE TABLE `legal_guardian` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `volunteer`;
CREATE TABLE `volunteer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `organisational_id` VARCHAR(50) NOT NULL,
  `person` INT NOT NULL,
  `created` DATETIME NOT NULL,
  `status` INT NOT NULL,
  `birthday` DATE,
  `birthplace` VARCHAR(50),
  `nationality` INT, 
  `social_insurance_number` VARCHAR(50),
  `health_insurance` INT,
  `tax_number` VARCHAR(50),
  `religion` INT,
  `bank_name` VARCHAR(50),
  `iban` VARCHAR(22),
  `bic` VARCHAR(11),
  `account_holder` VARCHAR(50),
  `level_of_school_edu` INT,
  `level_of_vocational_edu` INT,
  `ongoing_legal_proceedings` BOOLEAN,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `editing_history`;
CREATE TABLE `editing_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `editing_type` INT NOT NULL,
  `timestamp` DATETIME NOT NULL,
  `user` VARCHAR(50) NOT NULL,
  `volunteer` INT NOT NULL,
  `field` VARCHAR(50) NOT NULL,
  `former_value` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `editing_type`;
CREATE TABLE `editing_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `volunteer_status`;
CREATE TABLE `volunteer_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `healt_insurance`;
CREATE TABLE `healt_insurance` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `religion`;
CREATE TABLE `religion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `shorthand` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `school_edu`;
CREATE TABLE `school_edu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vocational_edu`;
CREATE TABLE `vocational_edu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `Contract`;
CREATE TABLE `Contract` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `timestamp` DATETIME NOT NULL,
  `volunteer` INT NOT NULL,
  `program` INT NOT NULL,
  `project` INT NOT NULL,
  `contact_person_of_project` INT,
  `start` DATE NOT NULL,
  `end` DATE NOT NULL,
  `visa_necessary` BOOLEAN NOT NULL,
  `incoming_volunteer` BOOLEAN NOT NULL,
  `salary` INT NOT NULL,
  `holiday` INT NOT NULL,
  `metadata` JSON,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `contract_modification`;
CREATE TABLE `contract_modification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `timestamp` DATETIME NOT NULL,
  `status` INT NOT NULL,
  `contract` INT NOT NULL,
  `type` INT NOT NULL,
  `into_force_from` DATE NOT NULL,
  `former_val` JSON NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `contract_modification_status`;
CREATE TABLE `contract_modification_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `contract_modification_type`;
CREATE TABLE `contract_modification_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(250),
  `pocket_money` DOUBLE NOT NULL,
  `free_of_charge_service` DOUBLE NOT NULL,
  `food_allowance` DOUBLE NOT NULL,
  `free_of_charge_food` DOUBLE NOT NULL,
  `accommodation_allowance` DOUBLE NOT NULL,
  `free_of_charge_accommodation` DOUBLE NOT NULL,
  `insurance_contributions` DOUBLE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `program`;
CREATE TABLE `program` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `shorthand` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `country`;
CREATE TABLE country (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(50) COMMENT 'Full country name.',
  `iso2` VARCHAR(2) NOT NULL COMMENT 'ISO 3166-1 alpha-2 code.',
  `iso3` VARCHAR(3) COMMENT 'ISO 3166-1 alpha-3 code.',
  `local_name` VARCHAR(50) COMMENT 'Local variation of the name.',
  `continent` ENUM('Africa', 'Antarctica', 'Asia', 'Europe', 'Oceania', 'North America', 'South America'),
  `nationality` VARCHAR(50) COMMENT 'Nationality in german language.'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COMMENT='Full list of countries.';

DROP TABLE IF EXISTS `health_insurance`;
CREATE TABLE health_insurance (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL COMMENT 'Full name of Insurance.',
  `web` VARCHAR(255) COMMENT 'Webside of Insurance.',
  `fee` DOUBLE NOT NULL COMMENT 'Zusatzbeitrag in Prozent',
  `additional_info` VARCHAR(512) COMMENT 'Geöffnet in'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COMMENT='Full list of german statutory health insurance companies.';

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `organisational_id` VARCHAR(50),
  `name` VARCHAR(50) NOT NULL,
  `shorthand` VARCHAR(32) NOT NULL,
  `description` TEXT(1000),
  `capacity` INT NOT NULL,
  `careof` VARCHAR(50),
  `country` INT NOT NULL,
  `state` VARCHAR(50),
  `street` VARCHAR(50) NOT NULL,
  `postal_code` VARCHAR(10) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `contact_person`;
CREATE TABLE `contact_person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person` INT NOT NULL,
  `project` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `project_role`;
CREATE TABLE `project_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `project_role_mapping`;
CREATE TABLE `project_role_mapping` (
  `contact_person` INT NOT NULL,
  `project_role` INT NOT NULL

);

ALTER TABLE `user` ADD FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `user_role_mapping` ADD FOREIGN KEY (`user`) REFERENCES `user` (`username`);

ALTER TABLE `user_role_mapping` ADD FOREIGN KEY (`user_role`) REFERENCES `user_role` (`id`);

ALTER TABLE `person` ADD FOREIGN KEY (`gender`) REFERENCES `gender` (`id`);

ALTER TABLE `address` ADD FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `address` ADD FOREIGN KEY (`country`) REFERENCES `country` (`id`);

ALTER TABLE `contact` ADD FOREIGN KEY (`type`) REFERENCES `contact_type` (`id`);

ALTER TABLE `contact` ADD FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `legal_guardian` ADD FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`status`) REFERENCES `volunteer_status` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`nationality`) REFERENCES `country` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`level_of_school_edu`) REFERENCES `school_edu` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`level_of_vocational_edu`) REFERENCES `vocational_edu` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`health_insurance`) REFERENCES `health_insurance` (`id`);

ALTER TABLE `volunteer` ADD FOREIGN KEY (`religion`) REFERENCES `religion` (`id`);

ALTER TABLE `editing_history` ADD FOREIGN KEY (`volunteer`) REFERENCES `volunteer` (`id`);

ALTER TABLE `editing_history` ADD FOREIGN KEY (`user`) REFERENCES `user` (`username`);

ALTER TABLE `editing_history` ADD FOREIGN KEY (`editing_type`) REFERENCES `editing_type` (`id`);

ALTER TABLE `contract` ADD FOREIGN KEY (`volunteer`) REFERENCES `volunteer` (`id`);

ALTER TABLE `contract` ADD FOREIGN KEY (`program`) REFERENCES `program` (`id`);

ALTER TABLE `contract` ADD FOREIGN KEY (`project`) REFERENCES `project` (`id`);

ALTER TABLE `contract` ADD FOREIGN KEY (`salary`) REFERENCES `salary` (`id`);

ALTER TABLE `contract` ADD FOREIGN KEY (`contact_person_of_project`) REFERENCES `contact_person` (`id`);

ALTER TABLE `contract_modification` ADD FOREIGN KEY (`type`) REFERENCES `contract_modification_type` (`id`);

ALTER TABLE `contract_modification` ADD FOREIGN KEY (`contract`) REFERENCES `contract` (`id`);

ALTER TABLE `contact_person` ADD FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `contact_person` ADD FOREIGN KEY (`project`) REFERENCES `project` (`id`);

ALTER TABLE `project_role_mapping` ADD FOREIGN KEY (`contact_person`) REFERENCES `contact_person` (`id`);

ALTER TABLE `project_role_mapping` ADD FOREIGN KEY (`project_role`) REFERENCES `project_role` (`id`);

ALTER TABLE `project` ADD FOREIGN KEY (`country`) REFERENCES `country` (`id`);

ALTER TABLE `contract_modification` ADD FOREIGN KEY (`status`) REFERENCES `contract_modification_status` (`id`);



/* ########################## */
/*      INSERT NEW DATA       */
/* ########################## */

INSERT INTO gender (name)
VALUES
  ('male'),
  ('female'),
  ('diverse'),
  ('not specified');

INSERT INTO person (gender, surname, firstname)
VALUES
  (1, 'Huber', 'Albert'),/*________01*/
  (2, 'Mayer', 'Roswita'),/*_______02*/
  (2, 'Schmidt', 'Susanne'),/*_____03*/
  (3, 'Winkler', 'Kim'),/*_________04*/
  (4, 'Schuster', 'Lian'),/*_______05*/
  (2, 'Wiesinger', 'Magdalena'),/*_06*/
  (1, 'Bopp', 'Jakob'),/*__________07*/
  (1, 'Urner', 'Fabian'),/*________08*/
  (2, 'Huber', 'Elisabeth'),/*_____09*/
  (1, 'Winkler', 'Horst'),/*_______10*/
  (1, 'Brunnhuber', 'Michael'),/*__11*/
  (1, 'Koalick', 'Björn'),/*_______12*/
  (1, 'Edenhofer', 'Martina'),/*___13*/
  (1, 'Goldsmits', 'Mike'),/*______14*/
  (1, 'Mohring', 'Erik');/*________15*/

INSERT INTO country (name, iso2, iso3, local_name, continent, nationality)
VALUES
  ('Bonaire, Sint Eustatius and Saba', 'BQ', 'BES', NULL, NULL, NULL),
  ('Curaçao','CW','CUW',NULL,NULL,NULL),
  ('Guernsey','GG','GGY',NULL,NULL,NULL),
  ('Isle of Man','IM','IMN',NULL,NULL,NULL),
  ('Jersey','JE','JEY',NULL,NULL,NULL),
  ('Åland Islands','AX','ALA',NULL,NULL,NULL),
  ('Montenegro','ME','MNE',NULL,NULL,NULL),
  ('Saint Barthélemy','BL','BLM',NULL,NULL,NULL),
  ('Saint Martin (French part)','MF','MAF',NULL,NULL,NULL),
  ('Serbia','RS','SRB',NULL,NULL,NULL),
  ('Sint Maarten (Dutch part)','SX','SXM',NULL,NULL,NULL),
  ('South Sudan','SS','SSD',NULL,NULL,NULL),
  ('Timor-Leste','TL','TLS',NULL,NULL,NULL),
  ('American Samoa','AS','ASM','Amerika Samoa','Oceania', 'Amerikanisch'),
  ('Andorra','AD','AND','Andorra','Europe', 'Andorranisch'),
  ('Angola','AO','AGO','Angola','Africa', 'Angolanisch'),
  ('Anguilla','AI','AIA','Anguilla','North America', 'Anguillanisch'),
  ('Antarctica','AQ','ATA','','Antarctica', NULL),
  ('Antigua and Barbuda','AG','ATG','Antigua und Barbuda','North America', 'Antiguanisch'),
  ('Argentina','AR','ARG','Argentinien','South America', 'Argentinisch'),
  ('Armenia','AM','ARM','Armenien','Asia', 'Armenisch'),
  ('Aruba','AW','ABW','Aruba','North America', 'Arubanisch'),
  ('Australia','AU','AUS','Australien','Oceania', 'Australisch'),
  ('Austria','AT','AUT','Österreich','Europe', 'Österreichisch'),
  ('Azerbaijan','AZ','AZE','Aserbaidschan','Asia', 'Aserbaidschanisch'),
  ('Bahamas','BS','BHS','Die Bahamas','North America', 'Bahamaisch'),
  ('Bahrain','BH','BHR','Al-Bahrayn','Asia', 'Bahrainisch'),
  ('Bangladesh','BD','BGD','Bangladesch','Asia', 'Bangladeschisch'),
  ('Barbados','BB','BRB','Barbados','North America', 'Barbadisch'),
  ('Belarus','BY','BLR','Weißrussland','Europe', 'Weißrussisch'),
  ('Belgium','BE','BEL','Belgien','Europe', 'Belgisch'),
  ('Belize','BZ','BLZ','Belize','North America', 'Belizianisch'),
  ('Benin','BJ','BEN','Benin','Africa', 'Beninisch'),
  ('Bermuda','BM','BMU','Bermuda','North America', 'Bermudisch'),
  ('Bhutan','BT','BTN','Bhutan','Asia', 'Bhutanisch'),
  ('Bolivia','BO','BOL','Bolivien','South America', 'Bolivianisch'),
  ('Bosnia and Herzegovina','BA','BIH','Bosnien und Herzegowina','Europe', 'Bosnisch'),
  ('Botswana','BW','BWA','Botswana','Africa', 'Botswanisch'),
  ('Bouvet Island','BV','BVT','Bouvetinsel','Antarctica', NULL),
  ('Brazil','BR','BRA','Brasilien','South America', 'Brasilianisch'),
  ('British Indian Ocean Territory','IO','IOT','Britisches Territorium im Indischen Ozean','Africa', 'Britisch'),
  ('Brunei Darussalam','BN','BRN','Brunei','Asia', 'Bruneiisch'),
  ('Bulgaria','BG','BGR','Bulgarien','Europe', 'Bulgarisch'),
  ('Burkina Faso','BF','BFA','Burkina Faso','Africa', 'Burkinisch'),
  ('Burundi','BI','BDI','Burundi','Africa', 'Burundisch'),
  ('Cambodia','KH','KHM','Kambodscha','Asia', 'Kambodschanisch'),
  ('Cameroon','CM','CMR','Kamerun','Africa', 'Kamerunisch'),
  ('Canada','CA','CAN','Kanada','North America', 'Kanadisch'),
  ('Cape Verde','CV','CPV','Kap Verde','Africa', 'Kap-Verdisch'),
  ('Cayman Islands','KY','CYM','Kaimaninseln','North America', 'Kaimanisch'),
  ('Central African Republic','CF','CAF','Zentralafrikanische Republik','Africa', 'Zentralafrikanisch'),
  ('Chad','TD','TCD','Tschad','Africa', 'Tschadisch'),
  ('Chile','CL','CHL','Chile','South America', 'Chilenisch'),
  ('China','CN','CHN','China','Asia', 'Chinesisch'),
  ('Christmas Island','CX','CXR','Weihnachtsinsel','Oceania', NULL),
  ('Cocos (Keeling) Islands','CC','CCK','Kokosinseln','Oceania', NULL),
  ('Colombia','CO','COL','Kolumbien','South America', 'Kolumbianisch'),
  ('Comoros','KM','COM','Komoren','Africa', 'Komorisch'),
  ('Congo','CG','COG','Kongo','Africa', 'Kongolesisch'),
  ('Congo, the Democratic Republic of the','CD','COD','Demokratische Republik Kongo','Africa', 'Kongolesisch'),
  ('Cook Islands','CK','COK','Cookinseln','Oceania', 'Cook-Inselnisch'),
  ('Costa Rica','CR','CRI','Costa Rica','North America', 'Costa-Ricanisch'),
  ('Cote DIvoire','CI','CIV','Elfenbeinküste','Africa', 'Ivorianisch'),
  ('Croatia','HR','HRV','Kroatien','Europe', 'Kroatisch'),
  ('Cuba','CU','CUB','Kuba','North America', 'Kubanisch'),
  ('Cyprus','CY','CYP','Zypern','Asia', 'Zyprisch'),
  ('Czech Republic','CZ','CZE','Tschechien','Europe', 'Tschechisch'),
  ('Denmark','DK','DNK','Dänemark','Europe', 'Dänisch'),
  ('Djibouti','DJ','DJI','Dschibuti','Africa', 'Dschibutisch'),
  ('Dominica','DM','DMA','Dominica','North America', 'Dominicanisch'),
  ('Dominican Republic','DO','DOM','Dominikanische Republik','North America', 'Dominicanisch'),
  ('Ecuador','EC','ECU','Ecuador','South America', 'Ecuadorianisch'),
  ('Egypt','EG','EGY','Ägypten','Africa', 'Ägyptisch'),
  ('El Salvador','SV','SLV','El Salvador','North America', 'Salvadorianisch'),
  ('Equatorial Guinea','GQ','GNQ','Äquatorialguinea','Africa', 'Äquatorialguineisch'),
  ('Eritrea','ER','ERI','Eritrea','Africa', 'Eritreisch'),
  ('Estonia','EE','EST','Estland','Europe', 'Estnisch'),
  ('Ethiopia','ET','ETH','Äthiopien','Africa', 'Äthiopisch'),
  ('Falkland Islands (Malvinas)','FK','FLK','Falklandinseln','South America', 'Falkländerisch'),
  ('Faroe Islands','FO','FRO','Färöer-Inseln','Europe', 'Färöisch'),
  ('Fiji','FJ','FJI','Fidschi','Oceania', 'Fidschianisch'),
  ('Finland','FI','FIN','Finnland','Europe', 'Finnisch'),
  ('France','FR','FRA','Frankreich','Europe', 'Französisch'),
  ('French Guiana','GF','GUF','Französisch-Guayana','South America', 'Französisch-Guayanisch'),
  ('French Polynesia','PF','PYF','Französisch-Polynesien','Oceania', 'Französisch-Polynesisch'),
  ('French Southern Territories','TF','ATF','Französische Süd- und Antarktisgebiete','Antarctica', 'Französisch'),
  ('Gabon','GA','GAB','Gabun','Africa', 'Gabunisch'),
  ('Gambia','GM','GMB','Gambia','Africa', 'Gambisch'),
  ('Georgia','GE','GEO','Georgien','Asia', 'Georgisch'),
  ('Germany','DE','DEU','Deutschland','Europe', 'Deutsch'),
  ('Ghana','GH','GHA','Ghana','Africa', 'Ghanaisch'),
  ('Gibraltar','GI','GIB','Gibraltar','Europe', 'Gibraltarianisch'),
  ('Greece','GR','GRC','Griechenland','Europe', 'Griechisch'),
  ('Greenland','GL','GRL','Grönland','North America', 'Grönländisch'),
  ('Grenada','GD','GRD','Grenada','North America', 'Grenadierisch'),
  ('Guadeloupe','GP','GLP','Guadeloupe','North America', 'Guadeloupisch'),
  ('Guam','GU','GUM','Guam','Oceania', 'Guamanisch'),
  ('Guatemala','GT','GTM','Guatemala','North America', 'Guatemaltekisch'),
  ('Guinea','GN','GIN','Guinea','Africa', 'Guineisch'),
  ('Guinea-Bissau','GW','GNB','Guinea-Bissau','Africa', 'Guinea-Bissauisch'),
  ('Guyana','GY','GUY','Guyana','South America', 'Guyanisch'),
  ('Haiti','HT','HTI','Haiti','North America', 'Haitianisch'),
  ('Heard Island and Mcdonald Islands','HM','HMD','Heard und McDonaldinseln','Antarctica', NULL),
  ('Holy See (Vatican City State)','VA','VAT','Heiliger Stuhl','Europe', 'Vatikanisch'),
  ('Honduras','HN','HND','Honduras','North America', 'Honduranisch'),
  ('Hong Kong','HK','HKG','Hongkong','Asia', 'Hongkongesisch'),
  ('Hungary','HU','HUN','Ungarn','Europe', 'Ungarisch'),
  ('Iceland','IS','ISL','Island','Europe', 'Isländisch'),
  ('India','IN','IND','Indien','Asia', 'Indisch'),
  ('Indonesia','ID','IDN','Indonesien','Asia', 'Indonesisch'),
  ('Iran, Islamic Republic of','IR','IRN','Iran','Asia', 'Iranisch'),
  ('Iraq','IQ','IRQ','Irak','Asia', 'Irakisch'),
  ('Ireland','IE','IRL','Irland','Europe', 'Irisch'),
  ('Israel','IL','ISR','Israel','Asia', 'Israelisch'),
  ('Italy','IT','ITA','Italien','Europe', 'Italienisch'),
  ('Jamaica','JM','JAM','Jamaika','North America', 'Jamaikanisch'),
  ('Japan','JP','JPN','Japan','Asia', 'Japanisch'),
  ('Jordan','JO','JOR','Jordanien','Asia', 'Jordanisch'),
  ('Kazakhstan','KZ','KAZ','Kasachstan','Asia', 'Kasachisch'),
  ('Kenya','KE','KEN','Kenia','Africa', 'Kenianisch'),
  ('Kiribati','KI','KIR','Kiribati','Oceania', 'Kiribatisch'),
  ('Korea, Democratic People''s Republic of','KP','PRK','Choson Minjujuui Inmin Konghwaguk (Bukhan)','Asia','Nordkoreanisch'),
  ('Korea, Republic of','KR','KOR','Taehan-minguk (Namhan)','Asia','Südkoreanisch'),
  ('Kuwait','KW','KWT','Al-Kuwayt','Asia','Kuwaitisch'),
  ('Kyrgyzstan','KG','KGZ','Kyrgyzstan','Asia','Kirgisisch'),
  ('Lao People''s Democratic Republic','LA','LAO','Lao','Asia','Laotisch'),
  ('Latvia','LV','LVA','Latvija','Europe','Lettisch'),
  ('Lebanon','LB','LBN','Lubnan','Asia','Libanesisch'),
  ('Lesotho','LS','LSO','Lesotho','Africa','Lesothisch'),
  ('Liberia','LR','LBR','Liberia','Africa','Liberianisch'),
  ('Libya','LY','LBY','Libiya','Africa','Libysch'),
  ('Liechtenstein','LI','LIE','Liechtenstein','Europe','Liechtensteinisch'),
  ('Lithuania','LT','LTU','Lietuva','Europe','Litauisch'),
  ('Luxembourg','LU','LUX','Luxembourg','Europe','Luxemburgisch'),
  ('Macao','MO','MAC','Macau/Aomen','Asia','Macaunisch'),
  ('Macedonia, the Former Yugoslav Republic of','MK','MKD','Makedonija','Europe','Mazedonisch'),
  ('Madagascar','MG','MDG','Madagasikara/Madagascar','Africa','Madagassisch'),
  ('Malawi','MW','MWI','Malawi','Africa','Malawisch'),
  ('Malaysia','MY','MYS','Malaysia','Asia','Malaysisch'),
  ('Maldives','MV','MDV','Dhivehi Raajje/Maldives','Asia','Maledivisch'),
  ('Mali','ML','MLI','Mali','Africa','Malisch'),
  ('Malta','MT','MLT','Malta','Europe','Maltesisch'),
  ('Marshall Islands','MH','MHL','Marshall Islands/Majol','Oceania','Marshallisch'),
  ('Martinique','MQ','MTQ','Martinique','North America','Martiniquais'),
  ('Mauritania','MR','MRT','Muritaniya/Mauritanie','Africa','Mauretanisch'),
  ('Mauritius','MU','MUS','Mauritius','Africa','Mauritisch'),
  ('Mayotte','YT','MYT','Mayotte','Africa','Mahoranisch'),
  ('Mexico','MX','MEX','Mexico','North America','Mexikanisch'),
  ('Micronesia, Federated States of','FM','FSM','Micronesia','Oceania','Mikronesisch'),
  ('Moldova, Republic of','MD','MDA','Moldova','Europe','Moldauisch'),
  ('Monaco','MC','MCO','Monaco','Europe','Monegassisch'),
  ('Mongolia','MN','MNG','Mongol Uls','Asia','Mongolisch'),
  ('Montenegro','ME','MNE','Montenegro','Europe','Montenegrinisch'),
  ('Montserrat','MS','MSR','Montserrat','North America','Montserratisch'),
  ('Morocco','MA','MAR','Al-Maghrib','Africa','Marokkanisch'),
  ('Mozambique','MZ','MOZ','Mozambique','Africa','Mosambikanisch'),
  ('Myanmar','MM','MMR','Myanma Pye','Asia','Myanmarisch'),
  ('Namibia','NA','NAM','Namibia','Africa','Namibisch'),
  ('Nauru','NR','NRU','Naoero/Nauru','Oceania','Nauruisch'),
  ('Nepal','NP','NPL','Nepal','Asia','Nepalesisch'),
  ('Netherlands','NL','NLD','Nederland','Europe','Niederländisch'),
  ('New Caledonia','NC','NCL','Nouvelle-Calédonie','Oceania','Neukaledonisch'),
  ('New Zealand','NZ','NZL','New Zealand/Aotearoa','Oceania','Neuseeländisch'),
  ('Nicaragua','NI','NIC','Nicaragua','North America','Nicaraguanisch'),
  ('Niger','NE','NER','Niger','Africa','Nigrisch'),
  ('Nigeria','NG','NGA','Nigeria','Africa','Nigerianisch'),
  ('Niue','NU','NIU','Niue','Oceania','Niuisch'),
  ('Norfolk Island','NF','NFK','Norfolk Island','Oceania','Norfolkinselisch'),
  ('Northern Mariana Islands','MP','MNP','Northern Mariana Islands','Oceania','Nördlich Marianisch'),
  ('Norway','NO','NOR','Norge','Europe','Norwegisch'),
  ('Oman','OM','OMN','Oman','Asia','Omanisch'),
  ('Pakistan','PK','PAK','Pakistan','Asia','Pakistanisch'),
  ('Palau','PW','PLW','Belau/Palau','Oceania','Palauisch'),
  ('Palestine, State of','PS','PSE','Filastin','Asia','Palästinensisch'),
  ('Panama','PA','PAN','República de Panamá','North America','Panamaisch'),
  ('Papua New Guinea','PG','PNG','Papua New Guinea/Papua Niugini','Oceania','Papua-Neuguineisch'),
  ('Paraguay','PY','PRY','Paraguay','South America','Paraguayisch'),
  ('Peru','PE','PER','Perú/Piruw','South America','Peruanisch'),
  ('Philippines','PH','PHL','Pilipinas','Asia','Philippinisch'),
  ('Pitcairn','PN','PCN','Pitcairn','Oceania','Pitcairnisch'),
  ('Poland','PL','POL','Polska','Europe','Polnisch'),
  ('Portugal','PT','PRT','Portugal','Europe','Portugiesisch'),
  ('Puerto Rico','PR','PRI','Puerto Rico','North America','Puerto Ricanisch'),
  ('Qatar','QA','QAT','Qatar','Asia','Katarisch'),
  ('Reunion','RE','REU','Reunion','Africa','Réunionnais'),
  ('Romania','RO','ROM','Romania','Europe','Rumänisch'),
  ('Russian Federation','RU','RUS','Rossija','Europe','Russisch'),
  ('Rwanda','RW','RWA','Rwanda/Urwanda','Africa','Rwandisch'),
  ('Saint Helena, Ascension and Tristan da Cunha','SH','SHN','Saint Helena','Africa','St. Helenisch'),
  ('Saint Kitts and Nevis','KN','KNA','Saint Kitts and Nevis','North America','St. Kittisch-Nevisisch'),
  ('Saint Lucia','LC','LCA','Saint Lucia','North America','St. Lucianisch'),
  ('Saint Pierre and Miquelon','PM','SPM','Saint-Pierre-et-Miquelon','North America','St. Pierreisch-Miquelonisch'),
  ('Saint Vincent and the Grenadines','VC','VCT','Saint Vincent and the Grenadines','North America','St. Vincentisch-Grenadinisch'),
  ('Samoa','WS','WSM','Samoa','Oceania','Samoanisch'),
  ('San Marino','SM','SMR','San Marino','Europe','San-Marinesisch'),
  ('Sao Tome and Principe','ST','STP','São Tomé e Príncipe','Africa','São-Toméisch-Prinzipisch'),
  ('Saudi Arabia','SA','SAU','Al-Mamlaka al-Arabiya as-Saudiya','Asia','Saudi-Arabisch'),
  ('Senegal','SN','SEN','Sénégal/Sounougal','Africa','Senegalesisch'),
  ('Seychelles','SC','SYC','Sesel/Seychelles','Africa','Seychellisch'),
  ('Sierra Leone','SL','SLE','Sierra Leone','Africa','Sierra-Leonisch'),
  ('Singapore','SG','SGP','Singapore/Singapura/Xinjiapo/Singapur','Asia','Singapurisch'),
  ('Slovakia','SK','SVK','Slovensko','Europe','Slowakisch'),
  ('Slovenia','SI','SVN','Slovenija','Europe','Slowenisch'),
  ('Solomon Islands','SB','SLB','Solomon Islands','Oceania','Salomonisch'),
  ('Somalia','SO','SOM','Soomaaliya','Africa','Somalisch'),
  ('South Africa','ZA','ZAF','South Africa','Africa','Südafrikanisch'),
  ('South Georgia and the South Sandwich Islands','GS','SGS','South Georgia and the South Sandwich Islands','Antarctica','Südgeorgisch-Sandwichisch'),
  ('Spain','ES','ESP','España','Europe','Spanisch'),
  ('Sri Lanka','LK','LKA','Sri Lanka/Ilankai','Asia','Sri-Lankisch'),
  ('Sudan','SD','SDN','As-Sudan','Africa','Sudanesisch'),
  ('Suriname','SR','SUR','Suriname','South America','Surinamisch'),
  ('Svalbard and Jan Mayen','SJ','SJM','Svalbard og Jan Mayen','Europe','Svalbardisch-Jan-Mayeisch'),
  ('Swaziland','SZ','SWZ','kaNgwane','Africa','Swasiländisch'),
  ('Sweden','SE','SWE','Sverige','Europe','Schwedisch'),
  ('Switzerland','CH','CHE','Schweiz/Suisse/Svizzera/Svizra','Europe','Schweizerisch'),
  ('Syrian Arab Republic','SY','SYR','Suriya','Asia','Syrisch'),
  ('Taiwan (Province of China)','TW','TWN','Tai-wan','Asia','Taiwanesisch'),
  ('Tajikistan','TJ','TJK','Tajikistan','Asia','Tadschikisch'),
  ('Tanzania, United Republic of','TZ','TZA','Tanzania','Africa','Tansanisch'),
  ('Thailand','TH','THA','Prathet Thai','Asia','Thailändisch'),
  ('Togo','TG','TGO','Togo','Africa','Togolesisch'),
  ('Tokelau','TK','TKL','Tokelau','Oceania','Tokelauisch'),
  ('Tonga','TO','TON','Tonga','Oceania','Tongaisch'),
  ('Trinidad and Tobago','TT','TTO','Trinidad and Tobago','North America','Trinidadisch-Tobagisch'),
  ('Tunisia','TN','TUN','Tunis/Tunisie','Africa','Tunesisch'),
  ('Turkey','TR','TUR','Türkiye','Asia','Türkisch'),
  ('Turkmenistan','TM','TKM','Türkmenistan','Asia','Turkmenisch'),
  ('Turks and Caicos Islands','TC','TCA','The Turks and Caicos Islands','North America','Turks-und-Caicos-Insulanisch'),
  ('Tuvalu','TV','TUV','Tuvalu','Oceania','Tuvaluisch'),
  ('Uganda','UG','UGA','Uganda','Africa','Ugandisch'),
  ('Ukraine','UA','UKR','Ukrajina','Europe','Ukrainisch'),
  ('United Arab Emirates','AE','ARE','Al-Amirat al-Arabiya al-Muttahida','Asia','Emiratisch'),
  ('United Kingdom','GB','GBR','United Kingdom','Europe','Britisch'),
  ('United States','US','USA','United States','North America','Amerikanisch'),
  ('United States Minor Outlying Islands','UM','UMI','United States Minor Outlying Islands','Oceania','Amerikanisch'),
  ('Uruguay','UY','URY','Uruguay','South America','Uruguayisch'),
  ('Uzbekistan','UZ','UZB','Uzbekiston','Asia','Usbekisch'),
  ('Vanuatu','VU','VUT','Vanuatu','Oceania','Vanuatuisch'),
  ('Venezuela','VE','VEN','Venezuela','South America','Venezolanisch'),
  ('Viet Nam','VN','VNM','Viet Nam','Asia','Vietnamesisch'),
  ('Virgin Islands (British)','VG','VGB','British Virgin Islands','North America','Britisch'),
  ('Virgin Islands (U.S.)','VI','VIR','Virgin Islands of the United States','North America','Amerikanisch'),
  ('Wallis and Futuna','WF','WLF','Wallis-et-Futuna','Oceania','Wallisianisch-Futunaisch'),
  ('Western Sahara','EH','ESH','As-Sahrawiya','Africa','Saharauisch'),
  ('Yemen','YE','YEM','Al-Yaman','Asia','Jemenitisch'),
  ('Zambia','ZM','ZMB','Zambia','Africa','Sambisch'),
  ('Zimbabwe', 'ZW', 'ZWE', 'Zimbabwe', 'Africa','Simbabwisch'),
  ('Afghanistan', 'AF', 'AFG', 'Afghanistan', 'Asia', 'Afghanisch'),
  ('Algeria', 'DZ', 'DZA', 'Algerien', 'Africa', 'Algerisch');

INSERT INTO address (person, status, name, careof, country, state, street, postal_code, city)
VALUES
  (1, 0, 'Zu Hause', null, 90, null, 'Unterm Auto 9', '81234', 'Hinterdupfing'),
  (1, 1, 'EST', 'c/o Aktionszentrum', 90, null, 'Don-Bosco-Str. 1', '83671  ', 'Benediktbeuern'),
  (2, 1, 'Zu Hause', null, 90, null, 'Blumenweg 8', '12345  ', 'Musterhause'),
  (3, 1, 'Zu Hause', null, 90, null, 'Hinterm-Schupfn 108', '65432  ', 'Tabularasa'),
  (4, 1, 'Zu Hause', null, 90, null, 'Sonnhalde 6', '32785', 'Maxdorf'),
  (5, 0, 'Zu Hause', null, 90, null, 'Vaubanallee 124', '67543  ', 'Haudorf'),
  (5, 1, 'EST', null, 90, null, 'St.-Wolfgangs-Platz 11', '81669', 'München'),
  (9, 1, 'LG', null, 90, null, 'Unterm Auto 9', '81234', 'Hinterdupfing'),
  (10, 1, 'LG', null, 90, null, 'Sonnhalde 6', '32785', 'Maxdorf');

INSERT INTO contact_type (name)
VALUES
  ('email'),
  ('mobile'),
  ('landline'),
  ('whatsapp'),
  ('signal'),
  ('threema'),
  ('telegram'),
  ('instagram');

INSERT INTO contact (person, type, val)
VALUES
  (1, 1, 'huber.alber@web.de'),
  (1, 2, '00491512345678'),
  (1, 3, '004989523498'),
  (1, 4, '00491512345678'),
  (1, 7, 'albi04'),
  (2, 1, 'bsp.email@web.de'),
  (2, 2, '00491512345678'),
  (3, 1, 'bsp.email@web.de'),
  (3, 2, '00491512345678'),
  (4, 1, 'bsp.email@web.de'),
  (4, 2, '00491512345678'),
  (5, 1, 'bsp.email@web.de'),
  (5, 2, '00491512345678'),
  (5, 1, 'brunnhuber@aktionszentrum.de'),
  (5, 1, 'koalick@don-bosco-jh.de'),
  (5, 1, 'edenhofer@donbosco.de'),
  (5, 1, 'goldsmits@donbosco.de'),
  (5, 1, 'mohring@manege-berlin.de');

INSERT INTO user (person, username, secret, organisational_role)
VALUES
  (6, 'magdalena.wiesinger', '$2a$12$bvG0ScICxF6gaF4cstczNub7PI79w/o0EIez9dlUoZe70eMP.eaL2', 'Fachreferentin für Freiwilligendienste'),
  (7, 'jakob.bopp', '$2a$12$bvG0ScICxF6gaF4cstczNub7PI79w/o0EIez9dlUoZe70eMP.eaL2', 'Fachreferent für Freiwilligendienste'),
  (8, 'fabian.urner', '$2a$12$bvG0ScICxF6gaF4cstczNub7PI79w/o0EIez9dlUoZe70eMP.eaL2', 'Fachreferent für Freiwilligendienste');

INSERT INTO user_role (name)
VALUES
  ('Administrator'),
  ('Referentin'),
  ('Buchhaltung'),
  ('Seminarleitung');

INSERT INTO user_role_mapping (user, user_role)
VALUES
  ('magdalena.wiesinger', 2),
  ('magdalena.wiesinger', 4),
  ('jakob.bopp', 2),
  ('jakob.bopp', 3),
  ('fabian.urner', 1),
  ('fabian.urner', 2),
  ('fabian.urner', 3);

INSERT INTO legal_guardian (person)
VALUES
  (9),
  (10);

INSERT INTO volunteer_status (name)
VALUES
  ('BEWORBEN'),/*---------------1*/
  ('GEEINIGT'),/*---------------2*/
  ('BEGR.-PKT VERSCHICKT'),/*---3*/
  ('VEREINBARUNG ERSTELLT'),/*--4*/
  ('IM EINSATZ'),/*-------------5*/
  ('BEENDET'),/*----------------6*/
  ('ZURÜCKGEZOGEN'),/*----------7*/
  ('ABGEBROCHEN'),/*------------8*/
  ('GEKÜNDIGT');/*--------------9*/


INSERT INTO health_insurance (name, web, fee, additional_info)
VALUES
  ('AOK - Die Gesundheitskasse für Niedersachsen', 'www.aok.de/niedersachsen', 1.50, 'Niedersachsen'),
  ('AOK - Die Gesundheitskasse in Hessen', 'www.aok.de/hessen', 1.60, 'Hessen'),
  ('AOK Baden-Württemberg', 'www.aok.de/badenwuerttemberg/index.php', 1.60, 'Baden-Württemberg'),
  ('AOK Bayern - Die Gesundheitskasse', 'www.aok.de/bayern', 1.58, 'Bayern'),
  ('AOK Bremen / Bremerhaven', 'www.aok.de/bremen', 1.38, 'Bremen'),
  ('AOK Nordost - Die Gesundheitskasse', 'www.aok.de/nordost/', 1.90, 'Berlin, Brandenburg, Mecklenburg-Vorpommern'),
  ('AOK NordWest - Die Gesundheitskasse', 'www.aok.de/nordwest', 1.89, 'Nordrhein-Westfalen, Schleswig-Holstein'),
  ('AOK PLUS - Die Gesundheitskasse für Sachsen und Thüringen', 'www.aok.de/aokplus', 1.50, 'Sachsen, Thüringen'),
  ('AOK Rheinland/Hamburg - Die Gesundheitskasse', 'www.aok.de/rheinlandhamburg', 1.80, 'Hamburg, Nordrhein-Westfalen'),
  ('AOK Rheinland-Pfalz/Saarland - Die Gesundheitskasse', 'www.aok.de/rheinland-pfalzsaarland/', 1.80, 'Rheinland-Pfalz, Saarland'),
  ('AOK Sachsen-Anhalt - Die Gesundheitskasse', 'www.aok.de/sachsen-anhalt', 1.00, 'Sachsen-Anhalt'),
  ('Audi BKK', 'www.Audibkk.de', 1.25, 'bundesweit'),
  ('BAHN-BKK', 'www.bahn-bkk.de', 1.70, 'bundesweit'),
  ('BARMER', 'www.barmer.de', 1.50, 'bundesweit'),
  ('BERGISCHE KRANKENKASSE', 'www.bergischekrankenkasse.de', 1.60, 'Hamburg, Hessen, Nordrhein-Westfalen'),
  ('Bertelsmann BKK', 'www.bertelsmann-bkk.de', 1.40, 'Baden-Württemberg, Bayern, Berlin, Hamburg, Hessen, Mecklenburg-Vorpommern, Niedersachsen, Nordrhein-Westfalen, Sachsen, Thüringen'),
  ('Betriebskrankenkasse BPW Bergische Achsen KG', 'www.bkk-bpw.de', 1.80, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('Betriebskrankenkasse der G.M. Pfaff AG', 'www.bkk-pfaff.de', 0.80, 'Rheinland-Pfalz'),
  ('Betriebskrankenkasse EWE', 'www.bkk-ewe.de', 0.90, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('Betriebskrankenkasse Miele', 'www.bkk-miele.de', 1.20, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('Betriebskrankenkasse Mobil', 'www.mobil-krankenkasse.de', 1.49, 'bundesweit'),
  ('Betriebskrankenkasse PricewaterhouseCoopers', 'www.bkk-pwc.de', 1.48, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('Betriebskrankenkasse Technoform', 'www.bkk-technoform.de', 1.50, 'Baden-Württemberg, Bayern, Niedersachsen, Rheinland-Pfalz'),
  ('BIG direkt gesund', 'www.big-direkt.de', 1.45, 'bundesweit'),
  ('BKK Akzo Nobel Bayern', 'www.bkk-akzo.de', 1.55, 'Bayern'),
  ('BKK B. Braun Aesculap', 'www.bkk-bba.de', 1.75, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('BKK Deutsche Bank AG', 'www.bkk-deutsche-bank.de', 1.60, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('BKK Diakonie', 'www.bkk-diakonie.de', 1.60, 'Baden-Württemberg, Bayern, Berlin, Brandenburg, Bremen, Hamburg, Hessen, Mecklenburg-Vorpommern, Niedersachsen, Nordrhein-Westfalen, Rheinland-Pfalz, Sachsen, Sachsen-Anhalt, Schleswig-Holstein'),
  ('BKK EUREGIO', 'www.bkk-euregio.de', 0.84, 'Hamburg, Nordrhein-Westfalen'),
  ('BKK evm', 'www.bkk-evm.de', 0.90, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('BKK exklusiv', 'www.bkkexklusiv.de', 1.99, 'Bremen, Mecklenburg-Vorpommern, Niedersachsen, Nordrhein-Westfalen, Sachsen-Anhalt, Schleswig-Holstein'),
  ('BKK Faber-Castell & Partner', 'www.bkk-faber-castell.de', 1.35, 'Bayern'),
  ('BKK firmus', 'www.bkk-firmus.de', 0.90, 'bundesweit'),
  ('BKK Freudenberg', 'www.bkk-freudenberg.de', 1.50, 'Baden-Württemberg, Bayern, Berlin, Hessen, Niedersachsen, Nordrhein-Westfalen, Rheinland-Pfalz, Sachsen'),
  ('BKK GILDEMEISTER SEIDENSTICKER', 'www.bkkgs.de', 0.90, 'bundesweit'),
  ('BKK Groz-Beckert', 'www.bkk-gb.de', 0.00, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('BKK Herkules', 'www.bkk-herkules.de/', 1.09, 'Bayern, Hessen, Niedersachsen'),
  ('BKK Linde', 'www.bkk-linde.de', 1.50, 'bundesweit'),
  ('BKK MAHLE', 'www.bkk-mahle.de', 1.50, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('bkk melitta hmr', 'www.bkk-melitta-hmr.de/', 1.40, 'Baden-Württemberg, Bayern, Brandenburg, Bremen, Hessen, Niedersachsen, NordrheinWestfalen, Sachsen, Sachsen-Anhalt, Schleswig-Holstein'),
  ('BKK MTU', 'www.bkk-mtu.de', 1.60, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('BKK Pfalz', 'www.bkkpfalz.de', 1.55, 'bundesweit'),
  ('BKK ProVita', 'www.bkk-provita.de/', 1.49, 'bundesweit'),
  ('BKK Public', 'www.bkk-public.de', 1.50, 'Hamburg, Niedersachsen, NordrheinWestfalen'),
  ('BKK Rieker.RICOSTA.Weisser', 'www.bkk-rrw.de', 2.00, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('BKK Salzgitter', 'www.bkk-salzgitter.de', 1.60, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('BKK Scheufelen', 'www.bkk-scheufelen.de', 1.40, 'Baden-Württemberg'),
  ('BKK Schwarzwald-Baar-Heuberg', 'www.bkk-sbh.de', 0.98, 'Baden-Württemberg'),
  ('BKK Textilgruppe Hof', 'www.bkk-textilgruppe-hof.de', 0.90, 'Bayern'),
  ('BKK VBU (BKK Verkehrsbau Union)', 'www.meine-krankenkasse.de', 1.80, 'bundesweit'),
  ('BKK VDN', 'www.bkk-vdn.de', 1.50, 'Hamburg, Nordrhein-Westfalen, Sachsen'),
  ('BKK VerbundPlus', 'www.bkk-verbundplus.de', 1.35, 'bundesweit'),
  ('BKK Voralb HELLER*INDEX*LEUZE', 'www.bkk-voralb.de', 0.50, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('BKK WERRA-MEISSNER', 'www.bkk-werra-meissner.de', 1.80, 'Bayern, Hessen'),
  ('BKK WIRTSCHAFT UND FINANZEN', 'www.bkk-wf.de/', 1.69, 'Baden-Württemberg, Bayern, Berlin, Brandenburg, Bremen, Hamburg, Hessen, Niedersachsen, Nordrhein-Westfalen, Rheinland-Pfalz, Saarland, Sachsen'),
  ('BKK ZF & Partner', 'www.bkk-zf-partner.de', 1.45, 'Baden-Württemberg, Bayern, Berlin, Brandenburg, Bremen, Hamburg, Hessen, Niedersachsen, Nordrhein-Westfalen, Rheinland-Pfalz, Saarland, Sachsen, Sachsen-Anhalt, Thüringen'),
  ('BKK_DürkoppAdler', 'www.bkk-da.de', 1.29, 'Nordrhein-Westfalen'),
  ('BKK24', 'www.bkk24.de', 1.79, 'bundesweit'),
  ('BKK-Würth', 'www.bkk-wuerth.de', 0.98, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('BMW BKK', 'www.bmwbkk.de', 0.30, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('Bosch BKK', 'www.bosch-bkk.de', 1.50, 'Baden-Württemberg, Bayern, Berlin, Brandenburg, Hamburg, Hessen, Mecklenburg-Vorpommern, Niedersachsen, Nordrhein-Westfalen, Rheinland-Pfalz, Saarland, Sachsen, Sachsen-Anhalt, Thüringen'),
  ('Continentale Betriebskrankenkasse', 'www.continentale-bkk.de', 1.40, 'bundesweit'),
  ('DAK-Gesundheit', 'www.dak.de', 1.70, 'bundesweit'),
  ('Debeka BKK', 'www.debeka-bkk.de', 1.69, 'bundesweit'),
  ('energie-Betriebskrankenkasse', 'www.energie-bkk.de', 1.59, 'bundesweit'),
  ('Ernst & Young BKK', 'www.ey-bkk.de', 1.54, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('Handelskrankenkasse (hkk)', 'www.hkk.de', 0.98, 'bundesweit'),
  ('Heimat Krankenkasse', 'www.heimat-krankenkasse.de', 1.30, 'bundesweit'),
  ('HEK - Hanseatische Krankenkasse', 'www.hek.de', 1.30, 'bundesweit'),
  ('IKK - Die Innovationskasse', 'www.die-ik.de/', 1.60, 'bundesweit'),
  ('IKK classic', 'www.ikk-classic.de', 1.60, 'bundesweit'),
  ('IKK gesund plus', 'www.ikk-gesundplus.de', 1.10, 'bundesweit'),
  ('IKK Südwest', 'www.ikk-suedwest.de', 1.65, 'Hessen, Rheinland-Pfalz, Saarland'),
  ('INNUNGSKRANKENKASSE BRANDENBURG UND BERLIN', 'www.ikkbb.de', 1.77, 'Berlin, Brandenburg'),
  ('KARL MAYER BKK', 'www.karlmayer-bkk.de', 2.50, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('Kaufmännische Krankenkasse - KKH', 'www.kkh.de', 1.50, 'bundesweit'),
  ('KNAPPSCHAFT', 'www.knappschaft.de', 1.60, 'bundesweit'),
  ('Koenig & Bauer BKK', 'www.koenig-bauer-bkk.de', 1.44, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('Krones Betriebskrankenkasse', 'www.bkk-krones.de', 0.90, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('Mercedes-Benz BKK', 'www.mercedes-benzbkk.com//', 1.30, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('Merck BKK', 'www.merck-bkk.de', 1.10, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('mhplus Betriebskrankenkasse', 'www.mhplus.de', 1.58, 'Baden-Württemberg, Bayern, Berlin, Hamburg, Hessen, Mecklenburg-Vorpommern, Niedersachsen, Nordrhein-Westfalen, Rheinland-Pfalz, Sachsen, Sachsen-Anhalt, Schleswig-Holstein, Thüringen'),
  ('Novitas BKK', 'www.novitas-bkk.de', 1.54, 'bundesweit'),
  ('Pronova BKK', 'www.pronovabkk.de', 1.70, 'bundesweit'),
  ('R+V Betriebskrankenkasse', 'www.ruv-bkk.de', 1.40, 'bundesweit'),
  ('Salus BKK', 'www.salus-bkk.de', 1.59, 'bundesweit'),
  ('SECURVITA BKK', 'www.securvita.de', 1.60, 'Baden-Württemberg, Bayern, Berlin, Brandenburg, Hamburg, Hessen, Mecklenburg-Vorpommern, Niedersachsen, Nordrhein-Westfalen, Rheinland-Pfalz, Sachsen, Sachsen-Anhalt, Schleswig-Holstein, Thüringen'),
  ('Siemens-Betriebskrankenkasse (SBK)', 'www.sbk.org', 1.50, 'bundesweit'),
  ('SKD BKK', 'www.skd-bkk.de', 1.15, 'Baden-Württemberg, Bayern, Berlin, Bremen, Hamburg, Hessen, Niedersachsen, Nordrhein-Westfalen, Saarland, Sachsen, Schleswig-Holstein'),
  ('Sozialversicherung für Landwirtschaft, Forsten und Gartenbau (SVLFG)', 'www.svlfg.de', 0, 'branchenbezogen'),
  ('Südzucker BKK', 'www.suedzucker-bkk.de', 1.10, 'betriebsbezogen (nur für Mitarbeitende wählbar)'),
  ('Techniker Krankenkasse (TK)', 'www.tk.de', 1.20, 'bundesweit'),
  ('TUI BKK', 'www.tui-bkk.de/', 1.50, 'bundesweit'),
  ('VIACTIV Krankenkasse', 'www.viactiv.de', 1.60, 'bundesweit'),
  ('vivida bkk', 'www.vividabkk.de/', 1.40, 'bundesweit'),
  ('WMF Betriebskrankenkasse', 'www.wmf-bkk.de', 1.60, 'bundesweit');

INSERT INTO religion (name, shorthand)
VALUES
  ('altkatholisch','ak'),/*____________1*/
  ('apostolisch','ap'),/*______________2*/
  ('baptistisch','ba'),/*______________3*/
  ('buddhistisch','bd'),/*_____________4*/
  ('calvinistisch','ca'),/*____________5*/
  ('Christliche Wissenschaft','cs'),/*_6*/
  ('Dissident','di'),/*________________7*/
  ('evangelisch','ev'),/*______________8*/
  ('französisch reformiert','fr'),/*___9*/
  ('griechisch orthodox','go'),/*______10*/
  ('islamisch','is'),/*________________11*/
  ('jüdisch','jd'),/*__________________12*/
  ('katholisch','ka'),/*_______________13*/
  ('evangelisch augsburgisch','lt'),/*_14*/
  ('lutherisch','lu'),/*_______________15*/
  ('mennoitisch','me'),/*______________16*/
  ('moslemisch','ml'),/*_______________17*/
  ('methodistisch','mt'),/*____________18*/
  ('neu apostolisch','na'),/*__________19*/
  ('ohne Glaubensbekenntnis','ob'),/*__20*/
  ('orthodox','ox'),/*_________________21*/
  ('presbyritanisch','pr'),/*__________22*/
  ('reformiert','rf'),/*_______________23*/
  ('römisch katholisch','rk'),/*_______24*/
  ('russisch orthodox','ro'),/*________25*/
  ('evangelisch uniert','un'),/*_______26*/
  ('walonisch reformiert','wr'),/*_____27*/
  ('Zeuge Jehovas','zj'),/*____________28*/
  ('Nicht bekannt','nb');/*____________29*/

INSERT INTO school_edu (name)
VALUES
  ('Kein Schulabschluss'),
  ('Qualifizierender Hauptschulabschluss'),
  ('Mittlere Reife / Berufsfachschulabschluss'),
  ('Abitur / Fachabitur'),
  ('keine Angaben');

INSERT INTO vocational_edu (name)
VALUES
  ('keine'),
  ('abgebrochene Berufsausbildung'),
  ('abgebrochenes Studium'),
  ('Berufsausbildung (abgeschlossen)'),
  ('Studium (abgeschlossen)'),
  ('keine Angaben');

INSERT INTO volunteer (organisational_id, person, created, status, birthday, birthplace, nationality, social_insurance_number, health_insurance, tax_number, religion, bank_name, iban, bic, account_holder, level_of_school_edu, level_of_vocational_edu, ongoing_legal_proceedings)
VALUES
  ('FWDSDB2223_001', 1, '2022-02-01 09:12:45.293', 5, '2002-03-18', 'Frankfurt', 90, NULL, NULL, NULL, 24, 'Musterbank', 'DE89370400440532013000', 'COBADEFFXXX', 'Albert Huber', 4, 1, false),
  ('FWDSDB2223_002', 2, '2022-03-01 09:12:45.293', 5, '2002-03-18', 'Frankfurt', 90, NULL, NULL, NULL, 1, 'Musterbank', 'DE89370400440532013000', 'COBADEFFXXX', 'Roswita Mayer', 3, 1, false),
  ('FWDSDB2223_003', 3, '2022-04-01 09:12:45.293', 5, '2002-03-18', 'Frankfurt', 90, NULL, 2, NULL, 8, 'Musterbank', 'DE89370400440532013000', 'COBADEFFXXX', 'Susanne Schmidt', 4, 1, false),
  ('FWDSDB2223_004', 4, '2022-06-01 09:12:45.293', 5, '2002-03-18', 'Frankfurt', 90, NULL, 9, NULL, 22, 'Musterbank', 'DE89370400440532013000', 'COBADEFFXXX', 'Kim Winkler', 4, 1, false),
  ('FWDSDB2223_005', 5, '2022-07-01 09:12:45.293', 5, '2002-03-18', 'Frankfurt', 90, NULL, 29, NULL, 20, 'Musterbank', 'DE89370400440532013000', 'COBADEFFXXX', 'Lian Schuster', 2, 1, false);

INSERT INTO editing_type (name)
VALUES
  ('post'),
  ('patch'),
  ('delete');

INSERT INTO editing_history (editing_type, timestamp, user, volunteer, field, former_value)
VALUES
  (2, '2023-09-01 09:12:45.293', 'jakob.bopp', 1, 'email', 'die-hubers@t-online.de'),
  (1, '2023-09-02 10:37:11.628', 'fabian.urner', 2, 'mobil', '+49 (0) 152 / 089782451'),
  (3, '2023-09-05 08:21:58.144', 'magdalena.wiesinger', 3, 'email', 'krieger-koenigin111@gmx.net');

INSERT INTO program (name, shorthand)
VALUES
  ('Bundesfreiwilligendienst', 'BFD'),
  ('Freiwilliges Soziales Jahr', 'FSJ'),
  ('Europäischer Solidaritätskorps', 'ESK'),
  ('weltwärts', 'ww');

INSERT INTO project (organisational_id, name, shorthand, description, capacity, careof, country, state, street, postal_code, city, phone, email)
VALUES
  ('ESTSDB001', 'Aktionszentrum Benediktbeuern', 'AZ', 'Jugendbildungsstätte bla blub etc. pp!', 4, NULL, 90, NULL, 'Don-Bosco-Str. 1', '83671', 'Benediktbeuern', '0049885788303', 'info@aktionszentrum.de'),
  ('ESTSDB002', 'Jugendherberge Benediktbeuern', 'JuHe', 'Jugendherberge bla blub etc. pp!', 6, NULL, 90, NULL, 'Don-Bosco-Str. 1', '83671', 'Benediktbeuern', '0049885788303', 'info@don-bosco-jh.de'),
  ('ESTSDB003', 'Jugendwohnheim Salesianum', 'Sales', 'Jugendwohnheim bla blub etc. pp!', 8, NULL, 90, NULL, 'St.-Wolfgangs-Platz 11', '81669', 'München', '004989480080', 'info@salesianum.de'),
  ('ESTSDB004', 'Jugendbildungsstätte Don Bosco', 'Jünkerath', 'Jugenbildungsstätte in der Eifel, bla blub etc pp', 4, NULL, 90, NULL, 'Don-Bosco-Str. 1', '54584', 'Jünkerath', '00496597929311', 'info@don-bosco-juenkerath.de'),
  ('ESTSDB005', 'Manege gGmbH', 'Berlin', 'Jugendhilfe, bla blub etc pp', 4, NULL, 90, NULL, 'Otto-Rosenberg-Str. 1', '12681', 'Berlin', '0049308560686251', 'info@manege-berlin.de');

INSERT INTO contact_person (person, project)
VALUES
  (11, 1),
  (12, 2),
  (13, 3),
  (14, 4),
  (15, 5);

INSERT INTO project_role (name)
VALUES
  ('Einrichtungsleint'),
  ('Personalsachbearbeitung'),
  ('Zuständige Person für FWD'),
  ('Fachliche Anleitung'),
  ('Mentor:in');

INSERT INTO project_role_mapping (contact_person, project_role)
VALUES
  (1, 5),
  (2, 3),
  (2, 4),
  (2, 5),
  (3, 3),
  (3, 5),
  (4, 3),
  (4, 4),
  (4, 5),
  (5, 1),
  (5, 3),
  (5, 4);

INSERT INTO salary (name, description, pocket_money, free_of_charge_service, food_allowance, free_of_charge_food, accommodation_allowance, free_of_charge_accommodation, insurance_contributions)
VALUES
  ('Modell 1', 'Wohnt in EST und wird von EST verpflegt.', 210, 0, 0, 288, 0, 225.25, 289.3),
  ('Modell 2', 'Wohnt zu Hause bei Eltern und wird dort verpflegt.', 210, 0, 160, 0, 0, 0, 148),
  ('Modell 3', 'Wohnt in eigener Wohnung und verpflegt sich selbst.', 210, 0, 265, 0, 200, 0, 270),
  ('Modell 4', 'Wohnt in EST verpflegt sich jedoch selbst.', 210, 0, 265, 0, 0, 225.25, 280.1);
  
INSERT INTO contract (timestamp, volunteer, program, project, contact_person_of_project, start, end, visa_necessary, incoming_volunteer, salary, holiday, metadata)
VALUES
  ('2023-03-08 12:34:56.789', 1, 1, 1, 1, '2022-09-01', '2023-08-31', false, false, 1, 26, '{ "bfd-nummer": "20020318AH1T3" }'),
  ('2023-03-08 12:34:56.789', 2, 2, 2, 2, '2022-09-01', '2023-08-31', false, false, 1, 26, '{ "bfd-nummer": "20020318RM411" }'),
  ('2023-03-08 12:34:56.789', 3, 1, 3, 3, '2020-09-01', '2021-08-31', false, false, 1, 26, '{ "bfd-nummer": "20020318SS7S2" }'),
  ('2023-03-08 12:34:56.789', 3, 1, 1, 1, '2022-09-01', '2023-02-28', false, false, 1, 26, '{ "bfd-nummer": "20020318SS7S2" }'),
  ('2023-03-08 12:34:56.789', 4, 1, 4, 4, '2022-09-01', '2023-08-31', false, false, 1, 26, '{ "bfd-nummer": "20020318KW32Q" }'),
  ('2023-03-08 12:34:56.789', 5, 1, 5, 5, '2022-09-01', '2023-08-31', false, false, 1, 26, '{ "bfd-nummer": "20020318LS91Z" }');

INSERT INTO contract_modification_type (name)
VALUES
  ('Änderung der Bezüge'),
  ('Verlängerung'),
  ('Verkürzung'),
  ('Aufhebung'),
  ('Kündigung');

INSERT INTO contract_modification_status (name)
VALUES
  ('beantrag'),
  ('genehmigt'),
  ('abgelehnt');

INSERT INTO contract_modification (timestamp, status, contract, type, into_force_from, former_val)
VALUES
  ('2023-09-08 12:34:56.789', 2, 1, 2, '2023-09-08', '{ "end": "2024-02-29"}'),
  ('2023-09-09 11:16:39.312', 1, 4, 1, '2023-10-01', '{ "salary": 3 }');
