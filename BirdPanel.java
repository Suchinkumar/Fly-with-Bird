// Name: Suchin Kumar
// Roll no: 2110110524
// To run the application, execute the MainFrame class.

import java.awt.*;
import java.util.List;
import javax.swing.*;

public class BirdPanel extends JPanel {
    private FlockX flock;

    public BirdPanel(FlockX flock) {
        this.flock = flock;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Bird> birds = flock.getBirds();
        for (Bird bird : birds) {
            Position pos = bird.getPos();
            g.fillOval(pos.getX(), pos.getY(), 10, 10);
        }
    }
}
