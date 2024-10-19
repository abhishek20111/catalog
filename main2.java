import java.util.ArrayList;
import java.util.List;

public class SecretSharing {

    // Method to decode a value from a given base
    public static String decodeValue(String base, String value) {
        return new java.math.BigInteger(value, Integer.parseInt(base)).toString(); // Convert to decimal string
    }

    // Method to extract points from the input format
    public static List<int[]> getPoints(List<String[]> inputData) {
        List<int[]> points = new ArrayList<>();
        int n = Integer.parseInt(inputData.get(0)[0]); // Get n from the first line

        for (int i = 1; i <= n; i++) {
            String[] point = inputData.get(i);
            int x = i; // The index serves as x
            String yString = decodeValue(point[0], point[1]); // Decode y
            points.add(new int[]{x, Integer.parseInt(yString)}); // Convert decoded string to int
        }
        return points;
    }

    // Method to calculate the constant term (c) of the polynomial
    public static double calculateConstantTerm(List<int[]> points) {
        double c = 0;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            double numerator = 1;
            double denominator = 1;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    numerator *= -points.get(j)[0]; // Accessing elements in List
                    denominator *= (points.get(i)[0] - points.get(j)[0]); // Accessing elements in List
                }
            }
            c += (points.get(i)[1] * numerator) / denominator; // Accessing elements in List
        }

        return c;
    }

    // Main method to execute the program
    public static void main(String[] args) {
        // Define the input in an array-like structure
        List<String[]> inputData = new ArrayList<>();
        inputData.add(new String[]{"10", "7"}); // n and k
        inputData.add(new String[]{"6", "13444211440455345511"}); // Base and value for point 1
        inputData.add(new String[]{"15", "aed7015a346d63"}); // Base and value for point 2
        inputData.add(new String[]{"15", "6aeeb69631c227c"}); // Base and value for point 3
        inputData.add(new String[]{"16", "e1b5e05623d881f"}); // Base and value for point 4
        inputData.add(new String[]{"8", "316034514573652620673"}); // Base and value for point 5
        inputData.add(new String[]{"3", "2122212201122002221120200210011020220200"}); // Base and value for point 6
        inputData.add(new String[]{"3", "20120221122211000100210021102001201112121"}); // Base and value for point 7
        inputData.add(new String[]{"6", "20220554335330240002224253"}); // Base and value for point 8
        inputData.add(new String[]{"12", "45153788322a1255483"}); // Base and value for point 9
        inputData.add(new String[]{"7", "1101613130313526312514143"}); // Base and value for point 10

        // Extract points from the input data
        List<int[]> points = getPoints(inputData);
        double secret = calculateConstantTerm(points);
        System.out.println("Secret (c) is: " + secret);
    }
}
