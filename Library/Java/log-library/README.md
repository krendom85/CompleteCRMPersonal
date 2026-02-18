# log-library

Librería Java para manejo de logs internos con soporte para niveles INFO, WARN, ERROR y DEBUG. Permite guardar logs en archivos y mostrar en consola. Fácil integración en otros proyectos Java agregando el .jar como dependencia.

## Ejemplo de uso

```java
import com.example.loglibrary.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.info("Mensaje informativo");
        logger.warn("Mensaje de advertencia");
        logger.error("Mensaje de error");
        logger.debug("Mensaje de depuración");
    }
}
```

## Instalación

1. Compila el proyecto con Maven:
   ```
   mvn clean package
   ```
2. Agrega el archivo `log-library-1.0.0.jar` como dependencia en tu proyecto.

## Documentación
- Soporte para niveles: INFO, WARN, ERROR, DEBUG
- Salida a consola y archivo
- Fácil de usar e integrar

---
Autor: Tu Nombre


javac -cp .\src\main\java .\src\test\java\com\krendom\loglibrary\LoggerExample.java

java -cp ".\src\main\java;.\src\test\java" com.krendom.loglibrary.LoggerExample

mvn clean package

mvn install:install-file -Dfile="D:\Chriss\Proyects\CompleteCRMPersonal\Library\Java\log-library\target\log-library-1.0.0.jar" -DgroupId=krendom -DartifactId=log-library -Dversion="1.0.0" -Dpackaging=jar

<dependency>
    <groupId>krendom</groupId>
    <artifactId>log-library</artifactId>
    <version>1.0.0</version>
</dependency>