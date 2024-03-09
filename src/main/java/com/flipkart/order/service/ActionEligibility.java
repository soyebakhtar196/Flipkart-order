package com.flipkart.order.service;

import com.flipkart.order.payload.ActionType;
import com.flipkart.order.payload.Order;
import com.flipkart.order.payload.OrderStatusHistory;

import java.time.LocalDate;

public class ActionEligibility<T> {
    public Boolean actionEligibility(Order order, ActionType actionType) {
        switch (actionType) {
            case CANCELLATION:
                return isCancellationEligible(order);
            case ADDRESS_CHANGE:
                return isAddressChangeEligible(order);
            case RETURN:
                return isReturnEligible(order);
            default:
                throw new IllegalArgumentException("Unknown action type");
        }
    }

    private boolean isCancellationEligible(Order order) {
        return !"Delivered".equals(order.getOrderStatus());
    }

    private boolean isAddressChangeEligible(Order order) {
        return !"Shipped".equals(order.getOrderStatus());
    }

    private boolean isReturnEligible(Order order) {
        LocalDate deliveryDate = null;
        LocalDate currentDate = LocalDate.now();
        for (OrderStatusHistory statusHistory : order.getOrderStatusHistory()) {
            if ("Delivered".equals(statusHistory.getStatus())) {
                deliveryDate = statusHistory.getTimestamp();
                break;
            }
        }

        if (deliveryDate == null)
            return false;

        int returnPeriod = order.getItemDetails().getCategory().equals("Mobile") ? 10 : 30;
        LocalDate returnEndDate = deliveryDate.plusDays(returnPeriod);


        return !currentDate.isAfter(returnEndDate);
    }
}