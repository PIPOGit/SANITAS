# SANITAS: Prueba Calculadora

Este repositorio contiene dos cosas:
* Calculadora
* MicroServices Manager

## SETUP MicroServices Manager
Esta aplicación es simplemente un **Spring BOOT Admin** para gestionar microservicios.

Aunque es una aplicación que ya se ha está utilizando en otros proyectos, explicamos aquí cómo gestionarla.

### Configuración de la aplicación
Abrimos un terminal **PowerShell**.
```shell
# Título del terminal
$host.ui.RawUI.WindowTitle = "MicroServices Manager Console"

# Nos ubicamos en la carpeta oportuna. Por ejemplo:
Set-Location "C:\Eclipse\WorkSpace\SANITAS\manager-apis-admin";

# Creamos una carpeta para los logs
New-Item logs;

# Actualizamos las versiones de las dependencias.
mvn -D"generateBackupPoms=false" `
clean `
versions:update-parent `
versions:display-dependency-updates `
versions:use-latest-releases `
2>&1 >"logs/maven.versions.log";

# Arrancamos la aplicación
mvn clean spring-boot:run 2>&1 >"logs/maven.spring.boot.log";
```

### Ejecución de un test
Probemos que la aplicación está correctamente funcionando en el puerto: **9007**.

```shell
# Web test
# Abrimos otro terminal PowerShell
$host.ui.RawUI.WindowTitle = "MicroServices Manager Test Console"

# Nos ubicamos también en la carpeta oportuna. Por ejemplo:
Set-Location "C:\Eclipse\WorkSpace\SANITAS\manager-apis-admin";

# Comprobamos, vía **actuators** que la aplicación responde.
# (*Recordemos que los logs están en la carpeta: "logs"*)
cls; IRM http://localhost:9007/actuator/health | ConvertTo-Json
cls; IRM http://localhost:9007/actuator/info | ConvertTo-Json

# Abrimos un navegador:
Start-Process http://127.0.0.1:9007

# Si queremos parar la aplicación
cls; IRM -Headers @{"Content-Type"="application/json"} -Method POST http://localhost:9007/actuator/shutdown | ConvertTo-Json
```


## SETUP Calculadora
Aquí viene la prueba en sí.

Para ello, empezamos por crear un proyecto Spring BOOT en: https://start.spring.io/

A continuación:
+ Tuneamos el POM convenientemente.
+ Tuneamos el "application.yml" y el "banner.txt".

Idealmente, vamos a crear un microservicio con dos operaciones:

+ GET /suma/AAA/BBB
+ GET /resta/AAA/BBB

Así que...

Primeros pasos, para ajustar versiones.
Abrimos otro terminal **PowerShell**.

```shell
# Título del terminal
$host.ui.RawUI.WindowTitle = "Calculadora Console"

# Nos ubicamos en la carpeta oportuna. Por ejemplo:
Set-Location "C:\Eclipse\WorkSpace\SANITAS\calculadora";

# Creamos una carpeta para los logs
New-Item logs;

# Actualizamos las versiones de las dependencias.
mvn -D"generateBackupPoms=false" `
clean `
versions:update-parent `
versions:display-dependency-updates `
versions:use-latest-releases `
2>&1 >"logs/maven.versions.log";

# Dejamos la versión de Spring BOOT en la: "**2.5.7**", por problemas con **SpringFOX**.

# Incorporamos el JAR del Tracer al repo maven local.
mvn -e -X install:install-file `
-Dfile='\Eclipse\WorkSpace\SANITAS\calculadora\setup\tracer-1.0.0.jar' `
-Dsources='\Eclipse\WorkSpace\SANITAS\calculadora\setup\tracer-1.0.0-sources.jar' `
-Djavadoc='\Eclipse\WorkSpace\SANITAS\calculadora\setup\tracer-1.0.0-javadoc.jar' `
-DgroupId='io.corp.calculator' `
-DartifactId='tracer' `
-Dpackaging='jar' `
-Dversion='1.0.0' `
2>&1 >'logs/maven.install.tracer.log';
# NOTA: TracerImpl NO IMPLEMENTA TracerAPI!!!

# Incorporamos la dependencia al POM.
      <dependency>
          <groupId>io.corp.calculator</groupId>
          <artifactId>tracer</artifactId>
          <version>1.0.0</version>
      </dependency>

# Implementamos el código necesario.

# Ejecutamos el microservicio
mvn clean spring-boot:run 2>&1 >"logs/maven.spring.boot.log";
```

## Prueba de ejecución
Para probar que funciona abrimos un nuevo terminal PowerShell.

```shell
# Prueba de suma:
IRM http://localhost:8080/suma/7/5
# Resultado: 12.0

# Prueba de resta:
IRM http://localhost:8080/resta/7/5
# Resultado: 2.0

# El Swagger de la aplicación está en:
Start-Process http://127.0.0.1:8080/swagger-ui/index.html
```

Para detener las aplicaciones:

```shell
# Shutdown Microservicio
IRM -Headers @{"Content-Type"="application/json"} -Method POST http://localhost:8080/actuator/shutdown | ConvertTo-Json

# Shutdown Manager Application
IRM -Headers @{"Content-Type"="application/json"} -Method POST http://localhost:9007/actuator/shutdown | ConvertTo-Json
```

## Lógica de Implementación
En primer lugar, indicar que no hemos implementado los tests funcionales por la cuestión del tiempo.

Notas:
+ El "API", tanto su declaración como su implementación, se encuentra en la carpeta: "controllers".
+ La lógica de negocio asociada, se encuentra en la carpeta: "service".
+ Se ha incorporado SpringFox para documentar el API de acuerdo con los estándares.
+ Se ha declarado alguna excepción, aunque no se ha planteado ningún tratamiento especial de las mismas ni tampoco se controlan los distintos tipos de situaciones posibles.

