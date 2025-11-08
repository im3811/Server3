package service;

import business.BusinessLayer;
import companydata.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.List;

@Path("CompanyServices")
public class CompanyServices {
    
    private Gson gson = new Gson();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @DELETE
    @Path("company")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCompany(@QueryParam("company") String company) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            int rowsDeleted = bl.deleteCompany();
            
            JsonObject response = new JsonObject();
            response.addProperty("success", company + "'s information deleted.");
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @GET
    @Path("department")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartment(@QueryParam("company") String company, 
                                   @QueryParam("dept_id") int deptId) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            Department dept = bl.getDepartment(deptId);
            
            if (dept == null) {
                return buildErrorResponse("Department not found");
            }
            
            JsonObject deptObj = new JsonObject();
            deptObj.addProperty("dept_id", dept.getId());
            deptObj.addProperty("company", dept.getCompany());
            deptObj.addProperty("dept_name", dept.getDeptName());
            deptObj.addProperty("dept_no", dept.getDeptNo());
            deptObj.addProperty("location", dept.getLocation());
            
            JsonObject response = new JsonObject();
            response.add("department", deptObj);
            
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @GET
    @Path("departments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments(@QueryParam("company") String company) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            List<Department> departments = bl.getAllDepartments();
            
            StringBuilder jsonArray = new StringBuilder("[");
            for (int i = 0; i < departments.size(); i++) {
                Department dept = departments.get(i);
                
                JsonObject deptObj = new JsonObject();
                deptObj.addProperty("dept_id", dept.getId());
                deptObj.addProperty("company", dept.getCompany());
                deptObj.addProperty("dept_name", dept.getDeptName());
                deptObj.addProperty("dept_no", dept.getDeptNo());
                deptObj.addProperty("location", dept.getLocation());
                
                JsonObject wrapper = new JsonObject();
                wrapper.add("department", deptObj);
                
                jsonArray.append(gson.toJson(wrapper));
                if (i < departments.size() - 1) {
                    jsonArray.append(",");
                }
            }
            jsonArray.append("]");
            
            return Response.ok(jsonArray.toString()).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @PUT
    @Path("department")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertDepartment(String jsonInput) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            
            JsonObject input = gson.fromJson(jsonInput, JsonObject.class);
            String company = input.get("company").getAsString();
            String deptName = input.get("dept_name").getAsString();
            String deptNo = input.get("dept_no").getAsString();
            String location = input.get("location").getAsString();
            
            Department dept = bl.insertDepartment(deptName, deptNo, location);
            
            JsonObject deptObj = new JsonObject();
            deptObj.addProperty("dept_id", dept.getId());
            deptObj.addProperty("company", dept.getCompany());
            deptObj.addProperty("dept_name", dept.getDeptName());
            deptObj.addProperty("dept_no", dept.getDeptNo());
            deptObj.addProperty("location", dept.getLocation());
            
            JsonObject innerWrapper = new JsonObject();
            innerWrapper.add("department", deptObj);
            
            JsonObject response = new JsonObject();
            response.add("success", innerWrapper);
            
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @POST
    @Path("department")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDepartment(@FormParam("dept_id") int deptId,
                                     @FormParam("company") String company,
                                     @FormParam("dept_name") String deptName,
                                     @FormParam("dept_no") String deptNo,
                                     @FormParam("location") String location) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            
            Department dept = bl.updateDepartment(deptId, deptName, deptNo, location);
            
            JsonObject deptObj = new JsonObject();
            deptObj.addProperty("dept_id", dept.getId());
            deptObj.addProperty("company", dept.getCompany());
            deptObj.addProperty("dept_name", dept.getDeptName());
            deptObj.addProperty("dept_no", dept.getDeptNo());
            deptObj.addProperty("location", dept.getLocation());
            
            JsonObject innerWrapper = new JsonObject();
            innerWrapper.add("department", deptObj);
            
            JsonObject response = new JsonObject();
            response.add("success", innerWrapper);
            
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @DELETE
    @Path("department")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDepartment(@QueryParam("company") String company,
                                     @QueryParam("dept_id") int deptId) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            int rowsDeleted = bl.deleteDepartment(deptId);
            
            JsonObject response = new JsonObject();
            response.addProperty("success", "Department " + deptId + " from " + company + " deleted.");
            
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @GET
    @Path("employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@QueryParam("emp_id") int empId) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            Employee emp = bl.getEmployee(empId);
            
            if (emp == null) {
                return buildErrorResponse("Employee not found");
            }
            
            JsonObject empObj = new JsonObject();
            empObj.addProperty("emp_id", emp.getId());
            empObj.addProperty("emp_name", emp.getEmpName());
            empObj.addProperty("emp_no", emp.getEmpNo());
            empObj.addProperty("hire_date", dateFormat.format(emp.getHireDate()));
            empObj.addProperty("job", emp.getJob());
            empObj.addProperty("salary", emp.getSalary());
            empObj.addProperty("dept_id", emp.getDeptId());
            empObj.addProperty("mng_id", emp.getMngId());
            
            JsonObject response = new JsonObject();
            response.add("employee", empObj);
            
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @GET
    @Path("employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees(@QueryParam("company") String company) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            List<Employee> employees = bl.getAllEmployees();
            
            StringBuilder jsonArray = new StringBuilder("[");
            for (int i = 0; i < employees.size(); i++) {
                Employee emp = employees.get(i);
                
                JsonObject empObj = new JsonObject();
                empObj.addProperty("emp_id", emp.getId());
                empObj.addProperty("emp_name", emp.getEmpName());
                empObj.addProperty("emp_no", emp.getEmpNo());
                empObj.addProperty("hire_date", dateFormat.format(emp.getHireDate()));
                empObj.addProperty("job", emp.getJob());
                empObj.addProperty("salary", emp.getSalary());
                empObj.addProperty("dept_id", emp.getDeptId());
                empObj.addProperty("mng_id", emp.getMngId());
                
                JsonObject wrapper = new JsonObject();
                wrapper.add("employee", empObj);
                
                jsonArray.append(gson.toJson(wrapper));
                if (i < employees.size() - 1) {
                    jsonArray.append(",");
                }
            }
            jsonArray.append("]");
            
            return Response.ok(jsonArray.toString()).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @PUT
    @Path("employee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertEmployee(String jsonInput) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            
            JsonObject input = gson.fromJson(jsonInput, JsonObject.class);
            String empName = input.get("emp_name").getAsString();
            String empNo = input.get("emp_no").getAsString();
            String hireDate = input.get("hire_date").getAsString();
            String job = input.get("job").getAsString();
            double salary = input.get("salary").getAsDouble();
            int deptId = input.get("dept_id").getAsInt();
            int mngId = input.get("mng_id").getAsInt();
            
            Employee emp = bl.insertEmployee(empName, empNo, hireDate, job, salary, deptId, mngId);
            
            JsonObject empObj = new JsonObject();
            empObj.addProperty("emp_id", emp.getId());
            empObj.addProperty("emp_name", emp.getEmpName());
            empObj.addProperty("emp_no", emp.getEmpNo());
            empObj.addProperty("hire_date", dateFormat.format(emp.getHireDate()));
            empObj.addProperty("job", emp.getJob());
            empObj.addProperty("salary", emp.getSalary());
            empObj.addProperty("dept_id", emp.getDeptId());
            empObj.addProperty("mng_id", emp.getMngId());
            
            JsonObject innerWrapper = new JsonObject();
            innerWrapper.add("employee", empObj);
            
            JsonObject response = new JsonObject();
            response.add("success", innerWrapper);
            
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @POST
    @Path("employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@FormParam("emp_id") int empId,
                                   @FormParam("emp_name") String empName,
                                   @FormParam("emp_no") String empNo,
                                   @FormParam("hire_date") String hireDate,
                                   @FormParam("job") String job,
                                   @FormParam("salary") double salary,
                                   @FormParam("dept_id") int deptId,
                                   @FormParam("mng_id") int mngId) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            
            Employee emp = bl.updateEmployee(empId, empName, empNo, hireDate, job, salary, deptId, mngId);
            
            JsonObject empObj = new JsonObject();
            empObj.addProperty("emp_id", emp.getId());
            empObj.addProperty("emp_name", emp.getEmpName());
            empObj.addProperty("emp_no", emp.getEmpNo());
            empObj.addProperty("hire_date", dateFormat.format(emp.getHireDate()));
            empObj.addProperty("job", emp.getJob());
            empObj.addProperty("salary", emp.getSalary());
            empObj.addProperty("dept_id", emp.getDeptId());
            empObj.addProperty("mng_id", emp.getMngId());
            
            JsonObject innerWrapper = new JsonObject();
            innerWrapper.add("employee", empObj);
            
            JsonObject response = new JsonObject();
            response.add("success", innerWrapper);
            
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @DELETE
    @Path("employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@QueryParam("emp_id") int empId) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            int rowsDeleted = bl.deleteEmployee(empId);
            
            JsonObject response = new JsonObject();
            response.addProperty("success", "Employee " + empId + " deleted.");
            
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @GET
    @Path("timecard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimecard(@QueryParam("timecard_id") int timecardId) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            Timecard tc = bl.getTimecard(timecardId);
            
            if (tc == null) {
                return buildErrorResponse("Timecard not found");
            }
            
            JsonObject tcObj = new JsonObject();
            tcObj.addProperty("timecard_id", tc.getId());
            tcObj.addProperty("start_time", timestampFormat.format(tc.getStartTime()));
            tcObj.addProperty("end_time", timestampFormat.format(tc.getEndTime()));
            tcObj.addProperty("emp_id", tc.getEmpId());
            
            JsonObject response = new JsonObject();
            response.add("timecard", tcObj);
            
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @GET
    @Path("timecards")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTimecards(@QueryParam("emp_id") int empId) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            List<Timecard> timecards = bl.getAllTimecards(empId);
            
            StringBuilder jsonArray = new StringBuilder("[");
            for (int i = 0; i < timecards.size(); i++) {
                Timecard tc = timecards.get(i);
                
                JsonObject tcObj = new JsonObject();
                tcObj.addProperty("timecard_id", tc.getId());
                tcObj.addProperty("start_time", timestampFormat.format(tc.getStartTime()));
                tcObj.addProperty("end_time", timestampFormat.format(tc.getEndTime()));
                tcObj.addProperty("emp_id", tc.getEmpId());
                
                JsonObject wrapper = new JsonObject();
                wrapper.add("timecard", tcObj);
                
                jsonArray.append(gson.toJson(wrapper));
                if (i < timecards.size() - 1) {
                    jsonArray.append(",");
                }
            }
            jsonArray.append("]");
            
            return Response.ok(jsonArray.toString()).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @PUT
    @Path("timecard")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertTimecard(String jsonInput) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            
            JsonObject input = gson.fromJson(jsonInput, JsonObject.class);
            int empId = input.get("emp_id").getAsInt();
            String startTime = input.get("start_time").getAsString();
            String endTime = input.get("end_time").getAsString();
            
            Timecard tc = bl.insertTimecard(empId, startTime, endTime);
            
            JsonObject tcObj = new JsonObject();
            tcObj.addProperty("timecard_id", tc.getId());
            tcObj.addProperty("start_time", timestampFormat.format(tc.getStartTime()));
            tcObj.addProperty("end_time", timestampFormat.format(tc.getEndTime()));
            tcObj.addProperty("emp_id", tc.getEmpId());
            
            JsonObject innerWrapper = new JsonObject();
            innerWrapper.add("timecard", tcObj);
            
            JsonObject response = new JsonObject();
            response.add("success", innerWrapper);
            
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @POST
    @Path("timecard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTimecard(@FormParam("timecard_id") int timecardId,
                                   @FormParam("emp_id") int empId,
                                   @FormParam("start_time") String startTime,
                                   @FormParam("end_time") String endTime) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            
            Timecard tc = bl.updateTimecard(timecardId, empId, startTime, endTime);
            
            JsonObject tcObj = new JsonObject();
            tcObj.addProperty("timecard_id", tc.getId());
            tcObj.addProperty("start_time", timestampFormat.format(tc.getStartTime()));
            tcObj.addProperty("end_time", timestampFormat.format(tc.getEndTime()));
            tcObj.addProperty("emp_id", tc.getEmpId());
            
            JsonObject innerWrapper = new JsonObject();
            innerWrapper.add("timecard", tcObj);
            
            JsonObject response = new JsonObject();
            response.add("success", innerWrapper);
            
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    @DELETE
    @Path("timecard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTimecard(@QueryParam("timecard_id") int timecardId) {
        BusinessLayer bl = null;
        try {
            bl = new BusinessLayer();
            int rowsDeleted = bl.deleteTimecard(timecardId);
            
            JsonObject response = new JsonObject();
            response.addProperty("success", "Timecard " + timecardId + " deleted.");
            
            return Response.ok(gson.toJson(response)).build();
            
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        } finally {
            if (bl != null) bl.close();
        }
    }
    
    private Response buildErrorResponse(String errorMessage) {
        JsonObject error = new JsonObject();
        error.addProperty("error", errorMessage);
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(gson.toJson(error))
                       .build();
    }
}
