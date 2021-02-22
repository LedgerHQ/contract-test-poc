# wd (Provider)

(Implementation based on: https://developer.lightbend.com/guides/akka-http-quickstart-scala/)

## Run

### Option 1. Run the Server Using Sbt
```
$ sbt run
```

### Option 2. Package the Server (fat jar) and Run it
```
$ sbt clean assembly 
$ scala target/scala-2.13/wd_provider-assembly-0.1.0-SNAPSHOT.jar
```

### Option 3. Docker

```
$ docker build -t wd .
$ docker run wd
```

### Examples of Requests

```
$ # age < 100: "young"
$ curl -H "Content-type: application/json" -X POST -d '{"name": "Alice", "age": 42}' http://localhost:8080/user
{"age":"young","name":"Alice"}
$ # age >= 100: "old"
$ curl -H "Content-type: application/json" -X POST -d '{"name": "Bob", "age": 102}' http://localhost:8080/user
{"age":"old","name":"Bob"}
```

## Verify

### External Approach (from command line)

Terminal 1: run provider
```
sbt run
```

Terminal 2: verify
```
sbt "pactVerify --source pact/ --host localhost --port 8080 --protocol http"
```

### Internal Approach (as a test)

TODO

## Issues

- It is required to have a `providerStateMatcher` in a `pact.sbt` file. Should be generic instead of having to match each provider state in the pact.