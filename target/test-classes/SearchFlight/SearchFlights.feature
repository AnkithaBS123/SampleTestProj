Feature: Emirates Flight

Scenario: To Search for the cheapest flight
    Given I navigate to "https://www.emirates.com/"
    When  I Search for flight from "Hyderabad" and to "Dubai" on Date "03  Feb 20"
    Then  it should display the flight results and display the cheapest flight number