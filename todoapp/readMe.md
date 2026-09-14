# Simple Todo api
Todo api to create, read, update and delete tasks. The project is used to get familiar with the basics and mechanisms of working with java and spring boot, nothing complex. Uses maven as build tool.


## Test using cURL

### CREATE TODO
`curl -X POST "http://localhost:8080/api/v1/todo" -H "Content-Type:application/json" -d '{"title":"Test Java project to ensure correctness", "description":"Complete a simple java crud project to get familair working with the basics of java"}'`

### GET TODOS
`curl -X GET "http://localhost:8080/api/v1/todo?page=1&limit=10" -H "Content-Type:application/json"`

### GET TODO 
`curl -X GET "http://localhost:8080/api/v1/todo/1" -H "Content-Type:application/json"`

### UPDATE TODO
` curl -X PUT "http://localhost:8080/api/v1/todo/1" -H "Content-Type:application/json" -d '{"title":"Complete Java Project and make it production grade"}'`

### MARK TODO COMPLELTE
` curl -X PUT "http://localhost:8080/api/v1/todo/1/complete"`

### DELETE TODO
` curl -X DELETE "http://localhost:8080/api/v1/todo/1" `

