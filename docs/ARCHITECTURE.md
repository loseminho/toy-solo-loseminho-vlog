# 아키텍처 설계 문서

## 개요

Loseminho는 개인 블로그 및 포트폴리오를 관리하는 풀스택 웹 애플리케이션입니다.

## 시스템 아키텍처

```
┌─────────────┐
│   사용자     │
└──────┬──────┘
       │
       ├─────────────────┐
       ↓                 ↓
┌──────────────┐  ┌──────────────┐
│   Frontend   │  │   Backend    │
│   (Vercel)   │→ │   (AWS EC2)  │
│   Next.js    │  │ Spring Boot  │
└──────────────┘  └──────┬───────┘
                         ↓
                  ┌──────────────┐
                  │   Database   │
                  │  (AWS RDS)   │
                  │    MySQL     │
                  └──────────────┘
```

## 백엔드 아키텍처

### 계층 구조

```
Controller (API Layer)
    ↓
Service (Business Logic)
    ↓
Repository (Data Access)
    ↓
Entity (Domain Model)
```

### 패키지 구조

```
com.loseminho
├── domain/              # 도메인별 분리
│   ├── blog/           # 블로그 도메인
│   ├── member/         # 회원 도메인
│   ├── resume/         # 이력서 도메인
│   ├── document/       # 문서 도메인
│   └── comment/        # 댓글 도메인
├── global/             # 전역 설정
│   ├── config/         # 설정 클래스
│   ├── common/         # 공통 컴포넌트
│   ├── security/       # 보안
│   └── search/         # 검색
└── admin/              # 관리자 기능
```

## 프론트엔드 아키텍처

### Next.js App Router 구조

```
app/
├── (main)/             # 일반 사용자 페이지
│   ├── page.tsx        # 메인 페이지
│   ├── blog/           # 블로그
│   ├── resume/         # 이력서
│   └── documents/      # 문서
├── (admin)/            # 관리자 페이지
│   ├── dashboard/
│   ├── posts/
│   └── settings/
└── api/                # API Routes (BFF)
```

## 데이터베이스 설계

### 주요 테이블

- **members**: 회원 정보
- **posts**: 블로그 게시글
- **comments**: 댓글
- **resumes**: 이력서
- **documents**: 문서

## 배포 전략

### 무중단 배포

- **Frontend**: Vercel의 자동 배포
- **Backend**: AWS ECS Blue-Green 배포

### CI/CD 파이프라인

```
Git Push → GitHub Actions → Build → Test → Deploy
```

## 보안

- **인증**: JWT 기반
- **권한**: Role-based (USER, ADMIN)
- **CORS**: 허용된 도메인만 접근
- **HTTPS**: 운영 환경 필수

## 성능 최적화

- **CDN**: Vercel Edge Network
- **캐싱**: Redis (향후 도입 예정)
- **DB 연결 풀**: HikariCP
- **쿼리 최적화**: N+1 문제 해결

## 모니터링

- **로그**: CloudWatch Logs
- **메트릭**: Spring Actuator
- **알림**: AWS SNS (향후 도입)
