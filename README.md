# microservice-e-com

# Microservice E-Commerce System

## 1. Project Overview

`microservice-e-com` is a microservices-based e-commerce application designed to manage users, products, shopping carts, and orders.

The system is divided into independent services that communicate with each other through APIs. Each service is responsible for a specific business domain.

### Main Services

The application consists of the following major services:

- **User Service** – manages customer/user information.
- **Product Service** – manages products and product searches.
- **Order Service** – manages shopping carts and orders.
- **PostgreSQL** – used as the relational database.
- **Docker** – used to containerize and run the application services and database.

---

# 2. System Architecture

The application follows a microservice architecture.

```text
                         ┌──────────────────┐
                         │      Client      │
                         │ Postman / Frontend│
                         └────────┬─────────┘
                                  │
                                  ▼
                    ┌─────────────────────────┐
                    │      API Gateway        │
                    └───────────┬─────────────┘
                                │
             ┌──────────────────┼──────────────────┐
             │                  │                  │
             ▼                  ▼                  ▼
     ┌──────────────┐   ┌──────────────┐   ┌──────────────┐
     │ User Service │   │Product Service│   │ Order Service│
     └──────┬───────┘   └──────┬───────┘   └──────┬───────┘
            │                  │                  │
            ▼                  ▼                  ▼
       PostgreSQL          PostgreSQL          PostgreSQL
```

Depending on the implementation, each service can have its own PostgreSQL database/schema. This keeps services loosely coupled and allows each service to manage its own data.

---

# 3. Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend programming language |
| Spring Boot | Building REST APIs |
| Spring Data JPA | Database access |
| PostgreSQL | Relational database |
| Docker | Containerization |
| Docker Compose | Running multiple containers |
| REST API | Communication between client and services |
| Maven | Dependency management and project build |
| Postman | API testing |

---

# 4. User Service

The **User Service** is responsible for creating, retrieving, and updating users.

## User Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/users` | Add a new user |
| GET | `/users/{id}` | Get a user by ID |
| GET | `/users` | Get all users |
| PUT | `/users/{id}` | Update a user |

> The exact URL can be adjusted to match the controller mappings in your project.

---

## 4.1 Add User

### Endpoint

```http
POST /users
```

### Description

Creates a new user in the system.

### Example Request

```json
{
  "firstName": "John",
  "lastName": "Akanmu",
  "email": "john@example.com",
  "phone": "08012345678"
}
```

### Response

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Akanmu",
  "email": "john@example.com",
  "phone": "08012345678"
}
```

### Expected Status

```text
201 CREATED
```

---

# 4.2 Get User

### Endpoint

```http
GET /users/{id}
```

### Description

Retrieves a specific user using the user's ID.

### Example

```http
GET /users/1
```

### Response

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Akanmu",
  "email": "john@example.com",
  "phone": "08012345678"
}
```

### Expected Status

```text
200 OK
```

---

# 4.3 Get All Users

### Endpoint

```http
GET /users
```

### Description

Retrieves all registered users.

### Response

```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Akanmu",
    "email": "john@example.com"
  },
  {
    "id": 2,
    "firstName": "David",
    "lastName": "Smith",
    "email": "david@example.com"
  }
]
```

### Expected Status

```text
200 OK
```

---

# 4.4 Update User

### Endpoint

```http
PUT /users/{id}
```

### Description

Updates an existing user's information.

### Example

```http
PUT /users/1
```

### Request

```json
{
  "firstName": "John",
  "lastName": "Johnson",
  "email": "john.johnson@example.com",
  "phone": "08098765432"
}
```

### Expected Status

```text
200 OK
```

---

# 5. Product Service

The **Product Service** is responsible for managing products available in the e-commerce application.

It supports creating, retrieving, updating, deleting, and searching products.

## Product Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/products` | Create a product |
| GET | `/products/{id}` | Get a product |
| GET | `/products` | Get all products |
| PUT | `/products/{id}` | Update a product |
| DELETE | `/products/{id}` | Delete a product |
| GET | `/products/search` | Search products |

---

# 5.1 Create Product

### Endpoint

```http
POST /products
```

### Description

Creates a new product.

### Example Request

```json
{
  "name": "Samsung Galaxy S25",
  "description": "Samsung smartphone",
  "price": 850000,
  "stock": 20
}
```

### Response

```json
{
  "id": 1,
  "name": "Samsung Galaxy S25",
  "description": "Samsung smartphone",
  "price": 850000,
  "stock": 20
}
```

### Expected Status

```text
201 CREATED
```

---

# 5.2 Get Product

### Endpoint

```http
GET /products/{id}
```

### Description

Retrieves a product by its ID.

### Example

```http
GET /products/1
```

### Response

```json
{
  "id": 1,
  "name": "Samsung Galaxy S25",
  "description": "Samsung smartphone",
  "price": 850000,
  "stock": 20
}
```

### Expected Status

```text
200 OK
```

---

# 5.3 Get Products

### Endpoint

```http
GET /products
```

### Description

Retrieves all available products.

### Response

```json
[
  {
    "id": 1,
    "name": "Samsung Galaxy S25",
    "price": 850000,
    "stock": 20
  },
  {
    "id": 2,
    "name": "Apple iPhone 17",
    "price": 1200000,
    "stock": 15
  }
]
```

### Expected Status

```text
200 OK
```

---

# 5.4 Update Product

### Endpoint

```http
PUT /products/{id}
```

### Description

Updates the details of an existing product.

### Example

```http
PUT /products/1
```

### Request

```json
{
  "name": "Samsung Galaxy S25 Ultra",
  "description": "Updated Samsung smartphone",
  "price": 950000,
  "stock": 25
}
```

### Expected Status

```text
200 OK
```

---

# 5.5 Delete Product

### Endpoint

```http
DELETE /products/{id}
```

### Description

Removes a product from the product catalogue.

### Example

```http
DELETE /products/1
```

### Expected Status

```text
204 NO CONTENT
```

or, depending on the implementation:

```text
200 OK
```

---

# 5.6 Search Products

### Endpoint

```http
GET /products/search
```

### Description

Searches for products based on a search keyword.

### Example

```http
GET /products/search?keyword=samsung
```

### Response

```json
[
  {
    "id": 1,
    "name": "Samsung Galaxy S25",
    "description": "Samsung smartphone",
    "price": 850000,
    "stock": 20
  }
]
```

### Expected Status

```text
200 OK
```

---

# 6. Order Service

The **Order Service** manages the customer's shopping cart and order creation process.

The service provides functionality for:

- Adding products to a cart
- Removing products from a cart
- Viewing the cart
- Creating an order

## Order Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/cart` | Add a product to the cart |
| DELETE | `/cart/{itemId}` | Remove a cart item |
| GET | `/cart` | Get the customer's cart |
| POST | `/orders` | Create an order |

---

# 6.1 Add to Cart

### Endpoint

```http
POST /cart
```

### Description

Adds a product to the customer's shopping cart.

### Example Request

```json
{
  "productId": 1,
  "quantity": 2
}
```

### Example Response

```json
{
  "cartId": 10,
  "productId": 1,
  "productName": "Samsung Galaxy S25",
  "quantity": 2,
  "price": 850000,
  "total": 1700000
}
```

### Expected Status

```text
201 CREATED
```

---

# 6.2 Remove Cart Item

### Endpoint

```http
DELETE /cart/{itemId}
```

### Description

Removes a product from the customer's shopping cart.

### Example

```http
DELETE /cart/10
```

### Expected Status

```text
204 NO CONTENT
```

---

# 6.3 Get Cart

### Endpoint

```http
GET /cart
```

### Description

Retrieves the current shopping cart for a customer.

### Example Response

```json
{
  "cartId": 10,
  "items": [
    {
      "productId": 1,
      "productName": "Samsung Galaxy S25",
      "quantity": 2,
      "price": 850000,
      "total": 1700000
    },
    {
      "productId": 2,
      "productName": "Wireless Headphones",
      "quantity": 1,
      "price": 50000,
      "total": 50000
    }
  ],
  "totalAmount": 1750000
}
```

### Expected Status

```text
200 OK
```

---

# 6.4 Create Order

### Endpoint

```http
POST /orders
```

### Description

Creates an order from the customer's cart.

The order process generally involves:

1. Retrieving the customer's cart.
2. Validating the products.
3. Checking product availability.
4. Calculating the order total.
5. Creating the order.
6. Saving the order items.
7. Clearing the cart after successful order creation.

### Example Request

```json
{
  "userId": 1
}
```

### Example Response

```json
{
  "orderId": 1001,
  "userId": 1,
  "status": "PENDING",
  "totalAmount": 1750000,
  "items": [
    {
      "productId": 1,
      "productName": "Samsung Galaxy S25",
      "quantity": 2,
      "price": 850000
    },
    {
      "productId": 2,
      "productName": "Wireless Headphones",
      "quantity": 1,
      "price": 50000
    }
  ]
}
```

### Expected Status

```text
201 CREATED
```

---

# 7. PostgreSQL Database

The application uses **PostgreSQL** as its relational database.

PostgreSQL stores persistent application data such as:

### User Data

```text
users
```

### Product Data

```text
products
```

### Cart Data

```text
carts
cart_items
```

### Order Data

```text
orders
order_items
```

A possible database relationship is:

```text
User
 │
 │ 1
 │
 ├───────────────*
 │
Cart
 │
 │ 1
 │
 ├───────────────*
 │
CartItem
 │
 │
 └────────────── Product


User
 │
 │ 1
 │
 ├───────────────*
 │
Order
 │
 │ 1
 │
 ├───────────────*
 │
OrderItem
 │
 │
 └────────────── Product
```

---

# 8. Docker

Docker is used to containerize the application and PostgreSQL database.

A typical Docker environment can contain:

```text
microservice-e-com
│
├── user-service
├── product-service
├── order-service
├── postgres
└── docker-compose.yml
```

---

# 9. Docker Compose

A typical `docker-compose.yml` can be structured as follows:

```yaml
services:

  postgres:
    image: postgres:16
    container_name: ecommerce-postgres
    environment:
      POSTGRES_DB: ecommerce
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  user-service:
    build: ./user-service
    container_name: user-service
    ports:
      - "8081:8080"
    depends_on:
      - postgres

  product-service:
    build: ./product-service
    container_name: product-service
    ports:
      - "8082:8080"
    depends_on:
      - postgres

  order-service:
    build: ./order-service
    container_name: order-service
    ports:
      - "8083:8080"
    depends_on:
      - postgres

volumes:
  postgres_data:
```

> The ports and folder names should be changed to match your actual project.

---

# 10. Running the Application

## Step 1: Clone the Project

```bash
git clone <repository-url>
```

Navigate into the project:

```bash
cd microservice-e-com
```

## Step 2: Start Docker

Make sure Docker Desktop is running.

Check Docker:

```bash
docker --version
```

Check Docker Compose:

```bash
docker compose version
```

## Step 3: Start the Services

Run:

```bash
docker compose up --build
```

To run in detached mode:

```bash
docker compose up -d --build
```

## Step 4: Check Running Containers

```bash
docker ps
```

You should see containers similar to:

```text
ecommerce-postgres
user-service
product-service
order-service
```

## Step 5: Stop the Application

```bash
docker compose down
```

To remove containers and the database volume:

```bash
docker compose down -v
```

---

# 11. API Testing

The APIs can be tested using Postman, Swagger UI, or another REST API client.

A typical testing sequence is:

### User

```text
1. Create User
2. Get User
```

### Product

```text
1. Create Product
2. Get Product
3. Get Products
4. Search Products
5. Update Product
```

### Cart

```text
1. Add Product to Cart
2. Get Cart
3. Remove Cart Item
```

### Order

```text
1. Add Products to Cart
2. Get Cart
3. Create Order
```

---

# 12. Complete API Summary

## User Service

| Operation | HTTP Method | Endpoint |
|---|---|---|
| Add User | POST | `/users` |
| Get User | GET | `/users/{id}` |
| Get All Users | GET | `/users` |
| Update User | PUT | `/users/{id}` |

## Product Service

| Operation | HTTP Method | Endpoint |
|---|---|---|
| Create Product | POST | `/products` |
| Get Product | GET | `/products/{id}` |
| Get Products | GET | `/products` |
| Update Product | PUT | `/products/{id}` |
| Delete Product | DELETE | `/products/{id}` |
| Search Products | GET | `/products/search` |

## Order Service

| Operation | HTTP Method | Endpoint |
|---|---|---|
| Add to Cart | POST | `/cart` |
| Remove Cart Item | DELETE | `/cart/{itemId}` |
| Get Cart | GET | `/cart` |
| Create Order | POST | `/orders` |

---

# 13. Error Handling

The services should return appropriate HTTP status codes.

| Status | Meaning |
|---|---|
| 200 | Request completed successfully |
| 201 | Resource created successfully |
| 204 | Request successful with no response body |
| 400 | Invalid request |
| 401 | Authentication required |
| 403 | Access denied |
| 404 | Resource not found |
| 409 | Resource conflict |
| 500 | Internal server error |

Example error response:

```json
{
  "status": 404,
  "message": "Product not found",
  "timestamp": "2026-09-30T12:00:00"
}
```

---

# 14. Microservice Responsibilities

### User Service

Responsible for:

- User creation
- User retrieval
- User listing
- User updates
- User-related data

### Product Service

Responsible for:

- Product creation
- Product retrieval
- Product listing
- Product updates
- Product deletion
- Product searching
- Product availability

### Order Service

Responsible for:

- Shopping cart management
- Adding products to carts
- Removing cart items
- Retrieving carts
- Creating orders
- Calculating order totals
- Managing order items

---

# 15. Service-to-Service Communication

Because this is a microservice application, services may need to communicate with one another.

For example, when creating an order:

```text
Client
  │
  ▼
Order Service
  │
  ├──► User Service
  │       └── Validate User
  │
  ├──► Product Service
  │       └── Validate Product / Stock
  │
  ▼
Create Order
  │
  ▼
Update Cart
```

This keeps each service responsible for its own domain while allowing the Order Service to coordinate the checkout process.

---

# 16. Project Structure

A possible project structure is:

```text
microservice-e-com/
│
├── user-service/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
│
├── product-service/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
│
├── order-service/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
│
├── docker-compose.yml
│
└── README.md
```

Each service should ideally follow a layered structure such as:

```text
src/main/java/
└── com.example.service/
    ├── controller/
    ├── service/
    ├── repository/
    ├── entity/
    ├── dto/
    ├── exception/
    └── config/
```

---

# 17. Conclusion

`microservice-e-com` provides a modular e-commerce backend using a microservice architecture.

The system separates responsibilities into:

```text
User Service
     │
     ├── Users
     │
Product Service
     │
     ├── Products
     ├── Product Search
     │
Order Service
     │
     ├── Cart
     └── Orders
```

PostgreSQL provides persistent relational storage, while Docker provides a consistent environment for running the services and database.

The architecture allows each service to be developed, tested, deployed, and scaled independently.