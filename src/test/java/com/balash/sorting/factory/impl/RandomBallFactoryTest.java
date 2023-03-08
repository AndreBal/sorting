package com.balash.sorting.factory.impl;

import com.balash.sorting.model.Ball;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomBallFactoryTest {

    @Test
    void createBall() {
        Ball testBall = new RandomBallFactory().createBall();
        assertAll(() -> assertNotNull(testBall.getBallType()),
                () -> assertNotNull(testBall.getColor()),
                () -> assertTrue(testBall.getPrice() < 1000),
                () -> assertTrue(testBall.getSize() < 100));
    }
}