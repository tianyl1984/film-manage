package com.tianyl.filmManage.util;

import java.nio.charset.Charset;

public class RequestResult {

	private boolean isOk;

	private String resultStr;

	private byte[] resultBytes;

	private int responseCode;

	public RequestResult() {

	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public String getResultStr() {
		resultStr = new String(resultBytes, Charset.forName("utf-8"));
		return resultStr;
	}

	public byte[] getResultBytes() {
		return resultBytes;
	}

	public void setResultBytes(byte[] resultBytes) {
		this.resultBytes = resultBytes;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

}
