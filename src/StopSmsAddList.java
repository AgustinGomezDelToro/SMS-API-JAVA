import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class StopSmsAddList {
    public static void main(String[] args) {
        try {
            // Prepare data for POST request
            String apiKey = "YOUR_API_KEY";
            String phoneNumber = "+336xxxxxxxx";

            // Create JSON payload
            String jsonPayload = "{\"apiKey\": \"" + apiKey + "\", \"phoneNumber\": \"" + phoneNumber + "\"}";

            // Create POST request
            URL url = new URL("https://api.smspartner.fr/v1/stop-sms/add");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Send POST request
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(jsonPayload.getBytes());
            outputStream.flush();
            outputStream.close();

            // Get response
            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode <= 299) {
                // Process your successful response here
                System.out.println("Stop SMS request successful");
            } else {
                // Process your error response here
                System.out.println("Stop SMS request failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
