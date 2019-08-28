package com.tips.webservice.post;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tips.webservice.post.dto.PostSaveRequestDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {

	private PostRepository postRepository;
	
	@Transactional
	public Long save(PostSaveRequestDto dto) {
		return postRepository.save(dto.toEntity()).getId();
	}
}
