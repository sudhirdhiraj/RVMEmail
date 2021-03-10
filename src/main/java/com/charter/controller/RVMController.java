package com.charter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.charter.dto.AccountDetails;
import com.charter.service.RVMService;

@RestController
public class RVMController {

	@Autowired
	private RVMService rvmService;
	
	@GetMapping("/getRvmEmailAddress")
	public AccountDetails getRvmEmailAddress(@RequestParam(required= true) String accountNumber, @RequestParam(required= true) String phoneNumber) {
		
		return rvmService.getRvmEmailAddress(accountNumber, phoneNumber);
	}
	
	@GetMapping("/getAccountNumber")
	public AccountDetails getAccountNumber(@RequestParam(required= true, name = "systemID") String systemId, @RequestParam(required= true) String phoneNumber) {
		
		return rvmService.getAccountNumber(systemId, phoneNumber);
	}
	
	@PutMapping("/updateRvmEmailAddress")
	public AccountDetails updateRvmEmailAddress(@RequestParam String accountNumber, @RequestParam String phoneNumber) {
		
		return rvmService.updateRvmEmailAddress();
	}
}
