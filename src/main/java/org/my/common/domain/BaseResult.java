package org.my.common.domain;

public class BaseResult<T> {

	private int resultCode = FAIL;
	private String resultMsg = "";
	
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	public static final String DEFAULT_SUCCESS_MESSAGE ="SUCCESS";
	
	private T data;
    
    public BaseResult() {
    }

    public BaseResult(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
    
    public BaseResult(int resultCode, String resultMsg,T data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }
    

    public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


	@Override
	public String toString() {
		return "BaseResult [resultCode=" + resultCode + ", resultMsg=" + resultMsg + ", data=" + data + "]";
	}
    
}
