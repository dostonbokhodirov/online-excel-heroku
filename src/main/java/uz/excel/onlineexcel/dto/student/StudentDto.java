package uz.excel.onlineexcel.dto.student;

import lombok.*;
import uz.excel.onlineexcel.dto.base.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudentDto extends GenericDto {

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

    @Builder
    public StudentDto(Long id, String fullName, String universityName, String entranceYear,
                      String graduationYear, String faculty, String speciality, String studyType,
                      String academicType, String diplomaSerial, String diplomaRegistrationNumber,
                      String givenDate, String academicLevel, String appendixNumber, Long organizationId) {
        super(id);
        this.fullName = fullName;
        this.universityName = universityName;
        this.entranceYear = entranceYear;
        this.graduationYear = graduationYear;
        this.faculty = faculty;
        this.speciality = speciality;
        this.studyType = studyType;
        this.academicType = academicType;
        this.diplomaSerial = diplomaSerial;
        this.diplomaRegistrationNumber = diplomaRegistrationNumber;
        this.givenDate = givenDate;
        this.academicLevel = academicLevel;
        this.appendixNumber = appendixNumber;
        this.organizationId = organizationId;
    }

    public StudentDto(String fullName, String universityName, String entranceYear, String graduationYear, String faculty, String speciality, String studyType, String academicType, String diplomaSerial, String diplomaRegistrationNumber, String givenDate, String academicLevel, String appendixNumber, Long organizationId) {
        this.fullName = fullName;
        this.universityName = universityName;
        this.entranceYear = entranceYear;
        this.graduationYear = graduationYear;
        this.faculty = faculty;
        this.speciality = speciality;
        this.studyType = studyType;
        this.academicType = academicType;
        this.diplomaSerial = diplomaSerial;
        this.diplomaRegistrationNumber = diplomaRegistrationNumber;
        this.givenDate = givenDate;
        this.academicLevel = academicLevel;
        this.appendixNumber = appendixNumber;
        this.organizationId = organizationId;
    }
}
