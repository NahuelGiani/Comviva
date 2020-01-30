# Pasos a seguir

## 1) Entrar en el directorio del proyecto (en mi caso):
cd /home/nahuel/eclipse-workspace/ejercicioTres

## 2) Generar el archivo .war con Maven
mvn clean package

## 3) Levantar una instancia local de MYSQL. 
Ponerle de nombre de la base: "comviva", con un usuario comviva@comviva (configurable desde application.properties)

## 4) Mover el war generado a un contenedor de servlets (Tomcat 8.5), y dejarlo dentro de la carpeta webapps

## 5) Levantar el servidor desde la carpeta ./bin
./startup.sh (startup.bat en windows)

## El context path de la aplicación es /ejerciciotres
