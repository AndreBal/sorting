package com.balash.sorting.factory.impl;

import com.balash.sorting.factory.BallFactory;
import com.balash.sorting.model.Ball;
import com.balash.sorting.model.characteristic.BallType;
import com.balash.sorting.model.characteristic.Color;

import java.util.Random;

public class RandomBallFactory implements BallFactory {

    private static final Random RANDOM = new Random();
    private static final int MAX_SIZE = 100;
    private static final int MAX_PRICE = 1000;

    @Override
    public Ball createBall() {
        Ball ball = new Ball();
        ball.setColor(getRandomColor());
        ball.setBallType(getRandomBallType());
        ball.setPrice(RANDOM.nextInt(MAX_PRICE));
        ball.setSize(RANDOM.nextInt(MAX_SIZE));
        return ball;
    }

    private Color getRandomColor() {
        Color[] values = Color.class.getEnumConstants();
        return values[RANDOM.nextInt(values.length)];
    }
    private BallType getRandomBallType() {
        BallType[] values = BallType.class.getEnumConstants();
        return values[RANDOM.nextInt(values.length)];
    }
}
