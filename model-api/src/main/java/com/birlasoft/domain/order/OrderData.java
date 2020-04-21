package com.birlasoft.domain.order;

import com.birlasoft.domain.common.ProductDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long orderTotal;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ProductDetails> productDetails;
}
