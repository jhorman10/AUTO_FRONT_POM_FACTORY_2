# AUTO_FRONT_POM_FACTORY

**Automatización de Tests Frontend con Page Object Model (POM) en Serenity BDD**

Suite de automatización end-to-end (E2E) para validar flujos críticos en [Automation Exercise](https://automationexercise.com) utilizando patrón POM (Page Object Model), Page Factory, Gherkin + Cucumber y reporte legible con Serenity BDD.

```
Test Case 6: Formulario de Contacto
├── Navegación → Landing Page → Contacto
├── Diligenciamiento de formulario (nombre, email, subjeto, mensaje)
├── Carga de archivo adjunto
├── Envío + confirmación del navegador
└── Validación de éxito + retorno a Home
```

---

## 📋 Stack Técnico

| Componente | Versión | Descripción |
|------------|---------|------------|
| **Java** | 21 | OpenJDK 21.0.10 |
| **Gradle** | 8.6 | Build tool con Wrapper |
| **Serenity BDD** | 4.2.34 | Framework de automatización |
| **Cucumber** | Integrado | Gherkin feature files |
| **Selenium WebDriver** | 4.10.0 | WebDriver para navegación |
| **JUnit** | 4.13.2 | Test runner |
| **Chrome Driver** | Auto-download | Navegador para ejecución |

---

## 🚀 Guía de Instalación y Ejecución

### Requisitos Previos (Todos los SO)

**Necesarios:**
- ✅ **Java 21+**: Descargar de [https://adoptium.net/](https://adoptium.net/)
- ✅ **Git**: Descargar de [https://git-scm.com/](https://git-scm.com/)
- ✅ **Conexión a Internet** (para descargar dependencias)

**Opcionales:**
- IDE: IntelliJ IDEA, VS Code, Eclipse

**Verificar instalación:**

```bash
# En Terminal (macOS/Linux) o PowerShell/CMD (Windows)
java --version
git --version
```

Debe mostrar:
```
openjdk 21.x.x
git version 2.x+
```

---

### 1️⃣ Clonar el Proyecto

```bash
# Clonar repositorio
git clone <tu-repo-url>
cd AUTO_FRONT_POM_FACTORY

# Verificar estructura (macOS/Linux)
ls -la
# ó en Windows PowerShell
dir
```

Debe mostrar: `.github/`, `src/`, `gradle/`, `build.gradle`, etc.

---

### 2️⃣ Verificar Gradle Wrapper

```bash
# Linux / macOS
./gradlew --version

# Windows PowerShell
./gradlew.bat --version

# Windows CMD
gradlew.bat --version
```

Debe mostrar: `Gradle 8.6`

Si no funciona, ejecutar un comando de test (ver siguiente sección) para que Gradle descargue automáticamente.

---

## 🧪 Ejecutar Tests

### ⭐ Opción 1: Ejecución Completa (Recomendada)

**Linux / macOS:**
```bash
./gradlew clean test aggregate
```

**Windows PowerShell:**
```powershell
./gradlew.bat clean test aggregate
```

**Windows CMD:**
```cmd
gradlew.bat clean test aggregate
```

**Qué hace:**
- `clean` — Limpia compilaciones previas
- `test` — Ejecuta todos los tests (Cucumber + JUnit)
- `aggregate` — Genera reporte Serenity HTML

**Tiempo esperado:** 20-30 segundos

**Salida esperada:**
```
BUILD SUCCESSFUL in 25s
6 actionable tasks: 6 executed

Formulario de Contacto.Enviar formulario de contacto exitosamente con dataset reutilizable PASSED
```

---

### Opción 2: Solo Tests (sin Reporte)

**Linux / macOS:**
```bash
./gradlew test
```

**Windows:**
```bash
./gradlew.bat test
```

---

### Opción 3: Compilar sin Ejecutar

**Linux / macOS:**
```bash
./gradlew build
```

**Windows:**
```bash
./gradlew.bat build
```

---

### Opción 4: Limpiar Caché (si hay problemas)

**Linux / macOS:**
```bash
./gradlew clean
rm -rf build target .gradle
./gradlew clean test aggregate
```

**Windows PowerShell:**
```powershell
./gradlew.bat clean
Remove-Item -Recurse -Force build, target, .gradle
./gradlew.bat clean test aggregate
```

---

## 📊 Ver Reportes

### 🔴 Reporte HTML Interactivo (Serenity)

**Ubicación:** `target/site/serenity/index.html`

**Abrir en navegador:**

**Linux:**
```bash
xdg-open target/site/serenity/index.html
```

**macOS:**
```bash
open target/site/serenity/index.html
```

**Windows PowerShell:**
```powershell
Start-Process "target/site/serenity/index.html"
```

**Windows CMD:**
```cmd
start target\site\serenity\index.html
```

**Contenido del reporte:**
- ✅ Status de cada test (PASSED / FAILED)
- 📸 Screenshots de cada paso
- ⏱️ Duración de ejecución
- 📝 Trazabilidad: Gherkin → Steps → Page Objects
- 📊 Métricas de cobertura

---

### 📋 Otros Reportes

**Reporte XML (JUnit - compatible con CI/CD):**
```bash
# Linux / macOS
cat target/surefire-reports/*.xml

# Windows PowerShell
Get-Content target\surefire-reports\*.xml

# Windows CMD
type target\surefire-reports\*.xml
```

**Logs de compilación:**
```bash
# Linux / macOS
tail -f build/gradle.log

# Windows PowerShell
Get-Content -Wait build\gradle.log
```

---

## 📁 Estructura del Proyecto

```
AUTO_FRONT_POM_FACTORY/
│
├── .github/
│   ├── specs/
│   │   └── front-pom-page-factory-contact-us.spec.md  ← Especificación técnica
│   ├── requirements/
│   │   └── front-pom-page-factory-contact-us.md       ← Requerimiento
│   └── copilot-instructions.md
│
├── src/test/
│   ├── java/com/automationexercise/front/pom/factory/
│   │   ├── pages/
│   │   │   ├── LandingPage.java              ← Page Object
│   │   │   ├── ContactUsPage.java            ← Page Object
│   │   │   └── ContactConfirmationPage.java  ← Page Object
│   │   │
│   │   ├── steps/
│   │   │   └── ContactUsFlowSteps.java       ← Steps reutilizables
│   │   │
│   │   ├── definitions/
│   │   │   └── ContactUsStepDefinitions.java ← Mapeo Gherkin a Steps
│   │   │
│   │   ├── data/
│   │   │   └── ContactUsDataProvider.java    ← Proveedor de datos
│   │   │
│   │   ├── model/
│   │   │   └── ContactUsFormData.java        ← Modelo DTO
│   │   │
│   │   └── runners/
│   │       └── ContactUsCucumberTest.java    ← Runner de Cucumber
│   │
│   └── resources/
│       ├── features/contact_us/
│       │   └── contact_us_form.feature       ← Escenarios Gherkin
│       │
│       └── test-data/
│           ├── contact-us-data.properties    ← Datos externalizados
│           └── contact-message.txt           ← Archivo para upload
│
├── build.gradle                               ← Configuración Gradle
├── serenity.conf                             ← Configuración Serenity BDD
├── settings.gradle
│
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       ├── gradle-wrapper.properties
│       ├── gradlew                           ← Ejecutable Linux/macOS
│       └── gradlew.bat                       ← Ejecutable Windows
│
└── README.md (este archivo)
```

---

## 🔧 Troubleshooting

### ❌ Error: "No se encuentra Java"

**Verificar instalación:**
```bash
java -version
```

**Solución:**
1. Descargar Java 21 de [https://adoptium.net/installation/](https://adoptium.net/installation/)
2. Instalar siguiendo las instrucciones del sitio
3. Reiniciar la terminal
4. Verificar nuevamente: `java -version`

---

### ❌ Error: "Permission denied: ./gradlew"

**Solución (Linux/macOS):**
```bash
chmod +x ./gradlew
chmod +x ./gradle/wrapper/gradlew
./gradlew clean test aggregate
```

**Windows:** No aplica (usar `./gradlew.bat`)

---

### ❌ Error: "Chrome driver not found"

**Solución:**
```bash
# Gradle descargará automáticamente el driver compatible
./gradlew clean test aggregate

# Si persiste, limpiar caché:
# Linux / macOS
rm -rf ~/.gradle/caches

# Windows PowerShell
Remove-Item -Recurse -Force $env:USERPROFILE\.gradle\caches

./gradlew clean test aggregate
```

---

### ❌ Error: "Port 9515 already in use"

**Matar procesos previos:**

**Linux / macOS:**
```bash
lsof -i :9515 | awk 'NR!=1 {print $2}' | xargs kill -9
```

**Windows PowerShell:**
```powershell
$process = Get-Process | Where-Object {$_.Port -eq 9515}
$process | Stop-Process -Force
```

**Windows CMD:**
```cmd
netstat -ano | findstr :9515
taskkill /PID <PID> /F
```

---

### ❌ Error: "Connection timed out" o "Cannot resolve host"

**Causas comunes:**
- Sin conexión a Internet
- Firewall/proxy corporativo bloqueando descargas
- DNS inestable

**Soluciones:**
1. Verificar conexión: `ping google.com`
2. Reintentar: `./gradlew clean test aggregate`
3. Si está en red corporativa, configurar proxy en Gradle:
   ```bash
   # Crear ~/.gradle/gradle.properties
   systemProp.http.proxyHost=proxy-corporativo.com
   systemProp.http.proxyPort=8080
   systemProp.https.proxyHost=proxy-corporativo.com
   systemProp.https.proxyPort=8080
   ```

---

## 📝 Archivos Clave

### `serenity.conf`
Configuración global de Serenity BDD:
```properties
webdriver.driver = "chrome"
webdriver.base.url = "https://automationexercise.com"
serenity.take.screenshots = FOR_EACH_ACTION
webdriver.chrome.switches = "--start-maximized"
```

### `src/test/resources/test-data/contact-us-data.properties`
Datos externalizados (Política zero-hardcode):
```properties
contact_us_default.name=Automation User
contact_us_default.email=automation.user@example.com
contact_us_default.subject=Automation contact request
contact_us_default.message=Please confirm this test flow is working.
contact_us_default.filePath=test-data/contact-message.txt
contact_us_default.expectedSuccessMessage=Success! Your details have been submitted successfully.
```

### `src/test/resources/features/contact_us/contact_us_form.feature`
Escenario Gherkin (data-driven):
```gherkin
Feature: Formulario de Contacto
  Scenario Outline: Enviar formulario de contacto exitosamente con dataset reutilizable
    Given el usuario esta en la pagina de inicio de Automation Exercise
    When el usuario abre el formulario de contacto
    And el usuario completa el formulario de contacto usando dataset "<datasetKey>"
    And el usuario adjunta archivo usando dataset "<datasetKey>"
    And el usuario envia el formulario de contacto y acepta la alerta de confirmacion
    Then el mensaje de exito debe coincidir con el dataset "<datasetKey>"
    And el usuario debe poder volver a la pagina de inicio

    Examples:
      | datasetKey         |
      | contact_us_default |
```

---

## 🎯 Casos de Uso Comunes

### Ejecutar en CI/CD (GitHub Actions, Jenkins)

**GitHub Actions (`.github/workflows/tests.yml`):**
```yaml
name: E2E Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '21'
      - run: ./gradlew clean test aggregate
      - name: Upload Serenity Report
        if: always()
        uses: actions/upload-artifact@v3
        with:
          name: serenity-report
          path: target/site/serenity/
```

---

### Ejecutar solo un escenario específico

**Linux / macOS:**
```bash
./gradlew test -Dcucumber.filter.tags=@contact-us
./gradlew test -Dcucumber.filter.name="Enviar formulario de contacto exitosamente con dataset reutilizable"
```

**Windows:**
```bash
./gradlew.bat test -Dcucumber.filter.tags=@contact-us
./gradlew.bat test -Dcucumber.filter.name="Enviar formulario de contacto exitosamente con dataset reutilizable"
```

---

### Modo Headless (sin interfaz gráfica)

Editar `serenity.conf` y cambiar:
```properties
webdriver.chrome.switches = --headless;--no-sandbox;--disable-dev-shm-usage;--disable-gpu
```

---

### Ejecutar sin limpiar compilación anterior

```bash
# Linux / macOS
./gradlew test aggregate

# Windows
./gradlew.bat test aggregate
```

---

## 📚 Especificación Técnica Completa

Ver detalles en: [`.github/specs/front-pom-page-factory-contact-us.spec.md`](.github/specs/front-pom-page-factory-contact-us.spec.md)

**Resumen:**
- ✅ **Patrón**: POM (Page Object Model) + Page Factory (`@FindBy`)
- ✅ **Framework**: Serenity BDD + Cucumber
- ✅ **Lenguaje**: Java 21
- ✅ **Datos**: Externalizados (política zero-hardcode)
- ✅ **Reporte**: Serenity HTML interactivo
- ✅ **Trazabilidad**: Gherkin → Steps → Page Objects

---

## ✅ Checklist de Validación

Antes de considerar el proyecto listo:

- [x] Java 21 instalado: `java --version` (muestra 21.x.x)
- [x] Gradle Wrapper funcionando: `./gradlew --version` (muestra 8.6)
- [x] Git instalado: `git --version` (muestra 2.x+)
- [x] `./gradlew clean test aggregate` ejecuta sin errores
- [x] Reporte Serenity generado: `target/site/serenity/index.html`
- [x] Test status: **PASSED**
- [x] Screenshots capturados para cada paso du flujo
- [x] Datos externalizados (zero-hardcodes en código Java)
- [x] Trazabilidad completa: Gherkin → Steps → Page Objects

---

## 📞 Soporte y Enlaces Útiles

**Documentación oficial:**
- [Serenity BDD Docs](https://serenity-bdd.github.io/)
- [Selenium WebDriver Docs](https://www.selenium.dev/documentation/)
- [Cucumber Gherkin Reference](https://cucumber.io/docs/gherkin/)
- [Gradle Wrapper Documentation](https://docs.gradle.org/current/userguide/gradle_wrapper.html)

**Para problemas:**
1. Revisar logs en consola/terminal
2. Revisar `build/` o `target/` para detalles
3. Ejecutar en modo debug: `./gradlew clean test aggregate --debug`
4. Limpiar caché: `rm -rf ~/.gradle/caches` (macOS/Linux) o Eliminar `%USERPROFILE%\.gradle` (Windows)

---

**Última actualización:** 29 de marzo de 2026  
**Versión:** 1.0 - Producción  
**Status:** ✅ READY FOR USE
