CREATE TABLE `cursos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) DEFAULT NULL,
  `descripcion` varchar(120) DEFAULT NULL,
  `instructor` varchar(45) DEFAULT NULL,
  `duracion` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `cursos` VALUES (1,'Guia Completa JUnit y Mockito incluye Spring Boot Test','Aprende desde cero JUnit 5 y Mockito en Spring Boot 2','Andres Guzman',15.12),(2,'Master Completo en Java de cero a experto con IntelliJ','Aprende Java SE, Jakarta EE, Hibernate y mas','Andres Guzman',98.53),(3,'Spring Framework 5: Creando webapp de cero a experto','Construye aplicaciones web con Spring Framework 5 & Spring Boot','Andres Guzman',41.51),(4,'Angular & Spring Boot: Creando web app full stack','Desarrollo frontend con Angular y backend Spring Boot 2','Andres Guzman',23.54),(5,'Microservicios con Spring Boot y Spring Cloud Netflix Eureka','Construye Microservicios Spring Boot 2, Eureka, Spring Cloud','Andres Guzman',19.55),(15,'Microservicios con Spring Boot y Spring Cloud Netflix Eureka','Construye Microservicios Spring Boot 2, Eureka, Spring Cloud','Andres Guzman',19.55),(16,'Guia Completa JUnit y Mockito incluye Spring Boot Test','Aprende desde cero JUnit 5 y Mockito en Spring Boot ','Andres Guzman',15.12);

