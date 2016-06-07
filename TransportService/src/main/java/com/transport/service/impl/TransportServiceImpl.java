package com.transport.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.transport.dto.SerializationEnum;
import com.transport.dto.TransportReqDTO;
import com.transport.exception.ConnectionException;
import com.transport.exception.SerializationException;
import com.transport.exception.TransportException;
import com.transport.service.ITransportService;

public class TransportServiceImpl implements ITransportService {

	private ObjectMapper mapper;
	private String contentType;

	public static enum TransportMethod {
		GET, POST, PUT, PATCH, DELETE
	}

	@Override
	public <T> T sendPostRequest(TransportReqDTO request, TransportMethod method, Class<T> responseClass) throws TransportException {
		createService(request.getSerializationType());

		method = method == null ? TransportMethod.POST : method;
		String url = generateQueryUrl(request);
		byte[] payload = generatePayload(request);
		return sendRequest(url, payload, method, responseClass);
	}

	@Override
	public <T> T sendGetRequest(TransportReqDTO request, Class<T> responseClass) throws TransportException {
		createService(request.getSerializationType());

		String url = generateQueryUrl(request);
		return sendRequest(url, null, TransportMethod.GET, responseClass);
	}

	private <T> T sendRequest(String url, byte[] payload, TransportMethod method, Class<T> responseType) throws ConnectionException {
		try {
			URL urlObj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
			connection.setRequestMethod(method.name());
			connection.setRequestProperty("Content-Type", contentType);

			switch (method) {
			case GET:
				break;
			case POST:
			case PUT:
			case DELETE:
			case PATCH:
				if (payload == null) {
					break;
				}
				connection.setDoOutput(true);
				OutputStream stream = connection.getOutputStream();
				stream.write(payload);
				stream.close();
				break;
			}

			InputStream inStream = connection.getInputStream();
			return mapper.readValue(inStream, responseType);

		} catch (IOException e) {
			throw new ConnectionException(e);
		}
	}

	private String generateQueryUrl(TransportReqDTO request) {
		String url = request.getUrl();

		if (request.getRequestParam() != null && !request.getRequestParam().isEmpty()) {
			StringBuilder queryString = new StringBuilder(url).append("?");
			for (String key : request.getRequestParam().keySet()) {
				queryString.append(key).append("=").append(request.getRequestParam()).append("&");
			}
			queryString.deleteCharAt(queryString.length() - 1);
			url = queryString.toString();
		}
		return url;
	}

	private byte[] generatePayload(TransportReqDTO request) throws SerializationException {
		try {
			return mapper.writeValueAsBytes(request.getRequestBody());
		} catch (JsonProcessingException e) {
			throw new SerializationException(e);
		}
	}

	private void createService(SerializationEnum serializationType) {
		switch (serializationType) {
		case JSON:
			mapper = (mapper == null ? new ObjectMapper() : mapper);
			contentType = "application/json";
			break;
		case XML:
			mapper = (mapper == null ? new XmlMapper() : mapper);
			contentType = "application/xml";
			break;
		}
	}
}
