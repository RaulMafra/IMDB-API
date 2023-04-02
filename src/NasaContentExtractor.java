import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {

    public List<Content> contentExtractor(String json) {

        // extrair só os dados que interessam (título e poster)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        // popular a lista de conteudos
        return listaDeAtributos.stream()
                .map(atributos -> new Content(atributos.get("title"), atributos.get("url"), atributos.get(null)))
                .toList();

    }

}
