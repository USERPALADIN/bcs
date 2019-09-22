package com.bcs.bcs.service;

import com.bcs.bcs.dto.RequestStockDTO;

import java.util.List;

public interface GenerateURLService {

	String getURL(List<RequestStockDTO> requestStockDTOList);
}
