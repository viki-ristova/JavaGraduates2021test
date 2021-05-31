# JavaGraduates2021test
### Books application 

The suggested solution for the JavaGraduates2021Test challenge is a Spring Boot Application that uses H2 in-memory database, and the interaction with the user is made through
showing the data on the screen with Thymeleaf.

### The application starts at http://localhost:8080/
#### The database can be accessed at http://localhost:8080/h2-console/
with JDBC URL:jdbc:h2:mem:testdb 
and User Name: sa
### *Note: This solution uses Java 11 (11.0.1 jdk)
### Task 1:

a)	 This task’s challenge was modeled with inheritance. The two classes PrintBook and Ebook inherit from the Book class. 
The challenge that appeared while solving this task was the fact that relational databases don’t have a straightforward way to map class hierarchies onto database tables. 
To address this JPA specification provides several strategies: MappedSupperclass, SingleTable, JoinedTable and Table-Per-Class. Each strategy with their pros and cons results 
in a different database structure.
In this case was used Single Table approach. This strategy creates one table for each class hierarchy. Since the records for all entities are in the same table, Hibernate needs 
a way to differentiate between them. By default this is done through a discriminator column called DTYPE which has the name of the entity as a value. We also need to tell 
Hibernate what value each sub-class record will have for the book_type column. 1 for PrintCopy, and 2 for Ebook.
This strategy has the advantage of polymorphic query performance since only one table needs to be accessed when querying parent entities. As a disadvantage,
this also means that we can no longer use NOT NULL constraints on sub-class entity properties.

b)	This part of the task is modeled with a simple one-to-many relationship between the author and book entities. (Every book has only one author, and an author can be an author of more than one book.) The way to overcome the challenge of infinite recursion cycle is to simply ignore one of the sides of the relationship. In this case, it can be in either way but here the author property in the book class is ignored with the @JsonIgnore annotation.

### Task 2:
The task 2 states that there should be books and authors inserted as data in the application. 
This application uses an in-memory database (H2) and Hibernate for creating the database schema. 
The data is inserted with data.sql file in the resources package. 
The data.sql is used to populate the schema created by Hibernate. 
As a result of a redesign of the SQL Script DataSource Initialization feature in Spring Boot 2.5 now data.sql scripts are run before Hibernate is initialized. 
To overcome this obstacle the spring.jpa.defer-datasource-initialization property in the application.properties file should be set to true.

a)	The application shows a list of all the books ordered by their year of publication at the index page: http://localhost:8080/ 

b)	All of the books by an author with a given first letter of her/his last name are shown with the help of a search bar that filters the data in the table of the authors:
http://localhost:8080/search?keyword=a 

v) 	   The function that returns the authors of the books published in the same decade as the birth year (of a given author) is called In the section ‘Statistics about the books’
on the index page. The specific example is as follows: The year in question Is 1981 – the birth year of Fredrik Backman. 
There is only one book published in the same decade, that is ‘A Brief History Of Time’ by Stephen Hawking published in 1988. Hence the result is: Stephen Hawking.

g)	  The result from more than 3 books’s authors functionality can be seen In the Statistic about the books section on the index page. There is only one author who has more 
than 3 books saved in the data layer of this application: Daniel Kahneman.

d)	 The outcome of the functions for finding oldest and newest book can be seen in the Statistic about the books section on the index page. 

### Task 3:
a) There is a button Create Book that is followed by a form with three necessary fields Title, isbn and publication year for preserving the book in the database.

b) The option to edit a book is possible with an Edit button in a column within the row of the book we want to edit. After clicking the button Edit the application context 
is redirected to http://localhost:8080/edit/1000000014 (for example) and there is a form with the fields which we can change when clicking on Submit.

v) The option to delete a book is possible with a Delete button in a column within the row of the book we want to delete. After the deletion the application redirects to 
index page (refreshes) and the book is no longer there (a delete query in the database table is executed).

g) All of the books are listed on the index page at start up.

d) A table of all of the authors with all of their books is presented at http://localhost:8080/authors 
