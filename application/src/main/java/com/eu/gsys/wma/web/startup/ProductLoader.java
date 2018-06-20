package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.infrastructure.entities.ProductEntity;
import com.eu.gsys.wma.infrastructure.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

	private ProductRepository productRepository;

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		ProductEntity shirt = new ProductEntity();
		shirt.setDescription("Spring Framework Guru Shirt");
		shirt.setPrice(18.95);
		shirt.setImageUrl(
				"https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
		shirt.setProductId("235268845711068308");
		productRepository.save(shirt);

		System.out.println("Saved Shirt - id: " + shirt.getId());

		ProductEntity mug = new ProductEntity();
		mug.setDescription("Spring Framework Guru Mug");
		mug.setImageUrl(
				"https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
		mug.setProductId("168639393495335947");
		mug.setPrice(20.0);
		productRepository.save(mug);

		System.out.println("Saved Mug - id:" + mug.getId());
	}
}
