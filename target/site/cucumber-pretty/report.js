$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("SearchFlight/SearchFlights.feature");
formatter.feature({
  "line": 1,
  "name": "Emirates Flight",
  "description": "",
  "id": "emirates-flight",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "To Search for the cheapest flight",
  "description": "",
  "id": "emirates-flight;to-search-for-the-cheapest-flight",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I navigate to \"https://www.emirates.com/\"",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I Search for flight from \"Hyderabad\" and to \"Dubai\" on Date \"03  Feb 20\"",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "it should display the flight results and display the cheapest flight number",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "https://www.emirates.com/",
      "offset": 15
    }
  ],
  "location": "FlightSearchResults.iNavigateTo(String)"
});
formatter.result({
  "duration": 11748187895,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Hyderabad",
      "offset": 26
    },
    {
      "val": "Dubai",
      "offset": 45
    },
    {
      "val": "03  Feb 20",
      "offset": 61
    }
  ],
  "location": "FlightSearchResults.iSearchForFlightFromAndToOnDate(String,String,String)"
});
formatter.result({
  "duration": 150513005208,
  "status": "passed"
});
formatter.match({
  "location": "FlightSearchResults.itShouldDisplayTheFlightResultsAndDisplayTheCheapestFlightNumber()"
});
formatter.result({
  "duration": 5785961844,
  "status": "passed"
});
});