package levi9.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getLogingPage(){
		return "static/app/html/registration.html";
	}
}
