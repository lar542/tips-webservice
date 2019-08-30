package com.tips.webservice.post;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tips.webservice.event.Event;
import com.tips.webservice.event.EventRepository;
import com.tips.webservice.event.EventService;
import com.tips.webservice.event.dto.EventSaveRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

	@Autowired
	private EventRepository postRepository;
	
	@Autowired
	private EventService postService;
	
	@After
	public void cleanup() {
		postRepository.deleteAll();
	}
	
	@Test
	public void dto데이터가_event테이블에_저장된다() {
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
		postService.save(dto);
		
		//then
		Event post = postRepository.findAll().get(0);
		assertThat(post.getTitle()).isEqualTo(dto.getTitle());
	}
}
