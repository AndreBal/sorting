package com.balash.sorting.sorters;

import com.balash.sorting.model.Ball;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface BallSorter {

    ArrayList<Ball> sort(ArrayList<Ball> ballsBasket);

}
