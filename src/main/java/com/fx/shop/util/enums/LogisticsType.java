package com.fx.shop.util.enums;

/**
 * 物流快递公司编码
 *
 * @author ly
 * @date 2020-7-21
 */
public enum LogisticsType {
    SF("SF", "顺丰速运"),
    HTKY("HTKY", "百世快递"),
    ZTO("ZTO", "中通快递"),
    STO("STO", "申通快递"),
    YTO("YTO", "圆通速递"),
    YD("YD", "韵达速递"),
    YZPY("YZPY", "邮政快递包裹"),
    EMS("EMS", "EMS"),
    HHTT("HHTT", "天天快递"),
    JD("JD", "京东快递"),
    UC("UC", "优速快递"),
    DBL("DBL", "德邦快递"),
    ZJS("ZJS", "宅急送"),
    FBOX("FBOX", "丰巢");

    private final String code;
    private final String name;

    LogisticsType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
