import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.json.JSONObject;


public class SubAccountCreation {
    public static void main(String[] args) {
        try {
            // Remplacez par votre clé API
            String apiKey = "VOTRE_CLÉ_API";

            // Préparer les données pour la requête POST
            JSONObject parameters = new JSONObject();
            parameters.put("email", "aaaa@bbb.ccc");
            parameters.put("creditToAttribute", 10);
            parameters.put("isBuyer", 0);
            parameters.put("firstname", "prenom");
            parameters.put("lastname", "nom");

            JSONObject json = new JSONObject();
            json.put("apiKey", apiKey);
            json.put("type", "advanced");
            json.put("parameters", parameters);

            URL url = new URL("https://api.smspartner.fr/v1/subaccount/create");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("cache-control", "no-cache");
            conn.setDoOutput(true);

            // Écriture des données JSON dans le corps de la requête HTTP
            OutputStream os = conn.getOutputStream();
            os.write(json.toString().getBytes());
            os.flush();

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
