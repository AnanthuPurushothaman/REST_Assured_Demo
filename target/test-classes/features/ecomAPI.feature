Feature: Validating Ecom API;s
@GetAllCategories
Scenario: Get all Categories
      Given when user request for all the Categores
      When user calls "GetCategoryAPI" with GET request
      Then API call is success with status code 200 
@CreateNewCategory    
Scenario: Verify Create category
			Given add Category Payload
			When user calss "AddCategoryAPI" with Post request
			Then API call is sucess with status code 201   
			Then "name" "image" in the response is String and "id" is an integer
			     
@DeleteCategories     
 Scenario: Delete categories
     Given the list of category IDs to delete
     Then API call is success with status code 200 
@CreateProduct    
Scenario Outline: Create Product               
     Given Add product payload with "<title>" <price> "<description>"
     When user calls "AddProduct" with Post request
     Then API call is sucess for cration with status code 201   

Examples:
      | title       | price | description                  |
      | Product A   | 100   | This is the first product    |
      | Product B   | 150   | This is the second product   |
      | Product C   | 200   | This is the third product    |