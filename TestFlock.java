// Name: Suchin Kumar
// Roll no: 2110110524
// To run the application, execute the MainFrame class.


import java.util.Random;

public class TestFlock {
    public static void main(String[] args) {
        FlockX flock = new FlockX();

        // Add a leader bird to the flock
        BirdX leader = new BirdX(0);
        leader.setPos(100, 100);
        flock.addBird(leader);
        flock.setLeader(leader);

        // Add more birds to the flock
        Random rand = new Random();
        for (int i = 1; i <= 40; i++) { // Increased from 20 to 40 birds
            BirdX bird = new BirdX(i);
            int x = rand.nextInt(800); // Random x position
            int y = rand.nextInt(600); // Random y position
            bird.setPos(x, y);
            flock.addBird(bird);
        }

        // Print initial positions of birds
        System.out.println("Initial positions of birds:");
        for (Bird bird : flock.getBirds()) {
            Position pos = bird.getPos();
            System.out.println("Bird ID: " + ((BirdX) bird).id + " Position: (" + pos.getX() + ", " + pos.getY() + ")");
        }

        // Run the simulation
        for (int step = 0; step < 100; step++) {
            for (Bird bird : flock.getBirds()) {
                bird.move();
            }
            try {
                Thread.sleep(100); // Adjust sleep time for smoother simulation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print final positions of birds
        System.out.println("Final positions of birds:");
        for (Bird bird : flock.getBirds()) {
            Position pos = bird.getPos();
            System.out.println("Bird ID: " + ((BirdX) bird).id + " Position: (" + pos.getX() + ", " + pos.getY() + ")");
        }

        // Split the flock
        FlockX newFlock = (FlockX) flock.split(20); // Split into two flocks of 20 birds each

        // Print positions of birds in the original flock
        System.out.println("Positions of birds in the original flock after split:");
        for (Bird bird : flock.getBirds()) {
            Position pos = bird.getPos();
            System.out.println("Bird ID: " + ((BirdX) bird).id + " Position: (" + pos.getX() + ", " + pos.getY() + ")");
        }

        // Print positions of birds in the new flock
        System.out.println("Positions of birds in the new flock after split:");
        for (Bird bird : newFlock.getBirds()) {
            Position pos = bird.getPos();
            System.out.println("Bird ID: " + ((BirdX) bird).id + " Position: (" + pos.getX() + ", " + pos.getY() + ")");
        }

        // Merge the new flock back into the original flock
        flock.merge(newFlock);

        // Print positions of birds in the merged flock
        System.out.println("Positions of birds in the merged flock:");
        for (Bird bird : flock.getBirds()) {
            Position pos = bird.getPos();
            System.out.println("Bird ID: " + ((BirdX) bird).id + " Position: (" + pos.getX() + ", " + pos.getY() + ")");
        }
    }
}
