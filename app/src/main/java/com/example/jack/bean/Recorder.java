package com.example.jack.bean;

/**
 * Created by Jack on 2015/9/10.
 */
public class Recorder {
    float time;
    String filePath;

    public Recorder(float time, String filePath) {
        super();
        this.time = time;
        this.filePath = filePath;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
