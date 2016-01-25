package com.astraltear.cmdlinerunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional  // 클래스에 선언하면  이 클래스에 속한 각 메소드를 다른 클래스에서 호출하면 DB 트랜잭션이 자동으로 이루어집니다.
public class CustomerRepository {
//	private final ConcurrentMap<Integer, Customer> customerMap  = new ConcurrentHashMap<>() ;
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<Customer> customerRowMapper = (rs, i) -> {
		Integer id = rs.getInt("id");
		String firstName = rs.getString("first_name");
		String lastName =  rs.getString("last_name");
		return new Customer(id, firstName, lastName);
	};
	
	public List<Customer> findAll(){
//		return new ArrayList<>(customerMap.values());
		List<Customer> customers = jdbcTemplate.query("select id, first_name, last_name from customers order by id", customerRowMapper);
		return customers;
	}
	
	public Customer findOne(Integer customerId) {
//		return customerMap.get(customerId);
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
		return jdbcTemplate.queryForObject("select id, first_name, last_name from customers where id=:id", param, customerRowMapper);
	}
	
	public Customer save(Customer customer) {
//		return customerMap.put(customer.getId(), customer);
		SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		
		if(customer.getId() == null) {
			jdbcTemplate.update("INSERT INTO customers(first_name,last_name) values(:firstName, :lastName)", param);
			
		} else {
			jdbcTemplate.update("update customers set first_name =:firstName ,last_name = :lastName where id=:id", param);
		}
		
		return customer;
	}
	
	public void delete(Integer customerId) {
//		customerMap.remove(customerId);
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
		jdbcTemplate.update("DELETE FROM customers where id= :id", param);
	}
}
