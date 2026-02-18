package com.krendom.loglibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private LoggerConfig config;
    private int lineCount;


    public Logger(LoggerConfig config) {
        this.config = config;
        try {
            this.lineCount = countLines(new File(getLogFilePath(0)));
        } catch (IOException e) {
            this.lineCount = 0;
        }
    }

    private String getLogFilePath(int index) {
        if(index == 0)
        {
            return config.getPath() + "/" + config.getLogFile() + ".log";
        }else{
            return config.getPath() + "/" + config.getLogFile() + "_" + index + ".log";
        }
    }

    private int countLines(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            int lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
            return lines;
        }
    } 

    private void rotateFiles(){
        for (int i = config.getMaxFiles() - 1; i >= 1; i--){
            File oldFile = new File(getLogFilePath(i));
            File newFile = new File(getLogFilePath(i + 1));
            if(oldFile.exists()){
                oldFile.renameTo(newFile);
            }
        }
        File currentLog = new File(getLogFilePath(0));
        File rotatedLog = new File(getLogFilePath(1));
        if(currentLog.exists()){
            currentLog.renameTo(rotatedLog);
        }

        try(FileWriter writer = new FileWriter(getLogFilePath(0)))
        {

        }catch(IOException e){
            e.printStackTrace();
        }

        lineCount = 0;
    }

    public void writeLog(String message) throws IOException {
        try(FileWriter fw = new FileWriter(getLogFilePath(0),true))
        {
            fw.write(message + System.lineSeparator());
            lineCount++;
        }

        if (lineCount > config.getMaxNumberLines()) {
            rotateFiles();
        }
    }
}
