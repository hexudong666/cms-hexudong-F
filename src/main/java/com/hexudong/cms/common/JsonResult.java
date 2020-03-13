package com.hexudong.cms.common;


public class JsonResult {
	/** json接口成功返回true，否则返回false **/
	private boolean result;
	/** 错误消息提示 **/
	private String message;
	/** 错误编码 **/
	private int errorCode;
	/** 接口返回的数据 **/
	private Object data;
	/**
	 * @Title: sucess   
	 * @Description: 成功返回方法  
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	public static JsonResult sucess() {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResult(true);
		return jsonResult;
	}
	/**
	 * @Title: sucess   
	 * @Description: 成功返回   
	 * @param: @param data
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	public static JsonResult sucess(Object data) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResult(true);
		jsonResult.setData(data);
		return jsonResult;
	}
	/**
	 * @Title: fail   
	 * @Description: 失败返回   
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	public static JsonResult fail() {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResult(false);
		return jsonResult;
	}
	/**
	 * @Title: fail   
	 * @Description: 失败返回   
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	public static JsonResult fail(int errorCode,String message) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResult(false);
		jsonResult.setErrorCode(errorCode);
		jsonResult.setMessage(message);
		return jsonResult;
	}
	
	public Object getData() {
		return data;
	}



	public void setData(Object data) {
		this.data = data;
	}



	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
