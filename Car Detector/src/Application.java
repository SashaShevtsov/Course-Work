import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Application extends JFrame {

    private int[][] bgPixels;
    final private int  MAX_FRAMES = 50;
    private int amOfFrames = 0;
    private BufferedImage bgImage;
    private BufferedImage[] frames;

    public Application(){
        super("Car Detection");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000, 700));

        JDialog bgDialog = new JDialog();
        bgDialog.setSize(300,200);
        bgDialog.setTitle("Select Background");
        JButton addBg = new JButton("Add BackGround...");
        addBg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png","gif", "jpg", "jpeg");
                chooser.setFileFilter(filter);
                int ret = chooser.showDialog(bgDialog, "Open..");
                if(ret==JFileChooser.APPROVE_OPTION){
                    try {
                        bgImage = ImageIO.read(chooser.getSelectedFile());
                        tabbedPane.addTab("Background", new ImagePanel(bgImage));
                        calculateBgColors();
                        bgDialog.setVisible(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        bgDialog.add(addBg);
        bgDialog.setVisible(true);


        frames = new BufferedImage[MAX_FRAMES];
        JButton frameButton = new JButton("Add new frame...");
        frameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png","gif", "jpg", "jpeg");
                chooser.setFileFilter(filter);
                int ret = chooser.showDialog(null, "Open..");
                if(ret==JFileChooser.APPROVE_OPTION){
                    try {
                        frames[amOfFrames] = ImageIO.read(chooser.getSelectedFile());
                        tabbedPane.addTab("Frame"+ (amOfFrames+1),new ImagePanel(frames[amOfFrames]));
                        checkFrame(frames[amOfFrames]);
                        amOfFrames++;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
        frameButton.setPreferredSize(new Dimension(500, 50));
        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(1000, 700));
        content.add(tabbedPane);
        add(frameButton,BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
    }

    public void calculateBgColors(){
        bgPixels = new int[bgImage.getWidth()][bgImage.getHeight()];
        for(int i=0;i<bgImage.getWidth();i++){
            for(int j=0;j<bgImage.getHeight();j++){
                bgPixels[i][j] = bgImage.getRGB(i,j);
            }
        }
    }

    public void checkFrame(BufferedImage frame){
        for(int i=0;i<frame.getWidth();i++){
            for(int j=0;j<frame.getHeight();j++){
                if(frame.getRGB(i,j)!=bgPixels[i][j]){
                    frame.setRGB(i,j,Color.GREEN.getRGB());
                }
            }
        }
    }
}
