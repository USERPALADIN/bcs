package com.bcs.bcs.service;

import com.bcs.bcs.dto.ResultStockDTO;

import java.util.Map;

public interface StockService {
	
	Map<String, Double> GroupBySector(String jsonData, Map<String, Integer> map);

	ResultStockDTO getProportion(Map<String, Double> sectorMap);
}
