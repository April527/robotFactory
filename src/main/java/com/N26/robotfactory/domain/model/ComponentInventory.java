package com.N26.robotfactory.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComponentInventory {

    private String code;
    private Double price;
    private Integer available;
    private String part;

}
