package com.imooc.repository;

import com.imooc.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role,Long> {
    List<Role> findRoleByUserId(Long userid);
}
