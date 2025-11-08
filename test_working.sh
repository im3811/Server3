#!/bin/bash
BASE_URL="http://localhost:8080/MiskicIP3/resources/CompanyServices"

echo "=== WORKING TEST ==="

# 1. Create Department
echo -e "\n1. CREATE Department:"
curl -X PUT "${BASE_URL}/department" \
  -H "Content-Type: application/json" \
  -d '{"company":"im3811","dept_name":"Engineering","dept_no":"im3811-d888","location":"Rochester"}'

# 2. Create CEO (manages themselves - mng_id will be same as their emp_id)
# We need to create with a valid mng_id, so we'll update after creation
echo -e "\n\n2. CREATE CEO (temporarily with mng_id=5, will update):"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"King","emp_no":"im3811-e888","hire_date":"2024-11-08","job":"CEO","salary":120000.0,"dept_id":5,"mng_id":5}'

# 3. Create Manager
echo -e "\n\n3. CREATE Manager:"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Johnson","emp_no":"im3811-e889","hire_date":"2024-11-08","job":"Manager","salary":90000.0,"dept_id":5,"mng_id":5}'

# 4. Create Developer
echo -e "\n\n4. CREATE Developer:"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Smith","emp_no":"im3811-e890","hire_date":"2024-11-08","job":"Developer","salary":75000.0,"dept_id":5,"mng_id":6}'

# 5. Get All Employees
echo -e "\n\n5. GET All Employees:"
curl "${BASE_URL}/employees?company=im3811"

# 6. Create Timecard
echo -e "\n\n6. CREATE Timecard:"
curl -X PUT "${BASE_URL}/timecard" \
  -H "Content-Type: application/json" \
  -d '{"emp_id":7,"start_time":"2024-11-08 09:00:00","end_time":"2024-11-08 17:00:00"}'

# 7. Get Timecards
echo -e "\n\n7. GET Timecards:"
curl "${BASE_URL}/timecards?emp_id=7"

echo -e "\n\n=== ✅ TEST COMPLETE! ==="
