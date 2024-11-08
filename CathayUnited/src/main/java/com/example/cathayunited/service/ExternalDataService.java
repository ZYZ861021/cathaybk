package com.example.cathayunited.service;

import com.example.cathayunited.param.GetExternalDataParam;
import com.example.cathayunited.vo.DataProcessingVo;

public interface ExternalDataService {

    DataProcessingVo getExternalData(GetExternalDataParam getExternalDataParam);
}
