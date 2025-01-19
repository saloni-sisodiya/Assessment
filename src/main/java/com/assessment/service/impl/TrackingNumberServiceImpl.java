package com.assessment.service.impl;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.assessment.response.TrackingNumberResponse;
import com.assessment.service.TrackingNumberService;

@Service
public class TrackingNumberServiceImpl implements TrackingNumberService {

	private static final Pattern TRACKING_NUMBER_PATTERN = Pattern.compile("^[A-Z0-9]{1,16}$");

	private final ConcurrentHashMap<String, Boolean> trackingNumberRegistry = new ConcurrentHashMap<>();

	@Override
	public TrackingNumberResponse generateTrackingNumber(String originCountry, String destinationCountry, double weight,
			String createdAt, String customerId, String customerName, String customerSlug) {

		// Parse and validate the created_at parameter
		OffsetDateTime createdAtTime;
		try {
			createdAtTime = OffsetDateTime.parse(createdAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid created_at format. Use RFC 3339 format.");
		}

		String trackingNumber;
		do {
			trackingNumber = createTrackingNumber(originCountry, destinationCountry, weight, customerId);

			// Validate the tracking number format
			if (!TRACKING_NUMBER_PATTERN.matcher(trackingNumber).matches()) {
				throw new IllegalStateException("Generated tracking number does not match the required format.");
			}
		} while (!isTrackingNumberUnique(trackingNumber));

		// Register the tracking number to ensure uniqueness
		trackingNumberRegistry.put(trackingNumber, true);

		return new TrackingNumberResponse(trackingNumber, createdAtTime);
	}

	/**
	 * Generates a tracking number using the given parameters.
	 * 
	 * @param originCountry      Origin country code.
	 * @param destinationCountry Destination country code.
	 * @param weight             Order weight in kilograms.
	 * @param customerId         Customer UUID.
	 * @return A 16-character alphanumeric tracking number.
	 */
	private String createTrackingNumber(String originCountry, String destinationCountry, double weight,
			String customerId) {
		String base = originCountry + destinationCountry + (int) (weight * 1000) + customerId.substring(0, 4);
		return UUID.nameUUIDFromBytes(base.getBytes()).toString().replace("-", "").substring(0, 16).toUpperCase();
	}

	/**
	 * Checks if the given tracking number is unique.
	 * 
	 * @param trackingNumber Tracking number to check.
	 * @return True if the tracking number is unique, false otherwise.
	 */
	private boolean isTrackingNumberUnique(String trackingNumber) {
		return !trackingNumberRegistry.containsKey(trackingNumber);
	}
}
