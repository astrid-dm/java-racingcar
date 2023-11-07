package racing.domain;

public class Car {
    private final int MOVE_THRESHOLD = 4;
    private int position;

    public Car() {
        this.position = 0;
    }

    public int getPosition() {
        return position;
    }

    public void move(int input) {
        if (isMovable(input)) {
            this.position++;
        }
    }

    private boolean isMovable(int input) {
        return input >= MOVE_THRESHOLD;
    }
}
