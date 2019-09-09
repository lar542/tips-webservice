package com.tips.webservice.event;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tips.webservice.event.dto.EventSaveRequestDto;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EventController {

	private EventService eventService;
	
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
	public Long newEvent(@RequestBody EventSaveRequestDto dto) {
		return eventService.save(dto);
	}
	
	@GetMapping("/event/manage")
	public String manage(Model model) {
		model.addAttribute("events", eventService.readEventManage());
		return "/views/event/manage";
	}
	
	@GetMapping("/event/{id}")
	public String viewEvent(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("event", eventService.read(id));
		return "/views/event/update";
	}
}
