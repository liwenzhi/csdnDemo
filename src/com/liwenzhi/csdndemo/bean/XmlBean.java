package com.liwenzhi.csdndemo.bean;

/**
 * Bean文件，定义数据原型
 */
public class XmlBean {
    //定义属性
    private String startTime;
    private int maxPress;
    private int minPress;
    private int heartRate;
    private int pulseRate;

    //定义get和set方法
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getMaxPress() {
        return maxPress;
    }

    public void setMaxPress(int maxPress) {
        this.maxPress = maxPress;
    }

    public int getMinPress() {
        return minPress;
    }

    public void setMinPress(int minPress) {
        this.minPress = minPress;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(int pulseRate) {
        this.pulseRate = pulseRate;
    }

    //定义toString方法


    public String toString() {
        return "XmlBean{" +
                "startTime='" + startTime + '\'' +
                ", maxPress=" + maxPress +
                ", minPress=" + minPress +
                ", heartRate=" + heartRate +
                ", pulseRate=" + pulseRate +
                '}';
    }
}
