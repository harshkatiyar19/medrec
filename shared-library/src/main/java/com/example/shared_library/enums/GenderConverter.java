package com.example.shared_library.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply=true)
public class GenderConverter implements AttributeConverter<Gender,String> {
    @Override
    public String convertToDatabaseColumn(Gender gender){
        return gender==null?null: gender.getCode();
    }

    @Override
    public Gender convertToEntityAttribute(String dbValue) {
        return dbValue==null?null:Gender.fromCode(dbValue);
    }

}
