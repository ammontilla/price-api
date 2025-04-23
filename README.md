# 🛍️ Inditex Price API

Servicio REST desarrollado en Java con Spring Boot para consultar precios de productos por marca, aplicando la tarifa con mayor prioridad en un rango de fechas.

## 📌 Requisitos funcionales

- Entrada: `applicationDate`, `productId`, `brandId`.
- Salida: `productId`, `brandId`, `priceList`, `startDate`, `endDate`, `price`, `currency`.

## 🧱 Arquitectura

- ✅ **Hexagonal Architecture**
- ✅ **DDD (Domain-Driven Design)**
- ✅ **Clean Code + SOLID Principles**
- ✅ Separación total de responsabilidades
- ✅ Patrón DTO + ControllerAdvice

## ⚙️ Tecnologías

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- H2 Database (in-memory)
- MapStruct
- OpenAPI (Swagger) 
- JUnit 5 + Mockito
- JaCoCo (cobertura de tests)
- Maven


---

## 🧰 Instalación y ejecución local


Clonar el repositorio:
```bach
git clone https://github.com/tu-usuario/price-api.git
cd price-api
```

## 🚀 Cómo ejecutar la app

```bash
./mvnw spring-boot:run
```
La app quedará disponible en:
http://localhost:8080


La app corre en http://localhost:8080.

### 🧪 Ejemplo de request

```bash
curl "http://localhost:8080/prices?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1"
```

### ✅ Respuesta esperada (200 OK):
```bash
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}
```
### ✅ Respuesta Error
```bash
{
  "timestamp": "2025-04-23T23:42:34.706129",
  "status": 404,
  "error": "Not Found",
  "message": "Brand not found",
  "path": "/prices"
}
```

---

## 🧪 Casos de prueba incluidos

- Test unitarios de servicios (`GetPriceService`)
- Test unitarios de mappers (`PriceEntityMapper`, `BrandEntityMapper`)
- Test unitarios de excepciones (`GlobalExceptionHandler`)
- Test de repositorio (`BrandJpaRepository`)
- Test de controller con mockMvc
- Test de error cuando no hay precio o marca (`orElseThrow`)

### 📈 Cobertura

Usamos **JaCoCo** para asegurar cobertura de:
- Núcleo de dominio
- Lógica de negocio
- Excepciones
- Controladores

> 🎯 Objetivo: > 90% de cobertura en núcleo

---

## 🧪 Cómo ejecutar los tests

```bash
mvn clean test
```

---
### 📈 Cobertura con JaCoCo
Para generar el reporte de cobertura:

```bash
mvn jacoco:report 
o
./mvnw verify
```

### 🏷️ Reporte HTML generado en:
```bash
target/site/jacoco/index.html
```
---
### 📂 H2 Console
Disponible en: http://localhost:8080/h2-console
<br>JDBC URL: jdbc:h2:mem:pricesdb

---
### 🔹 Uso en herramientas externas
Puedes importar openapi.yaml en:

- Swagger Editor
- Postman (Archivo OpenAPI)
- Cualquier cliente compatible con OpenAPI 3.0

---
## 📚 Documentación de la API

Esta aplicación expone un endpoint REST para consultar precios aplicables a productos según fecha, marca y reglas de prioridad.

La documentación completa está disponible en Swagger UI y como archivo OpenAPI.

### 🔹 Swagger UI

Puedes acceder a la interfaz interactiva de Swagger en:

➡️ [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### 🔹 OpenAPI Specification (YAML)

La especificación OpenAPI se genera automáticamente y está disponible en:

- YAML: [http://localhost:8080/v3/api-docs.yaml](http://localhost:8080/v3/api-docs.yaml)
- JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

También puedes descargar el archivo generado:

```bash
curl http://localhost:8080/v3/api-docs.yaml -o openapi.yaml
```

### ✅ Buenas prácticas implementadas
✔️ Nomenclatura de commits convencional (feat:, fix:, test:...)<br>
✔️ Código siguiendo principios SOLID<br>
✔️ Clean Code<br>
✔️ Separación de responsabilidades clara<br>
✔️ Gestión de errores con @ControllerAdvice<br>
✔️ Uso de códigos HTTP correctos<br>
✔️ Validación con @Valid y manejo de errores<br>
✔️ Alta cobertura de tests con JaCoCo<br>
✔️ Documentación auto-generada

### 👨‍💻 Autor
Desarrollado por un desarrollador senior con 19 años de experiencia para la prueba técnica de Inditex.

### 📝 Licencia
MIT