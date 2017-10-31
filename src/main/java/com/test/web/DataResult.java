package com.test.web;

import java.io.Serializable;

import lombok.Data;

@Data
public class DataResult<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private String info;
	private Integer status;
	private T data;
	
	public boolean success() {
		return this.getStatus() == ResultCode.SUCCESS.getVal();
	}
	
	
}
