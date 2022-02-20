package pre.fei.exceptions;

import pre.fei.enums.ResultEnum;

public class RequestException extends Exception{

    private String msg;
    private int code;

    public RequestException() {
        super();
    }

    public RequestException(String msg) {
        super(msg);
    }

    public RequestException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }


}
