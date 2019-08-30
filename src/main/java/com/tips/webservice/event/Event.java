package com.tips.webservice.event;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.tips.webservice.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BaseTimeEntity {

	@Id @GeneratedValue
	private Long id;
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(length = 500, nullable = false)
	private String address;
	
	@Column(nullable = false)
	private double latitude; //위도
	
	@Column(nullable = false)
	private double longitude; //경도
	
	@Column(nullable = false)
	private LocalDateTime eventStartDate;
	
	@Column(nullable = false)
	private LocalDateTime eventEndDate;
	
	@Column(length = 100, nullable = false)
	private String eventHost;
	
	@Column(columnDefinition = "SMALLINT", nullable = false)
	private int numberOfPeople;
	
	@Column(nullable = false)
	private LocalDateTime applyStartDate;
	
	@Column(nullable = false)
	private LocalDateTime applyEndDate;
	
	@Column(columnDefinition = "LONGTEXT", nullable = false)
	private String details;

	@Builder
	public Event(String title, String address, double latitude, double longitude, LocalDateTime eventStartDate,
			LocalDateTime eventEndDate, String eventHost, int numberOfPeople, LocalDateTime applyStartDate,
			LocalDateTime applyEndDate, String details) {
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
	
}
