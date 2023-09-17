package com.pichincha1.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha1.model.RoleEntity;

@Repository
public interface RoleRepo extends CrudRepository <RoleEntity, Long >{
	

}
