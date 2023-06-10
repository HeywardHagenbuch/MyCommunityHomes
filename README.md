# ToDo List
![image](https://github.com/HeywardHagenbuch/MyCommunityHomes/assets/54590885/7dc99f8c-fe87-4a84-92ee-29db8b9729ed)

## REST Service Using Java/Spring Boot
![image](https://github.com/HeywardHagenbuch/MyCommunityHomes/assets/54590885/ebdffba1-1669-4f84-9020-c25ad821d410)

### TESTING
- Using Postman to test the following APIs

https://github.com/HeywardHagenbuch/MyCommunityHomes/assets/54590885/a2696581-a9c4-4b08-988b-6f0842f6dc50

### REQUEST MAPPINGS
- ### Get All Tasks         
- #### GET http://localhost:8080/api/v1/tasks/
- ### Get Tasks By Id
- #### GET http://localhost:8080/api/v1/tasks/{id}
- ### Create Task
- #### POST http://localhost:8080/api/v1/tasks/

#### Post Body
- title: String
- description: String
- createdDate: LocalDateTime
- eta: LocalDateTime
- finished: boolean
- taskStatus: Enum

#### Sample Payload:
{
    "title": "Groceries",
    "description": "Buy groceries for the week",
    "createdDate": "2023-06-10T12:59:11.332",
    "eta": "2023-07-10T02:59:11.332",
    "finished": false, 
    "taskStatus": "IN_PROGRESS"
}

- ### Update Completed Task By Id
- #### PUT http://localhost:8080/api/v1/tasks/finish/{id}
- ### Delete Task By Id
- #### DELTE http://localhost:8080/api/v1/tasks/{id}

## Enhancements

### Mongo
If you were to switch from H2 in-memory database to MongoDB, there would be some differences in the implementation. MongoDB is a NoSQL document database, while H2 is a relational in-memory database. Here's how the implementation would differ:

#### Dependency and Configuration:
- Remove the H2 dependency from your project and add the MongoDB driver dependency to your build configuration.
- Update the database configuration in the application.properties file to connect to MongoDB instead of H2.

#### Model and Annotations:
- Replace the JPA annotations (@Entity, @Table, etc.) with MongoDB annotations (@Document, @Field, etc.) in the Task model class.
- Use appropriate MongoDB data types and annotations for fields, such as ObjectId for the ID field and @Indexed for indexed fields.

#### Repository:
- Replace the JpaRepository with a MongoDB-specific repository interface, such as MongoRepository.
- Use appropriate MongoDB query methods and annotations for querying and filtering tasks.

#### Service and Controller:
- Update the service and controller classes to use the MongoDB repository and operations instead of JPA repository.
- Adjust the data access and manipulation logic according to the MongoDB APIs.

#### Benefits of switching to MongoDB:
- Flexible Schema: MongoDB's document-based model allows for a flexible schema, making it easier to handle evolving data structures.
- Scalability: MongoDB is designed to scale horizontally, allowing you to distribute your data across multiple servers for high availability and performance.
- Rich Querying: MongoDB provides powerful query capabilities, including support for complex queries, indexing, and aggregation pipelines.
- No SQL Joins: With MongoDB, you can store related data within a single document, eliminating the need for complex SQL joins in many cases.
- JSON-like Documents: MongoDB stores data in a JSON-like format, which aligns well with many modern web development frameworks and APIs.
- Community and Ecosystem: MongoDB has a large and active community, along with extensive documentation, resources, and integrations with other tools and frameworks.

### Testing Automation

#### Set up Testing Dependencies: 
- Add the necessary testing dependencies to your project's build configuration file (e.g., pom.xml for Maven or build.gradle for Gradle). 
- These dependencies typically include testing frameworks, libraries, and plugins.

#### Write Unit Tests: 
- Create unit tests for individual components of your application, such as service classes or utility methods. 
- Use a testing framework like JUnit or TestNG to define test methods and assertions. 
- Mock any external dependencies using libraries like Mockito or EasyMock to isolate the unit under test and provide controlled behavior.

#### Write Integration Tests: 
- Create integration tests that verify the interaction and integration between different layers or components of your application. 
- For example, you can write integration tests for your REST API endpoints or test the interaction between the service and the database. 
- Use frameworks like Spring Boot's @SpringBootTest or @DataMongoTest annotations to facilitate integration testing. 
- Mock or use an in-memory database for testing to ensure isolated and repeatable test runs.

#### Test Coverage: 
- Aim for good test coverage to ensure that your tests adequately cover critical parts of your codebase. 
- Utilize code coverage tools like JaCoCo or Cobertura to measure the coverage and identify areas that need more thorough testing. 
- Adjust your tests and test cases as needed to achieve better coverage.

#### Continuous Integration (CI): 
- Set up a CI server (e.g., Jenkins, Travis CI, or CircleCI) to automate the execution of your tests. 
- Configure the CI server to build your application, run the tests, and generate reports or notifications for test results. 
- Integrate your code repository with the CI server to trigger test runs on code commits or at regular intervals.

#### Test Suites and Test Lifecycle: 
- Organize your tests into test suites based on functional areas or components to facilitate selective test execution. 
- Leverage annotations like @Before and @After to set up and tear down test data or resources before and after test execution. 
- Use test fixtures and data builders to create consistent and reusable test data.

#### Test Reporting: 
- Utilize test reporting tools and plugins to generate test reports and metrics. 
- Many testing frameworks and CI servers provide built-in support for generating test reports in various formats (e.g., HTML, XML, or JSON). 
- These reports can help track test results, identify failures, and analyze test trends over time.

## Author: Heyward Hagenbuch
