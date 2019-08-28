package com.tips.oauth2.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tips.oauth2.social.userconnection.UserConnection;

public interface UserRepository extends JpaRepository<User, Long> {

	User findBySocial(UserConnection userConnection);
}
