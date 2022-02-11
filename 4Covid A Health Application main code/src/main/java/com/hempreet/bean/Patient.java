package com.hempreet.bean;


//import javax.validation.constraints.Digits;

import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {
	private int patId;
	private String name;
	private String medicalHistory;
//	@Digits(fraction = 0, integer = 10)
	private String phone;
}