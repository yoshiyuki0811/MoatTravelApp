package com.example.moattravel.service;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.moattravel.entity.User;
import com.example.moattravel.entity.VerificationToken;
import com.example.moattravel.repository.VerificationTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {
	
	private final VerificationTokenRepository tokenRepository;
	
	
	@Transactional
	public void create(User user, String token) {
		VerificationToken verificationToken = new VerificationToken();
		
		verificationToken .setToken(token);
		
		verificationToken.setUser(user);
		
		tokenRepository.save(verificationToken);
		
	
	}
	
	public VerificationToken getVerificationToken(String token) {
		return tokenRepository.findByToken(token);
		
	}
	
}
