# 🔗 Matriz de Trazabilidad de Requisitos

Esta matriz muestra la relación entre los requisitos funcionales clave de la aplicación SauceDemo y los casos de prueba automatizados implementados.

| Requisito ID | Descripción                 | Casos de Prueba                                        | Automatizado | Comentarios                    |
|--------------|-----------------------------|--------------------------------------------------------|--------------|--------------------------------|
| RF01         | Login válido                | TC001                                                  | ✅ Sí         | Cubierto con Selenium          |
| RF02         | Gestión de errores de login | TC002, TC003, TC004, TC005                             | ✅ Sí         | Múltiples errores contemplados |
| RF03         | Visualización de catálogo   | TC006                                                  | ✅ Sí         | Incluye visual y validación    |
| RF04         | Ordenamiento de productos   | TC007.1, TC007.2, TC008.1, TC008.2                     | ✅ Sí         | Orden ascendente y descendente |
| RF05         | Gestión del carrito         | TC010, TC011, TC012                                    | ❌ No         | Alta prioridad                 |
| RF06         | Flujo completo de checkout  | TC013, TC014, TC015                                    | ❌ No         | Flujo end-to-end completo      |
| RF07         | Navegación entre secciones  | TC020, TC021, TC022, TC023, TC024, TC025, TC026, TC027 | ❌ No         | Validar flujos entre pantallas |


