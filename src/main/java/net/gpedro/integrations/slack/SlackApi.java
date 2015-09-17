package net.gpedro.integrations.slack;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SlackApi {

    private static final String POST = "POST";
    private static final String PAYLOAD = "payload=";
    private static final String UTF_8 = "UTF-8";

    private final String service;
    private final int timeout;

    public SlackApi(String service) {
        this(service, 5000);
    }

    public SlackApi(String service,
                    int timeout) {
        this.timeout = timeout;
        if (service == null) {
            throw new IllegalArgumentException(
                    "Missing WebHook URL Configuration @ SlackApi");
        } else if (!service.startsWith("https://hooks.slack.com/services/")) {
            throw new IllegalArgumentException(
                    "Invalid Service URL. WebHook URL Format: https://hooks.slack.com/services/{id_1}/{id_2}/{token}");
        }

        this.service = service;
    }

    /**
     * Prepare Message and send to Slack
     */
    public void call(SlackMessage message) {
        if (message != null) {
            this.send(message.prepare());
        }
    }

    private String send(JsonObject message) {
        HttpURLConnection connection = null;
        try {
            // Create connection
            final URL url = new URL(this.service);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(POST);
            connection.setConnectTimeout(timeout);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            final String payload = PAYLOAD
                    + URLEncoder.encode(message.toString(), UTF_8);

            // Send request
            final DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(payload);
            wr.flush();
            wr.close();

            // Get Response
            final InputStream is = connection.getInputStream();
            final BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }

            rd.close();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
