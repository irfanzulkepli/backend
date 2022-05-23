package com.imocha.lms.entities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZIUserController {
	
	@Autowired
	private ZIUserRepository urepo;
	
	@PostMapping("/ziadduser")
	public ZIUser adduser(@RequestBody ZIUser u) {
		return urepo.save(u);
	}

	@GetMapping("/zilist")
	public List<ZIUser> userlist() {
		return (List<ZIUser>)urepo.findAll();
	}

}
