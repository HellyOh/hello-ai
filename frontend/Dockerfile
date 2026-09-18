# ━━━━━ 1단계: 제조 공장 (Node로 화면 빌드) ━━━━━
FROM node:22-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

# ━━━━━ 2단계: 출하 박스 (nginx + 완성된 dist만) ━━━━━
FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist /usr/share/nginx/html