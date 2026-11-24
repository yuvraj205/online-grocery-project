# Online Grocery – Order Management REST API

A beginner-friendly Spring Boot REST API for managing customers, grocery items, and orders.

## Technology
- Java 17
- Spring Boot 3.4.5
- Maven
- Spring Web, Spring Data JPA, Bean Validation
- MySQL

## 1. Configure MySQL
Create the database:

```sql
CREATE DATABASE online_grocery;
```

Open `src/main/resources/application.properties` and replace `YOUR_MYSQL_PASSWORD` with your MySQL root password. Change the username too if yours is different.

## 2. Run in VS Code
1. Install JDK 17 and the VS Code Extension Pack for Java (including Spring Boot support).
2. Open the `online-grocery` folder in VS Code.
3. Make sure MySQL is running and the database exists.
4. Open `OnlineGroceryApplication.java` and click **Run**, or run from a terminal:

```bash
mvn spring-boot:run
```

The API runs at `http://localhost:8080`.

## 3. REST endpoints
All resources support Create, Read, Update, and Delete.

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/customers` | Get all customers |
| GET | `/api/customers/{id}` | Get one customer |
| POST | `/api/customers` | Create customer |
| PUT | `/api/customers/{id}` | Update customer |
| DELETE | `/api/customers/{id}` | Delete customer |
| GET | `/api/grocery-items` | Get all grocery items |
| GET | `/api/grocery-items/{id}` | Get one grocery item |
| POST | `/api/grocery-items` | Create grocery item |
| PUT | `/api/grocery-items/{id}` | Update grocery item |
| DELETE | `/api/grocery-items/{id}` | Delete grocery item |
| GET | `/api/orders` | Get all orders |
| GET | `/api/orders/{id}` | Get one order |
| POST | `/api/orders` | Create order |
| PUT | `/api/orders/{id}` | Update order |
| DELETE | `/api/orders/{id}` | Delete order |

## 4. Example requests

### Create a customer
`POST http://localhost:8080/api/customers`

```json
{
  "name": "Arvind Kumar",
  "email": "arvind@example.com",
  "address": "Sadabad, Hathras",
  "phone": "9876543210"
}
```

### Create a grocery item
`POST http://localhost:8080/api/grocery-items`

```json
{
  "name": "Rice",
  "category": "Grains",
  "price": 65.50,
  "quantity": 20
}
```

### Create an order
Use existing customer and grocery item IDs. This API currently adds the selected items' prices once each to calculate the total.
`POST http://localhost:8080/api/orders`

```json
{
  "customerId": 1,
  "groceryItemIds": [1, 2]
}
```

### Update an order
`PUT http://localhost:8080/api/orders/1` with the same JSON structure as create order.

## 5. Relationships
- One Customer can have many Orders (`@OneToMany` / `@ManyToOne`).
- One Order can contain multiple GroceryItems, and one GroceryItem can belong to multiple Orders (`@ManyToMany`).
- The many-to-many relationship is stored in the `order_grocery_items` join table.

## 6. Error handling
- `400 Bad Request`: invalid or missing request fields.
- `404 Not Found`: customer, item, or order ID does not exist.
- `409 Conflict`: database constraint conflict, such as duplicate customer email.
- `500 Internal Server Error`: unexpected server error.

## Notes
- Hibernate creates/updates tables automatically with `spring.jpa.hibernate.ddl-auto=update`.
- For a learning project, this uses entities directly in customer/item responses. Order requests use `OrderRequest` so clients submit IDs rather than nested entity objects.
- Deleting a customer who has orders may be rejected by the database due to the relationship; delete their orders first.
