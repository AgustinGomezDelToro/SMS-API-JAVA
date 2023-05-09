import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class StopSMSList {
    public static void main(String[] args) {
        try {
            String apiKey = "YOUR_API_KEY";
            URL url = new URL("https://api.smspartner.fr/v1/stop-sms/list?apiKey=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("cache-control", "no-cache");

            // Reading API response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = br.lines().collect(Collectors.joining());

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response);

            // Display the JSON response
            System.out.println(jsonResponse.toString(2));

            // Closing HTTP connection
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
