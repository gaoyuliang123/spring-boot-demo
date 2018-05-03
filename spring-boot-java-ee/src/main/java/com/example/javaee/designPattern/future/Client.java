package com.example.javaee.designPattern.future;

public class Client {
    public Data request(final String queryStr) {
        FutureData futureData = new FutureData();
        new Thread(){
            @Override
            public void run() {
                // RealData的构建很慢,所以在单独的线程中进行
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();
        return futureData;
    }
}
