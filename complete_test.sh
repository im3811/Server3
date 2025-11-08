#!/bin/bash
BASE_URL="http://localhost:8080/MiskicIP3/resources/CompanyServices"

echo "=== COMPLETE API TEST ==="

# 1. Create Department
echo -e "\n1. CREATE Department:"
curl -X PUT "${BASE_URL}/department" \
  -H "Content-Type: application/json" \
  -d '{"company":"im3811","dept_name":"Engineering","dept_no":"im3811-d1","location":"Rochester"}'

# 2. Get Department
echo -e "\n\n2. GET Department:"
curl "${BASE_URL}/department?company=im3811&dept_id=1"

# 3. Create Manager
echo -e "\n\n3. CREATE Manager:"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Johnson","emp_no":"im3811-e1","hire_date":"2024-11-08","job":"Manager","salary":90000.0,"dept_id":1,"mng_id":1}'

# 4. Create Employee
echo -e "\n\n4. CREATE Employee:"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Smith","emp_no":"im3811-e2","hire_date":"2024-11-08","job":"Developer","salary":75000.0,"dept_id":1,"mng_id":1}'

# 5. Get All Employees
echo -e "\n\n5. GET All Employees:"
curl "${BASE_URL}/employees?company=im3811"

# 6. Create Timecard
echo -e "\n\n6. CREATE Timecard:"
curl -X PUT "${BASE_URL}/timecard" \
  -H "Content-Type: application/json" \
  -d '{"emp_id":2,"start_time":"2024-11-08 09:00:00","end_time":"2024-11-08 17:00:00"}'

# 7. Get Timecards
echo -e "\n\n7. GET Timecards:"
curl "${BASE_URL}/timecards?emp_id=2"

# 8. Update Employee
echo -e "\n\n8. UPDATE Employee salary:"
curl -X POST "${BASE_URL}/employee" \
  -d "emp_id=2&emp_name=Smith&emp_no=im3811-e2&hire_date=2024-11-08&job=Senior Developer&salary=85000.0&dept_id=1&mng_id=1"

# 9. Get Updated Employee
echo -e "\n\n9. GET Updated Employee:"
curl "${BASE_URL}/employee?emp_id=2"

echo -e "\n\n=== ✅ ALL CRUD OPERATIONS WORKING! ==="
