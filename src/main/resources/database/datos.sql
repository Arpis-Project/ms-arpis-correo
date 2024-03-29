/*
 AP_EMAIL_TD_USUARIOS
*/
INSERT INTO REPORTUSER.AP_EMAIL_TD_USUARIOS (LOGIN,PASSWORD,NOMBRE_EMPRESA,ACTIVO,FECHA_CREACION,FECHA_MODIFICACION,URL_INTEGRACION)
VALUES('david', 'pass', 'david spa', 'S', SYSDATE, SYSDATE, 'URL');

/*
 AP_EMAIL_TD_PROYECTOS
*/
INSERT INTO REPORTUSER.AP_EMAIL_TD_PROYECTOS (NOMBRE,DESCRIPCION,ACTIVO)
VALUES('Inventario Integración', '', 'S');

/*
 AP_EMAIL_TD_CORREOS
*/
INSERT INTO REPORTUSER.AP_EMAIL_TD_CORREOS (EMAIL,NOMBRE,PASSWORD,ACTIVO,ID_PROYECTO)
VALUES ('david@arpis.cl', 'David Nilo', 'asd', 'S',
   (SELECT ID FROM REPORTUSER.AP_EMAIL_TD_PROYECTOS WHERE NOMBRE = 'Inventario Integración'));
INSERT INTO REPORTUSER.AP_EMAIL_TD_CORREOS (EMAIL,NOMBRE,PASSWORD,ACTIVO,ID_PROYECTO)
VALUES ('stephanie@arpis.cl', 'Stephanie Morales', NULL, 'S',
   (SELECT ID FROM REPORTUSER.AP_EMAIL_TD_PROYECTOS WHERE NOMBRE = 'Inventario Integración'));
INSERT INTO REPORTUSER.AP_EMAIL_TD_CORREOS (EMAIL,NOMBRE,PASSWORD,ACTIVO,ID_PROYECTO)
VALUES ('patricio@arpis.cl', 'Patricio Díaz', NULL, 'S',
   (SELECT ID FROM REPORTUSER.AP_EMAIL_TD_PROYECTOS WHERE NOMBRE = 'Inventario Integración'));

/*
 AP_EMAIL_TD_ETAPAS_PROYECTO
*/
INSERT INTO REPORTUSER.AP_EMAIL_TD_ETAPAS_PROYECTO (NOMBRE,DESCRIPCION,ACTIVO,ID_PROYECTO)
VALUES ('Finalización inventario', '', 'S',
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TD_PROYECTOS WHERE NOMBRE = 'Inventario Integración'));

/*
 AP_EMAIL_TP_TIPO_CORREO
*/
INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_CORREO (NOMBRE,DESCRIPCION,ACTIVO)
VALUES ('SERVICIO', 'Casilla que se conecta al servicio de correos', 'S');
INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_CORREO (NOMBRE,DESCRIPCION,ACTIVO)
VALUES ('DE', 'Remitente correo', 'S');
INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_CORREO (NOMBRE,DESCRIPCION,ACTIVO)
VALUES ('PARA', 'Receptor correo', 'S');
INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_CORREO (NOMBRE,DESCRIPCION,ACTIVO)
VALUES ('CC', 'Con copia', 'S');
INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_CORREO (NOMBRE,DESCRIPCION,ACTIVO)
VALUES ('CCO', 'Con copia ocuta', 'S');

/*
 AP_EMAIL_TP_TIPO_RECEPTOR
*/
INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_RECEPTOR (NOMBRE,DESCRIPCION,ACTIVO)
VALUES ('ADMIN', 'Administrador(a) sistema', 'S');
INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_RECEPTOR (NOMBRE,DESCRIPCION,ACTIVO)
VALUES ('AUDITORIA', 'Equipo Auditoría', 'S');
INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_RECEPTOR (NOMBRE,DESCRIPCION,ACTIVO)
VALUES ('JEFE_TIENDA', 'Jefe de Tienda', 'S');
INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_RECEPTOR (NOMBRE,DESCRIPCION,ACTIVO)
VALUES ('VISITA', 'Visitante', 'S');
INSERT INTO REPORTUSER.AP_EMAIL_TP_TIPO_RECEPTOR (NOMBRE,DESCRIPCION,ACTIVO)
VALUES ('GERENTE', 'Gerente', 'S');
-- ALTER TABLE REPORTUSER.AP_EMAIL_TD_PROY_CORREOS ADD ID_TIPO_RECEPTOR NUMBER(2,0) NULL;
-- ALTER TABLE REPORTUSER.AP_EMAIL_TD_PROY_CORREOS ADD CONSTRAINT FK_AP_EMAIL_TP_TIPO_RECEPTOR FOREIGN KEY (ID_TIPO_RECEPTOR) REFERENCES REPORTUSER.AP_EMAIL_TP_TIPO_RECEPTOR(ID);


INSERT INTO REPORTUSER.AP_EMAIL_TP_TEMPLATES (CONTENIDO,ACTIVO)
VALUES('<html><body>Nombre <p style="color:red;"><span th:text="${nombre}"></span></p></body></html>', 'S');

INSERT INTO REPORTUSER.AP_EMAIL_TP_VARIABLES (ID_TEMPLATE,NOMBRE,VALOR)
VALUES(1, 'nombre', 'David Nilo');

/*
 AP_EMAIL_TP_TIPO_CORREO
*/
INSERT INTO REPORTUSER.AP_EMAIL_TD_PROY_CORREOS (ID_ETAPA_PROYECTO,ID_CORREO,ID_TIPO_CORREO,FECHA_CREACION,ID_TEMPLATE,STORE_NO)
VALUES (
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TD_ETAPAS_PROYECTO WHERE NOMBRE = 'Finalización inventario'),
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TD_CORREOS WHERE EMAIL = 'david@arpis.cl'),
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TP_TIPO_CORREO WHERE NOMBRE = 'SERVICIO'),
	SYSDATE, 1, NULL
);
INSERT INTO REPORTUSER.AP_EMAIL_TD_PROY_CORREOS (ID_ETAPA_PROYECTO,ID_CORREO,ID_TIPO_CORREO,FECHA_CREACION,ID_TEMPLATE,STORE_NO)
VALUES (
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TD_ETAPAS_PROYECTO WHERE NOMBRE = 'Finalización inventario'),
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TD_CORREOS WHERE EMAIL = 'david@arpis.cl'),
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TP_TIPO_CORREO WHERE NOMBRE = 'DE'),
	SYSDATE, 1, NULL
);
INSERT INTO REPORTUSER.AP_EMAIL_TD_PROY_CORREOS (ID_ETAPA_PROYECTO,ID_CORREO,ID_TIPO_CORREO,FECHA_CREACION,ID_TEMPLATE,STORE_NO)
VALUES (
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TD_ETAPAS_PROYECTO WHERE NOMBRE = 'Finalización inventario'),
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TD_CORREOS WHERE EMAIL = 'stephanie@arpis.cl'),
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TP_TIPO_CORREO WHERE NOMBRE = 'PARA'),
	SYSDATE, 1, 2
);
INSERT INTO REPORTUSER.AP_EMAIL_TD_PROY_CORREOS (ID_ETAPA_PROYECTO,ID_CORREO,ID_TIPO_CORREO,FECHA_CREACION,ID_TEMPLATE,STORE_NO)
VALUES (
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TD_ETAPAS_PROYECTO WHERE NOMBRE = 'Finalización inventario'),
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TD_CORREOS WHERE EMAIL = 'patricio@arpis.cl'),
	(SELECT ID FROM REPORTUSER.AP_EMAIL_TP_TIPO_CORREO WHERE NOMBRE = 'CC'),
	SYSDATE, 1, 2
);
