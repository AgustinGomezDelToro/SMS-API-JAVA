import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

public class SMSparLots {
    public static void main(String[] args) {
        try {
            // Création de l'objet URL avec l'adresse de l'API SMS
            URL url = new URL("https://api.smspartner.fr/v1/bulk-send");
            // Ouverture de la connexion HTTP avec l'API
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("cache-control", "no-cache");
            conn.setDoOutput(true);

            // Création de l'objet JSON contenant les paramètres des SMS à envoyer en lot
            JSONObject json = new JSONObject();
            json.put("apiKey", "VOTRE_API_KEY"); // Remplacez par votre clé API
            json.put("sender", "mycompany");
            json.put("scheduledDeliveryDate", "21/10/2014");
            json.put("time", 9);
            json.put("minute", 0);

            JSONArray SMSList = new JSONArray();
            JSONObject sms1 = new JSONObject();
            sms1.put("phoneNumber", "06xxxxxxx1");
            sms1.put("message", "msg 0");
            SMSList.put(sms1);

            JSONObject sms2 = new JSONObject();
            sms2.put("phoneNumber", "06xxxxxxx2");
            sms2.put("message", "msg 1");
            SMSList.put(sms2);

            json.put("SMSList", SMSList);

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
