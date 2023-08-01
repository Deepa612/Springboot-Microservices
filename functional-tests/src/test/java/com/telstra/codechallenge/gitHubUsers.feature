# See
# https://github.com/intuit/karate#syntax-guide
# for how to write feature scenarios
Feature: As an api user I want to retrieve 0 followers oldest accounts

  Scenario: Get 0 followers oldest accounts
    Given url microserviceUrl
    And path 'v1/zero-followers/userAccounts/2'
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    # see https://github.com/intuit/karate#schema-validation
    # Define the required schema
    * def userAccountSchema = {  id : '#number',login : '#string',html_url: '#string'}
    # The response should have an array of user objects
    And match response == '#[] userAccountSchema'
