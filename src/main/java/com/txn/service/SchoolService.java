package com.txn.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.txn.data.entity.School;
import com.txn.data.repository.SchoolRepository;

@Service
public class SchoolService {
	

	private final SchoolRepository schoolRepository;
	
	@Autowired
	public SchoolService(SchoolRepository schoolRepository) {
		super();
		this.schoolRepository = schoolRepository;
	}
	
	public Map<String, String> getAllSchools() {
		Iterable<School> list = schoolRepository.findAll();
		Map<String, String> result = new HashMap<String, String>();
		list.forEach(school->{
			result.put(school.getId() + "", school.getName());
		});
		
		return result;
	}
	
//	@Transactional(propagation = Propagation.REQUIRED, readOnly=false, rollbackFor = Exception.class)
	@Transactional
	public void addSchool(String name, boolean error) throws Exception  {
		if(hasSchool(name)) {
			throw new RuntimeException("School already Exists: " + name);
		}
		School school = new School();
		school.setName(name);
		schoolRepository.save(school);
		/*trash*/
		school = new School();
		school.setName(name + "_____________");
		schoolRepository.save(school);
		/*trash*/
		if(error) {
			throw new RuntimeException("throwing exception");
//			int a = 0;
//			int b = 10/0;
		}
	}
	
	public boolean hasSchool(String name) {
		return schoolRepository.findByName(name) != null;
	}
	

}
