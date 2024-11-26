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
}
