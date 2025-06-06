# Chat Service API

A Spring Boot application that exposes REST APIs for user authentication and information retrieval.

## Features

- User authentication via `/api/login` endpoint
- User information retrieval via `/api/me` endpoint
- Token-based authentication using Bearer tokens
- CORS support for cross-origin requests

## API Endpoints

### Login

**Endpoint:** `POST /api/login`

**Request Body:**
```json
{
  "username": "user_a",
  "password": "password@123"
}
```

**Response:**
```json
{
  "token": "generated-token-value",
  "username": "user1",
  "message": "Login successful"
}
```

### Get Current User

**Endpoint:** `GET /api/me`

**Headers:**
- `Authorization: Bearer <token>`

**Response:**
```json
{
  "id": 1,
  "username": "user1",
  "email": "user1@example.com",
  "fullName": "User One"
}
```

## Demo Users

The application comes with two pre-configured users for testing:

1. Username: `user_a`, Password: `password@123`
2. Username: `user_b`, Password: `password@123`

## How to Run

### Using Java

1. Clone the repository
2. Build the project: `mvn clean package`
3. Run the application: `java -jar target/chat-service-0.0.1-SNAPSHOT.jar`
4. The application will start on port 8080

### Using Docker Compose

1. Clone the repository
2. Build and start the services:
   ```
   docker-compose up -d
   ```
3. The chat service will be available at http://localhost:8080
4. The Rocket.Chat server will be available at http://localhost:3000
5. A default admin account will be created for Rocket.Chat:
   - Username: `admin`
   - Password: `admin`
   - Email: `admin@yopmail.com`
6. A personal access token for the admin account will be automatically generated and displayed in the logs of the token-generator service:
   ```
   docker-compose logs token-generator
   ```

To stop the services:
```
docker-compose down
```

To stop the services and remove volumes:
```
docker-compose down -v
```

## Testing the API

You can use tools like Postman or curl to test the API:

1. Login to get a token:
```
curl -X POST http://localhost:8080/api/login -H "Content-Type: application/json" -d '{"username":"user1","password":"password1"}'
```

2. Use the token to get user information:
```
curl -X GET http://localhost:8080/api/me -H "Authorization: Bearer <token>"
```

## Technologies Used

- Spring Boot 2.7.0
- Spring Security
- Java 11
- Docker
- Docker Compose
- Rocket.Chat
