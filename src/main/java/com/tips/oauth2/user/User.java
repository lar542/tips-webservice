package com.tips.oauth2.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.tips.oauth2.social.userconnection.UserConnection;
import com.tips.webservice.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

	@Id @GeneratedValue
	private Long id;
	
	private String email;
	
	@Column(nullable = false, unique = true)
	private String nickName;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id", nullable = false, updatable = false, unique = true)
    private UserConnection social;
	
	@Builder
	private User(String email, String nickName, UserConnection social) {
        this.email = email;
        this.nickName = nickName;
        this.social = social;
    }
	
	public static User signUp(UserConnection userConnection) {
		return User.builder()
				.email(userConnection.getEmail())
				.nickName(userConnection.getDisplayName())
				.social(userConnection)
				.build();
	}
}
