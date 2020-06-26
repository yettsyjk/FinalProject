package com.skilldistillery.snitchapp.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.snitchapp.entities.Address;
import com.skilldistillery.snitchapp.services.AddressService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class AddressController {

	@Autowired
	private AddressService aSvc;
	
	//("snitches/{sId}/addresses")
	@PostMapping("addresses")
	public Address create(@PathVariable Integer sId, @RequestBody Address adr, Principal principal,
			HttpServletResponse resp, HttpServletRequest req) {
		try {
			adr = aSvc.create(principal.getName(), adr, sId);

			if (adr == null) {
				resp.setStatus(404);
			} else {
				resp.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(adr.getId());
				resp.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(409);
		}
		
		return adr;
	}
}
