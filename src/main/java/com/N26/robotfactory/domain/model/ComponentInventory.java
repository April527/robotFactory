package com.N26.robotfactory.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentInventory componentInventoryObj = (ComponentInventory) o;

        return Objects.equals(code, componentInventoryObj.code) && price.equals(componentInventoryObj.price) &&
                available == componentInventoryObj.available && Objects.equals(part, componentInventoryObj.part);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, price, available, part);
    }

}
