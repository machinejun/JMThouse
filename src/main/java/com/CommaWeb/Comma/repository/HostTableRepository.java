package com.CommaWeb.Comma.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import com.CommaWeb.Comma.dto.HostTableDto;
import com.CommaWeb.Comma.dto.HouseWaitDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class HostTableRepository {
	
	private final EntityManager entityManager;
	
	public List<HostTableDto> getlist(int hostid, int houseid, int month) {
		
		String sql= "SELECT r.id as id,username, headCount, checkInDate, checkOutDate, price ,phoneNumber, request, approvalStatus, h.id as houseId, h.name as houseName\r\n"
				+ "FROM reservation as r\r\n"
				+ "INNER JOIN house as h\r\n"
				+ "ON r.houseId = h.id\r\n"
				+ "INNER JOIN user as u\r\n"
				+ "ON r.guestId = u.id\r\n"
				+ "where r.hostId = "+ hostid +" and checkIndate like \"%-0" + month +"-%\"\r\n"
				+ "and h.id = " +houseid + "\r\n"
				+ "order by id desc\r\n"
				+ "limit 31";
		
		Query nativeQuery = entityManager.createNativeQuery(sql);
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		return jpaResultMapper.list(nativeQuery, HostTableDto.class);
		
	}
	
	public List<HostTableDto> getlist(int hostid, int month) {
		
		String sql= "SELECT r.id as id,username, headCount, checkInDate, checkOutDate, phoneNumber, request, approvalStatus, h.id as houseId, h.name as houseName\r\n"
				+ "FROM reservation as r\r\n"
				+ "INNER JOIN house as h\r\n"
				+ "ON r.houseId = h.id\r\n"
				+ "INNER JOIN user as u\r\n"
				+ "ON r.guestId = u.id\r\n"
				+ "where r.hostId = "+hostid+" and checkIndate like \"%-0"+month+"-%\"\r\n"
				+ "order by id desc;";
		
		Query nativeQuery = entityManager.createNativeQuery(sql);
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		return jpaResultMapper.list(nativeQuery, HostTableDto.class);
		
	}
	
	public List<HouseWaitDto> getWaitCount(int id) {
	
		String sql="select count(id) as wait, houseId\r\n"
				+ "from reservation\r\n"
				+ "where hostId ="+id+"\r\n"
				+ "and approvalStatus like 'WAITING'\r\n"
				+ "group by houseId";
		
		Query nativeQuery = entityManager.createNativeQuery(sql);
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		return jpaResultMapper.list(nativeQuery, HouseWaitDto.class);
		
	}

}
