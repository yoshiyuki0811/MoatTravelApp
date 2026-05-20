package com.example.moattravel.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.moattravel.entity.House;
import com.example.moattravel.entity.Reservation;
import com.example.moattravel.entity.User;
import com.example.moattravel.form.ReservationRegisterForm;
import com.example.moattravel.repository.HouseRepository;
import com.example.moattravel.repository.ReservationRepository;
import com.example.moattravel.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
	
	
	private final HouseRepository houserepository;
	private final UserRepository userRepository;
	private final ReservationRepository reservationRepository;
	
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
	@Transactional
	public void create(ReservationRegisterForm reservationRegisterForm) {
		
		Reservation reservation = new Reservation();
		
		House house =houserepository.getReferenceById(reservationRegisterForm.getHouseId());
		
		User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
		
		LocalDate checkinDate = LocalDate.parse(reservationRegisterForm.getCheckinDate());
		
		LocalDate checkoutDate = LocalDate.parse(reservationRegisterForm.getCheckoutDate());
		
		reservation.setHouse(house);
		reservation.setUser(user);
		reservation.setCheckinDate(checkinDate);
		reservation.setCheckoutDate(checkoutDate);
		reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());
		reservation.setAmount(reservationRegisterForm.getAmount());
		
		reservationRepository.save(reservation);
			
	}
	
}
