package carracing.step3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Cars {
    private final List<Car> cars;
    private final Supplier<Integer> numberGenerator;

    public Cars(int carCount, Supplier<Integer> numberGenerator) {
        cars = new ArrayList<>(carCount);
        for (int i = 0; i < carCount; i++) {
            cars.add(new Car());
        }
        this.numberGenerator = numberGenerator;
    }

    public void moveAllCars() {
        for (int i = 0; i < cars.size(); i++) {
            moveCar(i);
        }
    }

    private void moveCar(int carNum) {
        cars.get(carNum).move(numberGenerator.get());
    }

    public List<Integer> getAllCarsTrace() {
        List<Integer> allCarTrace = new ArrayList<>();
        for (Car car : cars) {
            allCarTrace.add(car.moveTrace());
        }
        return allCarTrace;
    }

}
