import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickersGenerator {


    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{

        // leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem na memória com transparência e novo tamanho
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 500;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setFont(font);
        graphics.setColor(Color.yellow);

        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERAAAA", largura - 1000, novaAltura - 400);
        
        // inserindo o meme na nova imagem
        BufferedImage inputMeme = ImageIO.read(new File("input/meme.png"));
        int w = inputMeme.getWidth();
        int h = inputMeme.getHeight();
        
        Graphics2D graphics2 = novaImagem.createGraphics();
        graphics.drawImage(inputMeme, largura - largura + 10 , novaAltura - 1000, w + 500, h + 500, null);
        graphics2.dispose();
        
        
        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
        
    }
    
}



