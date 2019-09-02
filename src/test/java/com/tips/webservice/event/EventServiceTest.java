package com.tips.webservice.event;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tips.webservice.event.dto.EventMainResponseDto;
import com.tips.webservice.event.dto.EventSaveRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventService eventService;
	
	@After
	public void cleanup() {
		eventRepository.deleteAll();
	}
	
//	@Test
	public void dto_저장() {
		//given
		EventSaveRequestDto dto = EventSaveRequestDto.builder()
				.title("제목")
				.address("주소")
				.latitude(123.456)
				.longitude(12.45)
				.eventStartDate(LocalDateTime.of(2019, 8, 1, 9, 00))
				.eventEndDate(LocalDateTime.of(2019, 8, 2, 5, 00))
				.eventHost("주최자")
				.numberOfPeople(125)
				.applyStartDate(LocalDateTime.now())
				.applyEndDate(LocalDateTime.now())
				.details("이 모임을 블라블라~")
				.build();
		//when
		eventService.save(dto);
		
		//then
		Event post = eventRepository.findAll().get(0);
		assertThat(post.getTitle()).isEqualTo(dto.getTitle());
	}
	
//	@Test
	public void event_dto로_조회하기() {
		//given
		EventSaveRequestDto dto = EventSaveRequestDto.builder()
				.title("제목")
				.address("주소")
				.latitude(123.456)
				.longitude(12.45)
				.eventStartDate(LocalDateTime.of(2019, 8, 1, 9, 00))
				.eventEndDate(LocalDateTime.of(2019, 8, 2, 5, 00))
				.eventHost("주최자")
				.numberOfPeople(125)
				.applyStartDate(LocalDateTime.now())
				.applyEndDate(LocalDateTime.now())
				.details("이 모임을 블라블라~")
				.build();
		
		eventService.save(dto);
		
		//when
		Event event = eventRepository.findAll().get(0);
		Long id = event.getId();
		EventMainResponseDto find_dto = eventService.read(id);
		
		//then
		assertThat(find_dto.getDetails()).isEqualTo(dto.getDetails());
	}
	
	@Test
	public void event_수정() {
		//given
		EventSaveRequestDto dto = EventSaveRequestDto.builder()
				.title("제목")
				.address("주소")
				.latitude(123.456)
				.longitude(12.45)
				.eventStartDate(LocalDateTime.of(2019, 8, 1, 9, 00))
				.eventEndDate(LocalDateTime.of(2019, 8, 2, 5, 00))
				.eventHost("주최자")
				.numberOfPeople(125)
				.applyStartDate(LocalDateTime.now())
				.applyEndDate(LocalDateTime.now())
				.details("이 모임을 블라블라~")
				.build();
		
		eventService.save(dto);
		
		//when
		EventSaveRequestDto update = EventSaveRequestDto.builder()
				.id(1L)
				.title("수정한 글")
				.address("주소")
				.latitude(123.456)
				.longitude(12.45)
				.eventStartDate(LocalDateTime.of(2019, 8, 1, 9, 00))
				.eventEndDate(LocalDateTime.of(2019, 8, 2, 5, 00))
				.eventHost("주최자")
				.numberOfPeople(125)
				.applyStartDate(LocalDateTime.now())
				.applyEndDate(LocalDateTime.now())
				.details("이 모임을 블라블라~")
				.build();
		
		eventService.modify(update);
		
		//then
		Event event = eventRepository.findAll().get(0);
		assertThat(event.getTitle()).isEqualTo(update.getTitle());
	}
	
//	@Test
	public void event_삭제() {
		//given
		EventSaveRequestDto dto = EventSaveRequestDto.builder()
				.title("제목")
				.address("주소")
				.latitude(123.456)
				.longitude(12.45)
				.eventStartDate(LocalDateTime.of(2019, 8, 1, 9, 00))
				.eventEndDate(LocalDateTime.of(2019, 8, 2, 5, 00))
				.eventHost("주최자")
				.numberOfPeople(125)
				.applyStartDate(LocalDateTime.now())
				.applyEndDate(LocalDateTime.now())
				.details("이 모임을 블라블라~")
				.build();
		
		eventService.save(dto);
		
		//when
		Event event = eventRepository.findAll().get(0);
		Long id = event.getId();
		eventService.remove(id);
		
		//then
		int length = eventRepository.findAll().size();
		assertThat(length).isEqualTo(0);
	}
}
