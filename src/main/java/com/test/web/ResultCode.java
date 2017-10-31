package com.test.web;

import lombok.Getter;

@Getter
public enum ResultCode {

	SUCCESS(10000, "操作成功"), 
	ACCOUNT_OR_PASSWORD_ERROR(10001, "信息输入错误，请重新输入"), 
	INFO_INPUT_ERROR(10002, "账户或密码有误"),
	PARAM_ERROR(10008, "参数错误"),
	PHONE_NUMBER_ERROR(10018, "手机号注册有误"),
	VOCODE_ERROR(10015, "验证码错误或过期"),
	ANSWER_ERROR(10027, "密保问题回答错误"),
	REGISTER_SUCCESS(20000, "注册成功"),
	MOBILE_EXIST(20001,	"手机号码已存在"),
	MOBILE_NOT_EXIST(20001, "手机号码不存在"),
	MOBILE_FORMART_INCORRECT(20002,	"手机号码格式不正确"),
	MUST_AGREE_CLAUSE(20003,"必须同意条款"),
	LATEST_VERSION(30000, "已是最新版本"),
	PARAMETER_ERROR(40000, "参数错误"),

	MOBILE_VERIFICATION_ERROR(60006, "手机验证码错误"),

	IDENTITY_VALIDATION_FAILURE(90000, "身份验证失效，请重新登录"),
	DATA_NULL(90001, "数据为空！"),
	VERIFICATION_FAILED(90002, "指纹验证失败"),
	RECHARGE_OVERFLOW(90003, "为了您账户的安全，您已超出每日可充值上限，请明天再试"),
	GOODS_OVERFLOW(90006, "购物车中的商品数量已达上限"),
	PICTURE_OVERFLOW(90007,	"图片太大"),
	APP_GRANT_FAILED(90008, "应用授权失败"),
	ERROR(99999, "操作失败"),
	TRADE_PASSWORD_ERROR(111, "交易密码错误");
	

	private int val;
	private String desc;

	ResultCode(int val, String desc) {
		this.val = val;
		this.desc = desc;
	}

	public String formatDesc(Object... objects) {
		return String.format(desc, objects);
	}

	public boolean isSuccess() {
		return val == SUCCESS.getVal();
	}

	public boolean isNotSuccess() {
		return !isSuccess();
	}

	public String getJson() {
		return "{\"code\":\"" + val + "\", \"msg\":\"" + desc + "\"}";

	}
}
