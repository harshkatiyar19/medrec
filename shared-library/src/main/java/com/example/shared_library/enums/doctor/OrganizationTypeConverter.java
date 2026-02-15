package com.example.shared_library.enums.doctor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrganizationTypeConverter implements AttributeConverter<OrganizationType,String> {
    @Override
    public String convertToDatabaseColumn(OrganizationType organizationType) {
        return organizationType==null?null:organizationType.getCode();
    }

    @Override
    public OrganizationType convertToEntityAttribute(String dbValue) {
        return dbValue==null?null:OrganizationType.fromCode(dbValue);
    }
}
