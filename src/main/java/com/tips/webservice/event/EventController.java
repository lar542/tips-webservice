package com.tips.webservice.event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tips.webservice.event.dto.EventSaveRequestDto;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EventController {

	private EventService postService;
	
	@GetMapping("/events")
	public String events() {
		return "/views/event/list";
	}
	
	@GetMapping("/event/new")
	public String newEvent() {
		return "/views/event/new";
	}
	
	@PostMapping("/event/new")
	@ResponseBody
	public Long eventCreate(@RequestBody EventSaveRequestDto dto) {
		return postService.save(dto);
	}
}
