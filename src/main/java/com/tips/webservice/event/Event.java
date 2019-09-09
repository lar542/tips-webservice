package com.tips.webservice.event;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tips.oauth2.user.User;
import com.tips.webservice.BaseTimeEntity;
import com.tips.webservice.event.dto.EventSaveRequestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Event extends BaseTimeEntity {

	@Id @GeneratedValue
	@Column(name = "EVENT_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@Column(length = 300)
	private String eventHostName; 
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(columnDefinition = "LONGTEXT")
	private String details;
	
	@Column(length = 500)
	private String address;
	
	private double latitude; //위도
	
	private double longitude; //경도
	
	private LocalDateTime eventStartDate;
	
	private LocalDateTime eventEndDate;
	
	@Column(columnDefinition = "SMALLINT")
	private int numberOfPeople;
	
	private LocalDateTime applyStartDate;
	
	private LocalDateTime applyEndDate;
	
	private char saveYn;

	
	public void setEntity(EventSaveRequestDto dto) {
		this.user = dto.getUser();
		this.eventHostName = dto.getEventHostName();
		this.title = dto.getTitle();
		this.details = dto.getDetails();
		this.address = dto.getAddress();
		this.latitude = dto.getLatitude();
		this.longitude = dto.getLongitude();
		this.eventStartDate = dto.getEventStartDate();
		this.eventEndDate = dto.getEventEndDate();
		this.numberOfPeople = dto.getNumberOfPeople();
		this.applyStartDate = dto.getApplyStartDate();
		this.applyEndDate = dto.getApplyEndDate();
	}

}
