package pre.fei.vo;

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

}
