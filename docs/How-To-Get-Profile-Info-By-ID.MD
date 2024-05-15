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

Response body:
- id - the user's id, equal to provided in query parameter
- display_name - display name of the user's profile. May be nullable
- email - user's email. The email is verified 