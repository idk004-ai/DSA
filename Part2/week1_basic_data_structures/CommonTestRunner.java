import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;

class CommonTestRunner {
    private static String TEST_DIRECTORY = "tests";
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java CommonTestRunner <assignment_number>");
            System.err.println("  assignment_number: 1 for brackets_in_code, 2 for tree_height");
            return;
        }
        
        int assignmentNumber;
        try {
            assignmentNumber = Integer.parseInt(args[0]);
            if (assignmentNumber != 1 && assignmentNumber != 2) {
                System.err.println("Assignment number must be 1 or 2");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("Assignment number must be an integer");
            return;
        }
        
        String workingDirectory = "";
        if (assignmentNumber == 1) {
            workingDirectory = "1_brackets_in_code";
        } else {
            workingDirectory = "2_tree_height";
        }
        
        // Change to the appropriate directory for the test files
        File currentDir = new File(".");
        File assignmentDir = new File(currentDir, workingDirectory);
        if (assignmentDir.exists() && assignmentDir.isDirectory()) {
            System.out.println("Testing in directory: " + assignmentDir.getAbsolutePath());
        } else {
            System.err.println("Assignment directory not found: " + workingDirectory);
            return;
        }
        
        try {
            File testDir = new File(assignmentDir, TEST_DIRECTORY);
            if (!testDir.exists() || !testDir.isDirectory()) {
                System.err.println("Test directory not found: " + testDir.getAbsolutePath());
                return;
            }
            
            // Get all test input files (files without extensions)
            List<String> testFiles = new ArrayList<>();
            for (File file : testDir.listFiles()) {
                String fileName = file.getName();
                if (!fileName.contains(".")) {  // No extension
                    testFiles.add(fileName);
                }
            }
            
            Collections.sort(testFiles, new Comparator<String>() {
                public int compare(String s1, String s2) {
                    try {
                        int n1 = Integer.parseInt(s1);
                        int n2 = Integer.parseInt(s2);
                        return n1 - n2;
                    } catch (NumberFormatException e) {
                        return s1.compareTo(s2);
                    }
                }
            });
            
            // Add assignment directory to classpath
            URLClassLoader classLoader = new URLClassLoader(
                new URL[] { assignmentDir.toURI().toURL() },
                ClassLoader.getSystemClassLoader()
            );
            
            System.out.println("Running " + testFiles.size() + " tests for assignment " + assignmentNumber + "...");
            int passed = 0;
            
            for (String testFile : testFiles) {
                TestResult result = runTest(testFile, assignmentDir, assignmentNumber, classLoader);
                if (result.passed) {
                    passed++;
                    System.out.println("Test " + testFile + ": PASSED");
                } else {
                    System.out.println("Test " + testFile + ": FAILED");
                    System.out.println("  Expected: [" + result.expectedOutput + "]");
                    System.out.println("  Actual:   [" + result.actualOutput + "]");
                }
            }
            
            System.out.println("\nResults: " + passed + "/" + testFiles.size() + " tests passed.");
            
        } catch (Exception e) {
            System.err.println("Error running tests: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static TestResult runTest(String testFile, File assignmentDir, int assignmentNumber, ClassLoader classLoader) {
        TestResult result = new TestResult();
        
        try {
            // Read input from test file
            File inputFile = new File(assignmentDir, TEST_DIRECTORY + File.separator + testFile);
            
            // Read expected output
            File outputFile = new File(assignmentDir, TEST_DIRECTORY + File.separator + testFile + ".a");
            result.expectedOutput = Files.readString(outputFile.toPath()).trim();
            
            // Set up input stream to read from file
            FileInputStream fileInputStream = new FileInputStream(inputFile);
            InputStream oldIn = System.in;
            System.setIn(fileInputStream);
            
            // Capture program output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            PrintStream oldOut = System.out;
            System.setOut(printStream);
            
            try {
                // Use reflection to load and execute the appropriate class
                if (assignmentNumber == 1) {
                    // Run check_brackets using reflection
                    Class<?> checkBracketsClass = Class.forName("check_brackets", true, classLoader);
                    Method mainMethod = checkBracketsClass.getDeclaredMethod("main", String[].class);
                    mainMethod.setAccessible(true);
                    String[] emptyArgs = new String[0];
                    mainMethod.invoke(null, (Object) emptyArgs);
                } else {
                    // Run tree_height using reflection
                    Class<?> treeHeightClass = Class.forName("tree_height", true, classLoader);
                    Object instance = treeHeightClass.getDeclaredConstructor().newInstance();
                    Method runMethod = treeHeightClass.getDeclaredMethod("run");
                    runMethod.setAccessible(true);
                    runMethod.invoke(instance);
                }
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found. Make sure you're in the correct directory and the class is compiled: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Error running assignment " + assignmentNumber + ": " + e.getMessage());
                e.printStackTrace();
            }
            
            // Restore original input and output
            System.setIn(oldIn);
            System.setOut(oldOut);
            fileInputStream.close();
            
            // Compare output
            result.actualOutput = outputStream.toString().trim();
            result.passed = result.expectedOutput.equals(result.actualOutput);
            
        } catch (Exception e) {
            System.err.println("Error in test " + testFile + ": " + e.getMessage());
            e.printStackTrace();
            result.passed = false;
            result.actualOutput = "ERROR: " + e.getMessage();
        }
        
        return result;
    }
    
    static class TestResult {
        boolean passed;
        String expectedOutput;
        String actualOutput;
    }
}