package meifans.mocktom.container;

import java.io.IOException;

import meifans.mocktom.connector.http.HttpRequest;
import meifans.mocktom.connector.http.HttpResponse;

public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void process(HttpRequest request, HttpResponse response) {
        // TODO Auto-generated method stub

    }
}
