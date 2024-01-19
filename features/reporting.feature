Feature: Report Generation

  Scenario: Generate and return a manager report successfully
    Given a manager report generation request and some payments in the repository
    Then a GetPaymentsRequest event is sent to the PaymentService
    Then a list of payments is received
    Then a ManagerReport is generated and is returned to the manager

  Scenario: Generate and return a customer report successfully
    Given a customer report generation request and some payments in the repository
    Then a GetPaymentsRequest event is sent to the PaymentService
    Then a list of payments is received
    Then a CustomerReport is generated and is returned to the customer

  Scenario: Generate and return a merchant report successfully
    Given a merchant report generation request and some payments in the repository
    Then a GetPaymentsRequest event is sent to the PaymentService
    Then a list of payments is received
    Then a MerchantReport is generated and is returned to the merchant