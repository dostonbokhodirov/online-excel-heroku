package uz.excel.onlineexcel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDto {


    private String fullName;

    private String universityName;

    private String entranceYear; // o'qishgan kirgan yili

    private String graduationYear; // bitirgan yili

    private String faculty; // fakultet

    private String speciality; //yo'nalish

    private String studyType; //byudjet yoki kontrakt

    private String academicType; //kunduzgi, sirtqi yoki kechki

    private String diplomaSerial; //diplom seriyasi

    private String diplomaRegistrationNumber; // diplom reg raqami

    private String givenDate; // berilgan vaqti

    private String academicLevel; //magistr yoki bakalavr

    private String appendixNumber; // ilova raqami



}
