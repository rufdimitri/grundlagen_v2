package rd.awt_eyes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionListenerX implements MouseMotionListener {
    PanelX panelx;

    public MouseMotionListenerX(PanelX panelx) {
        this.panelx = panelx;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        panelx.getFace().updatePupilPos(e.getX(), e.getY());
        panelx.repaint();
    }
}
