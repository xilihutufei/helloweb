package pre.fei.exceptions;

import pre.fei.enums.ResultEnum;

public class InitException extends Exception{

    private String msg;
    private int code;

    public InitException() {
        super();
    }

    public InitException(String msg) {
        super(msg);
    }

    public InitException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }


}
