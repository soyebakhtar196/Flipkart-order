package com.flipkart.order;

import com.flipkart.order.payload.ActionType;
import com.flipkart.order.payload.Item;
import com.flipkart.order.payload.Order;
import com.flipkart.order.payload.OrderStatusHistory;
import com.flipkart.order.service.ActionEligibility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FlipkartOrderApplicationTests {

	private final Boolean expected=false;
	ActionEligibility<String> action=new ActionEligibility<>();

	@Test
	void contextLoads() {
		Item item = new Item();
		item.setItemId("1");
		item.setCategory("Laptop");
		item.setQuantity(1);


		// Creating an instance of OrderStatusHistory
		OrderStatusHistory status1 = new OrderStatusHistory("Delivered", LocalDate.now().plusDays(-11));
		OrderStatusHistory status2 = new OrderStatusHistory("PROCESSING", LocalDate.now().plusDays(1));
		List<OrderStatusHistory> statusHistory = new ArrayList<>();
		statusHistory.add(status1);
		statusHistory.add(status2);

		// Creating an instance of Order
		Order order = new Order();
		order.setOrderId("123456");
		order.setOrderStatus("Delivered");
		order.setOrderDate(LocalDate.now());
		order.setItemDetails(item);
		order.setOrderStatusHistory(statusHistory);

		Boolean b = action.actionEligibility(order, ActionType.CANCELLATION);
		Assertions.assertEquals(expected,b);

	}

}
