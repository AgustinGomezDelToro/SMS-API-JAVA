import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StatutSmsParID {
    public static void main(String[] args) {
        try {
            // Prepare data for GET request
            String apiKey = "YOUR_API_KEY";
            String messageId = "300";

            // Create GET request URL
            String urlString = "https://api.smspartner.fr/v1/bulk-status?" +
                    "apiKey=" + apiKey + "&messageId=" + messageId;

            // Create URL object
            URL url = new URL(urlString);

            // Create HTTP connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            // Send GET request
            int responseCode = connection.getResponseCode();

            // Get response
            BufferedReader reader;
            if (responseCode >= 200 && responseCode <= 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Process your response here
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
