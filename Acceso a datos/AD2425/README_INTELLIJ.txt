Proyecto único para IntelliJ con todos los ejercicios como módulos Maven.

ABRIR:
1) Descomprime.
2) IntelliJ -> File -> Open -> elige la carpeta AD_2425_IntelliJ (la que tiene el pom.xml de arriba).
3) Acepta "Load Maven Project".

MÓDULOS:
- ad24-ej1-fichero
- ad24-ej2-json
- ad24-ej3-hibernate
- ad25-ej1-json
- ad25-ej2-hibernate
- ad25-ej3-spring

MySQL:
- Arranca MySQL (XAMPP/Docker).
- Crea BD: ad2024, ad2025, hotel_ue.
- Ajusta usuario/contraseña si hace falta en:
  - hibernate.cfg.xml (Hibernate)
  - application.properties (Spring)

MAIN:
- JSON: app.Main
- Hibernate 2024/2025: app.Main (si existe) o la clase Main del módulo (mira en src/main/java/app/Main.java)
- Spring: app.Ad25SpringApplication (o el que tenga el módulo)
