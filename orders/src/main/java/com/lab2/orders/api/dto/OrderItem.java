package com.lab2.orders.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class OrderItem {
    private Integer volume;
    private Integer weight;
    private String pointFrom;
    private String pointTo;
    private Integer status;
}
