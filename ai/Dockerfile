# ① 바탕: Python 3.12가 이미 설치된 리눅스 환경에서 시작한다
FROM python:3.12-slim

# ② 상자 안의 작업 폴더를 /app 으로 정한다 (없으면 자동 생성)
WORKDIR /app

# ③ 라이브러리 목록 파일을 내 PC → 상자 안으로 복사한다
COPY requirements.txt .

# ④ 상자 안에서 라이브러리를 설치한다
RUN pip install --no-cache-dir -r requirements.txt

# ⑤ 나머지 소스 파일을 전부 상자 안으로 복사한다
COPY . .

# ⑥ 상자를 가동할 때 실행할 명령
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]