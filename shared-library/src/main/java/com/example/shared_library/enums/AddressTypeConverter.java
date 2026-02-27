package com.example.shared_library.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class AddressTypeConverter implements AttributeConverter<AddressType,String> {
    @Override
    public String convertToDatabaseColumn(AddressType addressType) {
        return addressType==null?null:addressType.getCode();
    }

    @Override
    public AddressType convertToEntityAttribute(String dbValue) {
        return dbValue==null?null:AddressType.fromCode(dbValue);
    }
}
