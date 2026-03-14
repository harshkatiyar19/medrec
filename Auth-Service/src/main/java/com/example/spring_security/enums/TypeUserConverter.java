package com.example.spring_security.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)

public class TypeUserConverter implements AttributeConverter<TypeUser,String> {
    @Override
    public String convertToDatabaseColumn(TypeUser typeUser) {
        return typeUser==null?null:typeUser.getCode();
    }

    @Override
    public TypeUser convertToEntityAttribute(String dbValue) {
        return dbValue==null?null: TypeUser.fromCode(dbValue);
    }
}
