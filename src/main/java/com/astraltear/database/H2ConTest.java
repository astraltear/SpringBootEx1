package com.astraltear.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

@EnableAutoConfiguration
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
		
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(H2ConTest.class, args);
		
	}



}
