package com.me.callme.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.Api;

@Controller
@Api(value = "DefaultAPI", tags = { "Default API's" })
public class DefaultController {

	@GetMapping("/getBuildInfo")
	public String getBuildInfo() {
		return "Update 2 on Aug 6 2020 ";
	}
}
