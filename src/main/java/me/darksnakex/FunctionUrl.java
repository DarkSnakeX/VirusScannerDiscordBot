package me.darksnakex;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.HttpEntity;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionUrl {

    static String getVirusTotalSummary(String result) {
        if (result != null) {
            int positives = Integer.parseInt(result.split("\"positives\":")[1].split(",")[0].trim());
            String url = result.split("\"url\":")[1].split(",")[0].trim();

            if (positives > 0) {
                return "La URL " +  url +  " es potencialmente maliciosa, se detectaron " + positives + " amenazas.";
            } else {
                return "La URL" +  url + " es segura, no se detectaron amenazas.";
            }
        } else {
            return "No se pudo obtener la respuesta de VirusTotal.";
        }
    }


    public static String checkUrl(String url) throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://www.virustotal.com/vtapi/v2/url/report" + "?apikey=" + Main.VIRUS_TOTAL_API_KEY + "&resource=" + url);

        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            String responseContent = EntityUtils.toString(entity);
            JSONObject jsonResponse = new JSONObject(responseContent);

            int responseCode = jsonResponse.getInt("response_code");
            if (responseCode == 0) {
                sendUrlForAnalysis(url);
                return null;
            } else {
                return responseContent;
            }
        }

        return null;



    }


    private static void sendUrlForAnalysis(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "url=" + url + "&apikey=" + Main.VIRUS_TOTAL_API_KEY);

        Request request = new Request.Builder()
                .url("https://www.virustotal.com/vtapi/v2/url/scan")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        client.newCall(request).execute();
    }



    public static boolean isValidUrl(String texto) {

        String patronURL = "^(http(s)?://)?[a-zA-Z0-9.-]+(\\.[a-zA-Z]{2,6})+(/[a-zA-Z0-9_./-]+)*$";
        Pattern pat = Pattern.compile(patronURL);
        Matcher matcher = pat.matcher(texto);
        return matcher.matches();
    }


}
