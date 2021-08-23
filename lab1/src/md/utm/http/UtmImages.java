package md.utm.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtmImages {

    public static void main(String[] args) throws IOException {
        String hostname = "utm.md";
        String rootPath = "/";
        int port = 443;

        UtmSSLSocketClient contentClient = new UtmSSLSocketClient(hostname,
                rootPath,
                port);

        contentClient.initConnection();
        contentClient.getContent();
        contentClient.getImagesList();
        contentClient.closeConnection();

        for (String image : contentClient.imgList) {
            UtmSSLImageReceiver imageReceiver = new UtmSSLImageReceiver(hostname,
                    image,
                    port);

            imageReceiver.initConnection();
            Thread thread = new Thread(imageReceiver);
            thread.start();
        }
    }
}
