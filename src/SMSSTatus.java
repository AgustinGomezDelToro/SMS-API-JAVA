import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class SMSSTatus {
    public static void main(String[] args) {
        try {
            // Remplacez par votre clé API et autres données
            String apiKey = "VOTRE_CLÉ_API";
            String messageId = "300";
            String phoneNumber = "06xxxxxxxx";

            // Préparer les données pour la requête GET
            String data = String.format("apiKey=%s&messageId=%s&phoneNumber=%s", apiKey, messageId, phoneNumber);
            URL url = new URL("https://api.smspartner.fr/v1/message-status?" + data);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("cache-control", "no-cache");

            // Lecture de la réponse de l'API
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = br.lines().collect(Collectors.joining());

            // Afficher la réponse JSON
            System.out.println(response);

            // Fermeture de la connexion HTTP
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
