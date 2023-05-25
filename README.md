# MoviesSpringProject


This Java project is a Spring-based API designed to consume the Imdb and Tmdb APIs and populate a database with movies. It provides additional functionality for user login, allowing them to add favorite movies to their list. Each favorite movie is given a star rating, and the API also includes a ranking feature to showcase the top-rated movies. The project utilizes various technologies and frameworks such as Feign, Spring Security with JWT, Spring Data, Roles, Hazelcast cache, CircuitBreaker fallbacks, Swagger, and H2 database.

Features and Components:

Spring API:
The project is built as a Spring API, which offers a comprehensive framework for developing RESTful services. It provides the necessary tools and features for request handling, dependency injection, and data management.

Imdb and Tmdb API Integration:
The API integrates with the Imdb and Tmdb APIs to fetch movie details such as titles, ratings, release dates, and cast information. These external APIs serve as valuable data sources to populate the local movie database.

User Authentication and Favorite Movies:
Users can register and log in to the API, enabling them to add movies to their favorite list. Each favorite movie is assigned a star rating, allowing users to rate their preferred movies.

Star Ranking:
The project includes a star ranking feature that calculates and displays the top-rated movies based on user ratings. This ranking system helps users discover highly-rated movies within the database.

Feign:
Feign, a declarative web service client, is used to interact with the Imdb and Tmdb APIs. It simplifies the process of making API calls by abstracting the underlying HTTP communication and providing a clean interface for consuming external endpoints.

Spring Security with JWT:
To ensure secure access to the API, Spring Security is employed in conjunction with JSON Web Tokens (JWT). User authentication and authorization are implemented, allowing only authenticated users with valid tokens to perform certain actions.

Spring Data:
Spring Data is utilized to handle database operations seamlessly. It provides a higher-level abstraction for interacting with the database, simplifying the implementation of CRUD operations and entity management.

Roles:
Role-based access control is implemented using Spring Security. Different roles, such as user and admin, can be assigned to users, granting them specific privileges and access levels within the API.

Hazelcast Cache:
Hazelcast cache is integrated into the project to improve performance by caching frequently accessed movie data. This caching mechanism reduces the response time for subsequent requests, resulting in a smoother user experience.

CircuitBreaker Fallbacks:
To enhance fault tolerance and resilience, CircuitBreaker fallbacks are implemented. They prevent failures in external APIs, such as Imdb and Tmdb, from causing cascading failures in the application by providing alternative fallback mechanisms.

H2 Database:
The H2 database is utilized to store the movie data fetched from the Imdb and Tmdb APIs. H2 is an in-memory database known for its simplicity and speed, making it a suitable choice for development and testing purposes.

Swagger:
the documentation was done with Swagger
