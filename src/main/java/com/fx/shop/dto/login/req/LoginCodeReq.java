package com.fx.shop.dto.login.req;

import lombok.Data;

/**
 * 登录授权code请求实体
 *
 * @author 46105
 *
 */
@Data
public class LoginCodeReq {
    /**
     * 授权code
     */
    private String code;

    /**
     * appId
     */
    private String appId;
}
