package com.imooc.service;

import com.imooc.entity.SupportAddress;
import com.imooc.web.DTO.SubwayDTO;
import com.imooc.web.DTO.SubwayStationDTO;
import com.imooc.web.DTO.SupportAddressDTO;

import java.util.List;
import java.util.Map;

public interface AddressService {
    ServiceMultiResult<SupportAddressDTO> findAllCities();
    ServiceMultiResult<SupportAddressDTO> findAllByLevelAndAndBelongTo(String level);
    ServiceMultiResult<SubwayDTO> findAllSubwayByCityEnName(String cityEnName);
    List<SubwayStationDTO> findAllBySubwayId(Long subwayId);
    Map<SupportAddress.Level, SupportAddressDTO> findCityAndRegion(String cityEnName, String regionEnName);
}
