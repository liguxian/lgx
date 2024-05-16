package com.cyd.common.result;

import java.io.Serializable;

/**
 * @author pqs
 * @date 2021-07-24 15:22
 **/
public class CommonResponse implements Serializable {

    private static final long serialVersionUID = -2564445585181441109L;
    private int code;
    private String message = "";
    private static final transient CommonResponse successResponse = new CommonResponse(200000, "");

    public static CommonResponse buildSuccessCommonResponse() {
        return successResponse;
    }

    public CommonResponse() {
    }

    public CommonResponse(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
