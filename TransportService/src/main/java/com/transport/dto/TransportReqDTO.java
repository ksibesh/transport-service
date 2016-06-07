package com.transport.dto;

import java.io.Serializable;
import java.util.Map;

public class TransportReqDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7328422508323114197L;

	private String url;
	private Object requestBody;
	private Map<String, String> requestParam;
	private SerializationEnum serializationType;

	public Object getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}

	public Map<String, String> getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(Map<String, String> requestParam) {
		this.requestParam = requestParam;
	}

	public SerializationEnum getSerializationType() {
		return serializationType;
	}

	public void setSerializationType(SerializationEnum serializationType) {
		this.serializationType = serializationType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "TransportReqDTO [url=" + url + ", requestBody=" + requestBody + ", requestParam=" + requestParam + ", serializationType=" + serializationType + "]";
	}

}
