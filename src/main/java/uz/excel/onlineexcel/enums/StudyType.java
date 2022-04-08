package uz.excel.onlineexcel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StudyType implements BaseEnum {

    GRANT("Grant", "\u0413\u0440\u0430\u043D\u0442"),
    CONTRACT("Kontrakt", "\u041A\u043E\u043D\u0442\u0440\u0430\u043A\u0442");

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
