package com.transport.service;

import com.transport.dto.TransportReqDTO;
import com.transport.exception.TransportException;
import com.transport.service.impl.TransportServiceImpl.TransportMethod;

public interface ITransportService {

	<T> T sendPostRequest(TransportReqDTO request, TransportMethod method, Class<T> responseClass) throws TransportException;

	<T> T sendGetRequest(TransportReqDTO request, Class<T> responseClass) throws TransportException;

}
