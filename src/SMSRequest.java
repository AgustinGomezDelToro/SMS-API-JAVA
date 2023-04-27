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
        // Création de l'objet URL avec l'adresse de l'API SMS
            URL url = new URL("https://api.smspartner.fr/v1/send");
        // Ouverture de la connexion HTTP avec l'API
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("cache-control", "no-cache");
            conn.setDoOutput(true);

        // Création de l'objet JSON contenant les paramètres du SMS à envoyer   
            JSONObject json = new JSONObject();
            json.put("apiKey", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            json.put("phoneNumbers", "+336XXXXXXXX");
            json.put("virtualNumber", "+336XXXXXXXX");
            json.put("sender", "demo JAVA");
            json.put("gamme", 1);
            json.put("message", "C'est un message test en JAVA !");
            json.put("webhookUrl", "https://webhook.site/TOKEN");
        
        // Écriture des données JSON dans le corps de la requête HTTP
            OutputStream os = conn.getOutputStream(); 
            os.write(json.toString().getBytes());
            os.flush();

        // Lecture de la réponse de l'API
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = br.lines().collect(Collectors.joining());
            System.out.println(response);
            
        // Fermeture de la connexion HTTP
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



