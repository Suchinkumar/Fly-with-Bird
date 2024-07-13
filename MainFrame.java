// Name: Suchin Kumar
// Roll no: 2110110524
// To run the application, execute the MainFrame class.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Random;

public class MainFrame extends JFrame {
    private FlockX flock;
    private FlockX splitFlock;
    private Timer timer;
    private Random random = new Random();

    public MainFrame() {
        setTitle("Bird Flock Simulation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        flock = new FlockX();

        // Add a leader bird to the flock
        BirdX leader = new BirdX(0);
        leader.setPos(100, 100);
        flock.addBird(leader);
        flock.setLeader(leader);

        // Add more birds to the flock
        for (int i = 1; i <= 50; i++) {
            BirdX bird = new BirdX(i);
            int x = random.nextInt(800); // Random x position
            int y = random.nextInt(600); // Random y position
            bird.setPos(x, y);
            flock.addBird(bird);
        }

        JButton addButton = new JButton("Add Bird");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BirdX newBird = new BirdX(flock.getBirds().size());
                int x = random.nextInt(800); // Random x position
                int y = random.nextInt(600); // Random y position
                newBird.setPos(x, y);
                flock.addBird(newBird);
            }
        });

        JButton splitButton = new JButton("Split Flock");
        splitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (splitFlock == null) {
                    splitFlock = (FlockX) flock.split(10);
                }
            }
        });

        JButton mergeButton = new JButton("Merge Flocks");
        mergeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (splitFlock != null) {
                    flock.merge(splitFlock);
                    splitFlock = null;
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(splitButton);
        buttonPanel.add(mergeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Bird bird : flock.getBirds()) {
                    bird.move();
                }
                if (splitFlock != null) {
                    for (Bird bird : splitFlock.getBirds()) {
                        bird.move();
                    }
                }
                repaint();
            }
        });

        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawFlock(g, flock);
        if (splitFlock != null) {
            drawFlock(g, splitFlock);
        }
    }

    private void drawFlock(Graphics g, FlockX flock) {
        Graphics2D g2d = (Graphics2D) g;
        for (Bird bird : flock.getBirds()) {
            Position pos = bird.getPos();
            BirdX birdX = (BirdX) bird;
            g2d.setColor(birdX.getColor());

            AffineTransform transform = new AffineTransform();
            transform.translate(pos.getX(), pos.getY());

            // Scale the shape down to make birds smaller
            transform.scale(0.7, 0.7);

            g2d.fill(transform.createTransformedShape(birdX.getShape()));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}
