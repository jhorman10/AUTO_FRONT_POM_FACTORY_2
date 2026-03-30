Feature: Formulario de Contacto
  Como visitante
  Quiero enviar el formulario de contacto
  Para enviar feedback exitosamente

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
