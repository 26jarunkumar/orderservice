package com.arun.orderservice.route;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arun.orderservice.model.Order;
import com.arun.orderservice.repository.OrderRepository;

@Component
public class OrderRoute extends RouteBuilder {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void configure() throws Exception {
    	
        // Configure REST to use the servlet component
        restConfiguration()
            .component("servlet") // Use the servlet component
            .bindingMode("json") // Enable JSON binding
            .contextPath("/camel"); // Set the context path
    	
    	
        from("timer://orderTimer?period=100000") // Trigger every 10 seconds
            .routeId("OrderRoute")
            .process(exchange -> {
                // Fetch orders from the database
                Iterable<Order> orders = orderRepository.findAll();
                exchange.getIn().setBody(orders);
            })
            .log("Fetched orders: ${body}")
            .split(body()) // Split the list of orders
           .marshal().json() // Convert the Order object to JSON
            .to("http://localhost:8081/camel/inventory/process?bridgeEndpoint=true"); // Call Inventory Service
    }
}