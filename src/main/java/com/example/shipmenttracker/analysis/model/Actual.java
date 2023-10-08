package com.example.shipmenttracker.analysis.model;

import com.example.shipmenttracker.chain.model.Chain;
import com.example.shipmenttracker.customer.model.Customer;
import com.example.shipmenttracker.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "actual")
public class Actual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "chain_id", referencedColumnName = "id")
    private Chain chain;
    private Long volume;
    private Double salesValue;
    private boolean promo;
}
