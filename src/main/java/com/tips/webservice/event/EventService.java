package com.tips.webservice.event;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tips.oauth2.user.User;
import com.tips.webservice.event.dto.EventListManageResponseDto;
import com.tips.webservice.event.dto.EventMainResponseDto;
import com.tips.webservice.event.dto.EventSaveRequestDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventService {

	private EventRepository eventRepository;
	
	@Transactional
	public Long save(EventSaveRequestDto dto) {
		dto.setUser(currentUser()); //현재 사용자
		return eventRepository.save(dto.toEntity()).getId();
	}
	
	/**
	 * 개설한 이벤트 목록 조회
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<EventListManageResponseDto> readEventManage() {
		return eventRepository.findByUser(currentUser())
			.map(EventListManageResponseDto::new)
			.collect(Collectors.toList());
	}
	
	/**
	 * 이벤트 조회
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public EventMainResponseDto read(Long id) {
		EventMainResponseDto dto = eventRepository.findById(id).map(EventMainResponseDto::new).get();
		if(dto.getUser().getId() != currentUser().getId()) {
			throw new RuntimeException();
		}
		return dto;
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
	
	public User currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (User) authentication.getPrincipal();
	}
}
