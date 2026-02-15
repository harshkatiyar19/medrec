package com.example.shared_library.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Gender{
    MALE("M"),
    FEMALE("F"),
    TRANSGENDER("T");

    private final String code;
    Gender(String code){this.code=code;}

    private static final Map<String,Gender> lookUp = new HashMap<>();

    static{
        for (Gender g :Gender.values()){
            lookUp.put(g.code,g);
        }
    }

    public static Gender fromCode(String code){
        Gender gender = lookUp.get(code);
        if (gender == null) {
            throw new IllegalArgumentException("Invalid Gender code: " + code);
        }
        return gender;
    }
}
