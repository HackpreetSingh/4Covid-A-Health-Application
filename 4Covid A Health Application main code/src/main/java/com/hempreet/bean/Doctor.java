package com.hempreet.bean;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {
	private int docId;
	private String name, speciality;
	private String phone;
	private LocalDateTime from,to;
}