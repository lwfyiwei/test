package com.test.web;

import java.io.Serializable;

import lombok.Data;

@Data
public class DataList<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private T dataList;
}
