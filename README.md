# 📝 Task List Tracking Application

This is a **Task List Tracking** application built with **Spring Boot** for the backend and a **React** frontend.

The application allows users to:
- Create **Task Lists**.
- Create **Tasks** inside Task Lists.
- **Update**, **delete**, and **manage** both Task Lists and Tasks easily.

---

## 🚀 Features

- **Task List Management**: Create, update, delete, and view multiple task lists.
- **Task Management**: Add, update, delete, and track tasks within each task list.
- **Dockerized Database**: Uses a **PostgreSQL** database containerized with **Docker**.
- **Transactional Operations**: Service methods that involve multiple database interactions are wrapped with `@Transactional` to ensure atomicity and data integrity.
- **Global Exception Handling**: A `@ControllerAdvice` global exception handler is implemented to catch and manage exceptions cleanly. Currently, it mainly handles `IllegalArgumentException`, which is the most common exception type across the application.

## How to Run (the application frontend and backend)
1. Clone the repository. ```git clone git@github.com:Malak-Essam/tasks.git```, then run ```cd tasks```

2. Start the PostgreSQL container using Docker. 
   * Start Docker
   * run ```docker-compose up -d``` to start container with database
4. Configure the application database
```
By default, if you run docker-compose up without changing anything, the PostgreSQL database will be created with:

Database Name: postgres

Username: postgres

Password: changemeinprod!

👉 You do not need to manually configure anything — the application will connect automatically using these default values.

If you want to customize the database name, username, or password:

Update the Docker configuration:
Modify the environment variables inside the docker-compose.yml file.

Update the Spring Boot configuration:
Modify the database connection settings inside src/main/resources/application.properties.
```
5. Run the Spring Boot application (TaskApplication.java). 
   * to build the application run ```mvn clean install```
   * to start the application run ```mvn spring-boot:run```
  
7. Frontend React app will interact with the backend through exposed REST APIs. 
   * download the react front application from ```https://drive.google.com/file/d/1fwr6PpJliMdA6Y7ZMExkd71T3HxUgopV/view?usp=sharing```
   * to install all the packages, run ```npm install```
   * to run the application run ```npm run dev```
   * Go to the application link that will appear and you will see the application start and work correctly.

## 📖 API Documentation (Swagger UI)

The backend API is fully documented using **Swagger UI** with **SpringDoc OpenAPI**.

After running the backend, you can access the live API documentation at:
```
http://localhost:8080/swagger-ui.html
```

## 🛠️ Tech Stack

- **Backend**: Spring Boot, Spring Data JPA
- **Frontend**: React
- **Database**: PostgreSQL (Docker)
- **ORM**: Hibernate
- **Build Tool**: Maven
- **Other Tools**: Lombok (for cleaner entity classes), Docker

---

## 🐳 Docker Setup

Make sure you have Docker installed.  
To start the PostgreSQL container, you can use a simple `docker-compose.yml` (or manually run a Docker container).

Example `docker-compose.yml`:

```yaml
version: '3.1'
services:
  db:
    image: postgres
    container_name: tasklist_postgres
    restart: always
    environment:
      POSTGRES_USER: your_username
      POSTGRES_PASSWORD: your_password
      POSTGRES_DB: your_database
    ports:
      - "5432:5432"

```

## Project Structure (Backend)
```
com.malak.tasks
├── controllers        # REST API controllers
├── domain
│   ├── entities       # JPA entities
│   └── dto            # Data transfer objects (DTOs)
├── mappers            # DTO ↔ Entity mappers
├── repositories       # JPA repositories
├── services           # Business logic
```
   
## 📬 API Overview (Backend Endpoints)
* POST /api/task-lists → Create a new task list
* GET /api/task-lists/{task_list_id} -> get a single task list
* PUT /api/task-lists → Update a task list
* DELETE /api/task-lists/{task_list_id} -> delete a task list
* GET /api/task-lists → Get all task lists

* GET /api/task-lists/{task_list_id}/tasks -> get tasks of task list
* Get /api/task-lists/{task_list_id}/tasks/{task_id} -> get a task from task list tasks
* PUT /api/task-lists/{task_list_id}/tasks/{task_id} -> update task from task list tasks
* POST /api/task-lists/{task_list_id}/tasks -> create a task add it to task list tasks
* DELETE /api/task-lists/{task_list_id}/tasks/{task_id} -> delete a task from task list

