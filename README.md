# kyte-driver-onboarding

### Build Command 
`mvn clean install`

### Run Command (uses Java17)
`java -jar target/driver-onboarding-service-1.0.jar`

### Onboard Request
```
curl --location --request POST 'localhost:8092/api/driver' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "User Name",
    "contactNumber": "9898001100",
    "driversLicense": "UP14ABCD0001",
    "vehicleType": "BIKE",
    "vehicleNumber": "DL3CUA8900"
}'
```

### Update Request 
```
curl --location --request PUT 'localhost:8092/api/driver/b277ff85-d4ca-4c6f-9d9c-6b17e0c1093e' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "User Name",
    "contactNumber": "990270001",
    "driversLicense": "UP14ABCD0002",
    "vehicleType": "CAR",
    "status" : "CREATED",
    "vehicleNumber": "DL4CAV0001"
}'
```

### Update Status
```
curl --location --request PUT 'localhost:8092/api/driver/5d2e08a4-607c-4cf4-a816-b289af8806fa/status/ACTIVE'
```
