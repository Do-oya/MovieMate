# 영화 리뷰 프로젝트 To-Do 리스트

## 1. 프로젝트 초기 설정
- [x] **Spring Boot 프로젝트 생성**: IntelliJ에서 프로젝트 생성.
- [x] **의존성 추가**: Spring Web, Spring Data JPA, Thymeleaf, Spring Security, MySQL 추가.
- [x] **데이터베이스 설정**: `application.properties` 파일에 데이터베이스 연결 설정.

## 2. 영화 검색 및 외부 API 연동
- [x] **TMDB API 키 발급**: TMDB에 가입하고 API 키 발급받기.
- [x] **외부 API 호출 설정**: `RestTemplate`로 TMDB API 설정.
- [x] **영화 목록 페이지 구현**: TMDB API에서 받아온 영화 목록을 웹 페이지에 표시.
- [x] **영화 세부 정보 페이지 구현**: 선택한 영화의 세부 정보를 웹 페이지에 표시.

## 3. 리뷰 기능 구현
- [x] **Review 엔티티 생성**: 영화, 사용자, 리뷰 내용, 평점 등을 저장하는 엔티티 설계.
- [x] **리뷰 작성 폼 구현**: 사용자가 리뷰를 작성할 수 있는 HTML 폼 생성.
- [x] **리뷰 저장 기능 구현**: 작성된 리뷰를 데이터베이스에 저장.
- [ ] **리뷰 수정 기능 구현**: 사용자가 작성한 리뷰를 수정할 수 있는 기능 제공.
- [ ] **리뷰 삭제 기능 구현**: 사용자가 자신의 리뷰를 삭제할 수 있는 기능 구현.
- [x] **영화 상세 페이지에 리뷰 표시**: 해당 영화의 리뷰 목록을 영화 상세 페이지에 표시.

## 4. 회원 관리
- [ ] **User 엔티티 및 테이블 생성**: 사용자 정보를 저장하는 엔티티와 테이블 설계.
- [ ] **회원가입 페이지 구현**: 새로운 사용자를 등록할 수 있는 회원가입 페이지 생성.
- [ ] **로그인/로그아웃 페이지 구현**: 사용자 로그인 및 로그아웃 페이지 구현.
- [ ] **Spring Security 설정**: 회원가입, 로그인, 로그아웃 기능 구현 및 권한 설정.
- [ ] **리뷰 작성 권한 설정**: 로그인한 사용자만 리뷰 작성, 수정, 삭제 가능하도록 설정.

## 5. 평점 시스템 구현
- [ ] **평점 부여 기능 구현**: 사용자가 리뷰 작성 시 평점 입력 기능 추가.
- [ ] **평균 평점 계산**: 각 영화의 리뷰에 따른 평균 평점을 계산하여 표시.
- [ ] **평점 순 정렬 기능 구현**: 영화 목록을 평점 순으로 정렬하는 기능 구현.

## 6. 리뷰 페이지 및 UI
- [ ] **Thymeleaf 템플릿 설정**: 영화 목록, 리뷰 목록, 리뷰 작성 페이지를 위한 템플릿 구현.
- [ ] **영화 검색 기능 구현**: 영화 제목이나 키워드로 검색할 수 있는 기능 구현.
- [ ] **페이지네이션 구현**: 영화 목록과 리뷰 목록에 페이지네이션 기능 추가.

## 7. 관리자 기능
- [ ] **관리자 권한 설정**: Spring Security를 사용하여 관리자 권한 부여.
- [ ] **영화 추가/수정/삭제 기능**: 관리자가 영화를 추가, 수정, 삭제할 수 있는 기능 구현.
- [ ] **리뷰 관리 기능**: 관리자가 모든 사용자의 리뷰를 삭제할 수 있는 기능 구현.

## 8. 추가 기능 (선택 사항)
- [ ] **추천 기능 구현**: 평점이 높은 영화나 리뷰가 많은 영화를 추천하는 기능 추가.
- [ ] **즐겨찾기 기능 구현**: 사용자가 영화를 즐겨찾기 목록에 추가할 수 있는 기능 구현.
- [ ] **소셜 로그인 기능 추가**: 구글, 페이스북 등의 소셜 로그인 기능 추가.
