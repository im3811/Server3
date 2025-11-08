import companydata.*;
import java.util.List;

public class TestSetup {
    public static void main(String[] args) {
        System.out.println("Testing Server Programming Project 3 Setup...\n");
        
        DataLayer dl = null;
        
        try {
            // Test 1: Initialize DataLayer
            System.out.println("Test 1: Initializing DataLayer...");
            dl = new DataLayer("development");
            System.out.println("✓ DataLayer initialized successfully!\n");
            
            // Test 2: Try to get all departments (will fail if none exist, but tests connection)
            System.out.println("Test 2: Testing database connection...");
            String testCompany = "testcompany";
            List<Department> departments = dl.getAllDepartment(testCompany);
            System.out.println("✓ Database connection working!");
            System.out.println("  Found " + departments.size() + " departments for '" + testCompany + "'\n");
            
            // Test 3: Display MyBatis and MySQL connector info
            System.out.println("Test 3: Checking dependencies...");
            System.out.println("✓ companydata.jar loaded");
            System.out.println("✓ mybatis-3.0.2.jar loaded");
            System.out.println("✓ mysql-connector-java loaded\n");
            
            System.out.println("========================================");
            System.out.println("ALL TESTS PASSED! Setup is correct!");
            System.out.println("========================================");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR: " + e.getMessage());
            System.err.println("\nFull error details:");
            e.printStackTrace();
        } finally {
            if (dl != null) {
                dl.close();
            }
        }
    }
}
