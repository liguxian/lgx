package com.cyd.common.result;

import java.io.Serializable;

/**
 * @author pqs
 * @date 2021-07-24 15:23
 **/
public class GeneralResult<T> extends CommonResponse implements Serializable {

    private static final long serialVersionUID = 2523377728598055414L;
    private T result;
    private boolean isSuccess;

    public GeneralResult() {
    }

    private GeneralResult(int code, T data, String msg) {
        this.setCode(code);
        this.setMessage(msg);
        this.result = data;
        this.isSuccess = false;
    }

    public static <T> GeneralResult<T> error() {
        GeneralResult<T> response = new GeneralResult();
        response.setCode(500);
        response.setMessage("request error");
        response.isSuccess = false;
        return response;
    }

    public static <T> GeneralResult<T> error(String msg) {
        GeneralResult<T> response = new GeneralResult();
        response.setCode(500);
        response.setMessage(msg);
        response.isSuccess = false;
        return response;
    }

    public static <T> GeneralResult<T> error(int code, String msg) {
        return error(code, msg, null);
    }

    public static <T> GeneralResult<T> error(int code, String msg, T result) {
        GeneralResult<T> response = new GeneralResult();
        response.setCode(code);
        response.setMessage(msg);
        response.setResult(result);
        response.isSuccess = false;
        return response;
    }

    public static <T> GeneralResult<T> success(T data) {
        GeneralResult<T> response = new GeneralResult();
        response.setCode(200);
        response.setMessage("成功");
        response.setResult(data);
        response.isSuccess = true;
        return response;
    }

    public static <T> GeneralResult<T> success() {
        GeneralResult<T> response = new GeneralResult();
        response.setCode(200);
        response.setMessage("成功");
        response.isSuccess = true;
        return response;
    }

    public static GeneralResult newInstance(int code, Object data, String msg) {
        return new GeneralResult(code, data, msg);
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof GeneralResult)) {
            return false;
        } else {
            GeneralResult<?> other = (GeneralResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$result = this.getResult();
                Object other$result = other.getResult();
                if (this$result == null) {
                    if (other$result != null) {
                        return false;
                    }
                } else if (!this$result.equals(other$result)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GeneralResult;
    }


    public String toString() {
        return "R(result=" + this.getResult() + ")";
    }

    public void setResult(final T result) {
        this.result = result;
    }

    public T getResult() {
        return this.result;
    }


    public void setIsSuccess(final boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return this.isSuccess;
    }

}
