package com.medicare.entity;

import javax.persistence.*; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String name;
	private int price;
	private String category;
	private int quantity;
	private String imageName;
	private byte[] image;
	
}
