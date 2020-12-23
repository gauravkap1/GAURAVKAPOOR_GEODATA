Feature: GeoData Login Feature

  Scenario: Check Kickout functionality test case
    When user enters username and password and submits
      | username                         | password  |
      | gauravkapoor@cssoftsolutions.com | Test@2020 |
    Then user checks for kickout message
    Then user verifies title of main page

  Scenario: Check Distressed Property Search
    When user enters username and password and submits
      | username                         | password  |
      | gauravkapoor@cssoftsolutions.com | Test@2020 |
    Then user checks for kickout message
    Then user verifies title of main page
    When user clicks Distressed Properties search tab 
    Then user clicks on search button

@RegressionTest  @MapSearch
    Scenario: Check Map Property Search
    When user enters username and password and submits
      | username                         | password  |
      | gauravkapoor@cssoftsolutions.com | Test@2020 |
    Then user checks for kickout message
    Then user verifies title of main page
    When user clicks Map search tab 
    Then user clicks on search button at map search