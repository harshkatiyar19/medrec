package com.example.shared_library.enums;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum BloodGroup{
    O_NEGATIVE("O-"),
    O_POSITIVE("O+"),
    A_NEGATIVE("A-"),
    A_POSITIVE("A+"),
    B_NEGATIVE("B-"),
    B_POSITIVE("B+"),
    AB_NEGATIVE("AB-"),
    AB_POSITIVE("AB+");


    private final String code;

    BloodGroup(String code) {
        this.code=code;
    }

    private static final Map<String,BloodGroup> lookUp = new HashMap<>();

    static {
        for(BloodGroup b : BloodGroup.values()){
            lookUp.put(b.code,b);
        }
    }

    public static BloodGroup fromCode(String code){
        BloodGroup bloodGroup = lookUp.get(code);
        if(bloodGroup==null){
            throw new IllegalArgumentException("Invalid BloodGroup code: " + code);
        }
        return bloodGroup;
    }

}

