To demonstrate the capabilities of Microbots in a microservices architecture, we will create three Spring Boot microservices for an Order Management System. Here's the breakdown of the services:

Architecture Overview Order Service → Inventory Service → Shipping Service → Order Service.

Communication between services will be done using HTTP REST APIs.

Each service will use Apache Camel for routing and integration (Microbots Core Components to transform / validate and process data).

Order Service Functionality:
Reads orders from an in-memory H2 database.

Calls the Inventory Service with order details.

Inventory Service Functionality:
Receives order details from the Order Service.

Calls the Shipping Service to generate a device ID.

Shipping Service Functionality:
Generates a random device ID for each item.

Calls the Order Service to update the order with the device ID.

Expected Flow Order Service fetches orders from the database.

Sends order details to the Inventory Service.

Inventory Service forwards the details to the Shipping Service.

Shipping Service generates a device ID and updates the order in the Order Service.

Summary Three microservices demonstrate Microbots's routing and integration and pluggable capabilities.

Communication between services is done using HTTP REST APIs.

The Order Service acts as the central service, interacting with the Inventory Service and Shipping Service.

Each service is independent and can be scaled or modified as needed.

Sample Queries select * from "order"

insert into "order" ("QUANTITY","ID","ITEM") values (1,11111,'Mobile'); insert into "order" ("QUANTITY","ID","ITEM") values (1,2222,'Router'); insert into "order" ("QUANTITY","ID","ITEM") values (1,33333,'SIM'); insert into "order" ("QUANTITY","ID","ITEM") values (1,444444,'wifi'); commit;
