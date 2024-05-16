package com.cyd.cache.exception;

import lombok.Data;

/**
 * @description  : [业务异常共通类]
 * @author       : [peng]
 * @version      : [v1.0]
 * @createTime   : [2021/7/29 11:56]
 * @updateUser   : [peng]
 * @updateTime   : [2021/7/29 11:56]
 * @updateRemark : [说明本次修改内容]
 */
@Data
public class ServiceException extends BaseException {

  private static final long serialVersionUID = -6937826101963935619L;

  private String code;

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(String code, String message) {super(message);this.code = code;}

  public ServiceException(String code, String message, Throwable cause) {super(message, cause);this.code = code;}

  public ServiceException(String code, Throwable cause) {super(cause);this.code = code; }

}
