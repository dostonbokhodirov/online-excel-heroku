package uz.excel.onlineexcel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
//@Entity
//@Table(schema = "excel")
public class ExcelFile {
//    @Column(nullable = false)
    private String name;

//    @Column(nullable = false)
    private String path;

//    @Column(nullable = false)
    private String mimeType;
}
