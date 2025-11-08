#!/bin/bash
BASE_URL="http://localhost:8080/MiskicIP3/resources/CompanyServices"

echo "=== TESTING ALL ENDPOINTS ==="

# 1. Department Tests
echo -e "\n1. GET single department:"
curl "${BASE_URL}/department?company=im3811&dept_id=1"

echo -e "\n\n2. POST update department:"
curl -X POST "${BASE_URL}/department" \
  -d "dept_id=1&company=im3811&dept_name=Information Technology&dept_no=d11&location=rochester"

echo -e "\n\n3. GET updated department:"
curl "${BASE_URL}/department?company=im3811&dept_id=1"

# 2. Employee Tests
echo -e "\n\n4. PUT insert employee:"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Smith","emp_no":"im3811-e1","hire_date":"2024-11-08","job":"Developer","salary":75000.0,"dept_id":1,"mng_id":0}'

echo -e "\n\n5. GET all employees:"
curl "${BASE_URL}/employees?company=im3811"

echo -e "\n\n6. GET single employee (use emp_id from above):"
curl "${BASE_URL}/employee?emp_id=1"

# 3. Timecard Tests  
echo -e "\n\n7. PUT insert timecard:"
curl -X PUT "${BASE_URL}/timecard" \
  -H "Content-Type: application/json" \
  -d '{"emp_id":1,"start_time":"2024-11-08 09:00:00","end_time":"2024-11-08 17:00:00"}'

echo -e "\n\n8. GET all timecards for employee:"
curl "${BASE_URL}/timecards?emp_id=1"

echo -e "\n\n=== ALL TESTS COMPLETE ==="
