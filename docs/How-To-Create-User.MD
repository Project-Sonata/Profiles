### Create user

Allows to create a user with basic information about him

#### Request

```http request
POST /users/user_id
Content-Type: application/json
```

Http body:

```json
{
  "sonata_id": "4A2YFhVf1CosFA443ibQhH",
  "username": "mikunakano",
  "birthdate": "2004-05-20",
  "email": "miku.nakano@example.com",
  "gender": "FEMALE",
  "country": "JP"
}
```

- sonata_id - REQUIRED. String. ID that assigned for user and which will be used to get the info from different endpoints.
  NOTE: this ID MUST be the same in every microservice and MUST be immutable as it used as user identifier.  If user ID already exist, then error will be returned. Sonata ID should be generated only once in microservice that register users!


- username - REQUIRED. String. the user's name, note that username and display_name are different and not interchangeable!


- birthdate - REQUIRED. String. the user's birthdate, in format - 'yyyy-MM-dd'.


- email - REQUIRED. String. the user's email address, must be verified,


- gender - REQUIRED. Enum. the user's gender. One of: MALE, FEMALE, NONE


- country - REQUIRED. String. the user's country in [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) format,

#### Response

- 201 Created - user has been created successfully, a Location header will be included in response header with URI with created resource
- 400 BAD REQUEST - invalid body, request headers, etc.