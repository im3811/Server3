package business;

import companydata.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BusinessLayer {
    
    private DataLayer dl;
    private static final String COMPANY = "im3811";
    
    public BusinessLayer() throws Exception {
        dl = new DataLayer("development");
    }
    
    public void close() {
        if (dl != null) {
            dl.close();
        }
    }
    
    public int deleteCompany() throws Exception {
        return dl.deleteCompany(COMPANY);
    }
    
    public Department getDepartment(int deptId) throws Exception {
        return dl.getDepartment(COMPANY, deptId);
    }
    
    public List<Department> getAllDepartments() throws Exception {
        return dl.getAllDepartment(COMPANY);
    }
    
    public Department insertDepartment(String deptName, String deptNo, String location) throws Exception {
        if (!isDeptNoUnique(deptNo)) {
            throw new Exception("Department number must be unique across all companies");
        }
        
        if (deptName == null || deptName.trim().isEmpty()) {
            throw new Exception("Department name is required");
        }
        if (deptNo == null || deptNo.trim().isEmpty()) {
            throw new Exception("Department number is required");
        }
        if (location == null || location.trim().isEmpty()) {
            throw new Exception("Location is required");
        }
        
        if (deptName.length() > 255) {
            throw new Exception("Department name must be 255 characters or less");
        }
        if (deptNo.length() > 20) {
            throw new Exception("Department number must be 20 characters or less");
        }
        if (location.length() > 255) {
            throw new Exception("Location must be 255 characters or less");
        }
        
        Department dept = new Department(COMPANY, deptName, deptNo, location);
        return dl.insertDepartment(dept);
    }
    
    public Department updateDepartment(int deptId, String deptName, String deptNo, String location) throws Exception {
        Department existingDept = dl.getDepartment(COMPANY, deptId);
        if (existingDept == null) {
            throw new Exception("Department with ID " + deptId + " does not exist");
        }
        
        if (!deptNo.equals(existingDept.getDeptNo()) && !isDeptNoUnique(deptNo)) {
            throw new Exception("Department number must be unique across all companies");
        }
        
        if (deptName == null || deptName.trim().isEmpty()) {
            throw new Exception("Department name is required");
        }
        if (deptNo == null || deptNo.trim().isEmpty()) {
            throw new Exception("Department number is required");
        }
        if (location == null || location.trim().isEmpty()) {
            throw new Exception("Location is required");
        }
        
        if (deptName.length() > 255) {
            throw new Exception("Department name must be 255 characters or less");
        }
        if (deptNo.length() > 20) {
            throw new Exception("Department number must be 20 characters or less");
        }
        if (location.length() > 255) {
            throw new Exception("Location must be 255 characters or less");
        }
        
        existingDept.setDeptName(deptName);
        existingDept.setDeptNo(deptNo);
        existingDept.setLocation(location);
        
        return dl.updateDepartment(existingDept);
    }
    
    public int deleteDepartment(int deptId) throws Exception {
        return dl.deleteDepartment(COMPANY, deptId);
    }
    
    private boolean isDeptNoUnique(String deptNo) throws Exception {
        List<Department> depts = dl.getAllDepartment(COMPANY);
        for (Department d : depts) {
            if (d.getDeptNo().equals(deptNo)) {
                return false;
            }
        }
        return true;
    }
    
    public Employee getEmployee(int empId) throws Exception {
        return dl.getEmployee(empId);
    }
    
    public List<Employee> getAllEmployees() throws Exception {
        return dl.getAllEmployee(COMPANY);
    }
    
    public Employee insertEmployee(String empName, String empNo, String hireDateStr, 
                                   String job, double salary, int deptId, int mngId) throws Exception {
        
        if (empName == null || empName.trim().isEmpty()) {
            throw new Exception("Employee name is required");
        }
        if (empNo == null || empNo.trim().isEmpty()) {
            throw new Exception("Employee number is required");
        }
        if (job == null || job.trim().isEmpty()) {
            throw new Exception("Job title is required");
        }
        
        if (empName.length() > 50) {
            throw new Exception("Employee name must be 50 characters or less");
        }
        if (empNo.length() > 20) {
            throw new Exception("Employee number must be 20 characters or less");
        }
        if (job.length() > 30) {
            throw new Exception("Job title must be 30 characters or less");
        }
        
        if (!isEmpNoUnique(empNo)) {
            throw new Exception("Employee number must be unique across all companies");
        }
        
        Department dept = dl.getDepartment(COMPANY, deptId);
        if (dept == null) {
            throw new Exception("Department with ID " + deptId + " does not exist in company " + COMPANY);
        }
        
        if (mngId != 0) {
            Employee manager = dl.getEmployee(mngId);
            if (manager == null) {
                throw new Exception("Manager with ID " + mngId + " does not exist");
            }
        }
        
        Date hireDate = parseDate(hireDateStr);
        
        if (hireDate.after(new Date())) {
            throw new Exception("Hire date cannot be in the future");
        }
        
        if (!isWeekday(hireDate)) {
            throw new Exception("Hire date must be a weekday (Monday-Friday)");
        }
        
        if (salary < 0) {
            throw new Exception("Salary must be a positive number");
        }
        
        Employee emp = new Employee(empName, empNo, hireDate, job, salary, deptId, mngId);
        return dl.insertEmployee(emp);
    }
    
    public Employee updateEmployee(int empId, String empName, String empNo, String hireDateStr,
                                   String job, double salary, int deptId, int mngId) throws Exception {
        
        Employee existingEmp = dl.getEmployee(empId);
        if (existingEmp == null) {
            throw new Exception("Employee with ID " + empId + " does not exist");
        }
        
        if (empName == null || empName.trim().isEmpty()) {
            throw new Exception("Employee name is required");
        }
        if (empNo == null || empNo.trim().isEmpty()) {
            throw new Exception("Employee number is required");
        }
        if (job == null || job.trim().isEmpty()) {
            throw new Exception("Job title is required");
        }
        
        if (empName.length() > 50) {
            throw new Exception("Employee name must be 50 characters or less");
        }
        if (empNo.length() > 20) {
            throw new Exception("Employee number must be 20 characters or less");
        }
        if (job.length() > 30) {
            throw new Exception("Job title must be 30 characters or less");
        }
        
        if (!empNo.equals(existingEmp.getEmpNo()) && !isEmpNoUnique(empNo)) {
            throw new Exception("Employee number must be unique across all companies");
        }
        
        Department dept = dl.getDepartment(COMPANY, deptId);
        if (dept == null) {
            throw new Exception("Department with ID " + deptId + " does not exist in company " + COMPANY);
        }
        
        if (mngId != 0) {
            Employee manager = dl.getEmployee(mngId);
            if (manager == null) {
                throw new Exception("Manager with ID " + mngId + " does not exist");
            }
        }
        
        Date hireDate = parseDate(hireDateStr);
        
        if (hireDate.after(new Date())) {
            throw new Exception("Hire date cannot be in the future");
        }
        
        if (!isWeekday(hireDate)) {
            throw new Exception("Hire date must be a weekday (Monday-Friday)");
        }
        
        if (salary < 0) {
            throw new Exception("Salary must be a positive number");
        }
        
        existingEmp.setEmpName(empName);
        existingEmp.setEmpNo(empNo);
        existingEmp.setHireDate(hireDate);
        existingEmp.setJob(job);
        existingEmp.setSalary(salary);
        existingEmp.setDeptId(deptId);
        existingEmp.setMngId(mngId);
        
        return dl.updateEmployee(existingEmp);
    }
    
    public int deleteEmployee(int empId) throws Exception {
        return dl.deleteEmployee(empId);
    }
    
    private boolean isEmpNoUnique(String empNo) throws Exception {
        List<Employee> emps = dl.getAllEmployee(COMPANY);
        for (Employee e : emps) {
            if (e.getEmpNo().equals(empNo)) {
                return false;
            }
        }
        return true;
    }
    
    public Timecard getTimecard(int timecardId) throws Exception {
        return dl.getTimecard(timecardId);
    }
    
    public List<Timecard> getAllTimecards(int empId) throws Exception {
        return dl.getAllTimecard(empId);
    }
    
    public Timecard insertTimecard(int empId, String startTimeStr, String endTimeStr) throws Exception {
        
        Employee emp = dl.getEmployee(empId);
        if (emp == null) {
            throw new Exception("Employee with ID " + empId + " does not exist");
        }
        
        Department dept = dl.getDepartment(COMPANY, emp.getDeptId());
        if (dept == null) {
            throw new Exception("Employee does not belong to company " + COMPANY);
        }
        
        Timestamp startTime = parseTimestamp(startTimeStr);
        Timestamp endTime = parseTimestamp(endTimeStr);
        
        Timestamp oneWeekAgo = getOneWeekAgo();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        
        if (startTime.before(oneWeekAgo) || startTime.after(now)) {
            throw new Exception("Start time must be current date or up to 1 week ago");
        }
        
        long diff = endTime.getTime() - startTime.getTime();
        long oneHourInMillis = 60 * 60 * 1000;
        
        if (diff < oneHourInMillis) {
            throw new Exception("End time must be at least 1 hour after start time");
        }
        
        if (!isSameDay(startTime, endTime)) {
            throw new Exception("Start time and end time must be on the same day");
        }
        
        if (!isWeekday(startTime) || !isWeekday(endTime)) {
            throw new Exception("Timecard dates must be weekdays (Monday-Friday)");
        }
        
        if (!isWithinWorkHours(startTime) || !isWithinWorkHours(endTime)) {
            throw new Exception("Times must be between 06:00:00 and 18:00:00");
        }
        
        if (hasTimecardOnDay(empId, startTime)) {
            throw new Exception("Employee already has a timecard for this day");
        }
        
        Timecard tc = new Timecard(startTime, endTime, empId);
        return dl.insertTimecard(tc);
    }
    
    public Timecard updateTimecard(int timecardId, int empId, String startTimeStr, String endTimeStr) throws Exception {
        
        Timecard existingTc = dl.getTimecard(timecardId);
        if (existingTc == null) {
            throw new Exception("Timecard with ID " + timecardId + " does not exist");
        }
        
        Employee emp = dl.getEmployee(empId);
        if (emp == null) {
            throw new Exception("Employee with ID " + empId + " does not exist");
        }
        
        Department dept = dl.getDepartment(COMPANY, emp.getDeptId());
        if (dept == null) {
            throw new Exception("Employee does not belong to company " + COMPANY);
        }
        
        Timestamp startTime = parseTimestamp(startTimeStr);
        Timestamp endTime = parseTimestamp(endTimeStr);
        
        Timestamp oneWeekAgo = getOneWeekAgo();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        
        if (startTime.before(oneWeekAgo) || startTime.after(now)) {
            throw new Exception("Start time must be current date or up to 1 week ago");
        }
        
        long diff = endTime.getTime() - startTime.getTime();
        long oneHourInMillis = 60 * 60 * 1000;
        
        if (diff < oneHourInMillis) {
            throw new Exception("End time must be at least 1 hour after start time");
        }
        
        if (!isSameDay(startTime, endTime)) {
            throw new Exception("Start time and end time must be on the same day");
        }
        
        if (!isWeekday(startTime) || !isWeekday(endTime)) {
            throw new Exception("Timecard dates must be weekdays (Monday-Friday)");
        }
        
        if (!isWithinWorkHours(startTime) || !isWithinWorkHours(endTime)) {
            throw new Exception("Times must be between 06:00:00 and 18:00:00");
        }
        
        if (hasTimecardOnDay(empId, startTime, timecardId)) {
            throw new Exception("Employee already has a timecard for this day");
        }
        
        existingTc.setStartTime(startTime);
        existingTc.setEndTime(endTime);
        existingTc.setEmpId(empId);
        
        return dl.updateTimecard(existingTc);
    }
    
    public int deleteTimecard(int timecardId) throws Exception {
        return dl.deleteTimecard(timecardId);
    }
    
    private Date parseDate(String dateStr) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            return sdf.parse(dateStr);
        } catch (Exception e) {
            throw new Exception("Invalid date format. Expected yyyy-MM-dd");
        }
    }
    
    private Timestamp parseTimestamp(String timestampStr) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setLenient(false);
            return new Timestamp(sdf.parse(timestampStr).getTime());
        } catch (Exception e) {
            throw new Exception("Invalid timestamp format. Expected yyyy-MM-dd HH:mm:ss");
        }
    }
    
    private boolean isWeekday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY;
    }
    
    private boolean isWeekday(Timestamp timestamp) {
        return isWeekday(new Date(timestamp.getTime()));
    }
    
    private boolean isWithinWorkHours(Timestamp timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        return hour >= 6 && hour <= 18;
    }
    
    private boolean isSameDay(Timestamp ts1, Timestamp ts2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(ts1);
        cal2.setTime(ts2);
        
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
               cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
    
    private Timestamp getOneWeekAgo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -7);
        return new Timestamp(cal.getTimeInMillis());
    }
    
    private boolean hasTimecardOnDay(int empId, Timestamp timestamp) throws Exception {
        return hasTimecardOnDay(empId, timestamp, -1);
    }
    
    private boolean hasTimecardOnDay(int empId, Timestamp timestamp, int excludeTimecardId) throws Exception {
        List<Timecard> timecards = dl.getAllTimecard(empId);
        
        for (Timecard tc : timecards) {
            if (tc.getId() == excludeTimecardId) {
                continue;
            }
            if (isSameDay(tc.getStartTime(), timestamp)) {
                return true;
            }
        }
        
        return false;
    }
}
