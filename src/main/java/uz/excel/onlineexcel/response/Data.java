package uz.excel.onlineexcel.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data<T> {

    private Integer totalCount;
    private T body;

    public Data(T body) {
        this.body = body;
    }

    public Data(Integer totalCount, T body) {
        this.totalCount = totalCount;
        this.body = body;
    }
}
