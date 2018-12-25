package com.imooc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.imooc.entity.SupportAddress;


public interface SupportAddressRepository extends CrudRepository<SupportAddress, Long>{
        List<SupportAddress> findAllByLevel(String level);
        List<SupportAddress> findAllByLevelAndBelongTo(String level,String belong_to);
        SupportAddress findByEnNameAndLevel(String enName,String level);
        SupportAddress findByEnNameAndBelongTo(String enName,String belongTo);



}
