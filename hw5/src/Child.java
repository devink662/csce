public class Child implements Comparable<Child> {
    private String name;
    private int candyCount;

    public Child(String name, int candyCount) {
        this.name = name;
        this.candyCount = candyCount;
    }

    public String getName() {
        return name;
    }

    public int getCandyCount() {
        return candyCount;
    }

    public void setCandyCount(int candyCount) {
        this.candyCount = candyCount;
    }

    @Override
    public int compareTo(Child other) {
        return Integer.compare(this.candyCount, other.candyCount);
    }

    @Override
    public String toString() {
        return name + " has " + candyCount + " pieces of candy";
    }
}
