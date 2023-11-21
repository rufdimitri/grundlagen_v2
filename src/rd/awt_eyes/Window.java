package rd.awt_eyes;

import javax.swing.*;

public class Window extends JFrame {
    PanelX panel;


    public Window (String title, int width, int height) {
        super(title);
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new PanelX(width,height);
        add(panel);
        //addMouseMotionListener(new MouseMotionListenerX(panel));
        panel.addMouseMotionListener(new MouseMotionListenerX(panel));
        setVisible(true);
    }


}
