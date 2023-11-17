Requirements:
    Docker

Running the application locally:
    Run: docker-compose build
         docker-compose up
    Project will open on localhost:80

Project structure:
    Project consists of frontend(sector-frontend), backend(sector-api), database(Postgre) and nginx(for proxying requests between frontend and backend).

Testing:
    Project has integration tests. To run integration tests go to sectorapi folder and run ./gradlew test.

For further development:
    frontend:
        Change application entry point in App.tsx.
        Use  Redux for state management.
        If there are more forms coming with further development use Formik to handle forms.
    backend:
        Further development could be done package by feature by packaging sector, registration, customersector and customer under one feature package.
    database migrations:
        Use flyway or liquibase for migrations.
        

