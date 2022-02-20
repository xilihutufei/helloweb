package pre.fei.vo;

import pre.fei.enums.ResultEnum;

/**
 * @Auth puhongfei
 * @Date 2021/12/3
 * @Desc TODO
 */
public class Result {

    private String msg;
    private Object date;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static Result ofSuccess(){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        return result;
    }

    public static Result ofFail(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    public static Result ofFail(String msg){
        Result result = new Result();
        result.setCode(-100);
        result.setMsg(msg);
        return result;
    }

}
