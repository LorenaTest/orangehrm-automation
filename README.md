# OrangeHRM - Automatización del alta de empleado

Proyecto final del diplomado. Automatiza sobre https://opensource-demo.orangehrmlive.com/ el caso de negocio:

1. Iniciar sesión como administrador.
2. Ir al módulo **PIM** desde el menú.
3. Crear un empleado nuevo con sus datos personales (nombre, segundo nombre, apellido, Employee Id) y sus datos de usuario (usuario, contraseña y estado).
4. Buscar ese empleado en el listado de empleados.
5. Verificar que aparece en la grilla de resultados.

## Tecnologías

Java 11+, Maven, Selenium 4, TestNG, Gson, Log4j2 y ExtentReports.

## Estructura

```
resources/
  config.properties              URL y credenciales de administrador
  testdata/employeeData.json     datos de los empleados (DataProvider)
src/main/java/
  Models/Employee.java           modelo del empleado
  pages/                         Page Objects (locators arriba, acciones abajo)
    BasePage, LoginPage, HomePage, PimPage, AddEmployeePage, EmployeeListPage
  helpers/                       lectura de JSON/config, datos únicos, reporte y capturas
  utils/RandomUtil.java          generación de la parte única de los datos
src/test/java/
  base/BaseTest.java             navegador por parámetro, reporte y capturas
  pim/CreateEmployeeTest.java    la prueba del caso de negocio
testng.xml                       suite: el caso en Chrome y en Firefox
reports/ExtentReport.html        reporte de la última ejecución
```

## Diseño

- **Page Object Model:** la prueba no tiene locators ni búsquedas de elementos; se lee como el caso de negocio. Cada página declara sus locators como campos al inicio de la clase y los métodos de acción van debajo.
- **Aserciones en la prueba:** las páginas solo devuelven estados (`isDisplayed`, `isSavedSuccessfully`, `isEmployeeInResults`) y los `Assert` están en `CreateEmployeeTest`.
- **Datos desde archivo:** `employeeData.json` tiene dos empleados y el `DataProvider` ejecuta la prueba una vez por cada uno.
- **Nombre único por corrida:** del archivo vienen el nombre base, el segundo nombre, el apellido, la contraseña y el estado. Al ejecutar, `EmployeeDataFactory` genera un sufijo único que se agrega al nombre (por ejemplo `Lorena` → `Lorenancbjelo`), además del Employee Id y el usuario.

## Ejecución

Desde una terminal, sin depender del IDE:

```bash
mvn clean test
```

Maven ejecuta `testng.xml`, que corre el caso en **Chrome** y en **Firefox** (2 empleados × 2 navegadores = 4 ejecuciones). Se necesitan los dos navegadores instalados; Selenium Manager descarga los drivers solo.

Desde Eclipse: clic derecho sobre `testng.xml` → **Run As → TestNG Suite**.

## Reporte

Al terminar se genera `reports/ExtentReport.html`, con cada ejecución, el navegador, los datos del empleado y una captura de pantalla final. El log queda en `target/logs/automation.log`.
