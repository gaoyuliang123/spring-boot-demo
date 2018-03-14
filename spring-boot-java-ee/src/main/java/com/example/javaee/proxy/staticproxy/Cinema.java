package com.example.javaee.proxy.staticproxy;

/**
 * 代理模式可以在不修改被代理对象的基础上，通过扩展代理类，进行一些功能的附加与增强。
 * 值得注意的是，代理类和被代理类应该共同实现一个接口，或者是共同继承某个类。
 */
public class Cinema implements Movie {

    RealMovie realMovie;

    public Cinema(RealMovie realMovie) {
        this.realMovie = realMovie;
    }

    public void guanggao(boolean isStart) {
        if (isStart) {
            System.out.println("电影马上开始了，爆米花、可乐、口香糖9.8折，快来买啊！");
        } else {
            System.out.println("电影马上结束了，爆米花、可乐、口香糖9.8折，买回家吃吧！");
        }
    }

    @Override
    public void play() {
        guanggao(true);
        realMovie.play();
        guanggao(false);
    }
}
