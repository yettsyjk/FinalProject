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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.snitchapp.entities.Alert;
import com.skilldistillery.snitchapp.entities.Role;
import com.skilldistillery.snitchapp.entities.User;
import com.skilldistillery.snitchapp.services.AlertService;
import com.skilldistillery.snitchapp.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class AlertController {

	@Autowired
	private AlertService aSvc;

	@Autowired
	public UserService uSvc;

	@GetMapping("alerts")
	public List<Alert> findAll() {
		return aSvc.findAllAlerts();
	}

	@PostMapping("alerts")
	public Alert create(@RequestBody Alert alert, Principal principal, HttpServletResponse rs, HttpServletRequest rq) {
		try {
			alert = aSvc.createAlert(principal.getName(), alert);
			if (alert == null) {
				rs.setStatus(404);
			} else {
				rs.setStatus(201);
				StringBuffer url = rq.getRequestURL();
				url.append("/").append(alert.getId());
				rs.setHeader("Location: ", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(400);
			alert = null;
		}
		return alert;
	}

	@DeleteMapping("alerts/{aId}") // alerts have a disabled field
	public void deleteAlert(@PathVariable Integer aId, HttpServletResponse rs, HttpServletRequest rq,
			Principal principal) {
		try {
			User user = uSvc.findUserByUsername(principal.getName());
			if (user.getRole() == Role.ADMIN) {
				if (aSvc.deleteAlert(aId)) {
					rs.setStatus(204);
				} else {
					rs.setStatus(404);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(400);
		}
	}

}
