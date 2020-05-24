# REST API Car-registration-system

## Author

**Kirill Kupriyanov**

## Task

* Create two tables: cars and car status
* The cars table has three fields: identifier, model, status
* A car can have three statuses: available, sold, test drive
* You can only change the status of a car with the status available
* You can add a new car to the table only with the status available
* It is necessary to observe the uniqueness of status names
* You cannot delete the status from the table if there are cars with this status

## Starting

in the project folder

for run api use:
```
gradle bootRun
```
for run api testing use:
```
gradle test
```

### API adress

http://localhost:8080/api/v1/

## The application provides the following api:

 POST and PUT receive JSON params

```
GET /car/get/all
GET /car/get/byid/{id}
GET /car/get/bystatusid/{id}
GET /status/get/all
GET /status/get/byid/{id}
```
```
POST /car/create                 json params: {"model": "text", "statusId": 1}
POST /status/create              json params: {"statusName": "text"}
```
```
PUT /car/update/{id}             json params: {"model": "text", "statusId": 1}
PUT /status/update/{id}          json params: {"statusName": "text"}
```
```
DELETE /car/delete/{id}
DELETE /status/delete/{id}
```

## Examples

* curl -X GET http://localhost:8080/api/car/get/all
* curl -X GET http://localhost:8080/api/car/get/byid/1
* curl -X GET http://localhost:8080/api/car/get/bystatusid/1
* curl -X GET http://localhost:8080/api/status/get/all
* curl -X GET http://localhost:8080/api/status/get/byid/1

* curl -X POST -H "Content-Type: application/json; charset=utf-8" http://localhost:8080/api/car/create  -d "{\"model\":\"text\",\"statusId\":1}"
* curl -X POST -H "Content-Type: application/json; charset=utf-8" http://localhost:8080/api/status/create  -d "{\"statusName\":\"text\"}"

* curl -X PUT -H "Content-Type: application/json; charset=utf-8" http://localhost:8080/api/car/update/1  -d "{\"statusId\":2}"
* curl -X PUT -H "Content-Type: application/json; charset=utf-8" http://localhost:8080/api/car/update/2  -d "{\"model\":\"text\",\"statusId\":1}"
* curl -X PUT -H "Content-Type: application/json; charset=utf-8" http://localhost:8080/api/status/update/2  -d "{\"statusName\":\"text\"}"

* curl -X DELETE http://localhost:8080/api/car/delete/1
* curl -X DELETE http://localhost:8080/api/status/delete/2