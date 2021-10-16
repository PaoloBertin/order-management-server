package eu.opensource.ordermanagement.service.impl.dto;

import eu.opensource.ordermanagement.domain.Order;

import java.math.BigDecimal;

public class OrderDtoBuilder {

    private Order order;

    private OrderDtoBuilder(Order order) {

        this.order = order;
    }

    public static OrderDtoBuilder newOrderDto(Order order) {

        return new OrderDtoBuilder(order);
    }

    public OrderDto build() {

        BigDecimal totalAmount = BigDecimal.ZERO;
        totalAmount = order.getLineItems()
                           .stream()
                           .map(lineItem -> lineItem.getPrice()
                                                    .multiply(lineItem.getQuantityBig()))
                           .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OrderDto(order.getId(),
                            order.getOrderDate(),
                            totalAmount,
                            order.getCustomer().getId(),
                            order.getCustomer().getFirstname() + " " + order.getCustomer().getLastname(),
                            order.getLineItems()
        );
    }
}
