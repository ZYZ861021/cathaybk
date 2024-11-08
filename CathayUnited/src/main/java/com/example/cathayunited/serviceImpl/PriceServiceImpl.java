package com.example.cathayunited.serviceImpl;

import com.example.cathayunited.common.BeanConvertUtil;
import com.example.cathayunited.common.ChangeDateUtil;
import com.example.cathayunited.entity.Price;
import com.example.cathayunited.param.CreatePriceByDateParam;
import com.example.cathayunited.param.UpdatePriceByDateParam;
import com.example.cathayunited.repository.PriceRepository;
import com.example.cathayunited.service.PriceService;
import com.example.cathayunited.vo.DataProcessingVo;
import com.example.cathayunited.vo.PriceChangeCalculatorVo;
import com.example.cathayunited.vo.PriceVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    private final ChangeDateUtil changeDateUtil;

    public PriceServiceImpl(PriceRepository priceRepository, ChangeDateUtil changeDateUtil) {
        this.priceRepository = priceRepository;
        this.changeDateUtil = changeDateUtil;
    }

    @Override
    public List<PriceVo> getPriceListByDate(Long date) {
        List<Price> prices = priceRepository.findByDate(date);
        return prices.stream()
                .map(price -> {
                    PriceVo priceVO = BeanConvertUtil.convert(price, PriceVo.class);
                    priceVO.setPriceId(price.getPriceId());
                    return priceVO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public DataProcessingVo updatePriceByDate(String id, UpdatePriceByDateParam param) {
        long timestamp = changeDateUtil.stringChangeLong(param.getDate());
        Price price = priceRepository.findByIdAndDate(id, timestamp)
                .orElseThrow(() -> new RuntimeException("Price not found for the given id and date"));
        price.setPrice(param.getPrice());
        priceRepository.save(price);

        return new DataProcessingVo(true, 1, "Success");
    }

    @Override
    public DataProcessingVo createPrice(CreatePriceByDateParam param) {
        long timestamp = changeDateUtil.stringChangeLong(param.getDate());
        Price price = new Price();
        price.setId(param.getId());
        price.setPrice(param.getPrice());
        price.setDate(timestamp);
        priceRepository.save(price);
        return new DataProcessingVo(true, 1, "Success");
    }

    @Override
    @Transactional
    public DataProcessingVo deletePriceByIdAndDate(String id, String date) {
        long timestamp = changeDateUtil.stringChangeLong(date);
        priceRepository.deleteByIdAndDate(id, timestamp);
        return new DataProcessingVo(true, 1, "Success");
    }

    @Override
    public PriceChangeCalculatorVo priceChangeCalculator(String startDate, String endDate) {
        long dateStart = changeDateUtil.stringChangeLong(startDate);
        long dateEnd = changeDateUtil.stringChangeLong(endDate);

        Price startPrice = priceRepository.findFirstByDate(dateStart);
        Price endPrice = priceRepository.findFirstByDate(dateEnd);

        if (startPrice == null || endPrice == null) {
            throw new IllegalArgumentException("Price data not found for the given dates");
        }

        BigDecimal priceDifference = endPrice.getPrice().subtract(startPrice.getPrice());
        BigDecimal rateOfChange = priceDifference.divide(startPrice.getPrice(), 2, RoundingMode.HALF_UP);

        return new PriceChangeCalculatorVo(priceDifference, rateOfChange);
    }
}
