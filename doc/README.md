# Verify WD (producer)

## 1. Run Broker
```
$ cd broker
$ docker-compose up
```

## 2. Run Gate Tests
```
$ cd gate/tests
$ python -m pytest pact_wd.py
```

## 3. Verify (passing test)

```
$ cd wd
$ sbt compile run # terminal 1
$ sbt "pactVerify --host localhost --port 8080 --protocol http"
```

## 4. Verify (failing test)

### a) Modify WD to induce a breach of contract

```
--- a/wd/src/main/scala/com/Ledger/UserRegistry.scala
+++ b/wd/src/main/scala/com/Ledger/UserRegistry.scala
@@ -20,7 +20,7 @@ object UserRegistry {
       case JudgeAge(user, replyTo) =>
         var age = user.age
         var judgement = "young"
-        if (age >= 100) {
+        if (age >= 200) {
           judgement = "old"
         }
         replyTo ! JudgmentPerformed(s"${user.name}", judgement)
```

### b) Compile and Run the provider
```
$ sbt compile run
```

### c) Re-Verify

```
$ cd wd
$ sbt compile run # terminal 1
$ sbt "pactVerify --host localhost --port 8080 --protocol http"
```