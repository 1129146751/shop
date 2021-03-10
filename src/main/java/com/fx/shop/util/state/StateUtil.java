package com.fx.shop.util.state;

/**
 * 枚举值
 */
public interface StateUtil {
    /**
     * 订单状态：-1:已取消 0:待支付 1:待发货 2:待收货,3 完成 4 退款中 5 已经退款
     */
    interface  orderStatus{
        Integer os=-1;
        Integer os0=0;
        Integer os1=1;
        Integer os2=2;
        Integer os3=3;
        Integer os4=4;
        Integer os5=5;
    }

    /**
     * 配送方式 1-自取、2-配送
     */
    interface  distributType{
        Integer dt1=1;
        Integer dt2=2;
    }

    /**
     * 支付方式 1-微信、2-支付宝
     */
    interface  payType{
        Integer pt1=1;
        Integer pt2=2;
    }
}
