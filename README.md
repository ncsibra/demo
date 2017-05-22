# receipt-demo

### How to run the service
```
./gradlew bootRun
```
It runs on the 8090 port by default.

### Usage
The service has 1 endpoint `/receipt` and support POST requests.  
A valid request looks like this:  
```
curl -X POST \
  http://localhost:8090/receipt \
  -H 'content-type: application/json' \
  -d '{
  "items": [
    {
      "id": 1,
      "name": "book",
      "quantity": 1,
      "price": 29.49
    },
    {
      "id": 2,
      "name": "music cd",
      "quantity": 2,
      "price": 15.99
    },
    {
      "id": 3,
      "name": "chocolate snack",
      "quantity": 1,
      "price": 0.75
    }
  ]
}'
```
The supported item ids are: 1,2,3,4,5,6.  
For any other id, it will return an error.  
