# 🚀 API Endpoints & Postman Guide

This guide details the API endpoints available in the system. Use the provided **Postman JSON Spec** to test these requests.

> **Base URL**: `http://localhost:8080`

---

## 👤 Users API
*Manage Patient and Doctor accounts.*

### 1. Create User
Create a new generic user account. Roles can be `PATIENT` or `DOCTOR`.

*   **Endpoint**: `POST /api/users`
*   **Body**:
    ```json
    {
        "name": "Alice Patient",
        "email": "alice@example.com",
        "phone": "9876543210",
        "passwordHash": "securepass",
        "role": "PATIENT",
        "gender": "FEMALE",
        "dob": "1995-05-20"
    }
    ```

### 2. Get All Users
Retrieve a list of all registered users.

*   **Endpoint**: `GET /api/users`

---

## 👨‍⚕️ Doctors API
*Manage professional profiles for users with the DOCTOR role.*

### 1. Create Doctor Profile
Link a professional profile to an existing User ID.

*   **Endpoint**: `POST /api/doctors`
*   **Body**:
    ```json
    {
        "userId": 2,
        "experienceYears": 8,
        "consultationFee": 500.0,
        "about": "Expert Cardiologist",
        "licenseNumber": "MED-12345"
    }
    ```

### 2. Get Doctor by ID
*   **Endpoint**: `GET /api/doctors/{id}`

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
    1.  Create a **Patient**.
    2.  Create a **Doctor User** -> Then create a **Doctor Profile**.
    3.  Create **Slots** for the Doctor.
    4.  **Book** an Appointment.
    5.  Proceed to **Payment**.
