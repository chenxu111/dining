package com.nbtlxx.dining.common;

import java.io.InputStream;

/**
 * Created by chenxu on 17/12/29.
 */
public class ImageHolder {

    private InputStream inputStream;
    private String fileName;

    public ImageHolder() {
    }

    public ImageHolder(InputStream inputStream, String fileName) {
        this.inputStream = inputStream;
        this.fileName = fileName;
    }

    public ImageHolder(String originalFilename, InputStream inputStream) {
        this.fileName=originalFilename;
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
