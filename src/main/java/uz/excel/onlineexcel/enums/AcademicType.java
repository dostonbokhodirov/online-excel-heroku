package uz.excel.onlineexcel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AcademicType {

    FULL_TIME("Kunduzgi", "\u041A\u0443\u043D\u0434\u0443\u0437\u0433\u0438"),
    EVENING("Kechki", "\u041A\u0435\u0447\u043A\u0438"),
    DISTANCE("Sirtqi", "\u0421\u0438\u0440\u0442\u049B\u0438");

    private final String latin;
    private final String cyrillic;

    public  String getLatinLow() {
        return latin.toLowerCase().substring(0, 2);
    }

    public String getCyrillicLow() {
        return cyrillic.toLowerCase().substring(0, 2);
    }
}
