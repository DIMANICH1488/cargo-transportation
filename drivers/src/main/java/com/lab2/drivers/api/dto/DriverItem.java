package com.lab2.drivers.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class DriverItem {
    private Long userId;
    private String info;
    private Integer status;
}
