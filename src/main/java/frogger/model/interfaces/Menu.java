package frogger.model.interfaces;

import java.awt.event.MouseEvent;
import java.util.List;

public interface Menu {

    List<Button> getButtonList();

    void update();

    void mousePressed(MouseEvent e);

    void mouseReleased(MouseEvent e);

    void mouseMoved(MouseEvent e);

    boolean isIn(MouseEvent e, Button button);
}