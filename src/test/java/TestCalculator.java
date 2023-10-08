import org.junit.Test;
import org.minhtran.Calculator.Calculator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestCalculator {
    @Test
    public void testAddition() {
        var calculator = new Calculator();
        double result = calculator.add(11.3, 4);
        assertEquals(15.3, result, 0.1);
    }

    @Test
    public void testSubtraction() {
        var calculator = new Calculator();
        double result = calculator.subtract(13.2, 3.4);
        assertEquals(9.8, result, 0.1);
    }

    @Test
    public void testMultiplication() {
        var calculator = new Calculator();
        double result = calculator.multiply(3.45, 6.78);
        assertEquals(23.391, result, 0.001);
    }

    //Dividing by 0 should throw an ArithmeticException
    @Test(expected = ArithmeticException.class)
    public void testDivideBy0() {
        var calculator = new Calculator();
        double result = calculator.divide(6.7, 0.0);
    }

    @Test
    public void testDivisionSuccess() {
        var calculator = new Calculator();
        double result = calculator.divide(6.7, 4.45);
    }

    @Test
    public void TestAdditionWithDataFromCSVFile() {
        System.out.println("Test addition with data from csv file");
        var calculator = new Calculator();
        String path = "src\\main\\resources\\AddCSVData.csv", line = "";
        boolean isPassed = true;
        int count = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            //Skip header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                double a = Double.parseDouble(data[0]);
                double b = Double.parseDouble(data[1]);
                double result = Double.parseDouble(data[2]);
                double actualResult = calculator.add(a, b);
                boolean IsPassedUnit = (result == actualResult);
                System.out.printf("[Test Data %d] a: %.1f;b: %.1f;Expected Result: %.1f\n", count, a, b, result);
                System.out.println("[Test Result]: " + ((IsPassedUnit) ? "passed" : "failed"));
                assertEquals(result, calculator.add(a, b), 0.1);
                ++count;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("CSV Add data file not found: " + ex.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void TestSubtractionWithDataFromCSVFile() {
        System.out.println("Test subtraction with data from csv file");
        var calculator = new Calculator();
        String path = "src\\main\\resources\\SubtractCSVData.csv", line = "";
        int count = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            //Skip header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                double a = Double.parseDouble(data[0]);
                double b = Double.parseDouble(data[1]);
                double result = Double.parseDouble(data[2]);
                System.out.printf("[Test Data %d] a: %.1f;b: %.1f;Expected Result: %.1f\n", count, a, b, result);
                assertEquals(result, calculator.subtract(a, b), 0.1);
                ++count;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("CSV Subtract data file not found: " + ex.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void TestMultiplicationWithDataFromCSVFile() {
        System.out.println("Test multiplication with data from csv file");
        var calculator = new Calculator();
        String path = "src\\main\\resources\\MultiplyCSVData.csv", line = "";
        int count = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            //Skip header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                double a = Double.parseDouble(data[0]);
                double b = Double.parseDouble(data[1]);
                double result = Double.parseDouble(data[2]);
                System.out.printf("[Test Data %d] a: %.1f;b: %.1f;Expected Result: %.1f\n", count, a, b, result);
                assertEquals(result, calculator.multiply(a, b), 0.1);
                ++count;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("CSV Multiply data file not found: " + ex.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void TestDivisionWithDataFromCSVFile() {
        System.out.println("Test division with data from csv file");
        var calculator = new Calculator();
        String path = "src\\main\\resources\\DivisionCSVData.csv", line = "";
        int count = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            //Skip header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                double a = Double.parseDouble(data[0]);
                double b = Double.parseDouble(data[1]);
                double actualResult = calculator.divide(a, b);
                double result = 0.0f;
                try {
                    if (!data[2].trim().isEmpty()) {
                        result = Double.parseDouble(data[2]);
                        assertEquals(result, actualResult, 0.1);
                    }
                } catch (ArithmeticException ex) {
                    assertEquals("Can't divide by 0", ex.getMessage());
                }
                System.out.printf("[Test Data %d] a: %.1f;b: %.1f;Expected Result: %.1f\n", count, a, b, result);
                ++count;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("CSV Division data file not found: " + ex.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
