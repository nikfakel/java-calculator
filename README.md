# Calculator API

## Description
This project is a REST API for performing basic mathematical operations (addition, subtraction, multiplication, division) and managing a calculation history. It is developed using **Java**, **Spring Boot**, **MySQL**, and uses **JWT** for authentication.

## Step-by-step Installation Instructions

**Clone the repository**:
   ```bash
   git clone <REPOSITORY_URL>
   cd <PROJECT_NAME>
   ```

Set up environment variables: Create a .env file in the project root with the following variables:

```
JWT_SECRET=your_secret_key
JWT_EXPIRATION=86400000
DB_URL=jdbc:mysql://localhost:3306/calculator_db
DB_USERNAME=db_user
DB_PASSWORD=db_password
```

Build the project: Ensure Maven is installed and run:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

## Examples of Usage with curl/httpie


### Register a new user
```
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{
"username": "exampleUser",
"password": "examplePassword",
"email": "example@email.com"
}'
```

### Login
```
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{
"username": "exampleUser",
"password": "examplePassword"
}'
```

### Perform Operations
```
curl -X POST http://localhost:8080/api/calculate \
-H "Content-Type: application/json" \
-H "Authorization: Bearer <TOKEN>" \
-d '{
  "operation": "DIVISION",
  "operandA": 16,
  "operandB": 4
}
```

### Get Calculation History
```
curl -X GET http://localhost:8080/api/history \
Authorization:"Bearer <TOKEN>"
```

### Get Calculation History with Filters
```
curl -X GET http://localhost:8080/api/history?operation=DIVISION&startDate=2025-05-01T00:00:00Z&endDate=2025-05-12T23:59:59Z&page=0&size=5&sortDirection=DESC \
Authorization:"Bearer <TOKEN>"
```

### Get Calculation by ID
```
curl -X GET http://localhost:8080/api/history/<ID> \
-H "Authorization: Bearer <TOKEN>"
```

### Delete Calculation by ID
```
curl -X DELETE http://localhost:8080/api/history/<ID> \
-H "Authorization: Bearer <TOKEN>"
```


## Technical Decisions and Considerations

JWT Authentication:

JWT is used to ensure the security of operations.
Secret keys and expiration times are configured via environment variables.
Data Persistence:


The database used is MySQL.
JPA is used for entity management and queries.
Project Structure:


The project follows a clean architecture with separation of concerns:
controller: REST controllers.
service: Business logic.
repository: Data access.
Error Handling:


Proper HTTP responses are implemented for common errors like 400 Bad Request, 401 Unauthorized, and 404 Not Found.
Scalability:
The project is designed to be easily scalable by adding new operations or features.
