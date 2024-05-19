<a name="readme-top"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/github_username/repo_name">
    <img width="549" alt="Logo" src="https://github.com/qushe8r/stock-disscussion-forum/assets/115606959/e3ef8bbb-cac7-4680-ba2b-25e89732bc95">
  </a>

<h3 align="center">종목 토론 방</h3>

  <p align="center">
    주가에 대한 정보를 주제로 이야기 할 수 있는 커뮤니티
    <br />
    <a href="https://github.com/github_username/repo_name"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/github_username/repo_name">View Demo</a>
    ·
    <a href="https://github.com/github_username/repo_name/issues/new?labels=bug&template=bug-report---.md">Report Bug</a>
    ·
    <a href="https://github.com/github_username/repo_name/issues/new?labels=enhancement&template=feature-request---.md">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>목차</summary>
  <ol>
    <li>
      <a href="#프로젝트-설명">프로잭트 설명</a>
      <ul>
        <li><a href="#기술-스택">기술 스택</a></li>
        <li><a href="#ERD">ERD</a></li>
        <li><a href="#Docker-Compose">Docker Compose</a></li>
      </ul>
    </li>
    <li>
      <a href="#시작하기">시작하기</a>
      <ul>
        <li><a href="#사전-준비-사항">사전 준비 사항</a></li>
        <li><a href="#설치-및-실행하기">설치 및 실행하기</a></li>
      </ul>
    </li>
    <li>
      <a href="#지표-생성-데이터-흐름">지표 생성 데이터 흐름</a>
      <ul>
        <li><a href="#제한-사항">제한 사항 (가정)</a></li>
        <li><a href="#데이터-흐름도">데이터 흐름도</a></li>
      </ul>
    </li>
    <li><a href="#트러블-슈팅/기술적-의사-결정">트러블 슈팅/기술적 의사 결정</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->

## 프로젝트 설명


이 프로젝트는 MSA로 구성된 프로젝트입니다.

종목에 대한 정보를 실시간으로 받아 주식 지표를 생성합니다.

주식 정보를 토대로 자유롭게 글과 댓글, 좋아요 등의 기능으로 소통할 수 있는 커뮤니티입니다.

<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>

### 개발 및 변경 로그

[개발 및 변경 로그](https://pine-pie-554.notion.site/Stock-Disussion-Forum-fca64e638c234d3a829b3c1b71beef64?pvs=4)

### 기술 스택

[![SpringBoot][SpringBoot]][SpringBootUrl] [![SpringWeb][SpringWeb]][SpringWebUrl] [![SpringSecurity][SpringSecurity]][SpringSecurityUrl] [![SpringDataJPA][SpringDataJPA]][SpringDataJPAUrl] [![SpringBatch][SpringBatch]][SpringBatchUrl]</br>
[![SpringGateway][SpringGateway]][SpringGatewayUrl] [![SpringEureka][SpringEureka]][SpringEurekaUrl] [![SpringOpenFeign][SpringOpenFeign]][SpringOpenFeignUrl] [![SpringResilience4J][SpringResilience4J]][SpringResilience4JUrl] </br>
[![Gradle][Gradle]][GradleUrl] </br>
<a href="http://querydsl.com/">
<img width="112" alt="Screenshot 2024-05-18 at 3 12 39 PM" src="https://github.com/qushe8r/stock-disscussion-forum/assets/115606959/980c7832-98c6-4bf2-9d10-3a599c56881f">
</a>
[![MySQL][MySQL]][MySQLUrl] [![Redis][Redis]][RedisUrl]
<a href="https://kafka.apache.org/documentation/">
<img width="144" alt="Screenshot 2024-05-18 at 3 31 02 PM" src="https://github.com/qushe8r/stock-disscussion-forum/assets/115606959/3adfe2bd-e9f3-460f-8098-dbf20ebd33a3">
</a>
[![JWT][JWT]][JWTUrl] </br>
[![Docker][Docker]][DockerUrl] [![DockerCompose][DockerCompose]][DockerComposeUrl]

<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>

### ERD

<img width="1365" alt="Screenshot 2024-05-19 at 6 20 46 PM" src="https://github.com/qushe8r/stock-disscussion-forum/assets/115606959/67abdaac-e7ff-4434-bfff-623446c90fad">


<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>

### Docker Compose

<img width="1005" alt="도커 컴포우즈" src="https://github.com/qushe8r/stock-disscussion-forum/assets/115606959/2b453f6d-87f3-45c6-b3b5-e93da0900972">

<!-- GETTING STARTED -->

## 시작하기

다음은 로컬에서 프로젝트를 설정하는 방법에 대한 예시입니다.</br>
로컬에서 프로젝트를 설정하려면 다음 스크립트를 순서에 맞게 실행해주세요.

### 사전 준비 사항

어플리케이션을 실행하기 위해서는 도커가 필요합니다.

### 설치 및 실행하기

1. 클론 및 이동
   ```shell
   git clone https://github.com/qushe8r/stock-disscussion-forum.git
   cd stock-disscussion-forum
   ```
2. Docker Image 생성
    ```shell
    ./gradlew docker
    ```
3. Docker Compose 실행
   ```shell
   docker-compose up -d --force-recreate --build
   ```
4. 스프링 배치 실행(데이터 준비)
   ```shell
   java -jar ./stock-batch/build/libs/stock-batch-0.0.1-SNAPSHOT.jar
   ```

<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>

<!-- Data Flow -->

## 지표 생성 데이터 흐름

### 제한 사항 (가정)

1. 주식 데이터가 들어오는 상황을 가정 하였습니다.
    - WebSocket을 사용하여 데이터를 받는 상황일수도, API 콜을 통하여 데이터를 받는 상황일 수도 있을 것입니다.
    - 크롤링을 제외하면 유료 서비스를 통하여 데이터를 받아야 하는데, 비용 문제 때문에 기존 값 * random(-0.1 ~ 0.1 사이)값을 매 초 발생 시킵니다.
2. 지표가 필요한 상황을 가정해야 합니다.
    - 실제 그래프에 지표를 그린다고 가정하면, 사용자가 자신의 기준에 맞는 값을 입력하여야 합니다.
    - 프로젝트에서 이동 평균선의 기준 날자를 12, 20, 26으로 진행하였지만, 사용자가 자신의 기준에 맞는 값으로 변경, 추가하여 지표를 활용합니다.
    - 따라서, 추후에 현재 지표를 기준으로 위험을 알려주거나, 추천을 해주는 서비스가 생길 수 있다고 가정할 수 있습니다.
3. 현재는 실시간 주식 지표가 추후에 이용될 실시간 알림이나 추천 서비스에 사용될 것만을 가정하였기 때문에 Redis에 저장하고 덮어쓰는 방식을 사용 하였습니다.
    - 이 순간의 지표가 n초 뒤에는 의미가 소멸한다고 생각했습니다.

### 데이터 흐름도

<img width="1667" alt="지표 생성 데이터 흐름" src="https://github.com/qushe8r/stock-disscussion-forum/assets/115606959/9603960d-6759-4bfb-bbce-e25acfa659d8">

**설명**

1. 배치에서 계산에 필요한 데이터를 미리 준비합니다.
2. 주식 데이터가 들어오면 이동 평균선을 계산합니다.
    - 단순 이동 평균선, 지수 이동 평균선을 준비합니다.
    - 계산된 데이터는 Redis에 저장하고, Kafka로 produce 합니다.
3. 각 이동 평균선이 필요한 서비스에서 이동 평균선을 consume 합니다.
    - 볼린저 밴드에는 단순 이동 평균선이 사용되고, MACD에는 지수 이동 평균선이 필요합니다.
    - 계산된 데이터는 Redis에 저장하고, Kafka로 produce 합니다.
4. 현재 지수 정보에 대한 조회가 들어오면 Redis에 저장되어 있는 정보로 알려줍니다.

<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>

## 트러블 슈팅/기술적 의사 결정

작성중입니다.

<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge

[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge

[forks-url]: https://github.com/github_username/repo_name/network/members

[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge

[stars-url]: https://github.com/github_username/repo_name/stargazers

[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge

[issues-url]: https://github.com/github_username/repo_name/issues

[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge

[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/linkedin_username

[product-screenshot]: images/screenshot.png

[SpringBoot]: https://img.shields.io/badge/Spring%20Boot-success?style=for-the-badge&logo=Spring&logoColor=white

[SpringBootUrl]: https://docs.spring.io/spring-boot/docs/current/reference/html/

[SpringWeb]: https://img.shields.io/badge/Spring%20Web-success?style=for-the-badge&logo=Spring&logoColor=white

[SpringWebUrl]: http

[SpringSecurity]: https://img.shields.io/badge/Spring%20Security-success?style=for-the-badge&logo=Spring&logoColor=white

[SpringSecurityUrl]: https://docs.spring.io/spring-security/reference/index.html

[SpringDataJPA]: https://img.shields.io/badge/Spring%20Data%20JPA-success?style=for-the-badge&logo=Spring&logoColor=white

[SpringDataJPAUrl]: https://docs.spring.io/spring-data/jpa/reference/

[SpringBatch]: https://img.shields.io/badge/Spring%20Batch-success?style=for-the-badge&logo=Spring&logoColor=white

[SpringBatchUrl]: https://docs.spring.io/spring-batch/reference/index.html

[SpringGateway]: https://img.shields.io/badge/Spring%20Gateway-success?style=for-the-badge&logo=Spring&logoColor=white

[SpringGatewayUrl]: https://docs.spring.io/spring-cloud-gateway/reference/spring-cloud-gateway.html

[SpringEureka]: https://img.shields.io/badge/Spring%20Eureka-success?style=for-the-badge&logo=Spring&logoColor=white

[SpringEurekaUrl]: https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/

[SpringOpenFeign]: https://img.shields.io/badge/Spring%20OpenFeign-success?style=for-the-badge&logo=Spring&logoColor=white

[SpringOpenFeignUrl]: https://img.shields.io/badge/Spring%20OpenFeign-success?style=for-the-badge&logo=Spring&logoColor=white

[SpringResilience4J]: https://img.shields.io/badge/Spring%20Resilience4J-success?style=for-the-badge&logo=Spring&logoColor=white

[SpringResilience4JUrl]: https://resilience4j.readme.io/docs/getting-started-3

[QueryDSL]: https://github.com/qushe8r/stock-disscussion-forum/assets/115606959/980c7832-98c6-4bf2-9d10-3a599c56881f

[QueryDSLUrl]: http://querydsl.com/

[MySQL]: https://img.shields.io/badge/MySQL-blue?style=for-the-badge&logo=mysql&logoColor=white

[MySQLUrl]: https://docs.oracle.com/en-us/iaas/mysql-database/doc/getting-started.html

[JWT]: https://img.shields.io/badge/JWT-000000?style=for-the-badge&logoColor=61DAFB&logo=data:image/svg+xml;base64,PHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHdpZHRoPSIyNTAwIiBoZWlnaHQ9IjI1MDAiIHZpZXdCb3g9IjAsMCwyNTYsMjU2Ij48ZyBmaWxsPSIjZmZmZmZmIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiIHN0cm9rZT0ibm9uZSIgc3Ryb2tlLXdpZHRoPSIxIiBzdHJva2UtbGluZWNhcD0iYnV0dCIgc3Ryb2tlLWxpbmVqb2luPSJtaXRlciIgc3Ryb2tlLW1pdGVybGltaXQ9IjEwIiBzdHJva2UtZGFzaGFycmF5PSIiIHN0cm9rZS1kYXNob2Zmc2V0PSIwIiBmb250LWZhbWlseT0ibm9uZSIgZm9udC13ZWlnaHQ9Im5vbmUiIGZvbnQtc2l6ZT0ibm9uZSIgdGV4dC1hbmNob3I9Im5vbmUiIHN0eWxlPSJtaXgtYmxlbmQtbW9kZTogbm9ybWFsIj48ZyB0cmFuc2Zvcm09InRyYW5zbGF0ZSgtMS4wMjcwOCwtMC43NjgpIHNjYWxlKDIuNTY3NywyLjU2KSI+PGc+PHBhdGggZD0iTTU3LjgsMjcuMmwtMC4xLC0yNi45aC0xNWwwLjEsMjYuOWw3LjUsMTAuM3pNNDIuOCw3My4zdjI3aDE1di0yN2wtNy41LC0xMC4zeiI+PC9wYXRoPjxwYXRoIGQ9Ik01Ny44LDczLjNsMTUuOCwyMS44bDEyLjEsLTguOGwtMTUuOCwtMjEuOGwtMTIuMSwtMy45ek00Mi44LDI3LjJsLTE1LjksLTIxLjhsLTEyLjEsOC44bDE1LjgsMjEuOGwxMi4yLDMuOXoiPjwvcGF0aD48cGF0aCBkPSJNMzAuNiwzNmwtMjUuNiwtOC4zbC00LjYsMTQuMmwyNS42LDguNGwxMi4xLC00ek02Mi40LDU0LjJsNy41LDEwLjNsMjUuNiw4LjNsNC42LC0xNC4ybC0yNS42LC04LjN6Ij48L3BhdGg+PHBhdGggZD0iTTc0LjUsNTAuM2wyNS42LC04LjRsLTQuNiwtMTQuMmwtMjUuNiw4LjNsLTcuNSwxMC4zek0yNiw1MC4zbC0yNS42LDguM2w0LjYsMTQuMmwyNS42LC04LjNsNy41LC0xMC4zeiI+PC9wYXRoPjxwYXRoIGQ9Ik0zMC42LDY0LjVsLTE1LjgsMjEuOGwxMi4xLDguOGwxNS45LC0yMS44di0xMi43ek02OS45LDM2bDE1LjgsLTIxLjhsLTEyLjEsLTguOGwtMTUuOCwyMS44djEyLjd6Ij48L3BhdGg+PC9nPjwvZz48L2c+PC9zdmc+

[JWTUrl]: https://jwt.io/introduction

[Gradle]: https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logoColor=61DAFB&logo=data:image/svg+xml;base64,PHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHdpZHRoPSI4MDBweCIgaGVpZ2h0PSI4MDBweCIgdmlld0JveD0iMCwwLDI1NiwyNTYiPjxnIGZpbGw9IiNmZmZmZmYiIGZpbGwtcnVsZT0ibm9uemVybyIgc3Ryb2tlPSJub25lIiBzdHJva2Utd2lkdGg9IjEiIHN0cm9rZS1saW5lY2FwPSJidXR0IiBzdHJva2UtbGluZWpvaW49Im1pdGVyIiBzdHJva2UtbWl0ZXJsaW1pdD0iMTAiIHN0cm9rZS1kYXNoYXJyYXk9IiIgc3Ryb2tlLWRhc2hvZmZzZXQ9IjAiIGZvbnQtZmFtaWx5PSJub25lIiBmb250LXdlaWdodD0ibm9uZSIgZm9udC1zaXplPSJub25lIiB0ZXh0LWFuY2hvcj0ibm9uZSIgc3R5bGU9Im1peC1ibGVuZC1tb2RlOiBub3JtYWwiPjxnIHRyYW5zZm9ybT0idHJhbnNsYXRlKC0wLjE3ODY1LDAuMTg5OCkgc2NhbGUoOCw4KSI+PHBhdGggZD0iTTMwLjI2NSw1LjczNWMtMiwtMiAtNS4wNjgsLTIgLTcuMDY4LC0wLjEzNmMtMC4xMjksMC4xMzYgLTAuMTI5LDAuMjY1IC0wLjEyOSwwLjQwMWMwLDAuMTM2IDAsMC4yNjUgMC4xMjksMC40MDFsMC42NjcsMC42NjdjMC4xMzYsMC4xMjkgMC40MDEsMC4xMjkgMC42NjcsMGMwLjUzNywtMC40MDEgMS4wNjgsLTAuNTM3IDEuNzM1LC0wLjUzN2MxLjU5OSwwIDIuOTMyLDEuMzMzIDIuOTMyLDIuOTM5YzAsMC43OTYgLTAuMjY1LDEuNDYzIC0wLjc5NiwyYy00LDQgLTkuNDY5LC03LjIwNCAtMjEuNTk5LC0xLjQ2OWMtMC44MDMsMC40MDEgLTEuMjA0LDEuMzMzIC0wLjgwMywyLjEzNnYwLjEyOWwyLDMuNTk5YzAuNDAxLDAuODAzIDEuNDY5LDEuMDY4IDIuMjY1LDAuNjY3bDAuOTMyLC0wLjUzMWMxLjA2OCwtMC42NjcgMiwtMS4zMzMgMi45MzksLTIuMTM2YzAuMTI5LC0wLjEyOSAwLjUzMSwtMC4xMjkgMC42NjcsMGMwLjI2NSwwLjEzNiAwLjI2NSwwLjQwMSAwLjEyOSwwLjY2N2wtMC4xMjksMC4xMzZjLTAuOTM5LDAuODAzIC0yLDEuNTk5IC0zLjA2OCwyLjI2NWwtMC45MzIsMC40MDFjLTAuNDAxLDAuMjY1IC0wLjgwMywwLjQwMSAtMS4zMzMsMC40MDFjLTAuOTM5LDAgLTEuODcxLC0wLjUzNyAtMi4yNzIsLTEuMzMzbC0yLC0zLjQ2OWMtMy43MjgsMi42NjcgLTYuMTI5LDcuODcxIC00Ljc5NiwxNC40MDFjMCwwLjI2NSAwLjI2NSwwLjQwMSAwLjUzMSwwLjQwMWgyLjI2NWMwLjI3MiwwIDAuNDAxLC0wLjEzNiAwLjUzNywtMC40MDFjMC4yNjUsLTEuODY0IDEuODY0LC0zLjA2OCAzLjczNSwtMi44MDNjMS40NjMsMC4xMzYgMi42NjcsMS4zMzMgMi43OTYsMi44MDNjMCwwLjI2NSAwLjI2NSwwLjQwMSAwLjUzNywwLjQwMWgyLjEyOWMwLjI2NSwwIDAuNDAxLC0wLjEzNiAwLjUzNywtMC40MDFjMC4yNjUsLTEuODY0IDEuODY0LC0zLjA2OCAzLjcyOCwtMi44MDNjMS40NjksMC4xMzYgMi42NjcsMS4zMzMgMi44MDMsMi44MDNjMCwwLjI2NSAwLjI2NSwwLjQwMSAwLjUzMSwwLjQwMWgyLjEzNmMwLjI2NSwwIDAuNTMxLC0wLjI2NSAwLjUzMSwtMC41MzdjMCwtMy4wNjEgMC45MzksLTYuNTMxIDMuMjA0LC04LjI2NWM3Ljg2NCwtNi4xMjkgNS43MzUsLTExLjMzMyAzLjg2NCwtMTMuMTk3ek0yMiwxNC45MzJsLTEuNTk5LC0wLjc5NmMwLC0wLjUzNyAwLjQwMSwtMC45MzkgMC45MzIsLTAuOTM5YzAuNTMxLDAgMC45MzIsMC40MDEgMC45MzIsMC45MzljMC4xMzYsMC4zOTUgMCwwLjY2NyAtMC4yNjUsMC43OTZ6Ij48L3BhdGg+PC9nPjwvZz48L3N2Zz4=

[GradleUrl]: https://docs.gradle.org/current/userguide/userguide.html

[Docker]: https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white

[DockerUrl]: https://docs.docker.com/manuals/

[DockerCompose]: https://img.shields.io/badge/Docker%20Compose-2496ED?style=for-the-badge&logo=Docker&logoColor=white

[DockerComposeUrl]: https://docs.docker.com/compose/

[Redis]: https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white

[RedisUrl]: https://redis.io/docs/latest/

[Kafka]: https://img.shields.io/badge/Apache%20Kafka-000000?style=for-the-badge&logoColor=61DAFB&logo=data:image/svg+xml;base64,PHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHdpZHRoPSIxMjAwLjAwMDAwMHB0IiBoZWlnaHQ9IjE5NDguMDAwMDAwcHQiIHZpZXdCb3g9IjAsMCwxNTcuNjk1MzEsMjU2Ij48ZyBmaWxsPSIjZmZmZmZmIiBmaWxsLXJ1bGU9Im5vbnplcm8iIHN0cm9rZT0ibm9uZSIgc3Ryb2tlLXdpZHRoPSIxIiBzdHJva2UtbGluZWNhcD0iYnV0dCIgc3Ryb2tlLWxpbmVqb2luPSJtaXRlciIgc3Ryb2tlLW1pdGVybGltaXQ9IjEwIiBzdHJva2UtZGFzaGFycmF5PSIiIHN0cm9rZS1kYXNob2Zmc2V0PSIwIiBmb250LWZhbWlseT0ibm9uZSIgZm9udC13ZWlnaHQ9Im5vbmUiIGZvbnQtc2l6ZT0ibm9uZSIgdGV4dC1hbmNob3I9Im5vbmUiIHN0eWxlPSJtaXgtYmxlbmQtbW9kZTogbm9ybWFsIj48ZyB0cmFuc2Zvcm09InRyYW5zbGF0ZSgwLjAwNDQ4LDAuMTE5NjYpIHNjYWxlKDAuMTMxNDIsMC4xMzE0MikiPjxnIHRyYW5zZm9ybT0idHJhbnNsYXRlKDAsMTk0OCkgc2NhbGUoMC4xLC0wLjEpIj48cGF0aCBkPSJNMzEwMCwxOTQ3NGMtMzQ1LC0zMSAtNjM2LC0xMTIgLTkzNSwtMjU5Yy00NDIsLTIxOCAtNzk3LC01NDQgLTEwNTYsLTk3MWMtMTY4LC0yNzUgLTI3OSwtNTgzIC0zMzYsLTkyOWMtMjYsLTE1NiAtMjYsLTU4MSAwLC03NDNjMTMyLC04MjYgNjMwLC0xNTEzIDEzNzMsLTE4OTJjMTQxLC03MyAyOTUsLTEzMyA0NDksLTE3NWwxMjAsLTMzbDMsLTcyNmwyLC03MjZsLTIyLC01Yy0xMywtMiAtNzcsLTE2IC0xNDMsLTMxYy00ODcsLTEwNiAtOTg0LC0zNTUgLTEzODUsLTY5MmMtMTI3LC0xMDcgLTM0NywtMzMzIC00NTAsLTQ2MmMtMzA0LC0zODIgLTUyOSwtODUwIC02MzQsLTEzMjNjLTg3LC0zODggLTEwMCwtODU4IC0zNSwtMTI0N2MxODMsLTEwOTAgOTAwLC0yMDEyIDE5MTIsLTI0NTljMjE4LC05NyA0OTMsLTE4MyA3MTAsLTIyMmw2NywtMTJ2LTc2OHYtNzY4bC00MiwtMTFjLTMxNSwtODEgLTU2OCwtMTkxIC04MTIsLTM1NGMtMTY3LC0xMTIgLTI1OSwtMTg3IC00MDYsLTMzNWMtMzY3LC0zNjkgLTYwNCwtODI5IC02OTcsLTEzNTFjLTM0LC0xOTYgLTQzLC01MTEgLTE5LC03MTRjNjQsLTU1MiAzMDUsLTEwNjMgNjg5LC0xNDYyYzM5MiwtNDA4IDg3MCwtNjYwIDE0MzEsLTc1NWMxNTcsLTI2IDQ3NiwtMzYgNjQxLC0yMGM3NjcsNzcgMTQ0OCw0ODggMTg3OSwxMTM2YzE1MCwyMjUgMjk5LDU3NyAzNTYsODQwYzY4LDMxOSA3Nyw2NTkgMjQsOTcwYy0xNDgsODc1IC03NDcsMTYxMiAtMTU3NCwxOTM4Yy0xMDIsNDAgLTIwNSw3MyAtMzM3LDEwN2wtNDMsMTF2NzY5YzAsNzA1IDEsNzcwIDE2LDc3MGM5LDAgNjksMTIgMTMzLDI2YzUzNywxMjAgMTAyMSwzNjAgMTQzMSw3MTBjODgsNzUgMzIzLDMxMCAzNzcsMzc4YzE4LDIyIDM3LDM2IDQ1LDMzYzcsLTMgMjc2LC0xOTIgNTk4LC00MjBjNTAxLC0zNTUgNTg1LC00MTggNTg0LC00MzhjLTEsLTEzIC0xMywtNzMgLTI4LC0xMzRjLTQ4LC0yMDMgLTYwLC0zMjEgLTYwLC01NzBjMSwtMTYxIDYsLTI2MiAxNywtMzM4Yzg4LC01NzQgMzI1LC0xMDQ3IDcyNiwtMTQ0N2M4MCwtODAgMTk2LC0xODIgMjU2LC0yMjdjMzY4LC0yNzQgNzg0LC00NDMgMTIyNSwtNDk4YzE0MiwtMTcgNDQwLC0yMCA1NzUsLTVjNDYxLDUxIDg4NSwyMjAgMTI2NCw1MDNjMTI0LDkyIDM5NiwzNjQgNDg4LDQ4OGMyNzYsMzY5IDQ0Nyw3OTYgNDk5LDEyNDVjMjIsMTg3IDE1LDUxNyAtMTUsNjkwYy0xOTIsMTEyNSAtMTA2NywxOTY2IC0yMjAxLDIxMTZjLTEyNSwxNiAtNDg5LDE2IC02MTAsLTFjLTU3NCwtNzkgLTEwNTEsLTMxMCAtMTQ2MiwtNzA5bC0xMzcsLTEzNGwtMjgsMTljLTgxLDU2IC0xMTAzLDc3OSAtMTEzMCw3OTlsLTMyLDI0bDIwLDU4YzIyOSw2NjEgMjM1LDEzODAgMTgsMjA2MGMtMTcsNTEgLTI5LDk1IC0yNyw5NmMxMDQsNzcgMTE4MCw4MjYgMTE4Niw4MjZjNCwwIDI1LC0yMCA0NSwtNDVjMjEsLTI1IDg0LC04NyAxNDIsLTEzOGM0MDksLTM3MCA4ODEsLTU4OCAxNDE1LC02NTNjMTY3LC0yMCA1MzIsLTE1IDY4MCwxMGM1OTQsMTAwIDEwODksMzY3IDE0OTEsODAzYzM1NCwzODYgNTg0LDg5MCA2NDUsMTQxOWMyMiwxODcgMTUsNTE3IC0xNSw2OTBjLTE0MCw4MjIgLTY1MCwxNTEwIC0xMzg2LDE4NzBjLTI1MywxMjMgLTQ3NCwxOTMgLTc1MCwyMzZjLTE4NiwyOSAtNTMxLDMxIC03MDUsNWMtNTM4LC04MyAtOTkxLC0yOTUgLTEzNzAsLTY0MmMtNTUzLC01MDYgLTg1NCwtMTIxOCAtODI3LC0xOTU1YzgsLTE5MSAyNCwtMzExIDY4LC01MDJjMTgsLTc2IDMxLC0xMzkgMjgsLTE0MmMtMiwtMiAtMTczLC0xMjIgLTM3OSwtMjY2Yy0yMDYsLTE0NSAtNDcxLC0zMzEgLTU4OSwtNDEzbC0yMTQsLTE1MGwtNTYsNjRjLTQ5Miw1NjcgLTExMjEsOTM5IC0xODQ0LDEwODhsLTEzMiwyN2wtMyw3MTdsLTIsNzE3bDI3LDZjNjYzLDE0NyAxMjQyLDU1NyAxNjEzLDExNDNjMTc2LDI3OSAzMTIsNjYyIDM2MSwxMDIxYzE3LDEyMyAxOCw1MDcgMSw2MjVjLTEwMiw3MTUgLTQ1MCwxMzA3IC0xMDE1LDE3MjhjLTMyNSwyNDIgLTcyMSw0MDYgLTExMjksNDY4Yy0xMjgsMTkgLTQ1MSwzMiAtNTU4LDIzek0zNDk2LDE4MTU1YzUwNCwtODggOTExLC00OTUgOTk5LC05OTljMTksLTExMyAxOSwtMzA4IDAsLTQyMmMtODUsLTQ5NCAtNDY4LC04OTAgLTk2NSwtOTk3Yy0xMjYsLTI3IC0zNTgsLTI5IC00NzgsLTNjLTM4Nyw4MiAtNzAwLDMyMSAtODcyLDY2NmMtMjI4LDQ1NSAtMTQyLDEwMTYgMjEyLDEzODdjMTg0LDE5MiA0MjEsMzIxIDY3NCwzNjdjMTA4LDE5IDMyMCwyMCA0MzAsMXpNOTY2NiwxNDYxNWM1MDAsLTg4IDg5MSwtNDcwIDEwMDAsLTk4MGMyMSwtOTkgMjQsLTMyOSA1LC00MzVjLTk2LC01NDUgLTUzNCwtOTU3IC0xMDg0LC0xMDIxYy0zMzgsLTM5IC02OTcsNzggLTk1MiwzMTBjLTIwOCwxOTAgLTMzOSw0MjUgLTM5MCw3MDBjLTE5LDEwNyAtMTksMzEzIDAsNDI3Yzg4LDUwNSA0OTIsOTA4IDEwMDAsOTk4YzEwOSwxOSAzMTIsMjAgNDIxLDF6TTM0NjUsMTE1MDBjNjM0LC03MSAxMTc0LC00NzkgMTQwOCwtMTA2NWMxMzAsLTMyNyAxNTksLTY1NiA4NywtMTAwMWMtMTAyLC00ODggLTQzNSwtOTI2IC04ODAsLTExNThjLTM4NSwtMjAxIC04MTcsLTI0OSAtMTIzOCwtMTQwYy00MzUsMTEzIC04MjcsNDE4IC0xMDU5LDgyNWMtMTAwLDE3NSAtMTc5LDQyMSAtMjAzLDYyOGMtMTcsMTQ2IC04LDM5NyAxOSw1MzRjMTA1LDUyMiA0NDIsOTY0IDkxNiwxMjAyYzMwMCwxNTAgNjI4LDIxMSA5NTAsMTc1ek05NjY2LDczNzVjNTAwLC04OCA4OTAsLTQ3MCAxMDAwLC05ODBjMjMsLTExMCAyNCwtMzQwIDAsLTQ1OGMtMTA1LC01MzggLTU0MSwtOTM5IC0xMDg1LC05OThjLTM2MSwtMzkgLTczMiw5MyAtOTg4LDM1M2MtMTU5LDE2MCAtMjQ0LDMwMCAtMzExLDUwOGMtNTMsMTY0IC02NywzOTkgLTM3LDU3NmM4OSw1MDYgNDk5LDkxNCAxMDA1LDk5OWMxMDcsMTggMzEwLDE4IDQxNiwwek0zNTIxLDM3NjBjMjQ5LC01MSA0NTAsLTE1OSA2MzAsLTMzOWMyMzgsLTIzOCAzNTksLTUzMCAzNTksLTg2NmMwLC0zMjQgLTExNSwtNjEzIC0zMzUsLTg0NGMtMjQ0LC0yNTYgLTU0MywtMzg0IC04OTUsLTM4NGMtMTU5LDAgLTI0NCwxNCAtMzk0LDY0Yy00MDgsMTM2IC03MzEsNTA1IC04MTAsOTI2Yy04Myw0NDIgNjYsODY4IDQwNiwxMTY4YzE1OCwxMzkgMzQ5LDIzMiA1NjMsMjc1YzEzMCwyNiAzNDYsMjYgNDc2LDB6Ij48L3BhdGg+PC9nPjwvZz48L2c+PC9zdmc+

[KafkaUrl]: https://kafka.apache.org/documentation/
