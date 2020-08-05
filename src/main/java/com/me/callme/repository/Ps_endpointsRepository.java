package com.me.callme.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.me.callme.model.ps_endpoints;

@Repository
public interface Ps_endpointsRepository extends CrudRepository<ps_endpoints, Long>{

}
