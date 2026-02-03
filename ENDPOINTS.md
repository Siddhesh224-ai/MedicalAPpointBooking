# 🚀 API Endpoints & Postman Guide

This guide details the API endpoints available in the system. Use the provided **Postman JSON Spec** to test these requests.

> **Base URL**: `http://localhost:8080`

---

## 🔐 Auth API
*Authentication and Registration.*

### 1. Register User
Register a new user (Patient or Doctor).

*   **Endpoint**: `POST /api/auth/register`
*   **Body**:
    ```json
    {
        "name": "John Doe",
        "email": "john@example.com",
        "password": "securepassword",
        "phone": "9876543210",
        "role": "PATIENT", 
        "gender": "MALE",
        "dob": "1990-01-01"
    }
    ```
    *Note: `role` can be `PATIENT` or `DOCTOR`.*

### 2. Login
Authenticate a user and retrieve a JWT token.

*   **Endpoint**: `POST /api/auth/login`
*   **Body**:
    ```json
    {
        "email": "john@example.com",
        "password": "securepassword"
    }
    ```
*   **Response**:
    ```json
    {
        "token": "eyJhbGciOiJIUzI1NiJ9..."
    }
    ```

---

## 🏥 Clinics API
*Manage Medical Clinics.*

### 1. Create Clinic
*   **Endpoint**: `POST /api/clinics`
*   **Body**:
    ```json
    {
        "name": "City Health Clinic",
        "address": "123 Main St",
        "city": "New York",
        "latitude": 40.7128,
        "longitude": -74.0060
    }
    ```

### 2. Get All Clinics
*   **Endpoint**: `GET /api/clinics`

### 3. Get Clinic by ID
*   **Endpoint**: `GET /api/clinics/{id}`

### 4. Update Clinic
*   **Endpoint**: `PUT /api/clinics/{id}`
*   **Body**:
    ```json
    {
        "name": "Updated Clinic Name",
        "address": "456 New St",
        "city": "New York",
        "latitude": 40.7128,
        "longitude": -74.0060
    }
    ```

### 5. Delete Clinic
*   **Endpoint**: `DELETE /api/clinics/{id}`

---

## 👤 Users API
*Manage User Accounts.*

### 1. Create User (Admin/Direct)
*   **Endpoint**: `POST /api/users`
*   **Body**: Request body similar to Registration but handled via User Controller directly.

### 2. Get All Users
*   **Endpoint**: `GET /api/users`

### 3. Get User by ID
*   **Endpoint**: `GET /api/users/{id}`

### 4. Update User
*   **Endpoint**: `PUT /api/users/{id}`
*   **Body**:
    ```json
    {
        "name": "Updated Name",
        "email": "updated@example.com",
        "phone": "1234567890",
        "role": "PATIENT",
        "gender": "FEMALE",
        "dob": "1995-05-20"
    }
    ```

### 5. Delete User
*   **Endpoint**: `DELETE /api/users/{id}`

---

## 👨‍⚕️ Doctors API
*Manage Doctor Profiles.*

### 1. Create Doctor Profile
Link a professional profile to an existing User ID.

*   **Endpoint**: `POST /api/doctors`
*   **Body**:
    ```json
    {
        "userId": 2,
        "experienceYears": 10,
        "consultationFee": 500.0,
        "about": "Expert Cardiologist",
        "licenseNumber": "MED-123456"
    }
    ```

### 2. Get Doctor by ID
*   **Endpoint**: `GET /api/doctors/{id}`

### 3. Get All Doctors
*   **Endpoint**: `GET /api/doctors`

### 4. Update Doctor
*   **Endpoint**: `PUT /api/doctors/{id}`
*   **Body**:
    ```json
    {
        "experienceYears": 12,
        "consultationFee": 600.0,
        "about": "Senior Cardiologist",
        "licenseNumber": "MED-123456"
    }
    ```

### 5. Delete Doctor
*   **Endpoint**: `DELETE /api/doctors/{id}`

---

## 🗓️ Appointments API
*Scheduling and Slot Management.*

### 1. Create Slots
Add available time slots for a doctor.

*   **Endpoint**: `POST /api/appointments/slots?doctorId=1`
*   **Body**:
    ```json
    [
        "2025-02-10T09:00:00",
        "2025-02-10T10:00:00",
        "2025-02-10T11:00:00"
    ]
    ```

### 2. Book Appointment
Reserve a specific slot for a patient.

*   **Endpoint**: `POST /api/appointments/book`
*   **Body**:
    ```json
    {
        "patientId": 1,
        "slotId": 105
    }
    ```

### 3. Cancel Appointment
*   **Endpoint**: `POST /api/appointments/cancel/{id}`

### 4. Get Appointments for Patient
*   **Endpoint**: `GET /api/appointments/patient/{patientId}`

### 5. Get Appointments for Doctor
*   **Endpoint**: `GET /api/appointments/doctor/{doctorId}`

---

## 💳 Payments API (Razorpay)
*Handle transactions securely.*

### 1. Create Order
Initiate a payment session.

*   **Endpoint**: `POST /api/payments/create-order`
*   **Body**:
    ```json
    {
        "amount": 999.00
    }
    ```
*   **Response**: Returns `orderId` and `keyId` required for the frontend.

### 2. Verify Payment
Validate the signature returned by Razorpay after payment.

*   **Endpoint**: `POST /api/payments/verify`
*   **Body**:
    ```json
    {
        "razorpayOrderId": "order_P123...",
        "razorpayPaymentId": "pay_S456...",
        "razorpaySignature": "a1b2c3d4..."
    }
    ```

---

## 🧪 Testing with Postman (Quick Start)

1.  **Import**: Drag and drop the `postman_collection.json` file into Postman.
2.  **Environment**: Ensure the variable `{{baseUrl}}` is set to `http://localhost:8080`.
3.  **Order of Operations**:
    1.  **Register** a Patient and a Doctor.
    2.  **Login** to get the JWT Token.
    3.  Create a **Clinic**.
    4.  Create a **Doctor Profile** linked to the Doctor User.
    5.  Create **Slots** for the Doctor.
    6.  **Book** an Appointment.
    7.  Proceed to **Payment**.
