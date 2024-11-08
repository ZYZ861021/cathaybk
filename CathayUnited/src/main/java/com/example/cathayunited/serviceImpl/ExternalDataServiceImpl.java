package com.example.cathayunited.serviceImpl;

import com.example.cathayunited.client.ExternalApiClient;
import com.example.cathayunited.common.BeanConvertUtil;
import com.example.cathayunited.dto.request.ExternalRequestDto;
import com.example.cathayunited.dto.response.ExternalResponseDto;
import com.example.cathayunited.entity.Price;
import com.example.cathayunited.entity.Product;
import com.example.cathayunited.param.GetExternalDataParam;
import com.example.cathayunited.repository.PriceRepository;
import com.example.cathayunited.repository.ProductRepository;
import com.example.cathayunited.service.ExternalDataService;
import com.example.cathayunited.vo.DataProcessingVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalDataServiceImpl implements ExternalDataService {

    private final ExternalApiClient externalApiClient;
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;

    public ExternalDataServiceImpl(ExternalApiClient externalApiClient,
                                   ProductRepository productRepository,
                                   PriceRepository priceRepository) {
        this.externalApiClient = externalApiClient;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
    }

    @Value("${external.url:}")
    private String externalUrl;

    @Override
    @Transactional
    public DataProcessingVo getExternalData(GetExternalDataParam getExternalDataParam) {
        try {
            ExternalRequestDto requestDto = BeanConvertUtil.convert(getExternalDataParam, ExternalRequestDto.class);
            String response = externalApiClient.callExternalApi(externalUrl, requestDto);

            ExternalResponseDto dto = BeanConvertUtil.readValue(response, ExternalResponseDto.class);
            List<Product> productList = dto.getData().stream()
                    .map(m -> BeanConvertUtil.convert(m, Product.class))
                    .collect(Collectors.toList());
            List<Price> pricesList = dto.getData().stream()
                    .flatMap(outerData ->
                            outerData.getData().stream()
                                    .map(pL -> {
                                        Long date = pL.get(0).longValue();
                                        BigDecimal price = BigDecimal.valueOf(pL.get(1));
                                        return new Price(outerData.getId(), date, price);
                                    })
                    )
                    .collect(Collectors.toList());

            productRepository.saveAll(productList);
            priceRepository.saveAll(pricesList);

            return new DataProcessingVo(true, productList.size(), "Success");
        } catch (Exception e) {
            return new DataProcessingVo(false, 0, "Error: " + e.getMessage());
        }
    }
}
