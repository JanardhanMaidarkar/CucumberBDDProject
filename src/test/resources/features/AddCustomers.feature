Feature: Add New Customer Details
  Background:
    Given User Launch Chrome browser
    When User opens URL "http://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then User can view Dashboard

    @Sanity
  Scenario: Add customer
    When User click on Customers Menu
    And Click on customer Menu Item
    And Click on Add new Button
    Then User can view Add new customer page
    When User enter customer info
    And Click on Save button
    Then User can view confirmation message "The new customer has been added successfully."
    And close browser

  @Regression
  Scenario: Search Customer By Email
    When User click on Customers Menu
    And Click on customer Menu Item
    And Enter customer Email
    When Click on search button
    Then User should found Email in the search table
    And close browser

  @Regression
  Scenario: Search Customer By FirstName
    When User click on Customers Menu
    And Click on customer Menu Item
    And Enter customer FirstName
    And Enter customer LastName
    When Click on search button
    Then User should found Name in the search table
    And close browser