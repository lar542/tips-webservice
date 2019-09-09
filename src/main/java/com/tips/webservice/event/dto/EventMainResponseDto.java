package com.tips.webservice.event.dto;

import java.time.LocalDateTime;

import com.tips.oauth2.user.User;
import com.tips.webservice.event.Event;

import lombok.Getter;

@Getter
public class EventMainResponseDto {
	
	private Long id;
	private String title;
	private String address;
	private double latitude;
	private double longitude;
	private LocalDateTime eventStartDate;
	private LocalDateTime eventEndDate;
	private User user;
	private String eventHostName;
	private int numberOfPeople;
	private LocalDateTime applyStartDate;
	private LocalDateTime applyEndDate;
	private String details;
	private char saveYn;
	private LocalDateTime modifiedDate;

	public EventMainResponseDto(Event entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.address = entity.getAddress();
		this.latitude = entity.getLatitude();
		this.longitude = entity.getLongitude();
		this.eventStartDate = entity.getEventStartDate();
		this.eventEndDate = entity.getEventEndDate();
		this.user = entity.getUser();
		this.eventHostName = entity.getEventHostName();
		this.numberOfPeople = entity.getNumberOfPeople();
		this.applyStartDate = entity.getApplyStartDate();
		this.applyEndDate = entity.getApplyEndDate();
		this.details = entity.getDetails();
		this.saveYn = entity.getSaveYn();
		this.modifiedDate = entity.getModifiedDate();
	}
	
}
