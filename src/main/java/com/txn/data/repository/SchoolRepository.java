package com.txn.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.txn.data.entity.School;

@Repository
public interface SchoolRepository extends CrudRepository<School, Long> {
	
	School findByName(String name);

}
