package com.fx.shop.dto.notify;

import lombok.Data;

@Data
public class WxPayNotifyCheckRequest  {
    /**
    * 
    */
    private static final long serialVersionUID = 5398436389209632399L;

    private String xmlStr;
    /**
     * 小程序AppId
     */
    private String appId;
}
