package com.imooc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.imooc.entity.HouseSubscribe;

public interface HouseSubscribeRespository extends PagingAndSortingRepository<HouseSubscribe, Long>{


}
