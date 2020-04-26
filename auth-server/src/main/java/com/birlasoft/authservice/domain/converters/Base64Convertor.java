package com.birlasoft.authservice.domain.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Base64;

@Converter
public class Base64Convertor implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String rawData) {
        byte[] encodedBytes = Base64.getEncoder().encode(rawData.getBytes());
        StringBuilder builder = new StringBuilder();
        for(byte b : encodedBytes){

            builder.append(((char)b));
        }
       return builder.toString();
    }

    @Override
    public String convertToEntityAttribute(String encryptedData) {
        byte[] decodedBytes =  Base64.getDecoder().decode(encryptedData.getBytes());
        StringBuilder builder = new StringBuilder();
        for(byte b : decodedBytes){

            builder.append(((char)b));
        }
        return builder.toString();
    }
}
