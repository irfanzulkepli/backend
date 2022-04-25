package com.imocha.sfactr.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imocha.sfactr.model.SfactrAuthResultRequest;
import com.imocha.sfactr.service.SfactrService;

@RestController
@RequestMapping("sfactr")
public class SfactrController {

	@Autowired
	private SfactrService sfactrService;

//	@GetMapping("auth_smart")
	public String authSmart() throws IOException {
		return sfactrService.authSmart();
	}

	@GetMapping("otpless/auth_smart")
	public String OTPlessAuthSmart() throws IOException {
		return sfactrService.OTPlessAuthSmart();
	}

	@GetMapping("otpless/auth_smart/callback")
	public String OTPlessAuthSmartCallbackVerify() {
		return sfactrService.OTPlessAuthSmartCallbackVerify();
	}

	@PostMapping("otpless/auth_smart/callback")
	public String OTPlessAuthSmartCallback(SfactrAuthResultRequest request) throws IOException {
		return sfactrService.OTPlessAuthSmartCallback(request);
	}

}
