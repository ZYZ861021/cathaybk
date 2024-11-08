package com.example.cathayunited.controller;

import com.example.cathayunited.common.ChangeDateUtil;
import com.example.cathayunited.common.RestResponse;
import com.example.cathayunited.param.CreatePriceByDateParam;
import com.example.cathayunited.param.UpdatePriceByDateParam;
import com.example.cathayunited.service.PriceService;
import com.example.cathayunited.vo.DataProcessingVo;
import com.example.cathayunited.vo.PriceChangeCalculatorVo;
import com.example.cathayunited.vo.PriceVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;
    private final ChangeDateUtil changeDateUtil;

    public PriceController(PriceService priceService, ChangeDateUtil changeDateUtil) {
        this.priceService = priceService;
        this.changeDateUtil = changeDateUtil;
    }

    @Operation(summary = "查詢某日價格", description = "查詢某日價格")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功查詢")
    })
    @GetMapping("/prices")
    public RestResponse<List<PriceVo>> getPriceListByDate(
            @RequestParam("date")
            @Schema(description = "日期格式為 'yyyy/MM/dd HH:mm:ss'，例如 '2023/03/10 00:00:00'", example = "2023/03/10 00:00:00")
            @DateTimeFormat(pattern = "yyyy/MM/dd") String date) {
        long timestamp = changeDateUtil.stringChangeLong(date);
        return RestResponse.success(priceService.getPriceListByDate(timestamp));
    }
    @Operation(summary = "更新某日價格", description = "更新某日價格")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新")
    })
    @PatchMapping("/prices/{id}")
    public RestResponse<DataProcessingVo> updatePriceByDate(@PathVariable String id,
                                                            @RequestBody UpdatePriceByDateParam param) {
        return RestResponse.success(priceService.updatePriceByDate(id, param));
    }

    @Operation(summary = "新增某日價格", description = "新增某日價格")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功新增")
    })
    @PostMapping("/prices")
    public RestResponse<DataProcessingVo> createPrice(@RequestBody CreatePriceByDateParam param) {
        return RestResponse.success(priceService.createPrice(param));
    }

    @Operation(summary = "刪除某日價格", description = "刪除某日價格")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功刪除")
    })
    @DeleteMapping("/prices/{id}")
    public RestResponse<DataProcessingVo> deletePriceByIdAndDate(
            @Schema(description = "product提供的Id，例如 '10480016'", example = "10480016")
            @PathVariable String id,
            @Schema(description = "日期格式為 'yyyy/MM/dd HH:mm:ss'，例如 '2023/03/10 00:00:00'", example = "2023/03/10 00:00:00")
            @RequestParam("date") String date) {
        return RestResponse.success(priceService.deletePriceByIdAndDate(id, date));
    }

    @GetMapping("calculator")
    public RestResponse<PriceChangeCalculatorVo> priceChangeCalculator(
            @RequestParam("startDate")
            @Schema(description = "開始時間，日期格式為 'yyyy/MM/dd HH:mm:ss'，例如 '2023/03/10 00:00:00'", example = "2023/03/10 00:00:00")
            @DateTimeFormat(pattern = "yyyy/MM/dd") String startDate,
            @RequestParam("endDate")
            @Schema(description = "結束時間，日期格式為 'yyyy/MM/dd HH:mm:ss'，例如 '2023/03/10 00:00:00'", example = "2023/03/10 00:00:00")
            @DateTimeFormat(pattern = "yyyy/MM/dd") String endDate
    ){
        return RestResponse.success(priceService.priceChangeCalculator(startDate, endDate));
    }

    @ExceptionHandler(Exception.class)
    public RestResponse<Object> handleException(Exception ex) {
        return RestResponse.failure(500, "系統錯誤，請稍後再試");
    }


}
