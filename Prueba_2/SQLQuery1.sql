CREATE DATABASE pruebaNuxiba;
USE pruebaNuxiba;



CREATE TABLE costos(
	tipoDeLlamada VARCHAR(15) PRIMARY KEY,
	costo DECIMAL(10,4)
);



CREATE TABLE logDial(
	idLlamada VARCHAR(10) PRIMARY KEY,
	fechaDeLlamada dateTime,
	tiempoDialogo smallint,
	telefono VARCHAR(10),
	tipoDeLlamada VARCHAR(15),
	CONSTRAINT FK_costosLogDial FOREIGN KEY (tipoDeLlamada)
	REFERENCES costos(tipoDeLlamada)
);


SELECT        idLlamada ,fechaDeLlamada , tiempoDialogo , Telefono, tipoDeLlamada 
FROM            logDial
WHERE        (fechaDeLlamada BETWEEN '01-02-2020' AND '29-02-2020') AND (tipoDeLlamada = 'Cel LD')

