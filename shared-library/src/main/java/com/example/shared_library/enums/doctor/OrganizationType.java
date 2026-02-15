package com.example.shared_library.enums.doctor;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum OrganizationType {
    LAB("L"),
    CLINIC("C"),
    HOSPITAL("H");

    private final String code;

    OrganizationType(String code) {
        this.code = code;
    }

    private static final Map<String,OrganizationType> lookUp = new HashMap<>();

    static {
        for(OrganizationType o :OrganizationType.values()){
            lookUp.put(o.code,o);
        }
    }

    public static OrganizationType fromCode(String code){
        OrganizationType organizationType = lookUp.get(code);
        if(organizationType==null){
            throw new IllegalArgumentException("Invalid OrganizationType code: " + code);
        }
        return organizationType;
    }
}
