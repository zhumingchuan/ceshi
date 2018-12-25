package com.imooc.web.house;

import com.imooc.base.ApiResponse;
import com.imooc.entity.SupportAddress;
import com.imooc.service.AddressService;
import com.imooc.service.ServiceMultiResult;
import com.imooc.web.DTO.SubwayDTO;
import com.imooc.web.DTO.SubwayStationDTO;
import com.imooc.web.DTO.SupportAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HouseController {
    @Autowired
    private AddressService addressService;
    /*
    * 获取城市列表
    * */
    @GetMapping("address/support/cities")
    @ResponseBody
    public ApiResponse getSupportCities(){
        ServiceMultiResult<SupportAddressDTO> re = addressService.findAllCities();
        if (re.getResultSize()==0){
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }

        return ApiResponse.ofSuccess(re.getResult());
    }
    /*
    * 获取区域列表
    *
    * */
    @GetMapping("address/support/regions")
    @ResponseBody
    public ApiResponse getReigion(@RequestParam("city_name") String cityName){
        ServiceMultiResult<SupportAddressDTO> re = addressService.findAllByLevelAndAndBelongTo(cityName);
        if (re.getResultSize()==0){
            return  ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(re.getResult());
    }
    /*
    * 获取该城市的所有地铁线路
    *
    * */
    @GetMapping("address/support/subway/line")
    @ResponseBody
    public ApiResponse getAllSubway(@RequestParam("city_name") String cityName){
        ServiceMultiResult<SubwayDTO> result = addressService.findAllSubwayByCityEnName(cityName);
        if (result.getResultSize()==0){
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(result.getResult());
    }

    /*
    *
    * 获取该线路的所有站点
    * */

    @GetMapping("address/support/subway/station")
    @ResponseBody
    public ApiResponse getAllSubwayStation(@RequestParam(name = "subway_id") Long subwayId){
        List<SubwayStationDTO> stationDTOS = addressService.findAllBySubwayId(subwayId);
        if (stationDTOS.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }

        return ApiResponse.ofSuccess(stationDTOS);
    }

}
