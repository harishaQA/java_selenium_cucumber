Feature: Add the Product to cart and checkout

  Scenario Outline: Login As a user. add the product to cart and checkout till payment. verify product added at checkout
    Given I am on the landing page
    And I click on "SignIn" button
    Then I am redirected to "SignIn" page
    When I login as a user with "<username>" and "<password>"
    And I go to "Home" page
    When I add "First" product to cart
    And I click on "Proceed to checkout" button
    Then I am redirected to "Checkout" page
    And I click on "Proceed to checkout" button on "Summary" section of checkout page
    And I click on "Proceed to checkout" button on "Address" section of checkout page
    And I click on "Proceed to checkout" button on "Shipping" section of checkout page
    Then I am redirected to "Payment Section of Checkout" page
    Then I verify product added

Examples:

|username|password| 

|12test34@test.com|123456|