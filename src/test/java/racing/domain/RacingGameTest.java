package racing.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class RacingGameTest {

    @DisplayName("횟수와 자동차 대수를 입력받아 레이싱 게임을 생성한다.")
    @Test
    void create_racing_success() {
        // given
        int carCount = 5;
        int gameCount = 3;

        // when
        RacingGame racingGame = new RacingGame(carCount, gameCount);

        // then
        assertThat(racingGame.getCarCount()).isEqualTo(5);
        assertThat(racingGame.getGameCount()).isEqualTo(3);
    }

    @DisplayName("생성된 레이싱 게임의 초기 자동차 이동거리는 0 이다.")
    @Test
    void first_car_position_is_zero_success() {
        // given
        int carCount = 5;
        int gameCount = 1;

        // when
        RacingGame racingGame = new RacingGame(carCount, gameCount);

        // then
        assertThat(racingGame.getCarCount()).isEqualTo(5);
        assertThat(racingGame.getGameCount()).isEqualTo(1);
        assertThat(racingGame.getRacingCars()).extracting("position")
                .containsOnly(0);
    }

    @DisplayName("입력받은 자동차 대수만큼 자동차를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    void create_car_by_input_success(int input) {
        // given
        RacingGame racingGame = new RacingGame(input, 5);

        // when
        List<Car> racingCarList = racingGame.getRacingCars();

        // then
        assertThat(racingCarList).hasSize(input);
    }

    @DisplayName("자동차에 선택한 전략으로 게임을 수행한다.")
    @Test
    void do_game_sucess() {
        // given
        CarMoveStrategy carMoveStrategy = new CarMoveStrategy(new Random(), 3);
        RacingGame racingGame = new RacingGame(1, 5, carMoveStrategy);

        List<Car> racingCar = racingGame.getRacingCars();
        Car car = racingCar.get(0);

        // when
        racingGame.doGame(car);

        // then
        assertThat(car.getPosition()).isZero();
    }

    @DisplayName("설정한 게임횟수만큼 게임을 수행한다.")
    @Test
    void do_race_success() {
        // given
        CarMoveStrategy carMoveStrategy = new CarMoveStrategy(new Random(), 3);
        RacingGame racingGame = new RacingGame(3, 5, carMoveStrategy);

        // when
        racingGame.doRacing();

        // then
        assertThat(racingGame.getRacingCars()).hasSize(3)
                .extracting(Car::getPosition)
                .containsExactly(0, 0, 0);
    }
}
