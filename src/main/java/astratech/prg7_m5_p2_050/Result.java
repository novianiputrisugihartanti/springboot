package astratech.prg7_m5_p2_050;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    private Integer status;
    private String result;

    public Result(Integer status, String result){
        this.status = status;
        this.result = result;
    }
}
