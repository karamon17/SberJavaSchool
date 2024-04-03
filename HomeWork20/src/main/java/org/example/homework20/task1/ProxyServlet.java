package org.example.homework20.task1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@WebServlet("/proxy")
public class ProxyServlet extends HttpServlet {

    private final Map<String, CachedResponse> cache = new WeakHashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ProxyServlet.doGet");
        String url = request.getParameter("url");
        if (url == null || url.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("URL parameter is missing");
            return;
        }

        CachedResponse cachedResponse = cache.get(url);
        if (cachedResponse != null) {
            response.setStatus(cachedResponse.responseCode());
            response.setContentType(cachedResponse.getContentType());
            response.getWriter().write(cachedResponse.content());
            response.addHeader("Cached-Date", cachedResponse.cachedDate().toString());
            return;
        }

        HttpURLConnection connection = null;
        try {
            URL remoteUrl = new URL(url);
            connection = (HttpURLConnection) remoteUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                StringBuilder content = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line);
                    }
                }
                cachedResponse = new CachedResponse(responseCode, connection.getHeaderFields(), content.toString(), LocalDateTime.now());
                cache.put(url, cachedResponse);

                response.setStatus(responseCode);
                response.setContentType(connection.getContentType());
                response.getWriter().write(content.toString());
                response.addHeader("Cached-Date", cachedResponse.cachedDate().toString());
            } else {
                response.setStatus(responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    private record CachedResponse(int responseCode, Map<String, List<String>> headers, String content,
                                  LocalDateTime cachedDate) {
            private CachedResponse(int responseCode, Map<String, List<String>> headers, String content, LocalDateTime cachedDate) {
                this.responseCode = responseCode;
                this.headers = new WeakHashMap<>(headers);
                this.content = content;
                this.cachedDate = cachedDate;
            }

            public String getContentType() {
                return headers.get("Content-Type").get(0);
            }
        }
}