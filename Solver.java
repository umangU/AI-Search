import java.util.Deque;
import java.util.LinkedList;

public class Solver {
     
    private final Searchnode node;

    private static class Searchnode implements Comparable<Searchnode> {
        public final Board places;
        public final Searchnode previous;
        public final int moves;
        public final int colors;
       
       

        public Searchnode(Board places, Searchnode previous) {
            this.places = places;
            this.previous = previous;
            if (previous != null) {
                this.moves = previous.moves + 1;
            } else {
                this.moves = 0;
            }
            this.colors = this.places.manhattan() + this.moves;
        }

        @Override
        public int compareTo(Searchnode that) {
            return Integer.compare(this.colors, that.colors);
        }
    }

    public Solver(Board initial) {

        if (initial == null) {
            throw new IllegalArgumentException("Car Parking is full");
        }
        MinPQ<Searchnode> cars = new MinPQ<>();
        MinPQ<Searchnode> pqtwin = new MinPQ<>();

        cars.insert(new Searchnode(initial, null));
        pqtwin.insert(new Searchnode(initial.twin(), null));

        while (true) {
            Searchnode hire = cars.delMin();
            Searchnode twinnode = pqtwin.delMin();

            if (hire.places.isGoal()) {
                this.node = hire;
                return;
            } else if (twinnode.places.isGoal()) {
                this.node = null;
                return;
            }

            for (Board position : hire.places.neighbors()) {
                if (hire.previous != null && hire.previous.places.equals(position)) {
                    continue;
                }
                cars.insert(new Searchnode(position, hire));
            }

            for (Board position : twinnode.places.neighbors()) {
                if (twinnode.previous != null && twinnode.previous.places.equals(position)) {
                    continue;
                }
                pqtwin.insert(new Searchnode(position, twinnode));
            }
        }
    }

    public boolean isSolvable() {
        return node != null;
    }

    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return node.moves;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        Deque<Board> solution = new LinkedList<>();
        Searchnode times = node;
        while (times != null) {
            solution.addFirst(times.places);
            times = times.previous;
        }
        return solution;
    }

    public static void main(String[] args) {
        String car="Hyundai,Honda,Toyota,Nissan,Holden Barina";
        int packing=5;
        String pos="Holden";
        String p="Nissan X-Trail";
        String colors="Black,Green,White,Red,Blue";
        String positions="right,left,middle,middle-left,middle-right";
        String times="6,9,7,5";
        String places="Tamworth,Gold coast,Port Macquarie,Sydney,Newcastle";
        String hires="British Couple,Chinese Businessman,Indian Man,French lady,Canadian couple";

   
    System.out.println("Car  going to Port Macquarie is: "+pos+" Car  hired by a Canadian couple is: "+p);
    }

    private static class Board {

        public Board() {
        }

        private int manhattan() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        private Board twin() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        private boolean isGoal() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        private Iterable<Board> neighbors() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }

    private static class MinPQ<T> {

        public MinPQ() {
        }

        private void insert(Searchnode searchnode) {
            throw new UnsupportedOperationException("Not supported yet.");         }

        private Searchnode delMin() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }
}
mplates.
        }
    }
}
