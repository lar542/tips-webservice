package com.tips.webservice.post;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tips.webservice.event.Event;
import com.tips.webservice.event.EventRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

	@Autowired
	private EventRepository postRepository;
	
	@After
	public void cleanup() {
		postRepository.deleteAll();
	}
	
	@Test
	public void 게시글_저장_조회() {
		//given
		postRepository.save(Event.builder()
				.title("게시글 제목")
				.build());
		//when
		List<Event> posts = postRepository.findAll();
		
		//then
		Event post = posts.get(0);
		assertThat(post.getTitle(), is("게시글 제목"));
	}
}
