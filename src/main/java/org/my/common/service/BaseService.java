package org.my.common.service;

import org.my.common.domain.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected int resultCode = 0;
	protected String resultMsg = "";

	@SuppressWarnings({ "rawtypes", "unchecked" })
    protected BaseResult baseResult() {
		return new BaseResult(1, "success", null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected BaseResult baseResult(Object data) {
		return new BaseResult(1, "success", data);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected BaseResult baseResult(int resultCode, String resultMsg) {
		return new BaseResult(resultCode, resultMsg, null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected BaseResult baseResult(int resultCode, String resultMsg, Object data) {
		return new BaseResult(resultCode, resultMsg, data);
	}
}
