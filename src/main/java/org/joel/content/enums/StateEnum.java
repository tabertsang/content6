package org.joel.content.enums;

public enum StateEnum {
	SUCCESS(200, "请求成功"),
	//登录相关
	DATA_INVALID(300,"用户名或密码为空"),
	NOT_REGISTER(301,"用户名与密码不匹配"),
	//用户权限相关
	NOT_LOGGEDIN(401, "用户未登录"),
	WITHOUT_AUTHORIZATION(402, "未授权用户"),
	//数据库操作相关
	PRODUCT_NOT_EXIST(501, "交易的产品已下架"),
	DB_DELETE_FAILURE(502, "数据记录删除失败"),
	//其他系统内部异常
	INNER_ERROR(601, "系统内部异常"),
	UNKNOWN(602, "未知异常"),
	//资源相关
	FILE_EMPTY(701, "文件为空"),
	FILE_UPLOAD_FAILURE(702, "文件上传失败")
	;
	
	private int code;
	private String message;
	
	private StateEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
