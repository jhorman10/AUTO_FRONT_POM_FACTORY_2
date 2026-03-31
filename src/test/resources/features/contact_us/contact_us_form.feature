Feature: Formulario de Contacto
  Como visitante
  Quiero que el formulario de contacto permita enviar feedback
  Para recibir confirmación de envío exitoso

  Scenario Outline: Envío exitoso del formulario de contacto con dataset reutilizable
    Given la página de inicio de Automation Exercise está abierta
    And el formulario de contacto está visible
    And el formulario contiene los datos del dataset "<datasetKey>"
    And el formulario incluye un archivo adjunto definido en el dataset "<datasetKey>"
    When se procesa el envío del formulario de contacto
    Then se mostrará el mensaje de éxito definido en el dataset "<datasetKey>"
    And la navegación regresa a la página de inicio

    Examples:
      | datasetKey         |
      | contact_us_default |
