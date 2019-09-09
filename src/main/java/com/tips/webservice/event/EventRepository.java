package com.tips.webservice.event;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tips.oauth2.user.User;

public interface EventRepository extends JpaRepository<Event, Long> {
	
	/**
	 * 사용자가 개설한 이벤트 목록 조회
	 * @param user
	 * @return
	 */
	Stream<Event> findByUser(User user);
	
	/**
	 * 이벤트 조회
	 * @param id
	 * @return
	 */
	Optional<Event> findById(Long id);
}
