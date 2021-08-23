package md.utm.exthttp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class HttpClient {

    public static void main (String[] args) throws IOException {
        URL obj1 = new URL("wowhead.com");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("host", 80));
        HttpURLConnection connection = (HttpURLConnection) obj1.openConnection(proxy);

        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = connection.getResponseCode();
        System.out.println("GET Response --> " + responseCode);

        connection.setRequestMethod("HEAD");
        responseCode = connection.getResponseCode();
        System.out.println("GET Response --> " + responseCode);

        connection.setRequestMethod("OPTIONS");
        System.out.println(connection.getHeaderField("Allow"));

        String POST_PARAMS = "--search = cloth";

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();

        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        int responseCode4 = connection.getResponseCode();
        System.out.println("POST Response -->" + responseCode4);
    }
}
