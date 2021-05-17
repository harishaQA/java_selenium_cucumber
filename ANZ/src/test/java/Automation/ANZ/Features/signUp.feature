Feature: Sign Up modal

  Scenario: Verify if Sign Up functionality works
    Given I am on the landing page
    And I click on "SignIn" button
    Then I am redirected to "SignIn" page
    When I enter the emailId to create account
    And I click on "Create an account" button
    Then I am redirected to "Create an account" page
    When I fill details on personal Information Section
    And I click on "Register" button
    Then I verify first name and last name is displayed on header
