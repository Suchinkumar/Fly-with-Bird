// Name: Suchin Kumar
// Roll no: 2110110524
// To run the application, execute the MainFrame class.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlockX extends Flock {
    private static final Random random = new Random();
    private boolean isSplitting = false; // Flag to track split action
    private static final int BURST_RANGE = 50; // Adjust burst range
    private static final int SPLIT_DISTANCE = 50; // Distance between split groups

    @Override
    public Flock split(int n) {
        FlockX newFlock = new FlockX();

        // Calculate number of birds to move to new flock
        int size = birds.size();
        int numToMove = size / 2; // Split into two equal groups

        // Ensure at least 1 bird moves if flock size is odd
        numToMove = Math.min(numToMove, size - 1);

        // Randomly select birds to move
        for (int i = 0; i < numToMove; i++) {
            Bird bird = birds.remove(random.nextInt(birds.size()));
            newFlock.addBird(bird);
        }

        // Set leader for new flock
        if (!newFlock.getBirds().isEmpty()) {
            newFlock.setLeader(newFlock.getBirds().get(0));
        }

        // Trigger burst movement for split birds
        isSplitting = true;
        for (Bird bird : newFlock.getBirds()) {
            burstMovement(bird); // Apply burst movement to split birds
        }

        // Move new flock away from original flock
        moveSplitFlocks(newFlock);

        return newFlock;
    }

    @Override
    public void merge(Flock other) {
        if (other instanceof FlockX) {
            FlockX otherFlock = (FlockX) other;

            // Copy birds to current flock
            List<Bird> otherBirds = new ArrayList<>(otherFlock.getBirds());
            for (Bird bird : otherBirds) {
                bird.setFlock(this);
                birds.add(bird);
            }

            // Clear birds from the other flock after merging
            otherFlock.getBirds().clear();
            otherFlock.setLeader(null); // Reset leader of the other flock
        }
    }

    // Method to apply burst movement to a bird
    private void burstMovement(Bird bird) {
        Position currentPos = bird.getPos();
        int newX = currentPos.getX() + random.nextInt(BURST_RANGE * 2) - BURST_RANGE; // Random x movement
        int newY = currentPos.getY() + random.nextInt(BURST_RANGE * 2) - BURST_RANGE; // Random y movement
        bird.setPos(newX, newY);
    }

    // Method to move split flocks away from each other
    private void moveSplitFlocks(FlockX newFlock) {
        int xOffset = random.nextInt(SPLIT_DISTANCE) - (SPLIT_DISTANCE / 2);
        int yOffset = random.nextInt(SPLIT_DISTANCE) - (SPLIT_DISTANCE / 2);

        // Apply offset to new flock
        for (Bird bird : newFlock.getBirds()) {
            Position pos = bird.getPos();
            pos.setPos(pos.getX() + xOffset, pos.getY() + yOffset);
        }
    }

    // Override move method to handle burst movement and normal movement
    @Override
    public void move() {
        if (isSplitting) {
            // Handle burst movement for split birds
            for (Bird bird : birds) {
                if (bird.getFlock() == this) {
                    burstMovement(bird);
                }
            }
            isSplitting = false; // Reset splitting flag after burst movement
        } else {
            // Normal movement logic for all birds
            for (Bird bird : birds) {
                bird.move(); // Move each bird normally
            }
        }
    }
}
