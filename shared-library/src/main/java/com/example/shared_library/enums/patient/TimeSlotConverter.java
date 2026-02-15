package com.example.shared_library.enums.patient;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TimeSlotConverter implements AttributeConverter<TimeSlot,String> {
    @Override
    public String convertToDatabaseColumn(TimeSlot timeSlot) {
        return timeSlot==null?null: timeSlot.getCode();
    }

    @Override
    public TimeSlot convertToEntityAttribute(String dbValue) {
        return dbValue==null?null:TimeSlot.fromCode(dbValue);
    }
}
