import java.util.List;

public interface ContentExtractor {

    // retorna o json em uma lista de conteúdos
     List<Content> contentExtractor(String json);

}
