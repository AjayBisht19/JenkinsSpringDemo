package com.medicare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class proReq {
	private String name;
	private int price;
	private String category;
	private int quantity;
	//private byte[] image;
}
