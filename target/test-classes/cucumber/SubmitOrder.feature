@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

  Background: 
  Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test for Submitting the Oder
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." messgae is displayed on confirmation

    Examples:    
      | name                    |     password       |   productName     |
      | arar@gmail.com          |     Amera1919@     |   IPHONE 13 PRO   |
   
