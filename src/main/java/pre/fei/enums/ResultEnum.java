package pre.fei.enums;

public enum  ResultEnum {

    INIT_ERROR(-1, "初始化异常"),
    USER_EXIST(-2, "用户名重复"),
    SYSTEM_ERROR(-3, "系统异常"),
    ;

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
