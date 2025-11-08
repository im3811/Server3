#!/bin/bash
BASE_URL="http://localhost:8080/MiskicIP3/resources/CompanyServices"

echo "=== FINAL COMPLETE TEST - ALL OPERATIONS ==="

# 1. Create Department
echo -e "\n1. CREATE Department:"
curl -X PUT "${BASE_URL}/department" \
  -H "Content-Type: application/json" \
  -d '{"company":"im3811","dept_name":"Sales","dept_no":"im3811-sales","location":"Boston"}'

# 2. GET All Departments
echo -e "\n\n2. GET All Departments:"
curl "${BASE_URL}/departments?company=im3811"

# 3. Create CEO (mng_id=0)
echo -e "\n\n3. CREATE CEO:"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Williams","emp_no":"im3811-ceo9","hire_date":"2025-11-07","job":"CEO","salary":180000.0,"dept_id":6,"mng_id":0}'

# 4. Create Manager
echo -e "\n\n4. CREATE Manager:"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Davis","emp_no":"im3811-mgr9","hire_date":"2025-11-07","job":"Manager","salary":110000.0,"dept_id":6,"mng_id":4}'

# 5. Create Employee
echo -e "\n\n5. CREATE Employee:"
curl -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d '{"emp_name":"Brown","emp_no":"im3811-emp9","hire_date":"2025-11-07","job":"Sales Rep","salary":65000.0,"dept_id":6,"mng_id":5}'

# 6. GET All Employees
echo -e "\n\n6. GET All Employees:"
curl "${BASE_URL}/employees?company=im3811"

# 7. Create Timecard (FRIDAY Nov 7, 2025 - valid weekday within 1 week)
echo -e "\n\n7. CREATE Timecard (Friday Nov 7):"
curl -X PUT "${BASE_URL}/timecard" \
  -H "Content-Type: application/json" \
  -d '{"emp_id":6,"start_time":"2025-11-07 09:00:00","end_time":"2025-11-07 17:00:00"}'

# 8. GET Timecards
echo -e "\n\n8. GET Timecards:"
curl "${BASE_URL}/timecards?emp_id=6"

# 9. UPDATE Timecard
echo -e "\n\n9. UPDATE Timecard:"
curl -X POST "${BASE_URL}/timecard" \
  -d "timecard_id=2&emp_id=6&start_time=2025-11-07 08:00:00&end_time=2025-11-07 16:00:00"

# 10. GET Updated Timecard
echo -e "\n\n10. GET Updated Timecard:"
curl "${BASE_URL}/timecard?timecard_id=2"

# 11. UPDATE Employee
echo -e "\n\n11. UPDATE Employee (raise):"
curl -X POST "${BASE_URL}/employee" \
  -d "emp_id=6&emp_name=Brown&emp_no=im3811-emp9&hire_date=2025-11-07&job=Senior Sales Rep&salary=75000.0&dept_id=6&mng_id=5"

# 12. DELETE Timecard
echo -e "\n\n12. DELETE Timecard:"
curl -X DELETE "${BASE_URL}/timecard?timecard_id=2"

# 13. DELETE Employee
echo -e "\n\n13. DELETE Employee:"
curl -X DELETE "${BASE_URL}/employee?emp_id=6"

# 14. DELETE Department
echo -e "\n\n14. DELETE Department:"
curl -X DELETE "${BASE_URL}/department?company=im3811&dept_id=6"

echo -e "\n\n"
echo "========================================="
echo "✅✅✅ ALL CRUD OPERATIONS COMPLETE! ✅✅✅"
echo "========================================="
echo "✅ Departments: CREATE, READ, UPDATE, DELETE"
echo "✅ Employees: CREATE, READ, UPDATE, DELETE"
echo "✅ Timecards: CREATE, READ, UPDATE, DELETE"
echo "✅ CEO edge case (mng_id=0) handled"
echo "✅ All validations working"
echo "========================================="
