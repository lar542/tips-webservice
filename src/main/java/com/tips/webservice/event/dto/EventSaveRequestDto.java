package com.tips.webservice.event.dto;

import java.time.LocalDateTime;

import com.tips.oauth2.user.User;
import com.tips.webservice.event.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EventSaveRequestDto {

	private Long id;
	private String title;
	private String address;
	private double latitude; //위도
	private double longitude; //경도
	private LocalDateTime eventStartDate;
	private LocalDateTime eventEndDate;
	private User user;
	private String eventHostName;
	private int numberOfPeople;
	private LocalDateTime applyStartDate;
	private LocalDateTime applyEndDate;
	private String details;
	private char saveYn;
	
	public Event toEntity() {
		return Event.builder()
				.id(id)
				.title(title)
				.address(address)
				.latitude(latitude)
				.longitude(longitude)
				.eventStartDate(eventStartDate)
				.eventEndDate(eventEndDate)
				.user(user)
				.eventHostName(eventHostName)
				.numberOfPeople(numberOfPeople)
				.applyStartDate(applyStartDate)
				.applyEndDate(applyEndDate)
				.details(details)
				.saveYn(saveYn)
				.build();
	}
	
}
