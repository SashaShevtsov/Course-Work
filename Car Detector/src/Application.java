import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Application extends JFrame {

    final private int  MAX_FRAMES = 50;
    private int amOfFrames = 0;
    private Image bgImage;
    private Image[] frames;

    public Application(){
        super("Car Detection");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000, 700));

        JDialog bgDialog = new JDialog();
        bgDialog.setSize(100,50);
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
                        bgDialog.setVisible(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        bgDialog.add(addBg);
        bgDialog.setVisible(true);


        frames = new Image[MAX_FRAMES];
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
        b


    }

    public void calculateBgColors(){
        for(int i=0;i<)
            BufferedImage image=new BufferedImage()
    }
}
