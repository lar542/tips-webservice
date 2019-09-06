package com.tips.webservice.event;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tips.webservice.event.EventRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {

	@Autowired
	private EventRepository eventRepository;
	
	@After
	public void cleanup() {
		eventRepository.deleteAll();
	}
	
	@Test
	public void 게시글_저장_조회() {
		//given
		eventRepository.save(
			Event.builder()
				.title("게시글 제목")
				.build());
		//when
		List<Event> posts = eventRepository.findAll();
		
		//then
		Event post = posts.get(0);
		assertThat(post.getTitle(), is("게시글 제목"));
	}
}
