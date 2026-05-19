package com.example.moattravel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moattravel.entity.House;

@Repository
public interface HouseRepository extends JpaRepository<House,Integer>{
	//名前部分一致
	public Page<House> findByNameLike(String keyword, Pageable pageable);
	//名前は住所か部分一致
	public Page<House> findByNameLikeOrAddressLike(String nameKeyword, String addressKeyword, Pageable pageable);
	//住所大体一致
	public Page<House> findByAddressLike(String area, Pageable pageable);
	//値段
	public Page<House> findByPriceLessThanEqual(Integer price, Pageable pageable); 
	//新着順に名前、住所が部分一致
	public Page<House> findByNameLikeOrAddressLikeOrderByCreatedAtDesc(String nameKeyword, String addressKeyword, Pageable pageable);  
	//同上で値段が安い順
	public Page<House> findByNameLikeOrAddressLikeOrderByPriceAsc(String nameKeyword, String addressKeyword, Pageable pageable);  
	//住所一致の新着順
	public Page<House> findByAddressLikeOrderByCreatedAtDesc(String area, Pageable pageable);
	//住所一致の安い順
	public Page<House> findByAddressLikeOrderByPriceAsc(String area, Pageable pageable);
	//
	public Page<House> findByPriceLessThanEqualOrderByCreatedAtDesc(Integer price, Pageable pageable);
	
	public Page<House> findByPriceLessThanEqualOrderByPriceAsc(Integer price, Pageable pageable); 
	//すべてのデータの新着順
	public Page<House> findAllByOrderByCreatedAtDesc(Pageable pageable);
	//すべてのデータの安い順
	public Page<House> findAllByOrderByPriceAsc(Pageable pageable);	
}