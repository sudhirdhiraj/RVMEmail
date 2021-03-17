package com.charter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.charter.request.EmailAddressRequest;
import com.charter.response.AccountNumberResponse;
import com.charter.response.EmailAddressResponse;
import com.charter.response.EmailUpdateResponse;
import com.charter.service.RVMService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Api from Email Service")
@RestController
@CrossOrigin(origins = "*")
public class RVMController {

	@Autowired
	private RVMService rvmService;

	@ApiOperation(value = "View  Email address")
	@GetMapping("/rvmemail/getRvmEmailAddress")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the data "),
			@ApiResponse(code = 500, message = "Internal Error") })
	public EmailAddressResponse getRvmEmailAddress(@RequestParam(required = true) String accountNumber,
			@RequestParam(required = true) String phoneNumber) {

		return rvmService.getRvmEmailAddress(accountNumber, phoneNumber);
	}

	@ApiOperation(value = "View  Account Number")
	@GetMapping("/rvmemail/getAccountNumber")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the list"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public AccountNumberResponse getAccountNumber(@RequestParam(required = true, name = "systemID") String systemId,
			@RequestParam(required = true) String phoneNumber) {

		return rvmService.getAccountNumber(systemId, phoneNumber);
	}

	@PutMapping("/updateRvmEmailAddress")
	@ApiOperation(value = " Update  Email Ids based on account Number and Phone Number")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated Email id"),
			@ApiResponse(code = 500, message = "Internal error") })
	public EmailUpdateResponse updateRvmEmailAddress(@RequestBody EmailAddressRequest requestBody) { 
		
		return rvmService.updateRvmEmailAddress(requestBody);
	}
}