package StepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import Pojo.CategoriesList;
import Pojo.CreateCategories;
import Pojo.CreateCategoryresp;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.TestDataBuild;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinations extends Utils {
	
	static ArrayList<Integer> ids = new ArrayList<>();
	Response responsecat;
	Response getAllcat;
	Response CreatedProductResponse;
	Response DelResp;
	RequestSpecification CreateProduct;
	CreateCategoryresp CreateCatresp;
	List<CategoriesList> getCategory;
	RequestSpecification req;
	RequestSpecification CreateCat;
	List<Response> DelResps = new ArrayList<>();  // List to store delete responses
	TestDataBuild CreatePyload = new TestDataBuild();
	static int Catid;



@Given("when user request for all the Categores")
public void when_user_request_for_all_the_categores() throws IOException {
	
	 req = given().spec(requestSpecification());  
}

@When("user calls {string} with GET request")
public void user_calls_with_get_request(String string) {
	
	responsecat =req.when().get("/categories")
                 .then().log().all().extract().response();
	
	getCategory = responsecat.jsonPath().getList(".", CategoriesList.class);
	
	
	 for(int j=0;j<getCategory.size();j++) {
	
		if(getCategory.get(j).getName().equals("Book")) {
			
			ids.add(getCategory.get(j).getId());
		}
	 }
		System.out.println(ids);//Here its printing correctly
    
}

@Then("API call is success with status code {int}")
public void api_call_is_success_with_status_code(Integer int1) {
	
	assertEquals(responsecat.getStatusCode(),200);
	System.out.println(ids);//Here its printing correctly	
}	




////////////////////////	
	
@Given("add Category Payload")
public void add_category_payload() throws IOException {
	
		
	     System.out.println(ids);//Here its printing correctly
		 CreateCat = given().spec(requestSpecification()).body(CreatePyload.createCategoryPayload());
	
}
@When("user calss {string} with Post request")
public void user_calss_with_post_request(String string) {
	
	getAllcat =CreateCat.when().post("/categories/").then().log().all().extract().response();
	
	CreateCatresp =getAllcat.as(CreateCategoryresp.class);
	
    System.out.println(CreateCatresp.getName());
    Catid= CreateCatresp.getId();
    String actualName =getAllcat.jsonPath().getString("name");
    
    
	 	

}
@Then("API call is sucess with status code {int}")
public void api_call_is_sucess_with_status_code(Integer int1) {
	
	assertEquals(getAllcat.getStatusCode(),201);

}


@Then("{string} {string} in the response is String and {string} is an integer")
public void in_the_response_is_string_and_is_an_integer(String ExpectedName, String ExpectedImage, String ExpectedId) {
	   String actualName =getAllcat.jsonPath().getString("name");
	   String actualIamge =getAllcat.jsonPath().getString("image");
	   Integer actualId =getAllcat.jsonPath().getInt("id");
	   assertTrue("Name should be a String", actualName instanceof String);
	   assertTrue("Image should be a String", actualIamge instanceof String);
	   assertTrue("ID should be an Integer", actualId instanceof Integer);
    
}
//////////////////////////
@Given("the list of category IDs to delete")
public void the_list_of_category_i_ds_to_delete() throws InterruptedException, IOException {
	
	System.out.println(ids);
	
	
	
for(int i=0;i<ids.size();i++) {
		
	DelResp = given().spec(requestSpecification()).pathParam("id",ids.get(i))
				 .when().delete("/categories/{id}")
				 .then().log().all().extract().response();
		 DelResps.add(DelResp);  // Store each response
         System.out.println("Deleted Category ID: " + ids.get(i) + " Status Code: " + DelResp.getStatusCode());
		}
	
}
@Then("the categories should be deleted successfully")
public void the_categories_should_be_deleted_successfully() {
	
	
         assertEquals(DelResp.getStatusCode(), 200);  // Or 204 if your API returns 204 for successful deletions
      
}

@Given("Add product payload with {string} {int} {string}")
public void add_product_payload_with(String title, int  price, String description) throws IOException {
	
	CreateProduct= given().spec(requestSpecification()).body(CreatePyload.createProductPayload(Catid, title, price, description));
    
}

@When("user calls {string} with Post request")
public void user_calls_with_post_request(String string) {
	
	CreatedProductResponse = CreateProduct.when().when().post("/products/").then().log().all().extract().response();
    
}

@Then("API call is sucess for cration with status code {int}")
public void api_call_is_sucess_for_cration_with_status_code(Integer int1) {
	assertEquals(CreatedProductResponse.getStatusCode(),201);


}
}
