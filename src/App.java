import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP(protocolo pra se comunicar na web) e receber o body em json

        API api = API.IMDB_TOP_SERIES;

        String url = api.getUrl();
        ContentExtractor extractor = api.getExtractor();
        
        ClientHttp http = new ClientHttp();
        String json = http.searchData(url);

        
        // exibir e manipular os dados 
        List<Content> contents = extractor.contentExtractor(json);

        StickersGenerator generator = new StickersGenerator();

        for (int i = 0; i < 3; i++) {

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.urlImage()).openStream();
            String nomeArquivo = "output/" + content.title() + ".png"; //armazena as imagens no diretório output
            
            generator.cria(inputStream, nomeArquivo.replace(':', '-'));

            System.out.println("\n\n\u001b[37;1m \u001b[44;1m Title ->\u001b[m " + "\u001b[1m" + content.title() + "\u001b[m");
            System.out.println("\u001b[37;1m \u001b[40;1m Image ->\u001b[m" + " " + "\u001b[3m" + content.urlImage() + "\u001b[m");

            if(url.length() == 93 || url.length() == 90){

                double parseDouble = Double.parseDouble(content.imDbRating());
                int parseInt = (int) parseDouble;
                System.out.print("\u001b[37;1m \u001b[44;1m Rating ->\u001b[m");
    
                for (int j = 1; j <= parseInt; j++) {
                    System.out.print("\u2B50");//emoji estrelas
                }

            }
                       
           
        }

        
    }
} 
