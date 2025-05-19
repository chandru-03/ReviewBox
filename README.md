# ReviewBox

A Spring Boot application for managing movie details and user reviews. Built with Spring Boot, Thymeleaf, and Bootstrap, ReviewBox allows users to add, view, edit, and delete movies, as well as submit and manage reviews.

---

## Table of Contents

* [Features](#features)
* [Tech Stack](#tech-stack)
* [Getting Started](#getting-started)

  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
  * [Running the Application](#running-the-application)
* [Usage](#usage)

  * [Accessing the UI](#accessing-the-ui)
  * [API Endpoints](#api-endpoints)
* [Project Structure](#project-structure)
* [Contributing](#contributing)
* [License](#license)

--_

## Features

* **Movie Management**: Create, Read, Update, Delete (CRUD) operations for movies.
* **Review Management**: Users can add, edit, and delete reviews for movies.
* **Search & Filter**: Search movies by title and filter by genre or rating.
* **Responsive UI**: Bootstrap-based frontend integrated with Thymeleaf templates.
* **Validation & Error Handling**: Server-side form validation and user-friendly error pages.

---

## Tech Stack

* **Backend**: Spring Boot, Spring Data JPA, Hibernate
* **Database**: H2 (in-memory) or MySQL
* **Frontend**: Thymeleaf, Bootstrap 4
* **Build Tool**: Maven
* **Java Version**: 11+

---

## Getting Started

### Prerequisites

* Java 11 or higher installed
* Maven 3.6+
* (Optional) MySQL database if you prefer persistent storage

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/<your-username>/ReviewBox.git
   cd ReviewBox
   ```

2. **Configure the database**

   * For H2 (default): No additional setup. The app runs with an in-memory database.
   * For MySQL: Update `src/main/resources/application.properties`:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/reviewbox
     spring.datasource.username=<your-username>
     spring.datasource.password=<your-password>
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Build the project**

   ```bash
   mvn clean install
   ```

### Running the Application

```bash
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.

---

## Usage

### Accessing the UI

* **Home Page**: `http://localhost:8080/` - Lists all movies.
* **Add Movie**: `http://localhost:8080/movies/new`
* **Edit Movie**: `http://localhost:8080/movies/edit/{id}`
* **Delete Movie**: `http://localhost:8080/movies/delete/{id}`
* **Movie Details & Reviews**: `http://localhost:8080/movies/{id}`

### API Endpoints

| HTTP Method | Endpoint               | Description              |
| ----------- | ---------------------- | ------------------------ |
| GET         | `/movies`              | List all movies          |
| GET         | `/movies/{id}`         | Get movie details        |
| POST        | `/movies`              | Create a new movie       |
| PUT         | `/movies/{id}`         | Update an existing movie |
| DELETE      | `/movies/{id}`         | Delete a movie           |
| POST        | `/movies/{id}/reviews` | Add a review to a movie  |
| PUT         | `/reviews/{reviewId}`  | Update a review          |
| DELETE      | `/reviews/{reviewId}`  | Delete a review          |

---

## Project Structure

```
src/main/java/com/yourcompany/reviewbox
├── controller
│   ├── MovieController.java
│   └── ReviewController.java
├── model
│   ├── Movie.java
│   └── Review.java
├── repository
│   ├── MovieRepository.java
│   └── ReviewRepository.java
├── service
│   └── ReviewService.java
└── ReviewBoxApplication.java
```

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

Please adhere to the coding standards and include tests for new functionality.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
