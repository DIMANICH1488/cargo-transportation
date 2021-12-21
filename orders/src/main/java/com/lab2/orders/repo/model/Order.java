package com.lab2.orders.repo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "order")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer volume;
    private Integer weight;
    private String pointFrom;
    private String pointTo;
    private Integer status;

    public Order(Integer volume, Integer weight, String pointFrom, String pointTo, Integer status) {
        this.volume = volume;
        this.weight = weight;
        this.pointFrom = pointFrom;
        this.pointTo = pointTo;
        this.status = status;
    }
}
