package com.bcs.bcs.controller;

import com.bcs.bcs.dto.ResultStockDTO;
import com.bcs.bcs.dto.UserDTO;
import com.bcs.bcs.service.GenerateURLService;
import com.bcs.bcs.service.StockService;
import okhttp3.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.Map;
@RestController
public class MainController {


	private final GenerateURLService generateURLService;
	private final StockService stockService;

	public MainController(GenerateURLService generateURLService, StockService stockService) {
		this.generateURLService = generateURLService;
		this.stockService = stockService;
	}

	@PostMapping(value = "/check")
	public ResponseEntity<ResultStockDTO> postStocks(@RequestBody UserDTO userDTO) {

		String url = generateURLService.getURL(userDTO.getStocks());

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(url)
				.get()
				.build();

		String jsonData = null;
		try {
			Response response = client.newCall(request).execute();
			jsonData = response.body().string();

		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Double> groupBySectorMap = stockService.GroupBySector(jsonData, userDTO.getMap());
		ResultStockDTO resultStockDTO = stockService.getProportion(groupBySectorMap);
		return ResponseEntity.ok(resultStockDTO);
	}


}
