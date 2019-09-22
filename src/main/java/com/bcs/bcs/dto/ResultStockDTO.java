package com.bcs.bcs.dto;

import java.util.List;

public class ResultStockDTO {
	private Double value;

	private List<AllocationDTO> allocations;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public List<AllocationDTO> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<AllocationDTO> allocations) {
		this.allocations = allocations;
	}
}
