package com.tips.webservice.event;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tips.webservice.event.dto.EventMainResponseDto;
import com.tips.webservice.event.dto.EventSaveRequestDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventService {

	private EventRepository eventRepository;
	
	@Transactional
	public Long save(EventSaveRequestDto dto) {
		return eventRepository.save(dto.toEntity()).getId();
	}
	
	@Transactional(readOnly = true)
	public EventMainResponseDto read(Long id) {
		return eventRepository.findById(id)
				.map(EventMainResponseDto::new)
				.get();
	}

	@Transactional
	public void modify(EventSaveRequestDto dto) {
		Event event = eventRepository.findById(dto.getId()).get();
		event.setEntity(dto);
	}
	
	@Transactional
	public void remove(Long id) {
		eventRepository.deleteById(id);
	}
}
