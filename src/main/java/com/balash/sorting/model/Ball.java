package com.balash.sorting.model;

import com.balash.sorting.model.characteristic.BallType;
import com.balash.sorting.model.characteristic.Color;

import java.util.Objects;

public class Ball{
    private Color color;
    private BallType ballType;
    private int size;
    private int price;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BallType getBallType() {
        return ballType;
    }

    public void setBallType(BallType ballType) {
        this.ballType = ballType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ball)) return false;
        Ball ball = (Ball) o;
        return size == ball.size && price == ball.price && color == ball.color && ballType == ball.ballType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, ballType, size, price);
    }

    @Override
    public String toString() {
        return "Ball{" +
                "color=" + color +
                ", ballType=" + ballType +
                ", size=" + size +
                ", price=" + price +
                '}';
    }

}
