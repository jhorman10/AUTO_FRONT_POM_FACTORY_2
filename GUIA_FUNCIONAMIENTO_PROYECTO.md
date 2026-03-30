# Guia de Funcionamiento - AUTO_FRONT_POM_FACTORY

## 1) Que es este proyecto

Este proyecto automatiza una prueba E2E del sitio Automation Exercise usando el patron POM (Page Object Model) con Serenity BDD, Cucumber y Selenium.

Escenario automatizado actual:
- Test Case 6: Formulario de Contacto

Objetivo practico:
- Abrir la web
- Ir a Contacto
- Llenar formulario con datos externos
- Adjuntar archivo
- Enviar y aceptar alerta
- Validar mensaje de exito
- Volver al Home

---

## 2) Como esta organizado (carpeta por carpeta)

## Carpeta raiz

Archivos principales:
- `build.gradle`: define dependencias y tareas de ejecucion.
- `settings.gradle`: define nombre del proyecto Gradle.
- `serenity.conf`: configura navegador, URL base y reporte Serenity.
- `gradlew` y `gradlew.bat`: wrappers para ejecutar Gradle sin instalarlo manualmente.
- `README.md`: guia de uso del proyecto.

Carpetas principales:
- `.github/`: workflow ASDD (requerimientos, specs, instrucciones de agentes).
- `src/`: codigo fuente de pruebas.
- `build/`: salida de compilacion y resultados de tests (generado).
- `target/site/serenity/`: reporte HTML de Serenity (generado).
- `gradle/wrapper/`: binarios/config del wrapper de Gradle.

---

## Carpeta `.github/`

Uso funcional:
- Gestion de proceso ASDD (de requerimiento a especificacion y ejecucion).

Subcarpetas importantes:
- `.github/requirements/`: requerimientos funcionales en lenguaje negocio.
- `.github/specs/`: especificaciones tecnicas por feature.
- `.github/instructions/`: reglas de implementacion por tipo de archivo.
- `.github/skills/` y `.github/agents/`: definiciones para agentes de trabajo.

Archivo clave actual:
- `.github/specs/front-pom-page-factory-contact-us.spec.md` (estado IMPLEMENTED).

---

## Carpeta `src/test/`

Aqui vive toda la automatizacion.

### `src/test/java/com/automationexercise/front/pom/factory/config/`

Archivo:
- `TestConstants.java`

Responsabilidad:
- Centraliza strings reutilizables para evitar hardcode disperso.

Secciones internas:
- `Url`: URL base.
- `Data`: claves de archivo de datos.
- `Paths`: rutas de feature y glue de Cucumber.
- `Errors`: textos de errores tecnicos.
- `Steps`: frases de pasos Gherkin usadas por Step Definitions.

---

### `src/test/java/com/automationexercise/front/pom/factory/model/`

Archivo:
- `ContactUsFormData.java`

Responsabilidad:
- DTO inmutable con los datos del formulario de contacto.

Campos:
- `name`, `email`, `subject`, `message`, `fileName`.

Metodos (funcion por funcion):
- `ContactUsFormData(...)`: constructor que recibe todos los campos.
- `getName()`: retorna nombre.
- `getEmail()`: retorna email.
- `getSubject()`: retorna asunto.
- `getMessage()`: retorna mensaje.
- `getFileName()`: retorna ruta relativa del archivo a adjuntar.

---

### `src/test/java/com/automationexercise/front/pom/factory/data/`

Archivo:
- `ContactUsDataProvider.java`

Responsabilidad:
- Leer dataset desde properties y entregarlo al flujo como objetos tipados.

Metodos (funcion por funcion):
- `ContactUsDataProvider()`: carga el archivo `test-data/contact-us-data.properties` a memoria.
- `getFormData(String datasetKey)`: arma y devuelve `ContactUsFormData` usando un dataset.
- `expectedSuccessMessage(String datasetKey)`: retorna el mensaje esperado de exito para ese dataset.
- `getValue(String datasetKey, String key)`: (privado) construye la key completa `dataset.key`, valida existencia y devuelve el valor.

Por que existe esta capa:
- Separa datos de negocio del codigo de navegacion.
- Facilita cambiar datos sin tocar clases de pasos/pages.

---

### `src/test/java/com/automationexercise/front/pom/factory/pages/`

Estas son las clases POM (una pagina, una responsabilidad).

Archivo 1:
- `LandingPage.java`

Responsabilidad:
- Acciones y validaciones de la home.

Metodos:
- `openHomePage()`: abre la URL base definida por Serenity.
- `shouldBeVisible()`: espera y valida que el logo este visible.
- `openContactUs()`: hace click en el boton/enlace de Contacto.

Archivo 2:
- `ContactUsPage.java`

Responsabilidad:
- Encapsula el formulario de contacto.

Metodos:
- `shouldBeVisible()`: valida titulo "GET IN TOUCH" visible.
- `fillForm(ContactUsFormData formData)`: completa nombre, email, subject y message.
- `uploadFile(String fileAbsolutePath)`: adjunta archivo en input upload.
- `submitForm()`: click en Submit.
- `acceptConfirmationAlert()`: acepta alerta JS del navegador.

Archivo 3:
- `ContactConfirmationPage.java`

Responsabilidad:
- Validaciones y acciones de la pantalla posterior al submit.

Metodos:
- `successMessage()`: retorna texto del banner de exito.
- `returnToHome()`: click en boton Home.
- `homeIsVisible()`: valida que la home se visualiza nuevamente.

Nota de arquitectura POM:
- Los Page Objects no contienen asserts de negocio complejos.
- Solo exponen acciones/lecturas de UI.

---

### `src/test/java/com/automationexercise/front/pom/factory/steps/`

Archivo:
- `ContactUsFlowSteps.java`

Responsabilidad:
- Orquestar el flujo funcional combinando Page Objects + datos.
- Es la "capa de caso de uso" para Cucumber.

Metodos:
- `openHome()`: abre home y valida visibilidad.
- `openContactForm()`: navega a Contact Us y valida pagina.
- `fillContactForm(String datasetKey)`: toma dataset y llena formulario.
- `uploadAttachment(String datasetKey)`: resuelve ruta de archivo y lo adjunta.
- `submitAndAcceptAlert()`: envia formulario y acepta alerta.
- `validateSuccessMessage(String datasetKey)`: compara mensaje real vs esperado.
- `returnHome()`: vuelve al home y valida visibilidad.
- `resolveResourcePath(String resourcePath)`: (privado) convierte ruta de recurso en ruta absoluta del sistema operativo.

---

### `src/test/java/com/automationexercise/front/pom/factory/definitions/`

Archivo:
- `ContactUsStepDefinitions.java`

Responsabilidad:
- Mapear frases Gherkin a llamadas de `ContactUsFlowSteps`.

Metodos:
- `theUserIsOnTheAutomationExerciseHomePage()`: ejecuta `openHome()`.
- `theUserOpensContactUsForm()`: ejecuta `openContactForm()`.
- `theUserFillsTheContactFormUsingDataset(String datasetKey)`: ejecuta `fillContactForm(...)`.
- `theUserUploadsFileUsingDataset(String datasetKey)`: ejecuta `uploadAttachment(...)`.
- `theUserSubmitsTheContactFormAndAcceptsTheConfirmationAlert()`: ejecuta `submitAndAcceptAlert()`.
- `theSuccessMessageShouldMatchDataset(String datasetKey)`: ejecuta `validateSuccessMessage(...)`.
- `theUserShouldBeAbleToReturnToTheHomePage()`: ejecuta `returnHome()`.

Para un dev no QA:
- Piensalo como un "controller" que traduce texto de negocio (Gherkin) a metodos Java.

---

### `src/test/java/com/automationexercise/front/pom/factory/runners/`

Archivo:
- `ContactUsCucumberTest.java`

Responsabilidad:
- Punto de entrada de ejecucion para Cucumber + Serenity.

Configuracion:
- `features`: ruta del archivo `.feature` a ejecutar.
- `glue`: paquete donde estan las Step Definitions.
- `snippets`: estilo de nombres para snippets (camelCase).

---

### `src/test/resources/features/`

Archivo:
- `contact_us/contact_us_form.feature`

Responsabilidad:
- Describe el escenario en lenguaje de negocio en español (Given/When/Then).

Estructura importante:
- Usa `Scenario Outline` + `Examples`.
- Pasa `datasetKey` para no hardcodear datos funcionales en el feature.

---

### `src/test/resources/test-data/`

Archivos:
- `contact-us-data.properties`: datos funcionales por dataset.
- `contact-message.txt`: archivo real para la prueba de upload.

Responsabilidad:
- Fuente unica de datos de negocio reutilizables.

---

## Carpeta `build/` (generada)

Que contiene:
- Clases compiladas de test.
- Reportes unitarios/JUnit.
- Artefactos de ejecucion intermedia.

No se edita manualmente.

---

## Carpeta `target/site/serenity/` (generada)

Que contiene:
- Reporte HTML de Serenity con:
- pasos ejecutados,
- evidencias/snapshots,
- estado pass/fail,
- trazabilidad del escenario.

Archivo principal:
- `target/site/serenity/index.html`

---

## 3) Flujo completo de ejecucion (paso a paso)

1. Runner (`ContactUsCucumberTest`) levanta Cucumber.
2. Cucumber lee el feature `contact_us_form.feature`.
3. Cada paso textual se vincula a `ContactUsStepDefinitions`.
4. Step Definitions delegan en `ContactUsFlowSteps`.
5. Flow Steps pide datos a `ContactUsDataProvider`.
6. DataProvider arma `ContactUsFormData` desde properties.
7. Flow Steps usa Page Objects para interactuar con la UI.
8. Se valida mensaje de exito y retorno a Home.
9. Serenity genera reporte en `target/site/serenity/index.html`.

---

## 4) Como correr el proyecto

Comando recomendado:

```bash
./gradlew clean test aggregate
```

Resultado esperado:
- Test en estado PASSED.
- Reporte Serenity actualizado.

---

## 5) Donde cambiar cosas segun necesidad

Si quieres cambiar datos de prueba:
- Edita `src/test/resources/test-data/contact-us-data.properties`.

Si cambia un selector de UI:
- Edita el locator en el Page Object correspondiente en `pages/`.

Si cambia el flujo funcional:
- Ajusta primero `ContactUsFlowSteps`.
- Si cambia el lenguaje de negocio, ajusta tambien `*.feature` y `StepDefinitions`.

Si agregas otro escenario:
- Nuevo archivo `.feature`.
- Nuevas Step Definitions (o reutilizar existentes).
- Nuevos/metodos Page Objects segun necesidad.
- Actualizar/crear runner.

---

## 6) Resumen para dev no QA

Piensa esta arquitectura como una app por capas:
- `feature` = requerimiento funcional legible.
- `definitions` = adaptador texto->codigo.
- `steps` = logica del caso de uso.
- `pages` = capa de acceso a UI (como repositorio de pantalla).
- `data/model` = capa de datos.
- `runner` = main de ejecucion.

Con esto, el proyecto es mantenible, legible y escalable para agregar nuevos casos E2E sin mezclar responsabilidades.
