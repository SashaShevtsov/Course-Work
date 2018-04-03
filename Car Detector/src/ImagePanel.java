import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image image;
    public ImagePanel(Image img){
        super();
        image = img;
    }
    public void paint(Graphics g){
        g.drawImage(image, 0,0,null);
    }
}
