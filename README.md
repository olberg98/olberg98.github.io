# olberg98.github.io

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleWebServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new MyHandler());
        server.setExecutor(null);

        System.out.println("Server running on http://localhost:8080");
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String html = """
                <!DOCTYPE html>
                <html lang="no">
                <head>
                    <meta charset="UTF-8">
                    <title>Min Java Nettside</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background: #f4f4f4;
                            padding: 40px;
                        }
                        .box {
                            background: white;
                            padding: 20px;
                            border-radius: 8px;
                            max-width: 500px;
                        }
                    </style>
                </head>
                <body>
                    <div class="box">
                        <h1>Hei fra Java 👋</h1>
                        <p>Denne nettsiden er laget med ren Java.</p>
                        <button onclick="alert('Java + Web funker!')">Klikk meg</button>
                    </div>
                </body>
                </html>
                """;

            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, html.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(html.getBytes());
            os.close();
        }
    }
}
