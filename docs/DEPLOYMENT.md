# 배포 가이드

## 로컬 개발 환경

### Backend

```bash
cd backend
./gradlew bootRun
```

접속: http://localhost:8080
H2 콘솔: http://localhost:8080/h2-console

### Frontend

```bash
cd frontend
npm install
npm run dev
```

접속: http://localhost:3000

## Docker로 전체 실행

```bash
cd infra/docker
docker-compose up -d
```

서비스:
- MySQL: localhost:3306
- Backend: localhost:8080
- Nginx: localhost:80

## AWS 배포

### 1. Backend 배포 (EC2/ECS)

#### 1-1. Docker 이미지 빌드

```bash
cd backend
docker build -t loseminho-backend:latest .
```

#### 1-2. ECR에 푸시

```bash
# ECR 로그인
aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin {ECR_URI}

# 태그 지정
docker tag loseminho-backend:latest {ECR_URI}/loseminho-backend:latest

# 푸시
docker push {ECR_URI}/loseminho-backend:latest
```

#### 1-3. ECS 서비스 업데이트

```bash
aws ecs update-service \
  --cluster loseminho-cluster \
  --service loseminho-backend-service \
  --force-new-deployment
```

### 2. Frontend 배포 (Vercel)

#### 2-1. Vercel CLI 설치

```bash
npm install -g vercel
```

#### 2-2. 배포

```bash
cd frontend
vercel --prod
```

#### 2-3. 환경 변수 설정

Vercel Dashboard에서 다음 환경 변수 설정:
```
NEXT_PUBLIC_API_URL=https://api.yourdomain.com/api/v1
```

## 데이터베이스 마이그레이션

### Flyway 마이그레이션

```bash
cd backend
./gradlew flywayMigrate
```

마이그레이션 파일 위치: `backend/src/main/resources/db/migration/`

## 환경별 설정

### 개발 환경 (dev)

```yaml
spring:
  profiles:
    active: dev
  datasource:
    url: jdbc:h2:./db_dev
```

### 운영 환경 (prod)

```yaml
spring:
  profiles:
    active: prod
  datasource:
    url: jdbc:mysql://${DB_HOST}:3306/${DB_NAME}
```

## SSL 인증서 설정

### Let's Encrypt (무료)

```bash
sudo certbot --nginx -d yourdomain.com -d www.yourdomain.com
```

## 모니터링 설정

### CloudWatch 로그

```bash
aws logs create-log-group --log-group-name /aws/ecs/loseminho-backend
```

### 헬스체크

```bash
curl http://localhost:8080/actuator/health
```

## 롤백 절차

### ECS 롤백

```bash
aws ecs update-service \
  --cluster loseminho-cluster \
  --service loseminho-backend-service \
  --task-definition loseminho-backend:{이전_버전}
```

### Vercel 롤백

Vercel Dashboard → Deployments → 이전 버전 선택 → Promote to Production

## 트러블슈팅

### Backend 로그 확인

```bash
# Docker
docker logs loseminho-backend

# ECS
aws logs tail /aws/ecs/loseminho-backend --follow
```

### Database 연결 확인

```bash
mysql -h {RDS_ENDPOINT} -u {USERNAME} -p
```
