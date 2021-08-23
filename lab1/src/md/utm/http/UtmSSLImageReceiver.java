package md.utm.http;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

public class UtmSSLImageReceiver extends SSLFileReceiver {

    public UtmSSLImageReceiver(String hostName, String path, int port) {
        super(hostName, path, port);
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = this.socket.getInputStream();
            File destination = new File(pathToName(this.path));

            byte[] bytes = inputStream.readAllBytes();
            Files.write(destination.toPath(), bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
