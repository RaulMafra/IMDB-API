import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {

    public List<Content> contentExtractor(String json) {

        // extrair só os dados que interessam (título e poster)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        // popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String title = atributos.get("title");
            String urlImage = atributos.get("url");
            Content content = new Content(title, urlImage, null);
            contents.add(content);
        }

        return contents;

    }

}
