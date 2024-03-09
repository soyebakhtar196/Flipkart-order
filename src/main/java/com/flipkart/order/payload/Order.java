package com.flipkart.order.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String orderStatus;
    private LocalDate orderDate;
    private Item itemDetails;
    private List<OrderStatusHistory> orderStatusHistory;

   
}
