package com.fx.shop.util.common;
 
/**
 * 
    * @ClassName: RestCode
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author Carrier
    * @date 2018年9月12日
    *
 */
public enum RestCode {

	// 成功
	SUCCESS(200),

	// 失败
	FAIL(10000),

	// 未认证（签名错误）
	UNAUTHORIZED(401),

	/** 未登录 */
	UNAUTHEN(4401),

	/** 未授权，拒绝访问 */
	UNAUTHZ(4403),

	// 服务器内部错误
	INTERNAL_SERVER_ERROR(500);

	public int code;

	RestCode(int code) {
		this.code = code;
	}
}
