# API Endpoints and Test Commands

This file contains a list of all available API endpoints and `curl` commands to test them.

## 0. Payments

### Create Order
```bash
curl -X POST http://localhost:8080/api/payments/create-order \
-H "Content-Type: application/json" \
-d '{
    "amount": 500.00
}'
```

### Verify Payment
*Note: Replace placeholders with actual values received from Razorpay.*
```bash
curl -X POST http://localhost:8080/api/payments/verify \
-H "Content-Type: application/json" \
-d '{
    "razorpayOrderId": "order_123456",
    "razorpayPaymentId": "pay_123456",
    "razorpaySignature": "signature_string"
}'
```

## 1. Users

### Create Patient
```bash
curl -X POST http://localhost:8080/api/users \
-H "Content-Type: application/json" \
-d '{
    "name": "Alice Patient",
    "email": "alice@example.com",
    "phone": "1234567890",
    "passwordHash": "hashed_password",
    "role": "PATIENT",
    "gender": "FEMALE",
    "dob": "1990-01-01"
}'
```

### Create Doctor User
```bash
curl -X POST http://localhost:8080/api/users \
-H "Content-Type: application/json" \
-d '{
    "name": "Dr. Bob Smith",
    "email": "bob@example.com",
    "phone": "0987654321",
    "passwordHash": "hashed_password",
    "role": "DOCTOR",
    "gender": "MALE",
    "dob": "1980-05-15"
}'
```

### Get All Users
```bash
curl -X GET http://localhost:8080/api/users
```

### Get User by ID
```bash
curl -X GET http://localhost:8080/api/users/1
```

## 2. Clinics

### Create Clinic
```bash
curl -X POST http://localhost:8080/api/clinics \
-H "Content-Type: application/json" \
-d '{
    "name": "City Health Clinic",
    "address": "123 Healthcare Blvd",
    "city": "New York",
    "latitude": 40.7128,
    "longitude": -74.0060"
}'
```

### Get All Clinics
```bash
curl -X GET http://localhost:8080/api/clinics
```

## 3. Doctors

### Create Doctor Profile
*Note: Ensure `userId` matches the ID of the Doctor User created earlier.*
```bash
curl -X POST "http://localhost:8080/api/doctors" \
-H "Content-Type: application/json" \
-d '{
    "userId": 2,
    "experienceYears": 10,
    "consultationFee": 100.00,
    "about": "Experienced Cardiologist",
    "licenseNumber": "LIC-123456"
}'
```

### Get All Doctors
```bash
curl -X GET http://localhost:8080/api/doctors
```

### Create Appointment Slots
*Note: This endpoint still uses mixed params/body for simplicity, or check if updated.*
*Checking Controller: `createSlots` in `AppointmentController` still takes `RequestParam doctorId` and `RequestBody List<LocalDateTime>`. Use as before.*
```bash
curl -X POST "http://localhost:8080/api/appointments/slots?doctorId=1" \
-H "Content-Type: application/json" \
-d '[
    "2024-12-01T10:00:00",
    "2024-12-01T11:00:00",
    "2024-12-01T14:30:00"
]'
```

## 4. Appointments

### Book Appointment
*Note: Now uses Request Body instead of Query Params.*
```bash
curl -X POST "http://localhost:8080/api/appointments/book" \
-H "Content-Type: application/json" \
-d '{
    "patientId": 1,
    "slotId": 1
}'
```

### Get Patient Appointments
```bash
curl -X GET http://localhost:8080/api/appointments/patient/1
```

### Get Doctor Appointments
```bash
curl -X GET http://localhost:8080/api/appointments/doctor/1
```

### Cancel Appointment
```bash
curl -X POST http://localhost:8080/api/appointments/cancel/1
```
