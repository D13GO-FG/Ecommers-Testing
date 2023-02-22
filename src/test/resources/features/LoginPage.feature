Feature: Login to SwagLabs

  Background:
    Given User is on SwagLabs page "https://www.saucedemo.com/"

  @ValidCredentials
  Scenario: Login with valid credentials

    When User enters username as "standard_user" and password as "secret_sauce"
    Then User should be able to login successfully and new page open

  @InvalidCredentials
  Scenario Outline: Login with invalid credentials

    When User enters username as "<username>" and password as "<password>"
    Then User should be able to see error message "<errorMessage>"

    Examples:
      | username   | password  | errorMessage                      |
      | standard_user1      | secret_sauce | Epic sadface: Username and password do not match any user in this service               |
      |     | secret_sauce  | Epic sadface: Username is required               |
      | standard_user   |   | Epic sadface: Password is required               |
