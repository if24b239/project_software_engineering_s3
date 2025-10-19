#!/bin/bash

# Base URL for the API
BASE_URL="http://localhost:8080/api"

echo "Testing Registration and Login API"
echo "================================"

# Test 1: Register a new user
echo -e "\n1. Testing Registration with valid data:"
curl -X POST "${BASE_URL}/register" \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser", "password":"password123"}' \
  -w "\nStatus Code: %{http_code}\n"

# Wait a second between requests
sleep 1

# Test 2: Try to register the same user again (should fail)
echo -e "\n2. Testing Registration with duplicate username:"
curl -X POST "${BASE_URL}/register" \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser", "password":"password123"}' \
  -w "\nStatus Code: %{http_code}\n"

sleep 1

# Test 3: Test login with wrong password
echo -e "\n4. Testing Login with wrong password:"
curl -X POST "${BASE_URL}/login" \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser", "password":"wrongpassword"}' \
  -w "\nStatus Code: %{http_code}\n"

sleep 1

# Test 4: Test registration with invalid data (short username)
echo -e "\n5. Testing Registration with invalid username (too short):"
curl -X POST "${BASE_URL}/register" \
  -H "Content-Type: application/json" \
  -d '{"username":"ab", "password":"password123"}' \
  -w "\nStatus Code: %{http_code}\n"

sleep 1

# Test 5: Test registration with invalid data (short password)
echo -e "\n6. Testing Registration with invalid password (too short):"
curl -X POST "${BASE_URL}/register" \
  -H "Content-Type: application/json" \
  -d '{"username":"newuser", "password":"12345"}' \
  -w "\nStatus Code: %{http_code}\n"

sleep 20

#- Test 1: Should return 201 (Created)
#- Test 2: Should return 409 (Conflict)
#- Test 3: Should return 401 (Unauthorized)
#- Test 4: Should return 400 (Bad Request)
#- Test 5: Should return 400 (Bad Request)
