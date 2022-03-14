package com.N26.robotfactory.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseRobotFactory {
    private String order_id;
    private BigDecimal total;
}
