# shopping site api project 
### just to handle the comments and the expired product

in this application we can :
- fetch a list of comments related to any product
- fetch a list of comments related to any product typed in specific date range
- fetch a list of comments related to any user
- fetch a list of comments related to any user typed in specific date range
- fetch a list of the expired products
- fetch a list of the Not-expired products and the product that doesn't expire

## The usage of (api)

### ![](https://via.placeholder.com/15/808000/808000.png) Comment
|                                                 | method | mapping             | path variables |            request parameters            | 
|-------------------------------------------------|--------|---------------------|:--------------:|:----------------------------------------:|
| the comments related to a product               | GET ↓  | */comments/product* |      X ↓       |          **for All of them ↓**           |
| the comments related to a product in date range |        | */comments/product* |                |        String:  minDate, maxDate         |
| the comments related to a user                  |        | */comments/user*    |                |   format **: yyyy-MM-dd 'T' HH:mm:ss**   |
| the comments related to a user in date range    |        | */comments/user*    |                | example format : **2020-10-05T09:13:59** |

### ![](https://via.placeholder.com/15/808000/808000.png) products
|                           | method | mapping             | path variables | request parameters         | 
|---------------------------|--------|---------------------|:--------------:|----------------------------|
| the expired products      | GET    | */products/expired* |       X        | X                          |
| the Not-expired products  | GET    | */products/valid*   |       X        | X                          |
