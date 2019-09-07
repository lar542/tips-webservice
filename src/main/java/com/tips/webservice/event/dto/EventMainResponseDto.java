package com.tips.webservice.event.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import com.tips.webservice.event.Event;

import lombok.Getter;

@Getter
public class EventMainResponseDto {
	
	private Long id;
	private String title;
	private String address;
	private double latitude;
	private double longitude;
	private String eventStartDate;
	private String eventEndDate;
	private Long eventHost;
	private String eventHostName;
	private int numberOfPeople;
	private String applyStartDate;
	private String applyEndDate;
	private String details;

	public EventMainResponseDto(Event entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.address = entity.getAddress();
		this.latitude = entity.getLatitude();
		this.longitude = entity.getLongitude();
		this.eventStartDate = toStringDateTime(entity.getEventStartDate());
		this.eventEndDate = toStringDateTime(entity.getEventEndDate());
		this.eventHost = entity.getEventHost();
		this.eventHostName = entity.getEventHostName();
		this.numberOfPeople = entity.getNumberOfPeople();
		this.applyStartDate = toStringDateTime(entity.getApplyStartDate());
		this.applyEndDate = toStringDateTime(entity.getApplyEndDate());
		this.details = entity.getDetails();
	}
	
	/**
	 * View 영역에서는 LocalDateTime 타입을 모르기 때문에 문자열로 변환한다.
	 * @param localDateTime
	 * @return
	 */
	private String toStringDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) HH:mm").withLocale(Locale.KOREA);
		return Optional.ofNullable(localDateTime)
				.map(formatter::format)
				.orElse("");
	}
}
