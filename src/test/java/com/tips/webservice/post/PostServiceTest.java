package com.tips.webservice.post;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tips.webservice.post.dto.PostSaveRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostService postService;
	
	@After
	public void cleanup() {
		postRepository.deleteAll();
	}
	
	@Test
	public void dto데이터가_post테이블에_저장된다() {
		//given
		PostSaveRequestDto dto = PostSaveRequestDto.builder()
				.title("제목")
				.content("내용")
				.author("gugu")
				.build();
		//when
		postService.save(dto);
		
		//then
		Post post = postRepository.findAll().get(0);
		assertThat(post.getTitle()).isEqualTo(dto.getTitle());
		assertThat(post.getContent()).isEqualTo(dto.getContent());
		assertThat(post.getAuthor()).isEqualTo(dto.getAuthor());
	}
}
