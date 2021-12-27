package com.lab2.drivers.repo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "driver")
@Entity
public final class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String info;
    private Integer status;

    public Driver(Long userId, String info, Integer status) {
        this.userId = userId;
        this.info = info;
        this.status = status;
    }
}
