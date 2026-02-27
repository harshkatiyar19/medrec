package com.example.shared_library.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum AddressType {
    PERMANENT("P"),CURRENT("C"),BOTH("B");

    private final String code;

    AddressType(String code) {
        this.code = code;
    }

    private static final Map<String,AddressType>  lookUp= new HashMap<>();

    static{
        for(AddressType a : AddressType.values()){
            lookUp.put(a.code,a);
        }
    }

    public static AddressType fromCode(String code){
        AddressType address = lookUp.get(code);
        if(address==null){
            throw new IllegalArgumentException("Invalid Address code: " + code);
        }
        return address;
    }
}
