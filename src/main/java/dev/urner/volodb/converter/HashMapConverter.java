package dev.urner.volodb.converter;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> metadata) {

        String metadataJson = null;
        try {
            metadataJson = new ObjectMapper().writeValueAsString(metadata);
        } catch (final JsonProcessingException e) {
            System.err.println("JSON writing error" + e);
        }

        return metadataJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String metadataJson) {

        Map<String, Object> metadata = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(JsonWriteFeature.QUOTE_FIELD_NAMES.mappedFeature());
            metadata = mapper.readValue(metadataJson, new TypeReference<Map<String, Object>>() {});
        } catch (final IOException e) {
            System.err.println("JSON reading error" + e);
        }

        return metadata;
    }
}
