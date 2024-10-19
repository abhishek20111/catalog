import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Simulating the JSON input data as a Map
        Map<String, Object> jsonInput = new HashMap<>();
        jsonInput.put("keys", Map.of("n", 4, "k", 3));
        jsonInput.put("1", Map.of("base", "10", "value", "4"));
        jsonInput.put("2", Map.of("base", "2", "value", "111"));
        jsonInput.put("3", Map.of("base", "10", "value", "12"));
        jsonInput.put("6", Map.of("base", "4", "value", "213"));

        // Step 1: Decode the Y Values
        List<Node> points = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Map<String, String> entry = (Map<String, String>) jsonInput.get(String.valueOf(i));
            int base = Integer.parseInt(entry.get("base"));
            String value = entry.get("value");
            BigInteger decodedValue = decodeValue(base, value);
            points.add(new Node(i, decodedValue));
        }

        // Step 2: Find the constant term 'c' using Lagrange Interpolation
        BigInteger constantTermC = lagrangeInterpolation(points);
        System.out.printf("The constant term 'c' of the polynomial is approximately: %.2f\n", constantTermC.doubleValue());
    }

    // Node class to represent points (x, y)
    static class Node {
        int key; // x value
        BigInteger value; // y value

        public Node(int key, BigInteger value) {
            this.key = key;
            this.value = value;
        }
    }

    // Decode the value based on its base
    public static BigInteger decodeValue(int base, String value) {
        return new BigInteger(value, base);
    }

    // Lagrange Interpolation to find constant term 'c'
    public static BigInteger lagrangeInterpolation(List<Node> points) {
        BigInteger c = BigInteger.ZERO;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            BigInteger xi = BigInteger.valueOf(points.get(i).key);
            BigInteger yi = points.get(i).value;

            BigInteger li = BigInteger.ONE;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    BigInteger xj = BigInteger.valueOf(points.get(j).key);
                    li = li.multiply(BigInteger.ZERO.subtract(xj)).multiply(xi.subtract(xj).modInverse(BigInteger.valueOf(1_000_000_007))).mod(BigInteger.valueOf(1_000_000_007));
                }
            }
            c = c.add(yi.multiply(li)).mod(BigInteger.valueOf(1_000_000_007));
        }

        return c;
    }
}
