package medirec.demo.enums;

import lombok.Getter;

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

}

