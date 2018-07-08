package com.eu.gsys.wma.infrastructure.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String productId;
	private String description;
	private String imageUrl;
	private Double price;
}
