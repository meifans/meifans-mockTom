package meifans.mocktom.startup;

import meifans.mocktom.connector.http.HttpConnector;

public final class Bootstrap {

    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.start();
    }
}
