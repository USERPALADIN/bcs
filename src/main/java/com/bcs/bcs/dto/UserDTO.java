package com.bcs.bcs.dto;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class UserDTO {

	private Map<String, Integer> map = new HashMap<>();
	private List<RequestStockDTO> stocks;

	public List<RequestStockDTO> getStocks() {
		return stocks;
	}

	public Map<String, Integer> getMap() {
		for (RequestStockDTO stockDTO : stocks) {
			map.put(stockDTO.getSymbol(), stockDTO.getVolume());
		}
		return map;
	}
}
