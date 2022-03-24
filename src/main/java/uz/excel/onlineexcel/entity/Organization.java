package uz.excel.onlineexcel.entity;


import lombok.Getter;
import lombok.Setter;
import uz.excel.onlineexcel.entity.base.Auditable;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(schema = "organizationn", name = "organization")
public class Organization extends Auditable {

    @Column
    private String name;

    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;

    @OneToOne(fetch = FetchType.EAGER)
    private AuthUser owner;

}
