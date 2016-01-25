package com.astraltear.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.astraltear.cmdlinerunner.Customer;

@EnableAutoConfiguration
@ComponentScan  // 이 어노테이션을 활성화하면 DBConfig.java에서 DataSource를 읽어온다. DataSource를 명시적으로 정의하면 @EnableAutoConfiguration의 자동 등록 기능은 무시.
public class H2ConTest  implements CommandLineRunner {
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public void run(String... strings) throws Exception {
		String sql = "select 1";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer result = jdbcTemplate.queryForObject(sql, param, Integer.class);
		System.out.println("result::::::::::::::::::::::::::::::::::::::::::::"+result);
		
		sql="select :a + :b";
		param = new MapSqlParameterSource().addValue("a", 100).addValue("b", 200);
		result = jdbcTemplate.queryForObject(sql, param, Integer.class);
		System.out.println("result>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+result);
		
		sql="select id, first_name, last_name from customers where id = :id";
		param = new MapSqlParameterSource().addValue("id", 1);
		Customer result_customer = jdbcTemplate.queryForObject(sql, param, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}
		});
		
		System.out.println("result_customer++++++++++++++++"+result_customer);
		
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(H2ConTest.class, args);
		
	}



}
