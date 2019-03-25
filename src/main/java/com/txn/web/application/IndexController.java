package com.txn.web.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.txn.service.SchoolService;

@Controller
public class IndexController {
	
	private final SchoolService schoolService;
	
	@Autowired
	public IndexController(SchoolService schoolService) {
		super();
		this.schoolService = schoolService;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("schools", schoolService.getAllSchools());
		model.addAttribute("status", "Add school");
		return "index";
	}
	
	@PostMapping("/")
	public String add(@RequestParam(name="name") String name, @RequestParam(name="error", required = false) boolean error, Model model) throws Exception {
		try {
			if(schoolService.hasSchool(name)) {
				model.addAttribute("status", "School already Exists: " + name);
			} else {
				schoolService.addSchool(name, error);
				model.addAttribute("status", "School Added");
			}
			model.addAttribute("schools", schoolService.getAllSchools());
		} catch(Exception e) {
			model.addAttribute("status", "Throwing error and " + name + " should not be in the list");
			model.addAttribute("schools", schoolService.getAllSchools());
		}
		return "index";
	}
	

}
