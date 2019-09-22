package com.bcs.bcs.dto;

import java.util.Objects;

public class RequestStockDTO {

	private String symbol;
	private Integer volume;

	public String getSymbol() {
		return symbol;
	}

	public Integer getVolume() {
		return volume;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RequestStockDTO requestStockDTO = (RequestStockDTO) o;
		return Objects.equals(symbol, requestStockDTO.symbol) &&
				Objects.equals(volume, requestStockDTO.volume);
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, volume);
	}
}
