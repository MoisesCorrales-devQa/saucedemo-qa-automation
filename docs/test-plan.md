# 📝 Plan de Pruebas General

Este documento describe el enfoque general adoptado para probar la aplicación SauceDemo utilizando Selenium WebDriver y JUnit 5.

---

## 🎯 Objetivo

Validar el correcto funcionamiento de funcionalidades clave en la aplicación SauceDemo mediante pruebas automatizadas E2E.

---

## 📌 Alcance

Incluye pruebas de:

- Login de usuarios
- Visualización y ordenamiento de productos
- Gestión del carrito de compras
- Flujo de checkout completo

No incluye por ahora:

- Pruebas en múltiples navegadores
- Pruebas de rendimiento o seguridad

---

## 🧪 Estrategia de Pruebas

- **Tipo:** Pruebas funcionales automatizadas
- **Enfoque:** Caja negra
- **Técnicas:** Exploratoria, basada en requisitos

---

## 🗂️ Herramientas

- Selenium WebDriver
- JUnit 5
- WebDriverManager
- Maven

---

## 📊 Criterios de Aceptación

- El sistema debe comportarse según lo esperado en todos los flujos cubiertos
- Todos los test deben pasar exitosamente en ejecución local y CI

---

## ✅ Criterios de Éxito

- Cobertura mínima inicial del 80% de los flujos críticos
- Documentación clara y organizada
- Posibilidad de extender fácilmente la suite