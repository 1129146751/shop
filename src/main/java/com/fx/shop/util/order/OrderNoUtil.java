package com.fx.shop.util.order;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.sineyun.commons.base.constants.CustomDatePattern;

import java.util.Date;
import java.util.Random;

/**
 * 订单号生成工具类
 *
 * @author 46105
 */
public final class OrderNoUtil {
    // 问诊单
    public static final String PREFIX_CP = "CP";
    // 处方
    public static final String PREFIX_CF = "CF";
    // 病例
    public static final String PREFIX_EMR = "EMR";
    // 商品订单
    public static final String PREFIX_SC = "SC";
    // 订单号
    public static final String PREFIX_ORD = "ORD";
    // 支付宝
    public static final String PREFIX_ZFB = "zfb";

    //支付流水号
    public static final String PREFIX_LSH = "LSH";

    // /**
    // * 创建编号，前缀+18位大写字符串(10位日期(yyyyMMddHH)+2位随机字母+6位字母数字随机数)
    // *
    // * @param prefix
    // * 订单前缀前缀
    // * @return 生成的编号
    // */
    // private static String createNo(String prefix) {
    // String dateStr = DateUtil.format(new Date(),
    // CustomDatePattern.CUSTOM_PATTERN_YMDH);
    // String strA = RandomStringUtils.randomAlphabetic(2);
    // String strB = RandomStringUtils.randomAlphanumeric(6);
    // return (prefix + dateStr + strA + strB).toUpperCase();
    // }

    /**
     * 创建编号，前缀+16位大写字符串(14位日期(yyyyMMddHHmmss)+2位随机字母数字随机数)
     *
     * @param prefix 订单前缀前缀
     * @return 生成的编号
     */
    private static String createNo(String prefix) {
        String dateStr = DateUtil.format(new Date(), CustomDatePattern.CUSTOM_PATTERN_YMDHMS);
        String strA = getStringRandom(2);
        return (prefix + dateStr + strA).toUpperCase();
    }

    /**
     * 生成问诊订单号
     *
     * @return
     */
    public static String createCPOrderNo() { return createNo(PREFIX_CP); }

    /**
     * 生成处方订单号
     *
     * @return
     */
    public static String createCFOrderNo() {
        return createNo(PREFIX_CF);
    }

    /**
     * 生成病例号
     *
     * @return
     */
    public static String createEMRNo() { return createNo(PREFIX_EMR); }

    /**
     * 生成商城订单号
     *
     * @return
     */
    public static String createSCOrderNo() {
        return createNo(PREFIX_SC);
    }

    /**
     * 生成订单号
     *
     * @return
     */
    public static String createOrderNo() {
        return createNo(PREFIX_ORD);
    }

    /**
     * 生成流水号
     *
     * @return
     */
    public static String createLSH() {
        return createNo(PREFIX_LSH);
    }

    /**
     * 生成交易号
     *
     * @return
     */
    public static String createZfbNo() {
        return createNo(PREFIX_ZFB);
    }

    /**
     * 自定义订单号
     *
     * @return
     */
    public static String createCustomOrderNo() {
        return IdUtil.getSnowflake(1, 8).nextIdStr();
    }

    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if("char".equalsIgnoreCase(charOrNum)){
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            }else if("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

}
