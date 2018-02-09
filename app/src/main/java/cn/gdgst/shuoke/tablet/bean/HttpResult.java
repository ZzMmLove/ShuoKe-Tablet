package cn.gdgst.shuoke.tablet.bean;

import java.io.Serializable;

/**
 * 服务器返回结果的基类
 * Created by Administrator on 2/7 0007.
 */

public class HttpResult<T> implements Serializable {

    private boolean success;
    private int error_code;
    private String message;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
