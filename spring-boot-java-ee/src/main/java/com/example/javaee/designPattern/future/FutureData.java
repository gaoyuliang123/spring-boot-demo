package com.example.javaee.designPattern.future;

/**
 * FutureData是RealData的包装
 */
public class FutureData implements Data {
    private RealData realData = null;
    private boolean isRead = false;

    public synchronized void setRealData(RealData realData) {
        if (isRead) {
            return;
        }
        this.realData = realData;
        isRead = true;
        // RealData已经被注入，通知getResult()
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        // 会等待RealData构造完成
        if (!isRead) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }
}
