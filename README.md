# Search Challenge

Hexagonal Architecture example.

### Requirements

- Java > 21
- Maven > 3.8.X
- Docker & docker-compose

### Quick Up & Running

If you don´t want to waste your time and just set up the solution run:

```bash
$ cd ./search
$ docker-compose build
$ docker-compose up -d
```
Then you can check the API documentation:

[swagger link](http://localhost:8080/swagger-ui/index.html)

Or play with Postman collection:

```bash
search
...
├───postman
...
```

### Test coverage

For test coverage execute:

```bash
$ mvn clean verify
```

And browse aggregate-report files

### Notes

1. For simplicity, I only created unit tests
2. Sometimes I use Spring Converter, and sometimes I set the values directly in the builder for simplicity, MapStruct would be a better option
3. Swagger UI only accepts ISO 8601 for dates. If you want to test the API with Swagger, you must change dates manually
