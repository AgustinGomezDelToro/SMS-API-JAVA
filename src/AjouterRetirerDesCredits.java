import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AjouterRetirerDesCredits {
    public static void main(String[] args) {
        try {
            // Prepare data for POST request
            String apiKey = "YOUR API KEY";
            int credit = 100;
            String tokenSubaccount = "identifiant du sous-compte";

            // Create JSON payload
            String jsonPayload = "{\"apiKey\": \"" + apiKey + "\", \"credit\": " + credit + ", \"tokenSubaccount\": \"" + tokenSubaccount + "\"}";

            // Create POST request
            URL url = new URL("https://api.smspartner.fr/v1/subaccount/credit/add");
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
