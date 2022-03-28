package uz.excel.onlineexcel.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.excel.onlineexcel.dto.base.GenericDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateDto extends GenericDto {

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

    private String appendixNumber; // ilova raqami(nullable = false)

    private Long organizationId;
}
