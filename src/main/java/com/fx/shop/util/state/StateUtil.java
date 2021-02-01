package com.fx.shop.util.state;

public class StateUtil {
    //   配送状态

    //待配送
    public static  int trackingStatus0=0;
    //配送中
    public static  int trackingStatus1=1;
    //配送完成
    public static  int trackingStatus2=2;
    //取消中-药房主动取消订单配送
    public static  int trackingStatus3=3;
    //已取消
    public static  int trackingStatus00=-1;

    //  支付状态
    //已经失效 已经取消
    public static  int payStatus00=-1;
    //待支付
    public static  int payStatus0=0;
    //已经支付
    public static  int payStatus1=1;
    //待退款
    public static  int payStatus2=2;
    //退款中
    public static  int payStatus3=3;
    //已退款
    public static  int payStatus4=4;

    // 支付类型
    //微信
    public static Integer payType1=1;
    //支付宝
    public static Integer payType2=2;


    //是否评价
     //已经评价
    public static int commennt1=1;
     //待评价
    public static int commennt0=0;

    //订单类型
        //处方订单
    public static Integer createFrom1=1;
        //商城处方订单
     public static Integer createFrom2=2;
        //商城非处方订单
    public static Integer createFrom3=3;

     //查询方式(默认、时间、销量)
     public static String queryType1="1";
    public static String queryType2="2";
    public static String queryType3="3";

    //数据来源（1 是药房药品数据，2是商城药品数据）
    public static Integer dataFrom1=1;
    public static Integer dataFrom2=2;


    //日志来源（1 是药房药品数据，2是商城处方药品数据，3是商城非处方药品数据）
    public static String  logFrom1="1";
    public static String logFrom2="2";
    public static String logFrom3="3";

    //退款类型 (1是全退  2 退邮费)
    public static String  refundType1="1";
    public static String refundType2="2";

    //省内 1  省外 2
    public static Integer inOrOut1=1;
    public static Integer inOrOut2=2;
    //快递类型  1 是空运 2 是陆运  3是顺丰到付
    public static Integer expressType1=1;
    public static Integer expressType2=2;
    public static Integer expressType3=3;
    public static Long  ycHosId=1266194581507014657L;
    public static void main(String[] args) {
     /*   BigDecimal bignum1 = new BigDecimal("10.0001");
        BigDecimal bignum2 = new BigDecimal("5.0006");
        bignum1=bignum1.add(bignum2);
        System.out.println(bignum1);
        bignum1=bignum1.add(bignum2);
        System.out.println(bignum1);*/
        String  strs="430453.jpg";
        String[] s=strs.split(",");
      System.out.println(s[0]);

    }
}
