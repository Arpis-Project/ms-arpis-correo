
CREATE SEQUENCE REPORTUSER.SEQ_AP_EMAIL_TP_TIPO_ENVIO
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 9999
    NOCACHE;
CREATE TABLE REPORTUSER.AP_EMAIL_TP_TIPO_ENVIO (
  ID NUMBER(4, 0) DEFAULT REPORTUSER.SEQ_AP_EMAIL_TP_TIPO_ENVIO.NEXTVAL,
  NOMBRE VARCHAR(100) NOT NULL,
  DESCRIPCION VARCHAR(255),
  ACTIVO CHAR(1) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE SEQUENCE REPORTUSER.SEQ_AP_EMAIL_TP_TIPO_ERROR
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 9999
    NOCACHE;
CREATE TABLE REPORTUSER.AP_EMAIL_TP_TIPO_ERROR (
  ID NUMBER(4, 0) DEFAULT REPORTUSER.SEQ_AP_EMAIL_TP_TIPO_ERROR.NEXTVAL,
  NOMBRE VARCHAR(100) NOT NULL,
  DESCRIPCION VARCHAR(255),
  ACTIVO CHAR(1) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE SEQUENCE REPORTUSER.SEQ_AP_EMAIL_TP_TIPO_CORREO
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 9999
    NOCACHE;
CREATE TABLE REPORTUSER.AP_EMAIL_TP_TIPO_CORREO (
  ID NUMBER(4, 0) DEFAULT REPORTUSER.SEQ_AP_EMAIL_TP_TIPO_CORREO.NEXTVAL,
  NOMBRE VARCHAR(100) NOT NULL,
  DESCRIPCION VARCHAR(255),
  ACTIVO CHAR(1) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE SEQUENCE REPORTUSER.SEQ_AP_EMAIL_TD_CORREOS
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 999999999
    NOCACHE;	
CREATE TABLE REPORTUSER.AP_EMAIL_TD_CORREOS (
  ID NUMBER(9, 0) DEFAULT REPORTUSER.SEQ_AP_EMAIL_TD_CORREOS.NEXTVAL,
  EMAIL VARCHAR(100) NOT NULL,
  NOMBRE VARCHAR(150),
  PASSWORD VARCHAR(150),
  ID_TIPO_CORREO NUMBER(4, 0) NOT NULL,
  ACTIVO CHAR(1) NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (ID_TIPO_CORREO) REFERENCES REPORTUSER.AP_EMAIL_TP_TIPO_CORREO (ID)
);

CREATE SEQUENCE REPORTUSER.SEQ_AP_EMAIL_TD_PROYECTOS
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 999999999
    NOCACHE;	
CREATE TABLE REPORTUSER.AP_EMAIL_TD_PROYECTOS (
  ID NUMBER(9, 0) DEFAULT REPORTUSER.SEQ_AP_EMAIL_TD_PROYECTOS.NEXTVAL,
  NOMBRE VARCHAR(150) NOT NULL,
  DESCRIPCION VARCHAR(255),
  ACTIVO CHAR(1) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE SEQUENCE REPORTUSER.SEQ_AP_EMAIL_TD_PROY_CORREOS
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    NOCACHE;	
CREATE TABLE REPORTUSER.AP_EMAIL_TD_PROY_CORREOS (
  ID NUMBER(19, 0) DEFAULT REPORTUSER.SEQ_AP_EMAIL_TD_PROY_CORREOS.NEXTVAL,
  ID_PROYECTO NUMBER(9, 0) NOT NULL,
  ID_CORREO NUMBER(9, 0) NOT NULL,
  ID_TIPO_ENVIO NUMBER(4, 0) NOT NULL,
  FECHA_CREACION TIMESTAMP NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (ID_PROYECTO) REFERENCES REPORTUSER.AP_EMAIL_TD_PROYECTOS (ID),
  FOREIGN KEY (ID_CORREO) REFERENCES REPORTUSER.AP_EMAIL_TD_CORREOS (ID),
  FOREIGN KEY (ID_TIPO_ENVIO) REFERENCES REPORTUSER.AP_EMAIL_TP_TIPO_ENVIO (ID)
);

CREATE SEQUENCE REPORTUSER.SEQ_AP_EMAIL_TD_PROY_ERROR
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    NOCACHE;
CREATE TABLE REPORTUSER.AP_EMAIL_TD_PROY_ERROR (
  ID NUMBER(19, 0) DEFAULT REPORTUSER.SEQ_AP_EMAIL_TD_PROY_ERROR.NEXTVAL,
  ID_PROYECTO NUMBER(9, 0) NOT NULL,
  ID_CORREO NUMBER(9, 0) NOT NULL,
  ID_TIPO_ENVIO NUMBER(4, 0) NOT NULL,
  ID_TIPO_ERROR NUMBER(4, 0) NOT NULL,
  FECHA_CREACION TIMESTAMP NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (ID_PROYECTO) REFERENCES REPORTUSER.AP_EMAIL_TD_PROYECTOS (ID),
  FOREIGN KEY (ID_CORREO) REFERENCES REPORTUSER.AP_EMAIL_TD_CORREOS (ID),
  FOREIGN KEY (ID_TIPO_ENVIO) REFERENCES REPORTUSER.AP_EMAIL_TP_TIPO_ENVIO (ID),
  FOREIGN KEY (ID_TIPO_ERROR) REFERENCES REPORTUSER.AP_EMAIL_TP_TIPO_ERROR (ID)
);

CREATE SEQUENCE REPORTUSER.SEQ_AP_EMAIL_TD_USUARIOS
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 9999
    NOCACHE;
CREATE TABLE REPORTUSER.AP_EMAIL_TD_USUARIOS (
  ID NUMBER(4, 0) DEFAULT REPORTUSER.SEQ_AP_EMAIL_TD_USUARIOS.NEXTVAL,
  LOGIN VARCHAR(100) NOT NULL,
  PASSWORD VARCHAR(255),
  NOMBRE_EMPRESA VARCHAR(100),
  ACTIVO CHAR(1) NOT NULL,
  FECHA_CREACION TIMESTAMP NOT NULL,
  FECHA_MODIFICACION TIMESTAMP NOT NULL,
  URL_INTEGRACION VARCHAR(255),
  PRIMARY KEY (ID)
);

--===============================================================================
--===============================================================================
--===============================================================================

INSERT INTO REPORTUSER.AP_EMAIL_TD_USUARIOS (LOGIN,PASSWORD,NOMBRE_EMPRESA,ACTIVO,FECHA_CREACION,FECHA_MODIFICACION,URL_INTEGRACION)
VALUES('david', 'pass', 'david spa', 'S', SYSDATE, SYSDATE, 'URL' )

INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_ENVIO (NOMBRE,DESCRIPCION,ACTIVO)
VALUES('DIRECTO',NULL,'S');
INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_ERROR (NOMBRE,DESCRIPCION,ACTIVO)
VALUES('GENERAL',NULL,'S');
INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_CORREO (NOMBRE,DESCRIPCION,ACTIVO)
VALUES('GERENCIAL',NULL,'S');

INSERT INTO REPORTUSER.AP_EMAIL_TD_CORREOS (EMAIL,NOMBRE,PASSWORD,ID_TIPO_CORREO,ACTIVO)
VALUES('david@arpis.cl', 'David', 'pass', 1,'S');

INSERT INTO REPORTUSER.AP_EMAIL_TD_PROYECTOS (NOMBRE,DESCRIPCION,ACTIVO)
VALUES('PRUEBA', NULL, 'S');

INSERT INTO REPORTUSER.AP_EMAIL_TD_PROY_CORREOS (ID_PROYECTO,ID_CORREO,ID_TIPO_ENVIO,FECHA_CREACION)
VALUES(1,1,1,SYSDATE);


