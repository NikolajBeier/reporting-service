Feature: Report Generation

  Scenario: Generate and return a manager report successfully
    When a manager report generation request is received
    Then a "GetPaymentsRequest" event is sent to the PaymentService
    Then a list of payments is received
    Then a ManagerReport is generated
    And returned to the manager

  Scenario: Generate and return a customer report successfully
    When a customer report generation request is received
    Then a "GetPaymentsRequest" event is sent to the PaymentService
    Then a list of payments is received
    Then a CustomerReport is generated
    And returned to the customer

  Scenario: Generate and return a merchant report successfully
    When a merchant report generation request is received
    Then a "GetPaymentsRequest" event is sent to the PaymentService
    Then a list of payments is received
    Then a MerchantReport is generated
    And returned to the merchant



