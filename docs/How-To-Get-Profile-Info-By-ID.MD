### Get the user by ID

Returns an information about the user by ID

#### Request

```http request
GET /users/user_id
```

Query parameters:
- user_id - user's id to search with

#### Response:

Status: 
- 200 OK - information about the user has been returned successfully
- 204 No Content - if user by provided ID does not exist

Response body:
- id - the user's id, equal to provided in query parameter
- context_uri - the user's context uri, in the format: sonata:user: + the user's id
- display_name - display name of the user's profile. May be nullable
- email - the user's email. The email is verified 
- birthdate - the user's birthdate, in format yyyy-MM-dd. 
- gender - the user's gender, one of: MALE, FEMALE, NONE
- country - country code where the user was signed up. May be null