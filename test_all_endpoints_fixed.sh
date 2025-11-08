#!/bin/bash
BASE_URL="http://localhost:8080/MiskicIP3/resources/CompanyServices"

echo "=== TESTING ALL ENDPOINTS ==="

# 1. Insert a manager (with mng_id as 1 referencing itself - or we can set a different approach)
echo -e "\n1. PUT insert manager (CEO - no manager):"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"King","emp_no":"im3811-e1","hire_date":"2024-11-08","job":"CEO","salary":100000.0,"dept_id":1,"mng_id":1}'

echo -e "\n\n2. GET all employees:"
curl "${BASE_URL}/employees?company=im3811"

echo -e "\n\n3. PUT insert regular employee:"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Smith","emp_no":"im3811-e2","hire_date":"2024-11-08","job":"Developer","salary":75000.0,"dept_id":1,"mng_id":1}'

echo -e "\n\n4. GET all employees again:"
curl "${BASE_URL}/employees?company=im3811"

# 5. Timecard for employee 2
echo -e "\n\n5. PUT insert timecard for employee 2:"
curl -X PUT "${BASE_URL}/timecard" \
  -H "Content-Type: application/json" \
  -d '{"emp_id":2,"start_time":"2024-11-08 09:00:00","end_time":"2024-11-08 17:00:00"}'

echo -e "\n\n6. GET timecards for employee 2:"
curl "${BASE_URL}/timecards?emp_id=2"

echo -e "\n\n7. DELETE timecard:"
curl -X DELETE "${BASE_URL}/timecard?timecard_id=1"

echo -e "\n\n8. DELETE employee:"
curl -X DELETE "${BASE_URL}/employee?emp_id=2"

echo -e "\n\n9. DELETE department:"
curl -X DELETE "${BASE_URL}/department?company=im3811&dept_id=1"

echo -e "\n\n10. DELETE company:"
curl -X DELETE "${BASE_URL}/company?company=im3811"

echo -e "\n\n=== ALL TESTS COMPLETE ==="
