package frc.robot;

public enum Direction {
    Forward (1),
    Stop (0),
    Reverse (-1);

    private final int mult;

    private Direction(int mult) {
        this.mult = mult;
    }

    public int getMultiplier() {
        return this.mult;
    }


}
