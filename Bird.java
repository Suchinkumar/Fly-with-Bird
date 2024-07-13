// Name: Suchin Kumar
// Roll no: 2110110524
// To run the application, execute the MainFrame class.

public abstract class Bird {
    protected Position pos;
    protected Flock flock;

    public Bird() {
        this.pos = new Position(0, 0);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(int x, int y) {
        this.pos.setPos(x, y);
    }

    public void setFlock(Flock flock) {
        this.flock = flock;
    }

    public abstract void move();

    protected Position calculateNextPosition() {
        // This method should be overridden by subclasses to implement specific movement logic
        return new Position(pos.getX(), pos.getY());
    }

    protected abstract FlockX getFlock();
}
