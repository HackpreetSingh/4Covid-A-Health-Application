package com.hempreet.bean;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Appointments {	
	private int docId,patId,appointId;
	LocalDateTime timeScheduled;
}
