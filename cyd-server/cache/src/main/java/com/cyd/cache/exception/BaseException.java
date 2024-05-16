package com.cyd.cache.exception;

/**
 * @description  : [异常统一处理共通类]
 * @author       : [peng]
 * @version      : [v1.0]
 * @createTime   : [2021/7/29 11:56]
 * @updateUser   : [peng]
 * @updateTime   : [2021/7/29 11:56]
 * @updateRemark : [说明本次修改内容]
 */
public class BaseException extends RuntimeException {

  private static final long serialVersionUID = -4524309382639916401L;

  private int status = 200;

  public int getStatus() {return status;}

  public void setStatus(int status) {
    this.status = status;
  }

  public BaseException() {}

  public BaseException(String message, int status) {super(message);this.status = status;}

  public BaseException(String message) {
    super(message);
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
  }

  public BaseException(Throwable cause) {
    super(cause);
  }

  public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
