# Prompt for Copilot or AI Agent

Create a modular, production-ready full-stack project with the following requirements:

- **Frontend**: React (TypeScript, Vite, MUI, React Query, Recharts, Toastify, Formik, Yup, PWA, i18n, accessibility)
- **Backend**: Spring Boot (JDK 21, Gradle), modular, SOLID, Clean Architecture, API versioning, OpenAPI/Swagger, advanced validation, audit logging, distributed tracing, health checks
- **Database**: PostgreSQL (persistent, Dockerized)
- **Cache**: Redis (distributed, Dockerized, Spring Cache)
- **Observability**: Distributed logging, Prometheus, Grafana, Spring Boot Actuator, OpenTelemetry, Resilience4j
- **Orchestration**: Docker Compose (unique ports, persistent storage, Portainer, Docker secrets)
- **Exception Handling**: Robust, with global handler and logging
- **Separation of Concerns**: Controller, Service, Repository, Model, Exception layers
- **Frontend/Backend Integration**: REST API for CRUD, frontend connects via REST
- **Testing**: Backend uses Testcontainers for integration tests, frontend uses Jest/React Testing Library
- **Documentation**: Professional README, GitHub Pages docs, and architecture diagram
- **One-command startup**: All services start with `docker compose up`

**Deliverables:**

- Modular codebase for both frontend and backend
- Docker Compose file orchestrating all services
- Example API usage and implementation instructions
- Architecture diagram (ASCII or image)
- Docs for GitHub Pages
- Prompt file for future replication

**Best practices:**

- Clean code, maintainability, and extensibility
- Use environment variables and Docker secrets for sensitive data
- Use unique ports to avoid conflicts
- All code and docs should be professional and ready for production
- Full observability, distributed tracing, and health checks

---

**Example usage:**

- Clone repo, run `docker compose up`, access all services on unique ports
- Extend backend by adding new modules/services
- Extend frontend by adding new UI features, charts, and PWA enhancements

---

**Replicate this prompt to generate a similar project anytime.**
