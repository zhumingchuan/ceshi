package com.imooc.serviceImpl;

import com.imooc.entity.Subway;
import com.imooc.entity.SubwayStation;
import com.imooc.entity.SupportAddress;
import com.imooc.repository.SubwayRepository;
import com.imooc.repository.SubwayStationRepository;
import com.imooc.repository.SupportAddressRepository;
import com.imooc.service.AddressService;
import com.imooc.service.ServiceMultiResult;
import com.imooc.web.DTO.SubwayDTO;
import com.imooc.web.DTO.SubwayStationDTO;
import com.imooc.web.DTO.SupportAddressDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private SupportAddressRepository supportAddressRepository;
    @Autowired
    private SubwayRepository subwayRepository;
    @Autowired
    private SubwayStationRepository subwayStationRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllCities() {
        List<SupportAddress> addresses = supportAddressRepository.findAllByLevel(SupportAddress.Level.CITY.getValue());
        List<SupportAddressDTO> list = new ArrayList<>();
        for (SupportAddress supportAddress:addresses){
            SupportAddressDTO addressDTO = modelMapper.map(supportAddress, SupportAddressDTO.class);
            list.add(addressDTO);
        }
        return new ServiceMultiResult(list.size(),list);
    }

    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllByLevelAndAndBelongTo(String level) {
        if (level == null){
            return new ServiceMultiResult(0,null);
        }
        List<SupportAddress> supportAddressList = supportAddressRepository.findAllByLevelAndBelongTo(SupportAddress.Level.REGION.getValue(), level);
        List<SupportAddressDTO> list = new ArrayList<>();
        for (SupportAddress supportAddress:supportAddressList){
            SupportAddressDTO addressDTO = modelMapper.map(supportAddress, SupportAddressDTO.class);
            list.add(addressDTO);
        }
        return new ServiceMultiResult<>(list.size(),list);
    }

    @Override
    public ServiceMultiResult<SubwayDTO> findAllSubwayByCityEnName(String cityEnName) {
        if (cityEnName==null){
            return new ServiceMultiResult<>(0,null);
        }
        List<Subway> subwayList = subwayRepository.findAllByCityEnName(cityEnName);
        List<SubwayDTO> list = new ArrayList<>();
        for (Subway subway : subwayList){
            SubwayDTO subwayDTO = modelMapper.map(subway, SubwayDTO.class);
            list.add(subwayDTO);
        }
        return new ServiceMultiResult<>(list.size(),list);
    }

    @Override
    public List<SubwayStationDTO> findAllBySubwayId(Long subwayId) {
        List<SubwayStationDTO> result = new ArrayList<>();
        List<SubwayStation> stations = subwayStationRepository.findAllBySubwayId(subwayId);
        if (stations.isEmpty()) {
            return result;
        }

        stations.forEach(station -> result.add(modelMapper.map(station, SubwayStationDTO.class)));
        return result;
    }

    @Override
    public Map<SupportAddress.Level, SupportAddressDTO> findCityAndRegion(String cityEnName, String regionEnName) {
        Map<SupportAddress.Level, SupportAddressDTO> result = new HashMap<>();
        SupportAddress city = supportAddressRepository.findByEnNameAndLevel(cityEnName, SupportAddress.Level.CITY.getValue());
        SupportAddress region = supportAddressRepository.findByEnNameAndBelongTo(city.getEnName(), regionEnName);
       /* result.put(SupportAddress.Level.CITY,)*/
        return null;
    }

}
