# Advanced Quiz Application

<p>This is a backend-only quiz application built with <strong>Spring Boot</strong> and <strong>PostgreSQL</strong>. It offers features for quiz management, user authentication, and result tracking, implemented using <strong>microservices architecture</strong> and secured by <strong>Spring Security</strong>.</p>

## Features

<ul>
    <li><strong>User Authentication:</strong> Secure login and role-based access control using <strong>Spring Security</strong>.</li>
    <li><strong>Quiz Management:</strong> Create, update, and manage quizzes, questions, and options.</li>
    <li><strong>Quiz Modes:</strong> Support for timed and untimed quizzes with various question types (MCQ, true/false).</li>
    <li><strong>Results Tracking:</strong> Store and retrieve user performance data efficiently.</li>
</ul>

## Technologies Used

<ul>
    <li><strong>Spring Boot:</strong> Backend framework.</li>
    <li><strong>PostgreSQL:</strong> Relational database.</li>
    <li><strong>Spring Security:</strong> Authentication and authorization.</li>
    <li><strong>REST APIs:</strong> Communication between services.</li>
    <li><strong>Spring Web:</strong> Web layer for handling requests and responses.</li>
    <li><strong>Microservices Architecture:</strong> Scalable and modular design.</li>
</ul>

## Prerequisites

<ul>
    <li>Java 11+</li>
    <li>PostgreSQL 13+</li>
    <li>Maven 3.6+</li>
</ul>

## Setup

<ol>
    <li>Clone the repository:
        <pre><code>git clone https://github.com/your-username/quiz-app.git
cd quiz-app</code></pre>
    </li>
    <li>Configure PostgreSQL in <code>application.properties</code>:
        <pre><code>spring.datasource.url=jdbc:postgresql://localhost:5432/quizdb
spring.datasource.username=your-username
spring.datasource.password=your-password</code></pre>
    </li>
    <li>Run the application:
        <pre><code>mvn clean install
mvn spring-boot:run</code></pre>
    </li>
    <li>Access the API at <code>http://localhost:8080</code>.</li>
</ol>

## API Documentation

<p>You can explore the available APIs via Swagger (if integrated) or refer to the <code>/docs</code> endpoint for detailed API documentation.</p>

## License

<p>This project is licensed under the MIT License. See the <a href="LICENSE">LICENSE</a> file for details.</p>
