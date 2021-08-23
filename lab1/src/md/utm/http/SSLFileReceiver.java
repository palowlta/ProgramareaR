package md.utm.http;

abstract class SSLFileReceiver extends UtmSSLSocketClient implements Runnable {

    public SSLFileReceiver(String hostName, String path, int port) {
        super(hostName, path, port);
    }

}
