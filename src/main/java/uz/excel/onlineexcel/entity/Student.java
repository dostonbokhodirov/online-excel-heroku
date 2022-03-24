package uz.excel.onlineexcel.entity;

import lombok.*;
import lombok.ToString;
import uz.excel.onlineexcel.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "auth")
@ToString
public class Student extends Auditable {

    @Column
    private String fullName;

    @Column
    private String universityName;

    @Column
    private String entranceYear; // o'qishgan kirgan yili

    @Column
    private String graduationYear; // bitirgan yili

    @Column
    private String faculty; // fakultet

    @Column
    private String speciality; //yo'nalish

    @Column
    private String studyType; //byudjet yoki kontrakt

    @Column
    private String academicType; //kunduzgi, sirtqi yoki kechki

    @Column
    private String diplomaSerial; //diplom seriyasi

    @Column
    private String diplomaRegistrationNumber; // diplom reg raqami

    @Column
    private String givenDate; // berilgan vaqti

    @Column
    private String academicLevel; //magistr yoki bakalavr

    @Column
    private String appendixNumber; // ilova raqami

    @Column(nullable = false)
    private Long organizationId;
}
