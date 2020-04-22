package com.birlasoft.cartservice.repository;

import com.birlasoft.domain.common.ProductDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository  extends CrudRepository<ProductDetails,Long> {
}
