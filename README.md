# Monthly Savings App

A modular, production-grade full-stack application for managing family and business finances.

## Tech Stack

- **Frontend**: React (TypeScript, Vite, MUI, React Query, Recharts, Toastify, Formik, Yup, PWA, i18n)
- **Backend**: Spring Boot (JDK 21, Gradle, Clean Architecture, SOLID, API versioning, OpenAPI/Swagger, advanced validation, audit logging, distributed tracing, health checks)
- **Database**: PostgreSQL (persistent, Dockerized)
- **Cache**: Redis (distributed, Dockerized, Spring Cache)
- **Observability**: Distributed logging, Prometheus, Grafana, Spring Boot Actuator, OpenTelemetry, Resilience4j
- **Orchestration**: Docker Compose (unique ports, persistent storage, Portainer, Docker secrets)
- **Testing**: Unit and integration tests (Testcontainers)

## Features

- Personal and business workspaces
- CRUD for financial records (income, expense, transfer)
- Modular, maintainable codebase (Clean Architecture, SOLID)
- Robust exception handling, distributed logging, audit logging
- Distributed cache and rate limiting
- Full observability stack (Prometheus, Grafana, Actuator, OpenTelemetry)
- Health checks for all dependencies
- PWA support, i18n, accessibility, dark/light mode
- One-command startup with Docker Compose

## Quick Start

1. Clone the repository and open this folder in VS Code.
2. Run `docker compose up --build` to start all services (frontend, backend, database, Redis, Grafana, Prometheus, Portainer).
3. Access the frontend at `http://localhost:3100` (or the unique port specified in `docker-compose.yml`).
4. Access the backend API at `http://localhost:3200/api/v1/records` (or the unique port specified).
5. Access Portainer at `http://localhost:9000`.
6. Access Grafana at `http://localhost:3400` and Prometheus at `http://localhost:3500`.

## Architecture

- Follows Clean Architecture and SOLID principles.
- Backend and frontend are fully decoupled and communicate via REST.
- PostgreSQL and Redis data are persisted via Docker volumes.
- Observability, resilience, and distributed tracing are built-in.
- See `docs/architecture.md` and `docs/architecture.png.md` for diagrams.

## Development

- Frontend: `cd` into the root and use Vite scripts (`npm run dev`, `npm run build`).
- Backend: `cd backend` and use Gradle (`./gradlew bootRun`).
- Run tests: Backend uses JUnit/Testcontainers, frontend uses Jest/React Testing Library.

## Customization

- Update ports and credentials in `docker-compose.yml` and secrets as needed.
- Add your own modules/services following the modular structure.
- Extend frontend with new UI features, charts, and PWA enhancements.

## Onboarding Guide (For Non-Technical Users)

Welcome to the Monthly Savings App! This guide will help you get started, even if you have no technical background.

### What You Need

- A computer (Windows, Mac, or Linux)
- Internet access
- Google Chrome or another modern web browser

### How to Use the App

1. **Open the App:**
   - After your IT/admin has set up the app, you will receive a link (e.g., http://localhost:3100 or a company URL).
   - Click the link or enter it in your browser.
2. **Sign In (if enabled):**
   - Enter your credentials or use the provided login method.
3. **Dashboard Overview:**
   - The dashboard shows your financial records, charts, and options to add or edit records.
   - Use the menu to switch between personal and business workspaces.
4. **Add a Record:**
   - Click the “Add Record” button or fill out the form.
   - Enter the date, type (income, expense, transfer), amount, and other details.
   - Click “Save” or “Submit.”
5. **View & Edit Records:**
   - Your records appear in a table. Click on a record to edit or delete it.
6. **Charts & Reports:**
   - Visualize your savings and expenses with interactive charts.
7. **Switch Themes:**
   - Use the theme switcher (sun/moon icon) for dark or light mode.
8. **Change Language:**
   - Use the language selector (if available) to switch app language.

**If you have any issues, contact your IT support or see the troubleshooting guide below.**

---

## Troubleshooting Guide

### Common Issues & Solutions

#### 1. The app won’t load or shows an error page

- Make sure your computer is connected to the internet.
- Try refreshing the page (press F5 or click the refresh button).
- If the problem persists, contact your IT/admin to check if the server is running.

#### 2. Can’t connect to backend/API errors

- The backend service may be down. Ask your IT/admin to check Docker Compose or server status.
- Ensure you are using the correct URL (see Quick Start section).

#### 3. Login or authentication issues

- Double-check your username and password.
- If you forgot your password, use the reset option or contact support.

#### 4. Data not saving or updating

- Try refreshing the page.
- If the issue continues, there may be a backend or database problem. Contact IT support.

#### 5. Charts or tables not displaying

- Make sure your browser is up to date.
- Try clearing your browser cache.

#### 6. Docker Compose won’t start (for admins)

- Ensure Docker Desktop is installed and running.
- Run `docker compose up --build` in the project folder.
- Check for errors in the terminal and review logs for details.

#### 7. Port conflicts or address already in use

- Make sure no other app is using the same port (see `docker-compose.yml`).
- Change the port in the config if needed and restart Docker Compose.

#### 8. Database or Redis connection errors

- Ensure Docker volumes are not corrupted.
- Restart Docker containers.
- Check secrets and credentials in the `secrets/` folder.

#### 9. Observability tools (Grafana, Prometheus, Portainer) not accessible

- Confirm the correct ports (see Quick Start).
- Make sure the services are running in Docker Compose.

#### 10. Still stuck?

- Check the `README.md` and `docs/` folder for more help.
- Contact your IT/admin or open an issue on GitHub.

---

**Tip:** Most user issues can be solved by refreshing the page, checking your internet, or contacting your IT support. For technical issues, always check Docker and service logs first.

---

For more details, see the `docs/` folder and `.github/copilot-instructions.md` file.

## Deployment & GitHub Pages

To deploy the frontend as a static site (e.g., for GitHub Pages):

1. Run `npm run deploy` to build the frontend and copy the output to the `docs` folder.
2. Commit and push your changes:
   ```powershell
   git add .
   git commit -m "Deploy: build and copy frontend to docs for GitHub Pages"
   git push origin main
   ```
3. In your GitHub repository settings, go to the **Pages** section and set the source to the `main` branch and `/docs` folder.
4. After a few minutes, your app will be available at `https://<your-username>.github.io/<repo-name>/`.

## Repository Hygiene & .gitignore

- The `.gitignore` file is configured to exclude all unnecessary files, such as build output, dependencies, and custom components/pages not meant for production.
- Only essential files and `.gitkeep` placeholders are tracked for a clean, minimal repository.

## Step-by-step Git Workflow

1. Make your changes or add new features.
2. Stage your changes:
   ```powershell
   git add .
   ```
3. Commit your changes:
   ```powershell
   git commit -m "Describe your change"
   ```
4. Push to GitHub:
   ```powershell
   git push origin main
   ```

For more, see the `docs/` folder and onboarding/troubleshooting guides below.
