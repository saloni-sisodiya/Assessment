# Tracking Number API

## Overview
The Tracking Number API is a RESTful service built using Spring Boot to generate unique tracking numbers for parcels. This application is designed to be scalable, efficient, and capable of handling high concurrency.

## Features
- Generates unique tracking numbers based on provided parameters.
- Ensures compliance with regex pattern `^[A-Z0-9]{1,16}$`.
- Validates request inputs such as date formats and UUIDs.
- OpenAPI (Swagger) integration for API documentation.
- Built with the latest Spring Boot version (3.4.0).

## Prerequisites
To run the application, ensure the following software is installed:

- Java Development Kit (JDK) 21 or later
- Maven 3.6 or later
- Optional: Docker (if you wish to use the Docker setup)

## Installation
1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd tracking-number-api
   ```

2. **Build the Application**
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
   The application will be available at `http://localhost:8080`.

4. **Access API Documentation**
   Open your browser and visit:
   ```
   http://localhost:8080/swagger-ui.html
   ```

## API Usage
### Endpoint: `/next-tracking-number`

#### HTTP Method
`GET`

#### Query Parameters
| Parameter            | Type    | Description                                              | Example                                   |
|----------------------|---------|----------------------------------------------------------|-------------------------------------------|
| origin_country_id    | String  | ISO 3166-1 alpha-2 format of the origin country          | `MY`                                      |
| destination_country_id | String  | ISO 3166-1 alpha-2 format of the destination country     | `ID`                                      |
| weight               | Double  | Parcel weight in kilograms (up to 3 decimal places)      | `1.234`                                   |
| created_at           | String  | Order creation timestamp in RFC 3339 format             | `2018-11-20T19:29:32+08:00`              |
| customer_id          | UUID    | Unique identifier for the customer                      | `de619854-b59b-425e-9db4-943979e1bd49`   |
| customer_name        | String  | Name of the customer                                     | `RedBox Logistics`                        |
| customer_slug        | String  | Name of the customer in slug-case                       | `redbox-logistics`                        |

#### Response
**Success (200 OK)**
```json
{
  "tracking_number": "ABC1234567890DEF",
  "created_at": "2025-01-18T19:29:32+08:00"
}
```

**Error (400 Bad Request)**
```json
{
  "error": "Invalid created_at format. Use RFC 3339 format."
}
```

## Testing
### Manual Testing
1. Run the application.
2. Use tools like Postman or Curl to send requests to the API.

Example using Curl:
```bash
curl -X GET "http://localhost:8080/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=2025-01-18T19:29:32%2B08:00&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox%20Logistics&customer_slug=redbox-logistics"
```

### Unit Testing
Run unit tests using Maven:
```bash
mvn test
```

## Deployment

### Deploy to Heroku
1. Install the Heroku CLI.
2. Create a Heroku app:
   ```bash
   heroku create
   ```
3. Deploy the application:
   ```bash
   git push heroku main
   ```
4. Access your deployed API using the Heroku URL provided.

### Deploy to Docker
1. Build the Docker image:
   ```bash
   docker build -t tracking-number-api .
   ```
2. Run the Docker container:
   ```bash
   docker run -p 8080:8080 tracking-number-api
   ```
3. Access the API at `http://localhost:8080`.

## Additional Notes
- The project dependencies have been updated to the latest versions, including Spring Boot 3.4.0 and Springdoc OpenAPI 2.2.1.
- For production deployment, consider using a database to persist tracking numbers instead of an in-memory data structure.
- Use a cloud provider like AWS, GCP, or Azure for scalable deployment.

---

Feel free to reach out if you encounter any issues or need further assistance!
# Assessment
# Assessment
# Assessment
# Assessment
# Assessment
# Assessment
