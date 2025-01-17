
@tag
Feature: Purchase Order from Ecommerce website

Background:
Given User lauches the application

  @Regression
  Scenario Outline: Submit Order
    Then User logs in with <userName> and <password>
    When User adds <productName> to cart and goes to cart page
    Then User verifies the <productName> in cart and clicks on checkout and submits the order
    And Verifies "Thankyou for the order." message in confirmation page

    Examples: 
      | userName  				| password 		| productName  	|
      | swaraj@gmail.com 	| Swaraj@123 	| IPHONE 13 PRO |
