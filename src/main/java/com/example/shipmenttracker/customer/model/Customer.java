package com.example.shipmenttracker.customer.model;

import com.example.shipmenttracker.chain.model.Chain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "chain_id", referencedColumnName = "id")
    private Chain chain;
}
