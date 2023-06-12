# ms-arpis-correo

## ENV
- DB_ORACLE_HOST=[HOST]
- DB_ORACLE_PASSWORD=[PASS]
- DB_ORACLE_PORT=[PORT]
- DB_ORACLE_USERNAME=[USER]
- DB_POSTGRES_HOST=[HOST]
- DB_POSTGRES_PASSWORD=[PASS]
- DB_POSTGRES_PORT=[PORT]
- DB_POSTGRES_USERNAME=[USER]
- DP_ORACLE_JPA_DIALECT=org.hibernate.dialect.[DIALECT]
- LOGS_LEVEL=[DEBUG|INFO|ERROR]
- LOGS_PATH=[PATH TO LOGS]
- SERVER_PORT=[PORT]

## JVM Args
- -Dspring.profiles.active=[cliente],[env]

Ejemplo:
  -Dspring.profiles.active=samsonite,dev
