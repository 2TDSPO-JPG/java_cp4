# ---------- Etapa 1: build ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia primeiro o pom.xml para aproveitar cache de dependências
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copia o restante do código-fonte e compila
COPY src ./src
RUN mvn -B clean package -DskipTests

# ---------- Etapa 2: runtime ----------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia o jar gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Render injeta a variável PORT automaticamente
EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]