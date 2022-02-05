Feature: Application Login

  Scenario: Login with valid credentials
    Given Open any Browser
    And Navigate to Login page
    When User performs login with username as "testautomation999@gmail.com" and password as "test@1234" into the fields
    Then Verify user is able to successfully login