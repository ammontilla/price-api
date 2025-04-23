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
- JPA + H2 in-memory DB
- MapStruct
- JUnit 5 + Mockito
- JaCoCo
- GitHub Actions (CI)
- Docker

## 🚀 Cómo ejecutar la app

```bash
./mvnw spring-boot:run
```

La app corre en http://localhost:8080.

### 🧪 Ejemplo de request

```bash
curl "http://localhost:8080/prices?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1"
```

### ✅ Respuesta OK
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
### ✅ Respuesta Error
```bash
{
  "timestamp": "2025-04-23T23:42:34.706129",
  "status": 404,
  "error": "Not Found",
  "message": "Brand not found",
  "path": "/prices"
}
```

### 📂 H2 Console
Disponible en: http://localhost:8080/h2-console
<br>JDBC URL: jdbc:h2:mem:pricesdb

### 📈 Cobertura con JaCoCo
```bash
./mvnw verify
```

### 🏷️ Reporte HTML generado en:
target/site/jacoco/index.html


