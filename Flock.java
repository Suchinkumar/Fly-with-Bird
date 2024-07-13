// Name: Suchin Kumar
// Roll no: 2110110524
// To run the application, execute the MainFrame class.



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Flock {
    protected List<Bird> birds;
    protected Bird leader;
    protected static final Random random = new Random();

    public Flock() {
        this.birds = new ArrayList<>();
    }

    public void addBird(Bird bird) {
        bird.setFlock(this);
        birds.add(bird);
    }

    public List<Bird> getBirds() {
        return birds;
    }

    public Bird getLeader() {
        return leader;
    }

    public void setLeader(Bird leader) {
        this.leader = leader;
    }

    public Flock split(int n) {
        FlockX newFlock = new FlockX();
        List<Bird> birdsToTransfer = new ArrayList<>();
        
        for (int i = 0; i < n && !birds.isEmpty(); i++) {
            Bird bird = birds.remove(random.nextInt(birds.size()));
            birdsToTransfer.add(bird);
        }
        
        for (Bird bird : birdsToTransfer) {
            newFlock.addBird(bird);
        }
        
        if (!newFlock.getBirds().isEmpty()) {
            newFlock.setLeader(newFlock.getBirds().get(0));
        }
        
        return newFlock;
    }

    public void merge(Flock other) {
        for (Bird bird : other.getBirds()) {
            this.addBird(bird);
        }
        other.getBirds().clear();
    }

    public void changeLeader() {
        if (!birds.isEmpty()) {
            int newLeaderIndex = random.nextInt(birds.size());
            Bird newLeader = birds.get(newLeaderIndex);
            birds.set(newLeaderIndex, leader);
            leader = newLeader;
        }
    }

    protected abstract void move();
}
