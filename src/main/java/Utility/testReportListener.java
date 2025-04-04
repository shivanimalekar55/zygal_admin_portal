package Utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testReportListener implements ITestListener {
    private static final String FILE_PATH = "test-output/TestReport.xlsx";
    private static Workbook workbook = new XSSFWorkbook();
    private static Sheet sheet = workbook.createSheet("Test Results");
    private static int rowNum = 0;

    static {
        // Create Header Row
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Test Case ID");
        headerRow.createCell(1).setCellValue("Test Case Name");
        headerRow.createCell(2).setCellValue("Status");
        headerRow.createCell(3).setCellValue("Execution Time");
        headerRow.createCell(4).setCellValue("Comment");
    }
    
    //Delete previous Report automatically.
    
    @Override
    public void onStart(ITestContext context) {
        File reportFile = new File(FILE_PATH);
        if (reportFile.exists()) {
            if (reportFile.delete()) {
                System.out.println("Previous test report deleted successfully.");
            } else {
                System.out.println("Failed to delete previous test report.");
            }
        }
    }
   
    @Override
    
    public void onTestSuccess(ITestResult result) {
        writeResult(result, "PASSED", "Test Passed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Updated comment for failed test cases
        writeResult(result, "FAILED", "Test Failed Successfully");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        writeResult(result, "SKIPPED", "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        try (FileOutputStream fileOut = new FileOutputStream(new File(FILE_PATH))) {
            workbook.write(fileOut);
            System.out.println("Test Report generated and the path is: " + FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeResult(ITestResult result, String status, String comment) {
        Row row = sheet.createRow(rowNum++);
        
        // Generate alphanumeric Test Case ID
        String testCaseID = "TC-" + (1000 + rowNum);
        
        // Get Test Case Name from @Test(description) or fallback to method name
        String testName = result.getMethod().getDescription();
        
        // Format Date and Time (MM-dd-yyyy hh:mm:ss a)
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a");
        String executionTime = formatter.format(new Date());

        row.createCell(0).setCellValue(testCaseID); // Test Case ID
        row.createCell(1).setCellValue(testName != null ? testName : result.getName()); // Test Case Name
        row.createCell(2).setCellValue(status); // Status
        row.createCell(3).setCellValue(executionTime); // Execution Time
        row.createCell(4).setCellValue(comment); // Comment
    }
}