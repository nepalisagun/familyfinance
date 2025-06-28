# Architecture Diagram

```
+-------------------+        REST        +-------------------+
|    Frontend       | <----------------> |     Backend       |
| React + Vite      |                   | Spring Boot       |
| (3100)            |                   | (3200)            |
+-------------------+                   +-------------------+
        |                                         |
        |                                         |
        v                                         v
+-------------------+                   +-------------------+
|   User Browser    |                   |   PostgreSQL DB   |
+-------------------+                   |   (5433:5432)     |
                                        +-------------------+
        ^                                         ^
        |                                         |
        |                                         |
+-------------------+                   +-------------------+
|   Portainer       |                   |   Prometheus      |
|   (9000)          |                   |   (3500:9090)     |
+-------------------+                   +-------------------+
        |                                         |
        v                                         v
+-------------------+                   +-------------------+
|   Grafana         | <----------------> |   Metrics        |
|   (3400:3000)     |                   |   /actuator/prom. |
+-------------------+                   +-------------------+
```

**Legend:**

- All services are orchestrated by Docker Compose
- Unique ports for each service
- Prometheus scrapes backend metrics, Grafana visualizes
- Portainer manages Docker stack
- Data is persisted in PostgreSQL Docker volume
