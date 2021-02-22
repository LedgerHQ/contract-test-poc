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

Example:
```
$ sbt "pactVerify --source pact/ --host localhost --port 8080 --protocol http"
[info] welcome to sbt 1.4.6 (Oracle Corporation Java 14.0.2)
[info] loading global plugins from /Users/glethuillier/.sbt/1.0/plugins
[info] loading settings for project wd-build from plugins.sbt ...
[info] loading project definition from /Users/glethuillier/contract-test-poc/wd/project
[info] loading settings for project root from build.sbt,pact.sbt ...
[info] set current project to wd_provider (in build file:/Users/glethuillier/contract-test-poc/wd/)
*************************************
** ScalaPact: Running Verifier     **
*************************************
Attempting to use local pact files at: 'pact/'
Verifying against 'localhost' on port '8080' with a timeout of 1 second(s).
--------------------
Attempting to run provider state: User Doe is 123 years old
Provider state ran successfully
--------------------
cURL for request: curl -X POST 'http://localhost:8080/user' -H 'Content-Type: application/json' -H 'Content-Length: 35'
--------------------
Attempting to run provider state: User Smith is 10 years old
Provider state ran successfully
--------------------
cURL for request: curl -X POST 'http://localhost:8080/user' -H 'Content-Type: application/json' -H 'Content-Length: 36'
Results for pact between gate and wd
 - [  OK  ] a request for User Doe
 - [  OK  ] a request for User Smith
[scala-pact] Run completed in: 754 ms
[scala-pact] Total number of test run: 2
[scala-pact] Tests: succeeded 2, failed 0, pending 0
[scala-pact] All Pact verify tests passed or pending.
[success] Total time: 1 s, completed Feb 22, 2021, 2:53:41 PM
```

### Internal Approach (as a test)

TODO

## Issues

- It is required to have a `providerStateMatcher` in a `pact.sbt` file. Should be generic instead of having to match each provider state in the pact.