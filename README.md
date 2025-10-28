# Loseminho - 개인 블로그 & 포트폴리오

개인 자료, 문서, 이력서 및 블로그를 관리하는 풀스택 웹 애플리케이션

## 📁 프로젝트 구조

```
loseminho/
├── backend/          # Spring Boot 백엔드
├── frontend/         # Next.js 프론트엔드
├── infra/           # 인프라 설정 (Docker, AWS)
└── docs/            # 프로젝트 문서
```

## 🛠 기술 스택

### Backend
- **Java 21** + **Spring Boot 3.5.6**
- **Spring Data JPA** + **Querydsl**
- **MySQL 8.0** (운영) / **H2** (개발)
- **Spring Security** + **JWT**
- **Flyway** (DB 마이그레이션)

### Frontend
- **Next.js 14** (App Router)
- **TypeScript**
- **Tailwind CSS**
- **React Query** (데이터 페칭)

### Infrastructure
- **Docker** + **Docker Compose**
- **AWS EC2/ECS** + **RDS**
- **Vercel** (프론트엔드 배포)
- **GitHub Actions** (CI/CD)

## 🚀 로컬 개발 환경 실행

### Backend 실행

```bash
cd backend
./gradlew bootRun
```

- H2 콘솔: http://localhost:8080/h2-console
- Swagger UI: http://localhost:8080/swagger-ui.html

### Frontend 실행

```bash
cd frontend
npm install
npm run dev
```

- 개발 서버: http://localhost:3000

### Docker Compose로 전체 실행

```bash
cd infra/docker
docker-compose up -d
```

## 📝 주요 기능

- [ ] 회원 가입 / 로그인 (JWT)
- [ ] 블로그 포스트 작성/수정/삭제
- [ ] 댓글 시스템
- [ ] 이력서 관리
- [ ] 문서 업로드/다운로드
- [ ] 검색 기능 (제목, 내용)
- [ ] 관리자 페이지

## 🔐 환경 변수

### Backend (`backend/src/main/resources/application-prod.yml`)

```yaml
DB_HOST: MySQL 호스트
DB_PORT: MySQL 포트
DB_NAME: 데이터베이스 이름
DB_USERNAME: DB 사용자명
DB_PASSWORD: DB 비밀번호
```

### Frontend (`frontend/.env.local`)

```bash
NEXT_PUBLIC_API_URL=백엔드 API URL
```

## 📦 배포

### Backend (AWS EC2)

```bash
cd backend
./gradlew build
docker build -t loseminho-backend .
# AWS ECR에 푸시 후 ECS 배포
```

### Frontend (Vercel)

```bash
cd frontend
vercel --prod
```

## 📄 라이선스

MIT License

## 👤 작성자

Loseminho
