package com.krendom.loglibrary;


public class LoggerExample {
    public static void main(String[] args) {
        try{
            LoggerConfig config = new LoggerConfig()
                    .setPath("C:\\Users\\chris\\Downloads\\Logs")
                    .setLogFile("application")
                    .setMaxNumberLines(5)
                    .setMaxFiles(3);

            Logger logger = new Logger(config);
            for (int i = 1; i <= 20; i++) {
                logger.writeLog("This is log message number " + i);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
