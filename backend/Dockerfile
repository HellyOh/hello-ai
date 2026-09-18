# ━━━━━ 1단계: 제조 공장 (이름을 build로 붙임) ━━━━━
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace

# Gradle 관련 파일 먼저 복사 → 라이브러리 다운로드 (3장의 "순서 요령"과 같은 원리)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

# 소스 복사 → jar 만들기
COPY src src
RUN ./gradlew bootJar --no-daemon -x test

# ━━━━━ 2단계: 출하 박스 ━━━━━
FROM eclipse-temurin:21-jre
WORKDIR /app

# "build"라는 1단계 공장에서 완성된 jar만 가져온다
COPY --from=build /workspace/build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]