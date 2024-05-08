# Student CRUD Demo
```
Spring boot application with Postgres, Student CRUD Demo Application.
```

# Rest API - index Sketch :
```
as BASE_URL I saved in Post man collection : http://localhost:8080/api/students
```

### - GET ALL STUDENTS
```
    - GET - /api/students
```
### - FIND STUDENT BY ID
```
    - GET - /api/students/{id}
```
### - CREATE STUDENT
```
  - POST - /api/students
```
```
Sample Input Json : 
{
    "name": "Arjun",
    "dateOfBirth": "01-01-1995",
    "joiningDate": "10-05-2022",
    "className": "Maths"
}
```
### - UPDATE STUDENT
```
    - PUT - /api/students/{id}
    - you can pass just what are the fields you want to Update , not necessary to send every feilds.
```
```
Sample Input Json {
     "name": "Arjun",
    "dateOfBirth": "01-01-1995",
    "joiningDate": "10-05-2022",
    "className": "Maths"
}
```
```
Sample Input Json {
    "joiningDate": "10-05-2022",
    "className": "Maths"
}

```

