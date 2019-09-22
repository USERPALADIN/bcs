package com.bcs.bcs.service;

import com.bcs.bcs.dto.AllocationDTO;
import com.bcs.bcs.dto.ResultStockDTO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockServiceImpl implements StockService {
	@Override
	public Map<String, Double> GroupBySector(String jsonData, Map<String, Integer> userStocks) {
		JSONArray array = null;
		Map<String, Double> resultMap = new HashMap<>();
		try {
			array = new JSONArray(jsonData);
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				String symbol = object.getString("symbol");
				String sector = object.getString("sector");
				double lastSalePrice = object.getDouble("lastSalePrice");
				int volume = userStocks.get(symbol);
				if (resultMap.containsKey(sector)) {
					Double sum = resultMap.get(sector);
					sum = Double.sum(sum, volume * lastSalePrice);
					resultMap.put(sector, sum);
				} else {
					resultMap.put(sector, volume * lastSalePrice);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public ResultStockDTO getProportion(Map<String, Double> sectorMap) {

		Double sum = 0.0;
		for (Map.Entry<String, Double> map : sectorMap.entrySet()) {
			sum = Double.sum(sum, map.getValue());
		}
		List<AllocationDTO> allocationDTOList = new ArrayList<>();

		for (Map.Entry<String, Double> map : sectorMap.entrySet()) {
			AllocationDTO allocationDTO = new AllocationDTO();
			allocationDTO.setSector(map.getKey());
			allocationDTO.setAssetValue(map.getValue());
			allocationDTO.setProportion(map.getValue() / sum);
			allocationDTOList.add(allocationDTO);
		}
		ResultStockDTO resultStockDTO = new ResultStockDTO();
		resultStockDTO.setAllocations(allocationDTOList);
		resultStockDTO.setValue(sum);

		return resultStockDTO;
	}
}
