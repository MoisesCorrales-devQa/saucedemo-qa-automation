# saucedemo-qa-automation

Este proyecto contiene una suite de automatización de pruebas para el sitio [SauceDemo](https://www.saucedemo.com/), desarrollada con el objetivo de demostrar mis capacidades como QA Automation Engineer especializado en testing web. La suite valida funcionalidades clave de la aplicación mediante pruebas automatizadas de extremo a extremo, utilizando buenas prácticas de diseño, organización de código y documentación.

This project is an automated test suite for SauceDemo, created to showcase my skills as a QA automation engineer with a focus on web testing. It includes end-to-end tests that validate key features of the application, following best practices for test design, code structure, and documentation.

---

## 🔧 Tecnologías Utilizadas

- **Java** – Lenguaje principal del proyecto
- **Selenium WebDriver** – Framework para automatización de navegadores
- **JUnit 5** – Framework de testing usado para ejecutar las pruebas
- **Maven** – Gestión de dependencias y ciclo de vida del proyecto
- **WebDriverManager** – Gestión automática de drivers para Selenium
- **Page Object Model (POM)** – Patrón de diseño usado para organizar el código
- **GitHub Actions** *(próximamente)* – Para integración continua (CI)

---

## ✅ Casos de Prueba Cubiertos

La suite actual cubre las siguientes funcionalidades principales del sitio SauceDemo:

### 🔐 Autenticación (Login)
- Login exitoso con credenciales válidas
- Login fallido con credenciales inválidas
- Comprobación de redirección a la página de inventario

*(Se añadirán más casos como usuario bloqueado, mensajes de error, etc.)*

---

## ▶️ Cómo Ejecutar las Pruebas

1. Clonar el repositorio:

   git clone https://github.com/tu_usuario/saucedemo-qa-automation.git
   cd saucedemo-qa-automation

2. Instalar dependencias y compilar:

   mvn clean install

3. Ejecutar las pruebas:

   mvn test

   > También puedes ejecutar las pruebas desde tu IDE favorito (IntelliJ, VS Code, Eclipse, etc.)

---

## 🧩 Posibilidades de Expansión

- Más cobertura de casos funcionales (carrito, checkout, filtros, etc.)
- Incorporación de BDD con Cucumber
- Ejecución en múltiples navegadores con Selenium Grid
- Integración con GitHub Actions (CI/CD)
- Evidencias de ejecución (screenshots, logs, reportes HTML)
- Métricas de cobertura de pruebas automatizadas

---

## 📚 Documentación

- 📄 Casos de Prueba Funcionales: ./docs/test-cases.md
- 🔗 Matriz de Trazabilidad: ./docs/test-traceability.md
- 📝 Plan de Pruebas General (opcional): ./docs/test-plan.md

---

## 👤 Sobre el Autor

Este repositorio forma parte de mi portafolio profesional como QA Automation Engineer especializado en testing web con Selenium. Mi objetivo es mostrar un enfoque completo, desde el diseño funcional hasta la automatización robusta y mantenible.

Estoy abierto a colaboraciones, oportunidades freelance o empleos en QA.  
Puedes contactarme a través de LinkedIn o visitar mi perfil en GitHub.
