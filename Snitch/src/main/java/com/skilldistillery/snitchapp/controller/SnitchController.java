package com.skilldistillery.snitchapp.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.snitchapp.entities.Address;
import com.skilldistillery.snitchapp.entities.Category;
import com.skilldistillery.snitchapp.entities.Snitch;
import com.skilldistillery.snitchapp.services.AddressService;
import com.skilldistillery.snitchapp.services.CategoryService;
import com.skilldistillery.snitchapp.services.SnitchService;
import com.skilldistillery.snitchapp.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class SnitchController {

	@Autowired
	private SnitchService snitchSvc;

	@Autowired
	public UserService uSvc;
	
	@Autowired
	public AddressService addSvc;
	
	@Autowired
	public CategoryService catSvc;
	
	
	@GetMapping("personalsnitches") // for a particular user to find all of their snitches
	public List<Snitch> index(Principal principal) {

		return snitchSvc.index(principal.getName());
	}

	@GetMapping("snitches")
	public List<Snitch> findAll() {
		return snitchSvc.findAll();
	}

	@GetMapping("snitches/{sId}")
	public Snitch show(Principal principal, @PathVariable Integer sId, HttpServletResponse response) {
		Snitch snitch = snitchSvc.show(sId, principal.getName());
		System.out.println(snitch);
		if (snitch == null) {
			response.setStatus(404);
		} 
		return snitch;
	}

	@PostMapping("snitches")
	public Snitch create(@RequestBody Snitch snitch, Principal principal, HttpServletResponse rs,
			HttpServletRequest rq) {
		try {
			
			System.out.println(snitch);
			Address newAddress = snitch.getAddress();
			newAddress= addSvc.create(newAddress);
			snitch.setAddress(newAddress);
			Category newCategory = snitch.getCategory();
			newCategory = catSvc.findByName(newCategory.getName());
			snitch.setCategory(newCategory);
			snitch = snitchSvc.create(principal.getName(), snitch);
			if (snitch == null) {
				rs.setStatus(404);
			} else {
				rs.setStatus(201);
				StringBuffer url = rq.getRequestURL();
				url.append("/").append(snitch.getId());
				rs.setHeader("Location: ", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(400);
			snitch = null;
		}
		return snitch;
	}

	@PutMapping("snitches/{sId}")
	public Snitch update(@PathVariable Integer sId, @RequestBody Snitch snitch, Principal principal,
			HttpServletResponse rs, HttpServletRequest rq) {
		try {
			snitch = snitchSvc.update(principal.getName(), sId, snitch);
			if (snitch == null) {
				rs.setStatus(404);
			}

		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(400);
			snitch = null;
		}
		return snitch;
	}

	@DeleteMapping("snitches/{sId}")//front end has to filter by enabled=true
	public void disable(@PathVariable Integer sId, HttpServletResponse rs, HttpServletRequest rq) {
		try {
			if (snitchSvc.disable(sId)) {

				rs.setStatus(204);
			} else {
				rs.setStatus(404);

			}

		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(400);
		}
	}
	

	
	
	//front end has to filter by enabled=true
	@GetMapping("snitches/search/keyword/{keyword}")
	public List<Snitch> findByKeyword(@PathVariable String keyword){
		
		
		return snitchSvc.findByTitleLikeOrDescriptionLikeOrAddressStreetLike(keyword);
	}
	
	//front end has to filter by enabled=true
	@GetMapping("snitches/search/category/{category}")
	public List<Snitch> findByCategory(@PathVariable String category){
		
		return snitchSvc.findByCategory_NameLike(category);
	}
	
	
}
