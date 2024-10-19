import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SecretSharing {

    // Method to read JSON input from a file
    public static JSONObject readInput(String filePath) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return new JSONObject(content);
    }

    // Method to decode a value from a given base
    public static int decodeValue(String base, String value) {
        return Integer.parseInt(value, Integer.parseInt(base));
    }

    // Method to extract points from the JSON object
    public static List<int[]> getPoints(JSONObject json) {
        List<int[]> points = new ArrayList<>();
        int n = json.getJSONObject("keys").getInt("n");

        for (int i = 1; i <= n; i++) {
            if (json.has(String.valueOf(i))) {
                JSONObject point = json.getJSONObject(String.valueOf(i));
                int x = i; // The key itself is used as x
                int y = decodeValue(point.getString("base"), point.getString("value"));
                points.add(new int[]{x, y});
            }
        }
        return points;
    }

    // Method to calculate the constant term (c) of the polynomial
    public static int calculateConstantTerm(List<int[]> points) {
        int c = 0;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            int numerator = 1;
            int denominator = 1;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    numerator *= -points[j][0];
                    denominator *= (points[i][0] - points[j][0]);
                }
            }
            c += (points[i][1] * numerator) / denominator;
        }

        return c;
    }

    // Main method to execute the program
    public static void main(String[] args) {
        try {
            JSONObject json = readInput("input1.json"); 
            List<int[]> points = getPoints(json);
            int secret = calculateConstantTerm(points);
            System.out.println("Secret (c) is: " + secret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
