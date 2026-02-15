package com.example.shared_library.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BloodGroupConverter implements AttributeConverter<BloodGroup,String> {
    @Override
    public String convertToDatabaseColumn(BloodGroup bloodGroup) {
        return bloodGroup==null?null:bloodGroup.getCode();
    }

    @Override
    public BloodGroup convertToEntityAttribute(String dbValue) {
        return dbValue==null?null:BloodGroup.fromCode(dbValue);
    }
}
