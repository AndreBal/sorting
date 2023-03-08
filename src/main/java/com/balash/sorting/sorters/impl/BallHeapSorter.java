package com.balash.sorting.sorters.impl;

import com.balash.sorting.model.Ball;
import com.balash.sorting.sorters.BallSorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BallHeapSorter implements BallSorter {

    private Comparator<Ball> ballComparator;

    public BallHeapSorter(Comparator<Ball> ballComparator){
        this.ballComparator = ballComparator;
    }

    private static final int THREADS_NUMBER = 2;
    @Override
    public ArrayList<Ball> sort(ArrayList<Ball> ballsBasket) {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS_NUMBER);

        ArrayList<Ball> firstHalf = new ArrayList<>(ballsBasket.subList(0, ballsBasket.size()/2));
        ArrayList<Ball> secondHalf = new ArrayList<>(ballsBasket.subList(ballsBasket.size()/2, ballsBasket.size()));
        try {
            Future<?> task1 = executor.submit(new RunnableSort(firstHalf));
            Future<?> task2 = executor.submit(new RunnableSort(secondHalf));

            task1.get();
            task2.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return merge(firstHalf,secondHalf);
    }

    private void heapSort(ArrayList<Ball> ballsBasket){
        int n = ballsBasket.size();

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(ballsBasket, n, i);

        for (int i = n - 1; i >= 0; i--) {
            Ball temp = ballsBasket.get(0);
            ballsBasket.set(0, ballsBasket.get(i));
            ballsBasket.set(i, temp);

            heapify(ballsBasket, i, 0);
        }
    }

    private void heapify(ArrayList<Ball> bskt, int n, int i)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && ballComparator.compare(bskt.get(l),bskt.get(largest))>0) {
            largest = l;
        }

        if (r < n && ballComparator.compare(bskt.get(r),bskt.get(largest))>0) {
            largest = r;
        }

        if (largest != i) {
            Ball swap = bskt.get(i);
            bskt.set(i, bskt.get(largest));
            bskt.set(largest, swap);

            heapify(bskt, n, largest);
        }
    }

    class RunnableSort implements Runnable {
        private ArrayList<Ball> ballsBasket;

        public RunnableSort(ArrayList<Ball> ballsBasket) {
            this.ballsBasket = ballsBasket;
        }

        @Override
        public void run() {
            heapSort(ballsBasket);
        }
    }

    private ArrayList<Ball> merge(ArrayList<Ball> array1, ArrayList<Ball> array2){
        ArrayList<Ball> result = new ArrayList<>();
        int i, j;
        for (i = 0, j = 0; i < array1.size() && j < array2.size();) {
            if (ballComparator.compare(array1.get(i),array2.get(j))<0) {
                result.add(array1.get(i));
                i++;
            } else {
                result.add(array2.get(j));
                j++;
            }
        }

        while(i < array1.size()) result.add(array1.get(i++));
        while(j < array2.size()) result.add(array2.get(j++));
        return result;
    }
}
