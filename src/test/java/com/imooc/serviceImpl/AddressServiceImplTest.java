package com.imooc.serviceImpl;

import com.imooc.entity.SubwayStation;
import com.imooc.repository.SubwayStationRepository;
import com.imooc.service.ServiceMultiResult;
import com.imooc.web.DTO.SubwayStationDTO;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AddressServiceImplTest {
    @Autowired
    private SubwayStationRepository subwayStationRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Test
    public void findAllBySubwayId() {

    }
}