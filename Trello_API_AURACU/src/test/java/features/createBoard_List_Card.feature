Feature: Trello Board Tests

  Scenario: Create a board, list, and card
    Given a Trello board exists
    When a list is created in the board
    Then a card is created in the board
