import com.ingram.BowlingGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameShould {
    BowlingGame game;

    @BeforeEach
    void setUp() {
        game = new BowlingGame();
    }

    private void rollMany(int n, int pins) {
        for (int i = 0; i < n; i++) {
            game.roll(pins);
        }
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    private void rollStrike() {
        game.roll(10);
    }

    @Test
    void giveScoreForGutterGame() {
        rollMany(20, 0);
        assertThat(game.score()).isZero();
    }

    @Test
    void provideScoreForAllOnesGame() {
        rollMany(20, 1);
        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    void provideBonusForSpare() {
        rollSpare();
        game.roll(3);
        rollMany(17, 0);
        assertThat(game.score()).isEqualTo(16);
    }

    @Test
    void provideBonusForAStrike() {
        rollStrike();
        game.roll(3);
        game.roll(4);
        rollMany(16, 0);
        assertThat(game.score()).isEqualTo(24);
    }

    @Test
    void provideScoreForPerfectGame() {
        rollMany(12, 10);
        assertThat(game.score()).isEqualTo(300);
    }
}
