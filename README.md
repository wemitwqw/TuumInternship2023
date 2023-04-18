# TuumInternship2023
 
## Prerequisites

* Java 17
* Gradle
* Docker

## Running the project

1. Download .zip or clone project from GitHub.

2. Unzip and/or go to project location in __terminal__.

3. Navigate to /docker directory and compose mandatory docker images 
   ```sh
   docker compose up
   ```

3. Navigate to /tuumBanking directory and build the project using gradle
   ```sh
   ./gradlew build
   
   ```
   
4. Inside the same directory, run the project
   ```sh
   ./gradlew bootRun
   ```
   
5. Now you can use the project.

## How to use

### Endpoints

  - Get account by ID  

  | Endpoint (GET) |
  | ------ |
  | localhost:8080/accounts/{account_id} |  
   
  - Create a new account

  | Endpoint (POST) |
  | ------ |
  | localhost:8080/accounts |  

   Example request body:
   ```sh
   {
    "customer_id": 10,
    "country": "Estonia", 
    "currencies": ["EUR", "SEK", "USD", "GBP"]
   }
   ```
  - Create a new transaction

  | Endpoint (POST) |
  | ------ |
  | localhost:8080/accounts/{account_id}/transactions |  

   Example request body:
   ```sh
   {
     "amount": 100,
     "currency": "EUR", 
     "direction": "IN",
     "description": "Salary"
   }
   ```
   
  - Get all transactions by account id  

  | Endpoint (GET) |
  | ------ |
  | localhost:8080/accounts/{account_id}/transactions |  


* RabbitMQ Management panel is located at http://localhost:15672/

* PostgreSQL data

  | ip | localhost:5432 |
  | ------ | ------ |
  | DB | tuum |
  | user | sa |
  | password | password |
  
## Important choises

* Due to time constraints, I decided not to include any tests for the project. Initially, I wasn't planning on submitting the assessment because I lacked time for learning about the required technologies for the assignment. However, something changed on April 17th, and I felt motivated to start working on the solution. As the task doesn't require a perfect solution, I believe it's acceptable to skip the tests for now to submit the solution as soon as possible, escpecially because I've already missed the deadline.

## Horizontal scaling
* Firstly, horizontal scaling requires a change in an application logic. 
* Secondly, it reduces the downtime of an application.
* Thirdly, the initial invested resources (human hours for the change in the application logic & money for the equipment) are higher than when vertically scaling, but later on, the increase in the costs are linear.
