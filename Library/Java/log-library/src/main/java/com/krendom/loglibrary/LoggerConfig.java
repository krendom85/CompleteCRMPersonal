package com.krendom.loglibrary;

import java.nio.file.Files;
import java.nio.file.Paths;

public class LoggerConfig {
    private String logFile = "";
    private String path = "";
    private Integer maxFiles = 9;
    private Integer maxNumberLines = 3000;

    public LoggerConfig() {
        super();
    }

    public String getLogFile() {
        return logFile;
    }

    public LoggerConfig setLogFile(String logFile) {
        if(logFile != null && !logFile.trim().isEmpty()){
            this.logFile = logFile;
            return this;
        }
        throw new IllegalArgumentException("El nombre del archivo de log no puede ser nulo o vacío.");
    }

    public String getPath() {
        return path;
    }

    public LoggerConfig setPath(String path) {
        if(path != null && !path.trim().isEmpty()){
            boolean exists = Files.exists(Paths.get(path));
            if(!exists){
                throw new IllegalArgumentException("La ruta del archivo de log no existe: " + path);
            }
            this.path = path;
            return this;
        }
        throw new IllegalArgumentException("La ruta del archivo de log no puede ser nula o vacía.");
    }

    public Integer getMaxFiles() {
        return maxFiles;
    }

    public LoggerConfig setMaxFiles(Integer maxFiles) {
        if(maxFiles != null && maxFiles > 0){
            this.maxFiles = maxFiles;
            return this;
        }        
        throw new IllegalArgumentException("El número máximo de archivos de log debe ser un entero positivo.");
    }

    public Integer getMaxNumberLines() {
        return maxNumberLines;
    }

    public LoggerConfig setMaxNumberLines(Integer maxNumberLines) {
        if(maxNumberLines != null && maxNumberLines > 0){
            this.maxNumberLines = maxNumberLines;
            return this;
        }
        throw new IllegalArgumentException("El número máximo de líneas por archivo de log debe ser un entero positivo.");
    }
}
