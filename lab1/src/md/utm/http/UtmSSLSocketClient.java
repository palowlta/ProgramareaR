package md.utm.http;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtmSSLSocketClient extends HttpClient{

    protected SSLSocket socket;
    private SSLSocketFactory factory;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    List<String> imgList = new ArrayList<>();
    String parsed = null;
    String line = null;

    public UtmSSLSocketClient(String hostName, String path, int port) {
        super(hostName, path, port);
    }

    @Override
    public void initConnection() throws IOException {
        factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        socket = (SSLSocket) factory.createSocket(getHostName(), getPort());
        socket.startHandshake();

        printWriter = new PrintWriter(socket.getOutputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),
                StandardCharsets.UTF_8));

        printWriter.println("GET " + getPath() + " HTTP/1.1");
        printWriter.println("Host: " + getHostName());
        printWriter.println();
        printWriter.flush();
    }

    @Override
    public void closeConnection() throws IOException {
        bufferedReader.close();
        printWriter.close();
        socket.close();
    }

    public void getContent() throws IOException {
        while ((line = bufferedReader.readLine()) != null) {
            parsed += line;
            System.out.println(line);
        }
    }

    public void getImagesList() throws IOException {
        String regex = "src\\s*=\\s*\"([^\"]+[\\.png|\\.jpg|\\.gif])\"";
        while ((line = bufferedReader.readLine()) != null) {
            parsed += line;
            System.out.println(line);
        }

        // define finding pattern and filter source by regex
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(parsed);

        // put image names into list
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                imgList.add(urlToImgPath(matcher.group(i)));
            }
        }
    }

}
