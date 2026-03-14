package com.example.spring_security.enums;

import com.example.shared_library.enums.AddressType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
public enum TypeUser {
    ADMIN("A"),DOCTOR("D"),PATIENT("P");

    private final String code;


    TypeUser(String code) {
        this.code = code;
    }

    private static final Map<String, TypeUser> lookUp= new HashMap<>();

    static{
        for(TypeUser t : TypeUser.values()){
            lookUp.put(t.code,t);
        }
    }

    public static TypeUser fromCode(String code){
        TypeUser typeUser = lookUp.get(code);
        if(typeUser==null){
            throw new IllegalArgumentException("Invalid User Type code: " + code);
        }
        return typeUser;
    }
}
