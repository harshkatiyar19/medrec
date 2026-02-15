package com.example.shared_library.enums.patient;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum TimeSlot {
    MORNING("M"),
    NOON("N"),
    EVENING("E"),
    NIGHT("N");

    private final String code;

    TimeSlot(String code){this.code=code;}

    private static final Map<String,TimeSlot> lookUp =new HashMap<>();

    static{
        for(TimeSlot t:TimeSlot.values()){
            lookUp.put(t.code,t);
        }
    }

    public static TimeSlot fromCode(String code){
        TimeSlot timeSlot=lookUp.get(code);
        if(timeSlot==null){
            throw new IllegalArgumentException("Invalid TimeSlot code: " + code);
        }
        return timeSlot;
    }
}
