package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import constants.Constants;

public class Board_List_Card {

    @Given("a Trello board exists")
    public void createBoardAndGetId() {
        Response response = given()
                .baseUri("https://trello.com")
                .header("Content-Type", "application/json")
                .when()
                .post("/1/boards?key=" + Constants.KEY + "&name=" + Constants.BOARD_NAME + "&token=" + Constants.TOKEN + "&defaultLists=false")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();

        Constants.boardId = response.path("id");
    }

    @When("a list is created in the board")
    public void createListInBoardAndGetListId() {
        Response response = given()
                .baseUri("https://trello.com")
                .header("Content-Type", "application/json")
                .when()
                .post("/1/lists?name=Done&key=" + Constants.KEY + "&token=" + Constants.TOKEN + "&idBoard=" + Constants.boardId)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();

        Constants.listId = response.path("id");
    }

    @Then("a card is created in the board")
    public void createCardInBoard() {
        given()
                .baseUri("https://trello.com")
                .header("Content-Type", "application/json")
                .when()
                .post("/1/cards?idList=" + Constants.listId + "&key=" + Constants.KEY + "&token=" + Constants.TOKEN + "&name=Task1")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }
}
