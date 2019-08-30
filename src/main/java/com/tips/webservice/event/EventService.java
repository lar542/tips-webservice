package com.tips.webservice.event;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tips.webservice.event.dto.EventSaveRequestDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventService {

	private EventRepository postRepository;
	
	@Transactional
	public Long save(EventSaveRequestDto dto) {
		return postRepository.save(dto.toEntity()).getId();
	}
}
