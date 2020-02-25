package com.birlasoft.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<Product> productList;


}
