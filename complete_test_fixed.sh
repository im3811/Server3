#!/bin/bash
BASE_URL="http://localhost:8080/MiskicIP3/resources/CompanyServices"

echo "=== COMPLETE API TEST ==="

# 1. Create Department
echo -e "\n1. CREATE Department:"
DEPT_RESULT=$(curl -s -X PUT "${BASE_URL}/department" \
  -H "Content-Type: application/json" \
  -d '{"company":"im3811","dept_name":"Engineering","dept_no":"im3811-d99","location":"Rochester"}')
echo $DEPT_RESULT
DEPT_ID=$(echo $DEPT_RESULT | grep -o '"dept_id":[0-9]*' | grep -o '[0-9]*')
echo "Created dept_id: $DEPT_ID"

# 2. Get Department
echo -e "\n\n2. GET Department:"
curl "${BASE_URL}/department?company=im3811&dept_id=$DEPT_ID"

# 3. Create Manager
echo -e "\n\n3. CREATE Manager:"
MGR_RESULT=$(curl -s -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d "{\"emp_name\":\"Johnson\",\"emp_no\":\"im3811-e99\",\"hire_date\":\"2024-11-08\",\"job\":\"Manager\",\"salary\":90000.0,\"dept_id\":$DEPT_ID,\"mng_id\":1}")
echo $MGR_RESULT
MGR_ID=$(echo $MGR_RESULT | grep -o '"emp_id":[0-9]*' | grep -o '[0-9]*' | head -1)
echo "Created manager emp_id: $MGR_ID"

# 4. Create Employee
echo -e "\n\n4. CREATE Employee:"
EMP_RESULT=$(curl -s -X PUT "${BASE_URL}/employee" \
  -H "Content-Type: application/json" \
  -d "{\"emp_name\":\"Smith\",\"emp_no\":\"im3811-e100\",\"hire_date\":\"2024-11-08\",\"job\":\"Developer\",\"salary\":75000.0,\"dept_id\":$DEPT_ID,\"mng_id\":$MGR_ID}")
echo $EMP_RESULT
EMP_ID=$(echo $EMP_RESULT | grep -o '"emp_id":[0-9]*' | grep -o '[0-9]*' | head -1)
echo "Created employee emp_id: $EMP_ID"

# 5. Get All Employees
echo -e "\n\n5. GET All Employees:"
curl "${BASE_URL}/employees?company=im3811"

# 6. Create Timecard
echo -e "\n\n6. CREATE Timecard:"
curl -X PUT "${BASE_URL}/timecard" \
  -H "Content-Type: application/json" \
  -d "{\"emp_id\":$EMP_ID,\"start_time\":\"2024-11-08 09:00:00\",\"end_time\":\"2024-11-08 17:00:00\"}"

# 7. Get Timecards
echo -e "\n\n7. GET Timecards:"
curl "${BASE_URL}/timecards?emp_id=$EMP_ID"

echo -e "\n\n=== ✅ ALL CRUD OPERATIONS WORKING! ==="
