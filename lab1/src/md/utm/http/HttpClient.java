package md.utm.http;

import java.io.IOException;

abstract class HttpClient {

    protected String hostName, path;
    private int port;

    public HttpClient(String hostName, String path, int port) {
        this.hostName = hostName;
        this.path = path;
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "HttpClient{" +
                "hostName='" + hostName + '\'' +
                ", path='" + path + '\'' +
                ", port=" + port +
                '}';
    }

    public abstract void initConnection() throws IOException;

    public abstract void closeConnection() throws IOException;

    protected String urlToImgPath(String url) {

        String replaced = url.replaceAll("https://" + getHostName(), "");

        return replaced;
    }

    protected String pathToName(String path) {
        int index = path.lastIndexOf("/") + 1;

        String replaced = path.substring(index);

        return replaced;
    }
}
