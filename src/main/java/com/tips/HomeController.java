package com.tips;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@GetMapping("/")
	public String main() { 
		return "main";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/views/user/login";
	}
	
	@GetMapping("/events")
	public String events() {
		return "/views/event/list";
	}
	
	@GetMapping({"/user", "/me"})
	@ResponseBody
	public Map<String, String> user(Principal principal) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		return map;
	}
}
