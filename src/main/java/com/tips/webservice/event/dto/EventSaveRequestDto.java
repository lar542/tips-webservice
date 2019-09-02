package com.tips.webservice.event.dto;

import java.time.LocalDateTime;

import com.tips.webservice.event.Event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EventSaveRequestDto {

	private Long id;
	private String title;
	private String address;
	private double latitude; //위도
	private double longitude; //경도
	private LocalDateTime eventStartDate;
	private LocalDateTime eventEndDate;
	private String eventHost;
	private int numberOfPeople;
	private LocalDateTime applyStartDate;
	private LocalDateTime applyEndDate;
	private String details;

	@Builder
	public EventSaveRequestDto(Long id, String title, String address, double latitude, double longitude, LocalDateTime eventStartDate,
			LocalDateTime eventEndDate, String eventHost, int numberOfPeople, LocalDateTime applyStartDate,
			LocalDateTime applyEndDate, String details) {
		this.id = id;
		this.title = title;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.eventStartDate = eventStartDate;
		this.eventEndDate = eventEndDate;
		this.eventHost = eventHost;
		this.numberOfPeople = numberOfPeople;
		this.applyStartDate = applyStartDate;
		this.applyEndDate = applyEndDate;
		this.details = details;
	}
	
	public Event toEntity() {
		return Event.builder()
				.id(id)
				.title(title)
				.address(address)
				.latitude(latitude)
				.longitude(longitude)
				.eventStartDate(eventStartDate)
				.eventEndDate(eventEndDate)
				.eventHost(eventHost)
				.numberOfPeople(numberOfPeople)
				.applyStartDate(applyStartDate)
				.applyEndDate(applyEndDate)
				.details(details)
				.build();
	}
}
