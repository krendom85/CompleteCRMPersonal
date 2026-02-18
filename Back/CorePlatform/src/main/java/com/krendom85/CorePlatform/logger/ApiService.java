package com.krendom85.CorePlatform.logger;

import com.krendom.loglibrary.*;;

public class ApiService {
    private static final String serviceName = "ApiService";
    private static final String path = System.getProperty("user.dir");
    private final Integer numberLine = 5000;
    private final Integer numberFiles = 10;

    private final LoggerConfig config = new LoggerConfig();
    private final Logger logger;

    private static volatile ApiService instance;
    private static final Object mutex = new Object();

    private ApiService() {
        config.setLogFile(serviceName)
                .setPath(path)
                .setMaxFiles(numberFiles)
                .setMaxNumberLines(numberLine);
        logger = new Logger(config);
    }

    public static ApiService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new ApiService();
                }
            }
        }
        return instance;
    }

    // Métodos para escribir logs
    // Método para obtener fecha y hora actual en formato deseado
    private String getCurrentTimestamp() {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    // Métodos mejorados para escribir logs con formato y mutex
    public void logError(String usuario, String mensaje) {
        String log = String.format("%s | ERROR | %s | %s", getCurrentTimestamp(), usuario, mensaje);
        synchronized (mutex) {
            try{
                logger.writeLog(log);
            }
             catch (Exception e) {
                System.err.println("Error al escribir el log: " + e.getMessage());
            }
        }
    }

    public void logInfo(String usuario, String mensaje) {
        String log = String.format("%s | INFO | %s | %s", getCurrentTimestamp(), usuario, mensaje);
        synchronized (mutex) {
            try{
                logger.writeLog(log);
            }
             catch (Exception e) {
                System.err.println("Error al escribir el log: " + e.getMessage());
            }
        }
    }

    public void logWarning(String usuario, String mensaje) {
        String log = String.format("%s | WARNING | %s | %s", getCurrentTimestamp(), usuario, mensaje);
        synchronized (mutex) {
            try{
                logger.writeLog(log);
            }
             catch (Exception e) {
                System.err.println("Error al escribir el log: " + e.getMessage());
            }
        }
    }
}
