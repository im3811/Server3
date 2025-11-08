import companydata.*;

import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

public class TestDataLayer {

   public static void main(String args[]) {
      DataLayer dl = null;
 //include company in employee no because unique     
      try {
         //use "development" for not running under Glassfish
         //use "production" for running under Glassfish
         //********* test!!!!! to make sure gets written to database
         //or add a jndi to sqlmapconfig.xml file:
         /* 
               
            <environment id="prod">
               <transactionManager type="JDBC"/>
               <dataSource type="JNDI">
                   <property name ="data_source" value="jdbc/cpswebmon"/>
               </dataSource>
            </environment>
         */

         dl = new DataLayer("development");  
/*      
         //Department
         
         Department dept = new Department("bdfvks","IT","d50","rochester");
         dept = dl.insertDepartment(dept);
         if (dept.getId() > 0) {
            System.out.println("inserted id: "+ dept.getId());
         } else {
            System.out.println("Not inserted");
         }
         
         List<Department> departments = dl.getAllDepartment("bdfvks");
         
         for(Department d : departments ){    	  
            System.out.println(d.getId());
            System.out.println(d.getCompany());
            System.out.println(d.getDeptName());
            System.out.println(d.getDeptNo());
            System.out.println(d.getLocation()); 
            System.out.println("--------\n\n");
         } 
                  
         Department department = dl.getDepartment("bdfvks",5); 
	  
         //Print the department details
         System.out.println("\n\nCurrent Department:");
         System.out.println(department.getId());
         System.out.println(department.getCompany());
         System.out.println(department.getDeptName());
         System.out.println(department.getDeptNo());
         System.out.println(department.getLocation());
         
         department.setDeptName("Computing");
         department = dl.updateDepartment(department);
         
         //Print the updated department details
         System.out.println("\n\nUpdated Department:");
         System.out.println(department.getId());
         System.out.println(department.getCompany());
         System.out.println(department.getDeptName());
         System.out.println(department.getDeptNo());
         System.out.println(department.getLocation());
        
         int deleted = dl.deleteDepartment("bdfvks",5);
         if (deleted >= 1) {
            System.out.println("\nDepartment deleted");
         } else {
            System.out.println("\nDepartment not deleted");
         }
 
         //Employee
         
         Employee emp = new Employee("French","e55", new java.sql.Date(new java.util.Date().getTime()),"Developer",80000.00, 4, 2);
         emp = dl.insertEmployee(emp);
         if (emp.getId() > 0) {
            System.out.println("inserted id: "+ emp.getId());
         } else {
            System.out.println("Not inserted");
         }


         List<Employee> employees = dl.getAllEmployee("bdfvks");        
        
         for(Employee emp : employees ){    	  
            System.out.println(emp.getId());
            System.out.println(emp.getEmpName());          
            System.out.println(emp.getEmpNo());
            System.out.println(emp.getHireDate());
            System.out.println(emp.getJob()); 
            System.out.println(emp.getSalary());
            System.out.println(emp.getDeptId());
            System.out.println(emp.getMngId());            
            System.out.println("--------\n\n");
         }

         Employee employee = dl.getEmployee(15); 
	  
         //Print the employee details
         System.out.println(employee.getId());
         System.out.println(employee.getEmpName());          
         System.out.println(employee.getEmpNo());
         System.out.println(employee.getHireDate());
         System.out.println(employee.getJob()); 
         System.out.println(employee.getSalary());
         System.out.println(employee.getDeptId());
         System.out.println(employee.getMngId());            
         System.out.println("--------\n\n");

         
         employee.setSalary(60000.00);
         employee = dl.updateEmployee(employee);
         
         //Print the updated employee details
         System.out.println("\n\nUpdated Employee:");
         System.out.println(employee.getId());
         System.out.println(employee.getEmpName());          
         System.out.println(employee.getEmpNo());
         System.out.println(employee.getHireDate());
         System.out.println(employee.getJob()); 
         System.out.println(employee.getSalary());
         System.out.println(employee.getDeptId());
         System.out.println(employee.getMngId());            
         System.out.println("--------\n\n");

         int deletedEmp = dl.deleteEmployee(15);
         if (deletedEmp >= 1) {
            System.out.println("\nEmployee deleted");
         } else {
            System.out.println("\nEmployee not deleted");
         }
         
         //Timecard
         
         Timestamp startTime = new Timestamp(new Date().getTime());
         Calendar cal = Calendar.getInstance();
         cal.setTimeInMillis(startTime.getTime());
         cal.add(Calendar.HOUR, 5);
         Timecard tc = new Timecard(startTime,
                  new Timestamp(cal.getTime().getTime()),2);        
         tc = dl.insertTimecard(tc);
         if (tc.getId() > 0) {
            System.out.println("inserted id: "+ tc.getId());
         } else {
            System.out.println("Not inserted");
         }


         List<Timecard> timecards = dl.getAllTimecard(2);        
        
         for(Timecard tcard : timecards ){    	  
            System.out.println(tcard.getId());
            System.out.println(tcard.getStartTime());          
            System.out.println(tcard.getEndTime());
            System.out.println(tcard.getEmpId());            
            System.out.println("--------\n\n");
         }

         Timecard timecard = dl.getTimecard(1);
         System.out.println("\n\nCurrent Timecard:");
         System.out.println(timecard.getId());
         System.out.println(timecard.getStartTime());          
         System.out.println(timecard.getEndTime());
         System.out.println(timecard.getEmpId());            
         System.out.println("--------\n\n");
                
         cal.setTimeInMillis(timecard.getStartTime().getTime());
         cal.add(Calendar.HOUR, 8);
         timecard.setEndTime(new Timestamp(cal.getTime().getTime()));
         timecard = dl.updateTimecard(timecard);
         
         System.out.println("\n\nUpdated Timecard:");
         System.out.println(timecard.getId());
         System.out.println(timecard.getStartTime());          
         System.out.println(timecard.getEndTime());
         System.out.println(timecard.getEmpId());            
         System.out.println("--------\n\n");
         
         int deletedTC = dl.deleteTimecard(1);
         if (deletedTC >= 1) {
            System.out.println("\nTimecard deleted");
         } else {
            System.out.println("\nTimecard not deleted");
         }                 
*/         
         
         //delete all for a company
         
         int numRowsDeleted = dl.deleteCompany("bdfvks");
         System.out.println("Number of rows deleted: "+numRowsDeleted);

      } catch (Exception e) {
         System.out.println("Problem with query: "+e.getMessage());
      } finally {
         dl.close();
      }
      
   }
   
}