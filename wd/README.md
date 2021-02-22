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


### Package and Run

```
sbt clean assembly && scala target/scala-2.13/wd_provider-assembly-0.1.0-SNAPSHOT.jar
```

# Verify (external approach)

Terminal 1: run provider
```
sbt run
```

Terminal 2: verify
```
sbt "pactVerify --source pact/ --host localhost --port 8080 --protocol http"
```