# ::SIMPLE STORE PROJECT::

A group of brave and bold junior programers decided to join forces to create something that they'll remember their times as they started their journey in IT. 
Keyboards were on fire, sleepless nights were sacrificed, stackoverflow jamed due to countless searches for help and inforamtion to give birth to this exquisite, fine app
of a simple app store. The time has come to present what we've created.

## Table of contents

1. [Technologies](#technologies-used)
2. [Features](#features)
3. [Getting Started](#getting-started)
4. [Api](#api)
    - [User Register](#registration-controller)
    - [User Controller](#user-controller)
    - [Group Controller](#group-controller)
    - [Product Controller](#product-controller)
    - [Cart Controller](#cart-controller)
    - [Order Controller](#order-controller)
5. [Contributors](#contributors)

## Technologies Used
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::       (v2.1.18.RELEASE)
 :: Kodilla project::    (v0.0.1 RELEASE)
```
This project is based on Spring Boot in Java 11, secured by Spring Security with JWT Authentication.

* Java 11
* Spring FrameWork
* Spring Security
* JWT
* Hibernate/Spring Data JPA
* Gradle
* JUnit/Jupiter
* MySQL

## Features

List of features:

User
- As a User, I can register for a new account.
- As a registered User, I can view available products, content of the cart and view orders.
- As a registered User, I can add/delete products to the assigned cart.
- As a registered User, I can create an order based on users data and the content in the cart.
- As a registered User, I can shut down or bring up my account.

Admin
- As an Admin, I can view all registered users.
- As an Admin, I can create new product groups, add/delete products in those groups.
- As an Admin, I can view all carts and their contents.
- As an Admin, I have view on all active/canceled orders.
- As an Admin, i can cancel an order, on the Users request.
- As an Admin, I can shut down or bring up an User account.

Root
- As a Root, I can hard delete Users.
- As a Root, I can hard delete Orders.
- As a Root, I cna do whatever the User and Admin can do as well.


Stretch Goals:

- Simple Store app, to edit the content of a cart and create an order
- Have a view of orders

## Getting started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 
See deployment for notes on how to deploy the project on a live system. Just clone or download the files into a folder and it's ready to be used.

- Clone the repository
```
git clone https://github.com/piotr-grosicki/project-jdp-2204-02.git
```
- Build the project
```
./gradlew build
```
- Run the jar file on your local machine or in a docker container
  - Set the connection properties of your preferred database in the application.properties file

# API
Here we shall list out all available endpoints with their description, organiazing them by their controller source.

### Status Codes

This app returns the following status codes in most of its API:

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 201 | `CREATED` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |

Any differences shall be displayed with the discribed api.

## Registration Controller
Once a new user is created/registered a new cart is being assigned to him.

```http
POST /register
```

JSON body:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `username` | `string` | **Required**.  |
| `password` | `string` | **Required**.  |

### Responses

```
{
  "password": "string",
  "role": "USER",
  "userId": 0,
  "username": "string"
}
```

The `username` and `password` attributes contains the info to register a new account with the USER role.

## User Controller
### GET 
All GET endpoints in this controller return the same response:
### Response

```
{
  "password": "string",
  "role": "USER",
  "userId": 0,
  "username": "string"
}
```
Yet each http ending provides with different type of response:

1. Returns all registered users
```http
GET /v1/user
```

2. Returns all active users
```http
GET /v1/user/active_users
```

3. Returns all blocked users
```http
GET /v1/user/blocekd_users
```

4. Returns the specified user by its ID

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `userId` | `int` | **Required**.  |

```http
GET /v1/user/{userId}
```

### PUT

These endpoints may either block or unblock a user:

1. Blocks the specified user by his ID:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `userId` | `int` | **Required**.  |

```http
PUT /v1/user/block_user/{userId}
```
2. or unblocks a user:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `userId` | `int` | **Required**.  |

```http
PUT /v1/user/unblock_user/{userId}
```

### DELETE
And at last the final for this group the Hard DELETE of a user (this feature available for the ROOT role)
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `userId` | `int` | **Required**.  |

```http
DEL /v1/user/hardDeleteUser/{userId}
```

## Group Controller
This controller provides various endpoints to control product groups

### POST

```http
POST /v1/group/createGroup
```

JSON body:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `groupName` | `string` | **Required**.  |
| `groupDescription` | `string` | **Required**.  |

### GET

#### Response

```
{
  "groupDescription": "string",
  "groupId": 0,
  "groupName": "string"
}
```

1. This provides a list of all saved groups:

```http
GET /v1/group
```

2. This provides a group specified by its ID:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `groupId` | `int` | **Required**.  |

```http
GET /v1/group/groupId
```

### DELETE

With this tool you get the power to delete the desired group (first remember to delete all assigned products to this group)

```http
DEL /v1/group/{groupId}
```

## Product Controller
This controller provides various endpoints to control products

### POST

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `groupId` | `int` | **Required**.  |

```http
POST /v1/products/addToGroup={groupId}
```

JSON body:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `productName` | `string` | **Required**.  |
| `productPrice` | `bigDecimal` | **Required**.  |
| `productDescription` | `string` | **Required**.  |

### GET

#### Response

```
{
  "productDescription": "string",
  "productId": 0,
  "productName": "string",
  "productPrice": 0
}
```

1. Get all products

```http
GET /v1/products/
```

2. Get products of a group

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `groupId` | `int` | **Required**.  |

```http
GET /v1/products/group={groupId}
```

3. Get a product specified by its ID

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `rpoductId` | `int` | **Required**.  |

```http
GET /v1/products/{productId}
```

### PUT

This endpoint provides the abillity to edit an existing product

JSON body:

{
  "productDescription": "string",
  "productName": "string",
  "productPrice": 0
}

```http
PUT /v1/products/{productId}
```

### DELETE

With this tool you get the power to delete the desired product

```http
DEL /v1/products/{productId}
```

## Cart Controller
This controller allows you to edit the cart defined by its user ID

### POST
Of course we shall leave ourselves an option to create a lonely cart if needed.

```http
POST /v1/carts
```
Yep! Nothing more is needed here.

### GET

#### Response

```
{
  "cartPrice": 0,
  "id": 0,
  "products": [
    {
      "productDescription": "string",
      "productId": 0,
      "productName": "string",
      "productPrice": 0
    }
  ],
  "userId": 0
}
```

1. This endpoint provides all carts with their products

```http
GET /v1/carts
```

2. and this one gives a cart defined by its user

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `userId` | `int` | **Required**.  |

```http
GET /v1/carts/{userId}
```

### PUT
To add a product to a cart use this endpoint:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `userId` | `int` | **Required**.  |
| `productId` | `int` | **Required**.  |

```http
PUT /v1/carts/{userId}/add/{productId}
```

### DELETE
1. And to delete a product in the cart use this:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `userId` | `int` | **Required**.  |
| `productId` | `int` | **Required**.  |

```http
DEL /v1/carts/{userId}/remove/{productId}
```

2. Or if you want to start over:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `userId` | `int` | **Required**.  |

```http
DEL /v1/carts/{userId}
```


## Order Controller
Finally at last the last group we have is the Order
Firstly lets create a new order. Firstly you need to have a cart of products to create one.

### POST

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `userId` | `int` | **Required**.  |


```
POST /v1/order/create_order/user={userId}
```

This endpoint finilizes the whole process. It grabs all the products from the users cart and gets all the users data. Erases all products 
in the users cart and ready to shop again.

### GET

#### Response

```
{
    "created": "string",
    "id": 0,
    "orderPrice": 0,
    "products": [
      {
        "productDescription": "string",
        "productId": 0,
        "productName": "string",
        "productPrice": 0
      }
    ],
    "status": true,
    "userId": 0
}
```

1. Get all orders in the database:

```
GET /v1/order/
```

2. Get all done orders:

```
GET /v1/order/active
```

3. Get all canceled orders:

```
GET /v1/order/canceled
```

4. Get all users orders:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `userId` | `int` | **Required**.  |

```
GET /v1/order/user={userId}
```

5. Get the disired order by its id

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `orderId` | `int` | **Required**.  |

```
GET /v1/order/{orderId}
```

### PUT
Since the order is a final product of our app, the only thing we can edit in it, is to set it as canceled:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `orderId` | `int` | **Required**.  |

```
PUT /v1/order/cancel_order={orderId}
```

### DELETE
Of course, sometimes you need to cover tracks so we shall leave a way to delete an order (only for ROOT role)

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `orderId` | `int` | **Required**.  |

```
DEL /v1/order/{orderId}
```


# Contributors:
In alphabetical order by our github names:
```
authorList.stream()
            .map(a -> a.getGitName())
            .sorted()
            .collect(Collectors.toList());
            
sout(authorList);
```
Output:

[aodzie](https://github.com/aodzie),
[bernatd](https://github.com/bernatd),
[Grande17](https://github.com/Grande17),
[Mc-Beton](https://github.com/Mc-Beton),
[rydzwr](https://github.com/rydzwr),
[Wis1](https://github.com/Wis1)
