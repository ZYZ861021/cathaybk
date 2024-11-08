package com.example.cathayunited.service;

import com.example.cathayunited.param.CreatePriceByDateParam;
import com.example.cathayunited.param.UpdatePriceByDateParam;
import com.example.cathayunited.vo.DataProcessingVo;
import com.example.cathayunited.vo.PriceChangeCalculatorVo;
import com.example.cathayunited.vo.PriceVo;

import java.util.List;

public interface PriceService {

    List<PriceVo> getPriceListByDate(Long date);

    DataProcessingVo updatePriceByDate(String id, UpdatePriceByDateParam param);

    DataProcessingVo createPrice(CreatePriceByDateParam param);

    DataProcessingVo deletePriceByIdAndDate(String id, String date);

    PriceChangeCalculatorVo priceChangeCalculator(String startDate, String endDate);
}
