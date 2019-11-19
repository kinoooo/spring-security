package com.yunfeng.security.browser.support;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SimpleResponse {
	public SimpleResponse(Object content){
		this.content = content;
	}

	private Object content;

}
