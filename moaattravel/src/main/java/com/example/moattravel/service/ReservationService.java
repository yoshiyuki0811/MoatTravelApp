package com.example.moattravel.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {
	
	//宿泊人数が定員以下かどうかのチェック
	public boolean isWithnCapacity(Integer numberOfPeople, Integer capacity) {
		
		return numberOfPeople <=capacity;
	}

	//宿泊料金を計算する
	public Integer calculateAmount(LocalDate checkinDate, LocalDate checkoutDate, Integer price) {
		
		long numberOfNigts = ChronoUnit.DAYS.between(checkinDate,checkoutDate);
		
		int amout = price * (int)numberOfNigts;
		
		return amout;
		
		
	}
}
