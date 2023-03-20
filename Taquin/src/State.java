import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class State  implements Comparable<State> {

    private int wrongPositions;
    private final char[][] grid;

    public int getWrongPositions() {
        return wrongPositions;
    }

    public void setWrongPositions(int wrongPositions) {
        this.wrongPositions = wrongPositions;
    }

    public char[][] getGrid() {
        return grid;
    }

    public State(char[][] grid, int wrongPositions ) {
        this.grid = grid;
        this.wrongPositions= wrongPositions;
    }

    @Override
    public String toString() {
        return "State{" +
                "wrongPositions=" + wrongPositions +
                ", grid=" + Arrays.toString(grid) +
                "} \n";
    }


    @Override
    public int compareTo(State o) {
        return this.wrongPositions - o.wrongPositions;
    }
}
