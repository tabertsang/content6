package org.joel.content.dto;

import org.joel.content.enums.StateEnum;

/**
 * 异步请求的返回结果
 * Created by joel on Jun 22, 2016
 *
 */
public class AsynExecResult<T> {
	private int code;
	private String message;
	private T result;
	
	
	public AsynExecResult(StateEnum stateEnum, T result) {
		this.result = result;
		this.code = stateEnum.getCode();
		this.message = stateEnum.getMessage();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
}
