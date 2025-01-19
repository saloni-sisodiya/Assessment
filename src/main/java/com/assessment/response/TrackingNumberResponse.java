package com.assessment.response;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


public class TrackingNumberResponse {

	private final String tracking_number;
	private final String created_at;

	public TrackingNumberResponse(String trackingNumber, OffsetDateTime createdAt) {
		this.tracking_number = trackingNumber;
		this.created_at = createdAt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}

	public String getTracking_number() {
		return tracking_number;
	}

	public String getCreated_at() {
		return created_at;
	}
}