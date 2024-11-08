package com.example.cathayunited.controller;

import com.example.cathayunited.common.RestResponse;
import com.example.cathayunited.param.GetExternalDataParam;
import com.example.cathayunited.service.ExternalDataService;
import com.example.cathayunited.vo.DataProcessingVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external-data")
public class ExternalDataController {

    private final ExternalDataService externalDataService;

    public ExternalDataController(ExternalDataService externalDataService) {
        this.externalDataService = externalDataService;
    }

    @Operation(summary = "取得國泰提供資料", description = "取得國泰提供資料解析並存取資料庫")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功寫入")
    })
    @PostMapping()
    public RestResponse<DataProcessingVo> getExternalData(
            @Valid @RequestBody GetExternalDataParam getExternalDataParam
    ) {
        return RestResponse.success(externalDataService.getExternalData(getExternalDataParam));
    }

    @ExceptionHandler(Exception.class)
    public RestResponse<Object> handleException(Exception ex) {
        return RestResponse.failure(500, "系統錯誤，請稍後再試");
    }
}
