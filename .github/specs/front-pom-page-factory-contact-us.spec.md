---
id: SPEC-002
status: IMPLEMENTED
feature: front-pom-page-factory-contact-us
created: 2026-03-29
updated: 2026-03-29
author: spec-generator
version: "1.1"
related-specs: []
---

# Spec: Front POM Page Factory Formulario de Contacto

> **Estado:** `IMPLEMENTED`.
> **Ciclo de vida:** DRAFT → APPROVED → IN_PROGRESS → IMPLEMENTED → DEPRECATED

---

## 1. REQUERIMIENTOS

### Descripción
Esta funcionalidad define la automatización del punto 1 del reto de nivelación para frontend, implementando un escenario E2E con patrón POM usando Page Factory en Serenity BDD. El objetivo es validar de forma estable y legible el flujo de negocio de contacto en Automation Exercise.

### Requerimiento de Negocio
Fuente principal: solicitud del usuario en sesión (29-03-2026) para el repositorio `AUTO_FRONT_POM_FACTORY`.

Resumen del requerimiento base:
- Implementar 1 escenario tomado de `https://automationexercise.com/test_cases`.
- El patrón obligatorio es POM con `@FindBy` (Page Factory).
- El escenario seleccionado para este repositorio es `Test Case 6: Formulario de Contacto`.
- Debe mantenerse código limpio, nomenclatura semántica y reporte legible con Serenity.
- Ningún valor de negocio debe estar hardcodeado en Java ni en Gherkin.
- Todos los valores funcionales deben resolverse desde un archivo de datos reutilizable por dataset key.

### Historias de Usuario

#### HU-01: Enviar formulario de contacto exitosamente

```
Como:        QA Automation Engineer
Quiero:      automatizar el flujo de Formulario de Contacto con POM + Page Factory
Para:        validar que el formulario de contacto se puede completar y enviar con éxito

Prioridad:   Alta
Estimación:  S
Dependencias: Ninguna
Capa:        Frontend
```

#### Criterios de Aceptación — HU-01

**Happy Path**
```gherkin
CRITERIO-1.1: Envío exitoso del formulario de contacto
  Dado que:  el Usuario abre la web de Automation Exercise
  Cuando:    navega a Contact Us, diligencia nombre, email, subject, message, adjunta archivo y confirma el envío
  Entonces:  visualiza el mensaje "Success! Your details have been submitted successfully."
  Y:         puede hacer clic en Home y regresar a la página principal
```

**Error Path**
```gherkin
CRITERIO-1.2: Cancelación del envío en confirmación del navegador
  Dado que:  el Usuario diligenció el formulario de Contact Us
  Cuando:    hace clic en Submit y cancela el diálogo de confirmación
  Entonces:  el formulario no se envía
  Y:         no se muestra el mensaje de éxito
```

**Edge Case** *(si aplica)*
```gherkin
CRITERIO-1.3: Carga de archivo con ruta válida local
  Dado que:  existe un archivo de prueba en la ruta configurada del proyecto
  Cuando:    el Usuario adjunta el archivo en el campo upload_file
  Entonces:  el archivo queda seleccionado antes del Submit
  Y:         el flujo continúa sin errores de interacción del driver
```

### Reglas de Negocio
1. El escenario automatizado en este repositorio debe corresponder al `Test Case 6: Formulario de Contacto`.
2. El patrón de diseño obligatorio es POM con localizadores declarados mediante `@FindBy`.
3. Cada página debe encapsular solo comportamiento de su propia pantalla (responsabilidad única por Page Object).
4. Los datos de prueba deben ser explícitos y desacoplados de selectores y lógica de navegación.
5. El flujo debe contemplar el diálogo JavaScript de confirmación posterior al Submit.
6. El criterio de éxito funcional es la visualización exacta del texto: `Success! Your details have been submitted successfully.`.
7. El código debe mantenerse limpio: sin código comentado dentro de clases de automatización.
8. Ningún valor de negocio (name, email, subject, message, filePath, expectedSuccessMessage) puede quedar hardcodeado en clases Java ni en el `.feature`.
9. Los valores funcionales deben residir en `src/test/resources/test-data/contact-us-data.properties` y resolverse por `datasetKey`.

---

## 2. DISEÑO

### Modelos de Datos

#### Entidades afectadas
| Entidad | Almacén | Cambios | Descripción |
|---------|---------|---------|-------------|
| `ContactUsFormData` | memoria (test data object) | nueva | Objeto de datos para nombre, email, subject, message y ruta de archivo |
| `ExecutionConfig` | `serenity.conf` / propiedades Gradle | nueva o modificada | Configuración de navegador, base URL y estrategia de ejecución |

#### Campos del modelo
| Campo | Tipo | Obligatorio | Validación | Descripción |
|-------|------|-------------|------------|-------------|
| `name` | string | sí | no vacío | Nombre del remitente |
| `email` | string | sí | formato email válido | Correo para contacto |
| `subject` | string | sí | no vacío | Asunto del mensaje |
| `message` | string | sí | no vacío | Cuerpo del mensaje |
| `file_path` | string | sí | ruta local existente | Archivo a cargar en el formulario |
| `expected_success_message` | string | sí | coincidencia exacta | Mensaje esperado tras enviar |

#### Índices / Constraints
- No aplica para base de datos. Las restricciones se gestionan a nivel de datos de prueba y validaciones de interacción UI.

### API Endpoints

#### GET /
- **Descripción**: Carga la home de Automation Exercise para iniciar el flujo.
- **Auth requerida**: no.
- **Response esperada**: página principal visible y opción de navegación a Contact Us.

#### GET /contact_us
- **Descripción**: Página con formulario Contact Us.
- **Auth requerida**: no.
- **Response esperada**: título `Get In Touch`, inputs de formulario y botón Submit.

> Nota: esta spec no define API propia del proyecto; consume únicamente UI pública de `automationexercise.com`.

### Page Objects (POM)

#### Page Objects a Implementar
| Page Object | Archivo | Responsabilidad |
|------------|---------|-----------------|
| `LandingPage` | `src/test/java/.../pages/LandingPage.java` | Navegación a Contact Us |
| `ContactUsPage` | `src/test/java/.../pages/ContactUsPage.java` | Formulario de contacto con `@FindBy` |
| `ContactConfirmationPage` | `src/test/java/.../pages/ContactConfirmationPage.java` | Validación de éxito y retorno a Home |

### Stack Técnico
- **Framework**: Serenity BDD + Cucumber + Selenium WebDriver
- **Patrón**: POM (Page Object Model) con Page Factory (`@FindBy`)
- **Build**: Gradle
- **Lenguaje**: Java 21
- **Configuración**: `serenity.conf` con `webdriver.base.url = https://automationexercise.com`

### Notas de Implementación
- Separar localizadores en clases Page Object usando `@FindBy` y evitar localizadores duplicados en Step Definitions.
- Asegurar el manejo del diálogo de confirmación (`accept`) justo después de Submit.
- Incluir archivo de prueba fijo en recursos del proyecto para evitar dependencias del entorno local.
- Implementar `Scenario Outline` con `<datasetKey>` para desacoplar el flujo de valores literales.
- Resolver todos los valores desde `ContactUsDataProvider` y archivo `.properties`.
- Priorizar assertions funcionales claras para que el reporte Serenity sea entendible en la socialización.

---

## 3. LISTA DE TAREAS

> Checklist accionable. El Orchestrator monitorea el progreso.

### Page Objects &amp; Automatización

#### Implementación
- [ ] Crear archivo de datos: `src/test/resources/test-data/contact-us-data.properties`
- [ ] Implementar `ContactUsDataProvider` para resolución por `datasetKey`
- [ ] Implementar `LandingPage` con métodos de navegación
- [ ] Implementar `ContactUsPage` con localizadores `@FindBy` (Page Factory)
- [ ] Implementar `ContactConfirmationPage` para validación de éxito
- [ ] Crear feature file Gherkin (`Scenario Outline`) para Formulario de Contacto
- [ ] Crear Step Definitions consumiendo `datasetKey`
- [ ] Validar política zero-hardcode (ningún dato funcional en código Java ni Gherkin)

#### Ejecución
- [ ] Ejecutar: `./gradlew clean test aggregate`
- [ ] Verificar reporte Serenity en `target/site/serenity/index.html`
- [ ] Validar test status PASSED
- [ ] Confirmar trazabilidad: Gherkin → Step Definition → Page Object

### QA
- [ ] Validar escenario corresponde a Test Case 6 oficial
- [ ] Confirmar ausencia de código comentado
- [ ] Ejecutar validación estática de hardcodes
- [ ] Verificar estabilidad mínima con 2 ejecuciones
