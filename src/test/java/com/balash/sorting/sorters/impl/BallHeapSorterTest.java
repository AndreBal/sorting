package com.balash.sorting.sorters.impl;

import com.balash.sorting.factory.impl.RandomBallFactory;
import com.balash.sorting.model.Ball;
import com.balash.sorting.model.comparator.BallComparatorByPrice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BallHeapSorterTest {

    @Test
    void sort() {
        ArrayList<Ball> ballsBasket = new ArrayList<>();
        for(int i = 0;i<20;i++){
            ballsBasket.add(new RandomBallFactory().createBall());
        }
        List<Integer> resultCustomSort = new BallHeapSorter(new BallComparatorByPrice()).sort(ballsBasket).stream().map(ball -> ball.getPrice()).collect(Collectors.toList());
        List<Integer> resultDefaultSort = ballsBasket.stream().map(ball -> ball.getPrice()).sorted().collect(Collectors.toList());
        assertEquals( resultDefaultSort,resultCustomSort);
    }
}