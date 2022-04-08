package uz.excel.onlineexcel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AcademicLevel implements BaseEnum {

    BACHELOR("Bakalavr", "\u0411\u0430\u043A\u0430\u043B\u0430\u0432\u0440"),
    MASTER("Magistr", "\u041C\u0430\u0433\u0438\u0441\u0442\u0440");

    private final String latin;
    private final String cyrillic;

    @Override
    public String getLatin() {
        return latin;
    }

    @Override
    public String getCyrillic() {
        return cyrillic;
    }

}
