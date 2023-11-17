public enum Direction {
    Up(0, -1),
    Down(0, 1),
    Left(-1, 0),
    Right(1,0)


    private final int xDelta;
    private final int yDelta;

    Direction(int xDelta, int yDelta) {
        this.xDelta = xDelta;
        this.yDelta = yDelta;
    }

    public int getXDelta() {
        return xDelta;
    }

    public int getYDelta() {
        return yDelta;
    }

    public boolean isOpposite(Direction direction) {
        return 
    }

}

