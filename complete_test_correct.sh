#!/bin/bash
BASE_URL="http://localhost:8080/MiskicIP3/resources/CompanyServices"

echo "=== COMPLETE API TEST ==="

# 1. Create Department
echo -e "\n1. CREATE Department:"
curl -X PUT "${BASE_URL}/department" \
  -H "Content-Type: application/json" \
  -d '{"company":"im3811","dept_name":"Engineering","dept_no":"im3811-d555","location":"Rochester"}'

# 2. Create CEO (no manager - mng_id=0)
echo -e "\n\n2. CREATE CEO (mng_id=0):"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"King","emp_no":"im3811-e555","hire_date":"2024-11-08","job":"CEO","salary":120000.0,"dept_id":4,"mng_id":0}'

# 3. Create Manager under CEO
echo -e "\n\n3. CREATE Manager (reports to CEO):"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Johnson","emp_no":"im3811-e556","hire_date":"2024-11-08","job":"Manager","salary":90000.0,"dept_id":4,"mng_id":3}'

# 4. Create Developer under Manager
echo -e "\n\n4. CREATE Developer (reports to Manager):"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Smith","emp_no":"im3811-e557","hire_date":"2024-11-08","job":"Developer","salary":75000.0,"dept_id":4,"mng_id":4}'

# 5. Get All Employees
echo -e "\n\n5. GET All Employees:"
curl "${BASE_URL}/employees?company=im3811"

# 6. Create Timecard for Developer
echo -e "\n\n6. CREATE Timecard:"
curl -X PUT "${BASE_URL}/timecard" \
  -H "Content-Type: application/json" \
  -d '{"emp_id":5,"start_time":"2024-11-08 09:00:00","end_time":"2024-11-08 17:00:00"}'

# 7. Get Timecards
echo -e "\n\n7. GET Timecards for employee 5:"
curl "${BASE_URL}/timecards?emp_id=5"

echo -e "\n\n=== ✅ ALL CRUD OPERATIONS TEST COMPLETE! ==="
