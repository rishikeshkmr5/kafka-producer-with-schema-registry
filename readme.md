# Kafka + Avro + Spring Boot - Developer Setup

This project demonstrates how to build a **Spring Boot** application that produces and consumes **Kafka messages** using **Apache Avro** for serialization and **Confluent Schema Registry** for schema management.

It provides a fully working setup using:

- Docker Compose for Kafka, Zookeeper, and Schema Registry
- Java 17 with Spring Boot 3.2+
- KafkaTemplate for producing messages
- @KafkaListener for consuming Avro-encoded messages

---

## 🚀 Project Structure & Concepts

| Module/Component             | Purpose                                                     |
| ---------------------------- | ----------------------------------------------------------- |
| `KafkaProducerConfig.java`   | Kafka producer config using Avro serializer                 |
| `KafkaMessagePublisher.java` | Publishes messages using `KafkaTemplate<String, User>`      |
| `WelcomeController.java`     | REST controller for testing message publishing              |
| `CodersApplication.java`     | Main Spring Boot application class                          |
| `application.properties`     | Configuration for Kafka, Schema Registry, and Spring server |
| `docker-compose.yml`         | Sets up Kafka, Zookeeper, Schema Registry containers        |

---

## 📦 Technologies Used

- **Spring Boot** (3.2+)
- **Apache Kafka** (via Confluent Platform 7.6.0)
- **Apache Avro** (1.11.1)
- **Confluent Schema Registry** (7.6.0)
- **Docker & Docker Compose**
- **Java 17**
- **KafkaTemplate / KafkaListener**

---

## ⚙️ Prerequisites

- Docker & Docker Compose installed
- Java 17 SDK
- Maven 3.8+
- IDE (e.g. IntelliJ IDEA, Eclipse)

---

## 🧱 Setup in IntelliJ / Eclipse

1. Clone the project:

```bash
git clone https://github.com/your-user/your-kafka-avro-app.git
cd your-kafka-avro-app
```

2. Start Kafka, Zookeeper, Schema Registry:

```bash
docker-compose up -d
```

3. Open the project in your IDE.

4. Compile Avro schema:

```bash
mvn clean compile
```

> Make sure you have `user.avsc` inside `src/main/avro/`

5. Run the Spring Boot app:

```bash
mvn spring-boot:run
```

---

## 📘 Avro Schema Example

`src/main/avro/user.avsc`

```json
{
  "type": "record",
  "name": "User",
  "namespace": "com.coders.daily.coders.avro",
  "fields": [
    {"name": "name", "type": "string"},
    {"name": "age", "type": "int"},
    {"name": "email", "type": ["null", "string"], "default": null}
  ]
}
```

---

## 🌐 REST API Usage

### ➕ Publish a message to Kafka

```
POST http://localhost:8084/api/v1/kafka/publish
Content-Type: application/json

{
  "name": "Alice",
  "age": 30,
  "email": "alice@example.com"
}
```

### ✅ Expected Response:

```
Message published successfully to Kafka
```

---

## 📂 Docker Services Overview

| Service         | Port | Description                    |
| --------------- | ---- | ------------------------------ |
| kafka-zookeeper | 2181 | Coordination service for Kafka |
| kafka-broker    | 9092 | Kafka message broker           |
| schema-registry | 8081 | Stores and serves Avro schemas |

---

## 🔁 Concepts Explained

### ✅ KafkaTemplate

Spring Boot wrapper around Kafka producer API. Used to publish messages.

### ✅ @KafkaListener

Consumes messages from a topic. Supports deserialization with Avro automatically.

### ✅ Avro Serialization

Compact binary format. Schema-driven. Requires both producers and consumers to share the same Avro schema.

### ✅ Schema Registry

Manages Avro schemas and versions centrally. Avoids manual syncing between microservices.

### ✅ Docker Compose

Bootstraps Kafka, Zookeeper, and Schema Registry in isolated containers for easy local development.

---

## 🧪 Verifying Kafka Setup

```bash
docker exec -it kafka-broker kafka-topics --bootstrap-server localhost:9092 --list
curl http://localhost:8081/subjects
```

---

## 🛠 Troubleshooting

| Problem                                      | Solution                                                            |
| -------------------------------------------- | ------------------------------------------------------------------- |
| Cannot connect to Kafka from Schema Registry | Use `kafka-broker` instead of `localhost` in `ADVERTISED_LISTENERS` |
| Avro schema not registering                  | Ensure Schema Registry is running and reachable at port 8081        |
| Port conflicts                               | Make sure no other services use ports `9092`, `2181`, or `8081`     |

---

## 📚 References

- [Kafka Quick Start](https://kafka.apache.org/quickstart)
- [Spring for Apache Kafka](https://docs.spring.io/spring-kafka/docs/current/reference/html/)
- [Confluent Schema Registry](https://docs.confluent.io/platform/current/schema-registry/index.html)
- [Avro Specification](https://avro.apache.org/docs/current/spec.html)

---

## 👨‍💻 Author

**Rishikesh Kumar**

> Feel free to reach out for contributions or suggestions!

---

## 📝 License

This project is licensed under the MIT License.

