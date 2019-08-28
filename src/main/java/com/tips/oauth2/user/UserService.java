package com.tips.oauth2.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tips.oauth2.social.userconnection.UserConnection;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;
	
	@Transactional
	public User signUp(UserConnection userConnection) {
		User user = User.signUp(userConnection);
		return userRepository.save(user);
	}
	
	public User findBySocial(UserConnection userConnection) {
		User user = userRepository.findBySocial(userConnection);
		if(user == null) throw new RuntimeException();
		return user;
	}
	
	public boolean isExistUser(UserConnection userConnection) {
		User user = userRepository.findBySocial(userConnection);
		return (user != null);
	}
}
