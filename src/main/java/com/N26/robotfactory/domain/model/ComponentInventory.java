package com.N26.robotfactory.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComponentInventory {

    private String code;
    private BigDecimal price;
    private Integer available;
    private String part;

}
