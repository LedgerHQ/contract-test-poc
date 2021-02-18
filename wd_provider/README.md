# Quick implementation notes

## DONE

Implementation based on: https://developer.lightbend.com/guides/akka-http-quickstart-scala/

```
$ # age < 100: "young"
$ curl -H "Content-type: application/json" -X POST -d '{"name": "Alice", "age": 42}' http://localhost:8080/user
{"age":"young","name":"Alice"}
$ # age >= 100: "old"
$ curl -H "Content-type: application/json" -X POST -d '{"name": "Bob", "age": 102}' http://localhost:8080/user
{"age":"old","name":"Bob"}
```

Dockerized

## TODO

- Interface with Python backend
- Contract testing