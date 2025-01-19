package com.assessment.service;

import org.springframework.stereotype.Service;

import com.assessment.response.TrackingNumberResponse;

@Service
public interface TrackingNumberService {

	TrackingNumberResponse generateTrackingNumber(String originCountry, String destinationCountry, double weight,
			String createdAt, String customerId, String customerName, String customerSlug);
}