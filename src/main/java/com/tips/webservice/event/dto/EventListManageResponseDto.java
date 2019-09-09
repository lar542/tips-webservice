package com.tips.webservice.event.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import com.tips.webservice.event.Event;

import lombok.Getter;

@Getter
public class EventListManageResponseDto {

	private Long id;
	private String title;
	private String eventStartDate;
	private String eventEndDate;
	private char saveYn;

	public EventListManageResponseDto(Event entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.eventStartDate = toStringDateTime(entity.getEventStartDate());
		this.eventEndDate = toStringDateTime(entity.getEventEndDate());
		this.saveYn = entity.getSaveYn();
	}
	
	/**
	 * View 영역에서는 LocalDateTime 타입을 모르기 때문에 문자열로 변환한다.
	 * @param localDateTime
	 * @return
	 */
	private String toStringDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a HH:mm").withLocale(Locale.KOREA);
		return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
	}
}
