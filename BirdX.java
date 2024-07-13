// Name: Suchin Kumar
// Roll no: 2110110524
// To run the application, execute the MainFrame class.

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class BirdX extends Bird {
    public int id;
    private Shape shape;
    private Color color;

    public BirdX(int id) {
        this.id = id;
        setRandomShapeAndColor();
    }

    private void setRandomShapeAndColor() {
        int shapeType = (int) (Math.random() * 3);
        switch (shapeType) {
            case 0:
                shape = new Ellipse2D.Double(-5, -5, 10, 10);
                color = Color.RED;
                break;
            case 1:
                shape = new Rectangle2D.Double(-5, -5, 10, 10);
                color = Color.GREEN;
                break;
            case 2:
                shape = new Path2D.Double();
                ((Path2D) shape).moveTo(-5, -5);
                ((Path2D) shape).lineTo(0, 5);
                ((Path2D) shape).lineTo(5, -5);
                ((Path2D) shape).closePath();
                color = Color.BLUE;
                break;
        }
    }

    @Override
    public void move() {
        Position nextPos = calculateNextPosition();
        setPos(nextPos.getX(), nextPos.getY());
    }

    @Override
    protected Position calculateNextPosition() {
        if (flock == null) {
            return pos;
        }

        List<Bird> neighbors = flock.getBirds();
        double avgX = 0, avgY = 0;
        int count = 0;
        for (Bird neighbor : neighbors) {
            if (neighbor != this && pos.distanceTo(neighbor.getPos()) < 100) {
                avgX += neighbor.getPos().getX();
                avgY += neighbor.getPos().getY();
                count++;
            }
        }

        if (count > 0) {
            avgX /= count;
            avgY /= count;
        } else {
            avgX = pos.getX();
            avgY = pos.getY();
        }

        int newX = (int) ((pos.getX() + avgX) / 2);
        int newY = (int) ((pos.getY() + avgY) / 2);

        // Avoid collisions
        for (Bird neighbor : neighbors) {
            if (neighbor != this && pos.distanceTo(neighbor.getPos()) < 20) {
                newX += (pos.getX() - neighbor.getPos().getX()) / 2;
                newY += (pos.getY() - neighbor.getPos().getY()) / 2;
            }
        }

        return new Position(newX, newY);
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    @Override
    protected FlockX getFlock() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFlock'");
    }
}
