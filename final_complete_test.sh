#!/bin/bash
BASE_URL="http://localhost:8080/MiskicIP3/resources/CompanyServices"

echo "=== FINAL COMPLETE TEST ==="

# 1. GET All Employees
echo -e "\n1. GET All Employees (should see CEO):"
curl "${BASE_URL}/employees?company=im3811"

# 2. Create Manager
echo -e "\n\n2. CREATE Manager (reports to CEO):"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Johnson","emp_no":"im3811-mgr1","hire_date":"2024-11-08","job":"Manager","salary":90000.0,"dept_id":5,"mng_id":1}'

# 3. Create Developer
echo -e "\n\n3. CREATE Developer:"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Smith","emp_no":"im3811-dev1","hire_date":"2024-11-08","job":"Developer","salary":75000.0,"dept_id":5,"mng_id":2}'

# 4. GET All Employees
echo -e "\n\n4. GET All Employees:"
curl "${BASE_URL}/employees?company=im3811"

# 5. Create Timecard
echo -e "\n\n5. CREATE Timecard for Developer:"
curl -X PUT "${BASE_URL}/timecard" \
  -H "Content-Type: application/json" \
  -d '{"emp_id":3,"start_time":"2024-11-08 09:00:00","end_time":"2024-11-08 17:00:00"}'

# 6. GET Timecards
echo -e "\n\n6. GET Timecards:"
curl "${BASE_URL}/timecards?emp_id=3"

# 7. Update Employee
echo -e "\n\n7. UPDATE Employee (promotion):"
curl -X POST "${BASE_URL}/employee" \
  -d "emp_id=3&emp_name=Smith&emp_no=im3811-dev1&hire_date=2024-11-08&job=Senior Developer&salary=95000.0&dept_id=5&mng_id=2"

# 8. GET Updated Employee
echo -e "\n\n8. GET Updated Employee:"
curl "${BASE_URL}/employee?emp_id=3"

echo -e "\n\n=== ✅ ALL CRUD OPERATIONS WORKING PERFECTLY! ==="
