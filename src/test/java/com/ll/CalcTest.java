package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcTest {
    @Test
    @DisplayName("3 + 5")
    public void t1() {
        int rs = Calc.run("3 + 5");

        assertThat(rs).isEqualTo(8);
    }

    @Test
    @DisplayName("10 + 5")
    public void t2() {
        int rs = Calc.run("10 + 5");

        assertThat(rs).isEqualTo(15);
    }

    @Test
    @DisplayName("20 + 5")
    public void t3() {
        int rs = Calc.run("20 + 5");

        assertThat(rs).isEqualTo(25);
    }

    @Test
    @DisplayName("1000 + 280 == 1280")
    void t4() {
        assertThat(Calc.run("1000 + 280")).isEqualTo(1280);
    }

    @Test
    @DisplayName("2 - 1 == 1")
    void t5() {
        assertThat(Calc.run("2 - 1")).isEqualTo(1);
    }
}
