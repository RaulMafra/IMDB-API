import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP(protocolo pra se comunicar na web) e buscar os top 10 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url); //URI - termo genérico pra url
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular os dados 
        StickersGenerator generator = new StickersGenerator();
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            String title = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = title + ".png";
            
            generator.cria(inputStream, nomeArquivo.replace(':', '-'));

            title = filme.get("title");
            System.out.println("\n\n\u001b[37;1m \u001b[44;1m Title ->\u001b[m " + "\u001b[1m" + filme.get("title") + "\u001b[m");
            System.out.println("\u001b[37;1m \u001b[40;1m Image ->\u001b[m" + " " + "\u001b[3m" + filme.get("image") + "\u001b[m");
            String ratings = filme.get("imDbRating");
            double parseDouble = Double.parseDouble(ratings);
            int parseInt = (int) parseDouble;
            System.out.print("\u001b[37;1m \u001b[44;1m Rating ->\u001b[m");

            for (int i = 1; i <= parseInt; i++) {
                System.out.print("\u2B50");//emoji estrelas
            }
        }

        
    }
} 
