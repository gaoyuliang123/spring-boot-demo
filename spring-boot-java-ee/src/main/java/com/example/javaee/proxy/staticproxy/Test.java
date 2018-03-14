package com.example.javaee.proxy.staticproxy;

public class Test {
    public static void main(String[] args){
        RealMovie realMovie = new RealMovie();
        Cinema cinema = new Cinema(realMovie);
        cinema.play();
    }
}
