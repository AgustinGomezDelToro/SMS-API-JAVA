import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class SMSRequest {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.smspartner.fr/v1/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("cache-control", "no-cache");
            conn.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("apiKey", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            json.put("phoneNumbers", "+336XXXXXXXX");
            json.put("virtualNumber", "+336XXXXXXXX");
            json.put("sender", "demo JAVA");
            json.put("gamme", 1);
            json.put("message", "C'est un message test en JAVA !");
            json.put("webhookUrl", "https://webhook.site/TOKEN");

            OutputStream os = conn.getOutputStream(); 
            os.write(json.toString().getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = br.lines().collect(Collectors.joining());
            System.out.println(response);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



