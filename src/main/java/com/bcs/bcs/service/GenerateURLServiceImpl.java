package com.bcs.bcs.service;

import com.bcs.bcs.dto.RequestStockDTO;
import okhttp3.HttpUrl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerateURLServiceImpl implements GenerateURLService {

	@Override
	public String getURL(List<RequestStockDTO> requestStockDTOList) {
		StringBuilder stringBuilder = new StringBuilder();
		if (requestStockDTOList.isEmpty()) {
			throw new RuntimeException("User doesn't have stocks");
		}
		for (int i = 0; i < requestStockDTOList.size(); i++) {
			stringBuilder.append(requestStockDTOList.get(i).getSymbol());
			if (i != requestStockDTOList.size() - 1) {
				stringBuilder.append(",");
			}

		}
		String tops = "https://api.iextrading.com/1.0/tops";
		HttpUrl.Builder urlBuilder = HttpUrl.parse(tops).newBuilder();
		urlBuilder.addQueryParameter("symbols", stringBuilder.toString());
		return urlBuilder.build().toString();
	}
}
