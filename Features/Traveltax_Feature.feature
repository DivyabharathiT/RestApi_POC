Feature: To validate CRUD operations

@check
@Register @POST 
Scenario: User registers an account
Given I have an traveltax register API
When I post the register payload request
Then I have created the user
And I am validating the register response

@check
@Createpassword @POST
Scenario: User creates a new password
Given I have an traveltax createpassword API
When I post the create password payload request
Then I have created new password
And I am validating the create password response

@check
@Login @POST
Scenario: User Login with valid credentials
Given I have an traveltax login API
When I post the login payload request
Then I have logged in
And I am validating the login response

@check
@createtravelrecord @PUT
Scenario: User creates a travel record
Given I have an traveltax createtravel API
When I request to create a travel info
Then I check the created travel info
And I am validating the created travel response

@check
@getrecords @GET
Scenario: User gets the travel record
Given I have an traveltax getrecord API
When I request to get all travel records
Then I check the records
And I am validating the travel records response

@check
@deletetravelrecord @DELETE
Scenario: User delete the travel record
Given I have an traveltax delete API
When I request to delete a record
Then I check the deleted record
And I am validating the deleted record response

# @check
#Scenario: User failed to create account
#Given I have an traveltax register API
#When I post the register payload request with already existing account
#Then I log the response
#And I am validating the register response for failure

@check
Scenario: Report cooldown
Given Report flush