package com.balash.sorting.model.comparator;

import com.balash.sorting.model.Ball;

import java.util.Comparator;

public class BallComparatorByPrice implements Comparator<Ball> {
    @Override
    public int compare(Ball ball1, Ball ball2) {
        return ball1.getPrice() - ball2.getPrice();
    }
}
