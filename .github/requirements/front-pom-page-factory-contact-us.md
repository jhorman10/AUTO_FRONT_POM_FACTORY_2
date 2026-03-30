# Requerimiento: Front POM + Page Factory (Formulario de Contacto)

## Descripción General

Este repositorio implementa el punto 1 del reto de nivelación de automatización frontend. Se debe construir un escenario E2E usando patrón POM con Page Factory para validar un flujo crítico de la aplicación Automation Exercise.

## Problema / Necesidad

Se requiere demostrar dominio técnico en automatización UI con arquitectura mantenible, reporte legible y estructura preparada para cambios futuros, cumpliendo lineamientos de Serenity BDD, Gradle y Cucumber.

## Solución Propuesta

### 1. Escenario objetivo (obligatorio)

Automatizar `Test Case 6: Formulario de Contacto` tomado de:
- `https://automationexercise.com/test_cases`
- Flujo en `https://automationexercise.com/contact_us`

Pasos funcionales esperados:
1. Launch browser
2. Navigate to `http://automationexercise.com`
3. Verify home page visible
4. Click `Contact Us`
5. Verify `GET IN TOUCH`
6. Diligenciar nombre, email, asunto y mensaje
7. Adjuntar archivo
8. Click `Submit`
9. Click `OK` en el confirm del navegador
10. Verificar mensaje de éxito `Success! Your details have been submitted successfully.`
11. Click `Home` y verificar regreso a la página principal

### 2. Restricciones técnicas

- Patrón obligatorio: `POM` con `@FindBy` (Page Factory).
- Framework: Serenity BDD.
- Lenguaje: Java.
- Build tool: Gradle.
- Runner: Cucumber.
- No incluir código comentado en clases.
- Nomenclatura semántica en clases, métodos y variables.

### 3. Criterios de aceptación (alto nivel)

1. El escenario de Formulario de Contacto se ejecuta end-to-end sin fallos de flujo.
2. Se maneja correctamente el diálogo de confirmación de envío.
3. Se valida el mensaje exacto de éxito.
4. Se valida retorno a Home al final del caso.
5. El reporte Serenity evidencia cada paso de negocio de forma legible.

## Prioridad

Alta — entregable obligatorio para evaluación técnica del reto de nivelación.
