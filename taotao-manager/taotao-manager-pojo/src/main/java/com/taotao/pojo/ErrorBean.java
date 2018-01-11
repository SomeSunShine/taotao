package com.taotao.pojo;



/** 
 * 	error实体类
 * @ClassName: ErrorBean 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年10月31日 下午2:42:58  
 */
public class ErrorBean {
	
	private Integer errorType;
	
	private String errorMesg;

	public Integer getErrorType() {
		return errorType;
	}

	public void setErrorType(Integer errorType) {
		this.errorType = errorType;
	}

	public String getErrorMesg() {
		return errorMesg;
	}

	public void setErrorMesg(String errorMesg) {
		this.errorMesg = errorMesg;
	}

}
