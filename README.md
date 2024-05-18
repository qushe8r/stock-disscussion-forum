<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->

[//]: # ([![Contributors][contributors-shield]][contributors-url])
[//]: # ([![Forks][forks-shield]][forks-url])
[//]: # ([![Stargazers][stars-shield]][stars-url])
[//]: # ([![Issues][issues-shield]][issues-url])
[//]: # ([![MIT License][license-shield]][license-url])
[//]: # ([![LinkedIn][linkedin-shield]][linkedin-url])

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
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#시작하기">시작하기</a>
      <ul>
        <li><a href="#사전 준비 사항">사전 준비 사항</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->

## 프로젝트 설명

[//]: # ([![Product Name Screen Shot][product-screenshot]]&#40;https://example.com&#41;)
[//]: # (도커 컴포우즈 배포 이미지)


이 프로젝트는 MSA로 구성된 프로젝트입니다.

종목에 대한 정보를 실시간으로 받아 주식 지표를 생성합니다.

주식 정보를 토대로 자유롭게 글과 댓글, 좋아요 등의 기능으로 소통할 수 있는 커뮤니티입니다.

<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>

### 기술 스택
[![SpringBoot][SpringBoot]][SpringBootUrl] [![SpringWeb][SpringWeb]][SpringWebUrl] [![SpringSecurity][SpringSecurity]][SpringSecurityUrl] [![SpringDataJPA][SpringDataJPA]][SpringDataJPAUrl] [![SpringBatch][SpringBatch]][SpringBatchUrl]</br>
[![SpringGateway][SpringGateway]][SpringGatewayUrl] [![SpringEureka][SpringEureka]][SpringEurekaUrl] [![SpringOpenFeign][SpringOpenFeign]][SpringOpenFeignUrl] [![SpringResilience4J][SpringResilience4J]][SpringResilience4JUrl] </br>
[![Gradle][Gradle]][GradleUrl] </br>
[![QueryDSL][QueryDSL]][QueryDSLUrl] [![MySQL][MySQL]][MySQLUrl] [![Redis][Redis]][RedisUrl] [![Kafka][Kafka]][KafkaUrl] [![JWT][JWT]][JWTUrl] </br>
[![Docker][Docker]][DockerUrl] [![DockerCompose][DockerCompose]][DockerComposeUrl]


<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>

### 도커 컴포우즈
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
2. 도커 이미지 생성
    ```shell
    ./gradlew docker
    ```
3. 도커 컴포우즈 실행
   ```shell
   docker-compose up -d --force-recreate --build
   ```
4. 스프링 배치 실행(데이터 준비)
   ```shell
   java -jar ./stock-batch/build/libs/stock-batch-0.0.1-SNAPSHOT.jar
   ```

<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>




<!-- ROADMAP -->

## 지표 생성 데이터 흐름

<img width="1667" alt="지표 생성 데이터 흐름" src="https://github.com/qushe8r/stock-disscussion-forum/assets/115606959/9603960d-6759-4bfb-bbce-e25acfa659d8">


[//]: # (- [ ] Feature 1)

[//]: # (- [ ] Feature 2)

[//]: # (- [ ] Feature 3)

[//]: # (    - [ ] Nested Feature)

[//]: # ()
[//]: # (See the [open issues]&#40;https://github.com/github_username/repo_name/issues&#41; for a full list of proposed features &#40;and)

[//]: # (known issues&#41;.)

[//]: # ()
[//]: # (<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>)

[//]: # ()
[//]: # (<!-- USAGE EXAMPLES -->)

[//]: # ()
[//]: # (## Usage)

[//]: # ()
[//]: # (Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos)

[//]: # (work well in this space. You may also link to more resources.)

[//]: # ()
[//]: # (_For more examples, please refer to the [Documentation]&#40;https://example.com&#41;_)

[//]: # ()
[//]: # (<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>)

[//]: # ()
[//]: # (<!-- CONTRIBUTING -->)

[//]: # ()
[//]: # (## Contributing)

[//]: # ()
[//]: # (Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any)

[//]: # (contributions you make are **greatly appreciated**.)

[//]: # ()
[//]: # (If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also)

[//]: # (simply open an issue with the tag "enhancement".)

[//]: # (Don't forget to give the project a star! Thanks again!)

[//]: # ()
[//]: # (1. Fork the Project)

[//]: # (2. Create your Feature Branch &#40;`git checkout -b feature/AmazingFeature`&#41;)

[//]: # (3. Commit your Changes &#40;`git commit -m 'Add some AmazingFeature'`&#41;)

[//]: # (4. Push to the Branch &#40;`git push origin feature/AmazingFeature`&#41;)

[//]: # (5. Open a Pull Request)

[//]: # ()
[//]: # (<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (<!-- LICENSE -->)

[//]: # ()
[//]: # (## License)

[//]: # ()
[//]: # (Distributed under the MIT License. See `LICENSE.txt` for more information.)

[//]: # ()
[//]: # (<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>)

[//]: # ()
[//]: # ()
[//]: # (<!-- CONTACT -->)

[//]: # ()
[//]: # (## Contact)

[//]: # ()
[//]: # (Your Name - [@twitter_handle]&#40;https://twitter.com/twitter_handle&#41; - email@email_client.com)

[//]: # ()
[//]: # (Project Link: [https://github.com/github_username/repo_name]&#40;https://github.com/github_username/repo_name&#41;)

[//]: # ()
[//]: # (<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>)

[//]: # ()
[//]: # ()
[//]: # (<!-- ACKNOWLEDGMENTS -->)

[//]: # ()
[//]: # (## Acknowledgments)

[//]: # ()
[//]: # (* []&#40;&#41;)

[//]: # (* []&#40;&#41;)

[//]: # (* []&#40;&#41;)

[//]: # ()
[//]: # (<p align="right"><a href="#readme-top">가장 위로 돌아가기</a></p>)


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

[QueryDSL]: https://img.shields.io/badge/queryDSL-0769AD?style=for-the-badge&logoColor=61DAFB&logo=data:image/svg+xml;base64,PHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHdpZHRoPSIzNDAuMDAwMDAwcHQiIGhlaWdodD0iMzQwLjAwMDAwMHB0IiB2aWV3Qm94PSIwLDAsMjU2LDI1NiI+PGcgZmlsbD0iI2ZmZmZmZiIgZmlsbC1ydWxlPSJub256ZXJvIiBzdHJva2U9Im5vbmUiIHN0cm9rZS13aWR0aD0iMSIgc3Ryb2tlLWxpbmVjYXA9ImJ1dHQiIHN0cm9rZS1saW5lam9pbj0ibWl0ZXIiIHN0cm9rZS1taXRlcmxpbWl0PSIxMCIgc3Ryb2tlLWRhc2hhcnJheT0iIiBzdHJva2UtZGFzaG9mZnNldD0iMCIgZm9udC1mYW1pbHk9Im5vbmUiIGZvbnQtd2VpZ2h0PSJub25lIiBmb250LXNpemU9Im5vbmUiIHRleHQtYW5jaG9yPSJub25lIiBzdHlsZT0ibWl4LWJsZW5kLW1vZGU6IG5vcm1hbCI+PGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoMC40NDgxNCw2LjQwNDEzKSBzY2FsZSgwLjc1Mjk0LDAuNzUyOTQpIj48ZyB0cmFuc2Zvcm09InRyYW5zbGF0ZSgwLDM0MCkgc2NhbGUoMC4xLC0wLjEpIj48cGF0aCBkPSJNMTE1NCwzMjkxYy02MCwtMTUgLTE0MiwtODYgLTE2OCwtMTQ1Yy0yOCwtNjMgLTQ0LC0xODAgLTI2LC0xOTFjNSwtMyAxMCwtMTUgMTAsLTI1YzAsLTQ0IDkxLC0xNjAgMTI1LC0xNjBjOCwwIDE1LC00IDE1LC0xMGMwLC01IDksLTEwIDE5LC0xMGMxMSwwIDIzLC00IDI2LC0xMGMzLC01IDQ2LC0xMCA5NiwtMTBjNTMsMCA4OSw0IDg5LDEwYzAsNiA3LDEwIDE1LDEwYzQwLDAgMTE4LDcyIDE0NiwxMzRjMjQsNTYgMzcsMTUyIDI2LDE5MWMtMTMsNDMgLTM1LDk0IC00NywxMDhjLTYsNyAtMTgsMjEgLTI4LDMzYy05LDExIC0zOSwzMyAtNjcsNDhjLTQwLDIzIC02NSwyOSAtMTI1LDMyYy00MSwyIC04OSwtMSAtMTA2LC01eiI+PC9wYXRoPjxwYXRoIGQ9Ik0xODUwLDMyODNjLTM3LC0xNiAtMTEwLC04NCAtMTEwLC0xMDJjMCwtNiAtNCwtMTEgLTEwLC0xMWMtNSwwIC0xMCwtMTMgLTEwLC0yOGMwLC0xNSAtNSwtMzMgLTExLC0zOWMtMTUsLTE1IDExLC0xMzMgMzYsLTE1OWMxMSwtMTIgMjgsLTMwIDM3LC00MGM2NiwtNzQgMTc0LC05MiAyNjcsLTQ1YzM2LDE5IDg1LDY4IDEwOSwxMDljMTUsMjYgMTcsMTkyIDIsMTkyYy01LDAgLTEwLDcgLTEwLDE1YzAsMjMgLTY4LDg3IC0xMTMsMTA4Yy00OCwyMSAtMTM4LDIxIC0xODcsMHoiPjwvcGF0aD48cGF0aCBkPSJNMjM5NywyOTg2Yy0xNiwtOSAtNDQsLTMxIC02MywtNTBjLTMyLC0zMiAtMzQsLTM3IC0zNCwtMTA2di03Mmw0NywtNDRjNDUsLTQzIDQ3LC00NCAxMTUsLTQ0YzM5LDAgNjgsNCA2OCwxMGMwLDYgNSwxMCAxMiwxMGMxOSwwIDY4LDYyIDgwLDEwMmM4LDI4IDgsNDggLTIsODJjLTEzLDQ2IC01Miw5NiAtNzYsOTZjLTcsMCAtMTQsNSAtMTYsMTFjLTYsMTkgLTk5LDIyIC0xMzEsNXoiPjwvcGF0aD48cGF0aCBkPSJNNTEwLDI4MzRjLTQyLC05IC0xMDksLTQzIC0xNDAsLTcyYy0xNiwtMTYgLTQzLC01NCAtNjAsLTg2Yy0yNiwtNTEgLTMwLC02OCAtMzAsLTE0MmMwLC03MCA0LC05MyAyNSwtMTM0YzI1LC01MCA4NSwtMTEzIDEyNywtMTM0YzgyLC00MiAyMDgsLTQ3IDI4MCwtMTJjNDUsMjIgMTI4LDk1IDEyOCwxMTNjMCw3IDUsMTMgMTAsMTNjNiwwIDEwLDkgMTAsMjBjMCwxMSA0LDIwIDksMjBjMTIsMCAzMSwxNTMgMjAsMTY1Yy01LDUgLTksMTkgLTksMzJjMCwxMiAtOSwzOSAtMjEsNjBjLTI1LDQ3IC0xMTYsMTMzIC0xNDAsMTMzYy05LDAgLTE5LDQgLTIxLDEwYy0zLDEwIC0xNDksMjEgLTE4OCwxNHoiPjwvcGF0aD48cGF0aCBkPSJNMjc1NSwyNjA2Yy0zNywtMTQgLTQ4LC0yNCAtNjksLTYzYy0yNywtNTMgLTE5LC04OSAyOSwtMTQxYzMyLC0zNSAxMTksLTM2IDE1OCwtMmM0MywzOCA1Miw3MSAzMiwxMjljLTksMzAgLTIzLDUxIC0zMSw1MWMtOCwwIC0xNCw1IC0xNCwxMGMwLDYgLTksMTAgLTE5LDEwYy0xMSwwIC0yMyw1IC0yNiwxMGMtOCwxMiAtMjEsMTEgLTYwLC00eiI+PC9wYXRoPjxwYXRoIGQ9Ik0yOTAzLDIxODJjLTQ5LC01OSAtMTYsLTE0MiA1NiwtMTQyYzgzLDAgMTI0LDY5IDgwLDEzNWMtMjEsMzEgLTI5LDM1IC02OSwzNWMtMzcsMCAtNDksLTUgLTY3LC0yOHoiPjwvcGF0aD48cGF0aCBkPSJNMjczLDIwMDNjLTI5LC0yIC01MywtOCAtNTMsLTE0YzAsLTUgLTksLTkgLTIwLC05Yy0xMSwwIC0yMCwtNCAtMjAsLTEwYzAsLTUgLTcsLTEwIC0xNiwtMTBjLTE3LDAgLTI3LC05IC02NSwtNjBjLTUwLC02OCAtNjEsLTk4IC02MSwtMTgwYzAsLTk1IDIyLC0xNDQgOTMsLTIxMWM2OSwtNjUgMTc3LC05NSAyNTUsLTcwYzE2LDUgNDEsMTIgNTYsMTVjMzIsNyAxMTgsODkgMTM3LDEzMWM3LDE3IDE3LDM4IDIyLDQ3YzEyLDI1IDExLDE3OCAtMSwxNzhjLTUsMCAtMTAsNyAtMTAsMTVjMCw4IC0xNSwzOCAtMzIsNjVjLTU1LDgyIC0xNTYsMTIyIC0yODUsMTEzeiI+PC9wYXRoPjxwYXRoIGQ9Ik0yOTcxLDE4MDNjLTI1LC0zMSAtMjAsLTU4IDE1LC05MGMyMCwtMTkgMjgsLTIwIDU0LC0xMWMzNSwxMiA1MCwzNSA1MCw3NWMwLDM5IC0xNyw1MyAtNjEsNTNjLTI5LDAgLTQzLC02IC01OCwtMjd6Ij48L3BhdGg+PHBhdGggZD0iTTE5MzUsMTYwMWMtNzUsLTE5IC0xNTUsLTk5IC0xNTUsLTE1NWMwLC0xNCAtNSwtMjYgLTExLC0yNmMtMTgsMCA2LC0xMjMgMzAsLTE1M2M4MCwtMTAxIDE2MSwtMTI3IDI3NywtOTJjNjQsMTkgMTA4LDE5IDE0NywtMmMzNywtMTkgNTYsLTQxIDU3LC02NWMwLC0xMCA0LC0xOCA5LC0xOGM2LDAgOSwtNzUgOCwtMTgyYy0xLC0xMzYgMSwtMTgyIDExLC0xODZjNiwtMiAxMiwtMTQgMTIsLTI4YzAsLTIyIDI3LC03OSA1MCwtMTA3YzYsLTcgMjksLTM2IDUyLC02NGMyMywtMjkgNDUsLTUzIDQ4LC01M2M0LDAgMjEsLTEzIDM4LC0zMGMxOCwtMTcgMzksLTMwIDQ3LC0zMGM4LDAgMTUsLTQgMTUsLTEwYzAsLTUgNywtMTAgMTUsLTEwYzgsMCAyMywtNCAzMywtOWM0NiwtMjMgOTUsLTMzIDE4OCwtMzhjODIsLTUgMTA4LC0zIDEzNCwxMWMxOCw5IDQxLDE2IDUyLDE2YzEwLDAgMjYsNCAzNiw5YzksNSAzMSwxNSA0NywyMmMxNyw4IDM5LDIyIDQ5LDMyYzExLDkgMjQsMTcgMjgsMTdjMTEsMCA2Nyw1NyA5NSw5NWM3NiwxMDkgMTA1LDE5NyAxMDMsMzI0Yy0xLDExMyAtMTEsMTY0IC01MiwyNDhjLTY5LDE0MyAtMjY0LDI4MyAtMzk0LDI4M2MtMTgsMCAtMzYsNSAtMzksMTBjLTMsNiAtMTksMTAgLTM2LDEwYy0xNiwwIC0yOSwtNCAtMjksLTEwYzAsLTUgLTE5LC0xMCAtNDIsLTEwYy02OCwwIC0xNjgsLTQwIC0yODgsLTExNWMtNDUsLTI4IC02NSwtMzQgLTExMSwtMzVjLTUwLDAgLTU4LDMgLTg5LDM1Yy0zNSwzNiAtNjAsOTIgLTYwLDEzNmMwLDI4IC0yMSw3OSAtMzIsNzljLTUsMCAtOCw2IC04LDEzYzAsMTYgLTM4LDUwIC04MCw3MmMtMzcsMTkgLTExMywyNyAtMTU1LDE2eiI+PC9wYXRoPjxwYXRoIGQ9Ik01MDAsMTIwMGMwLC01IC05LC0xMCAtMjAsLTEwYy0yMSwwIC0zNSwtOSAtNzMsLTQ2Yy0zNiwtMzQgLTU3LC02MyAtNTcsLTc5YzAsLTcgLTQsLTE3IC0xMCwtMjBjLTIzLC0xNCAtMywtMTg1IDIyLC0xODVjNCwwIDgsLTYgOCwtMTJjMCwtMjAgNDUsLTY4IDY0LC02OGM5LDAgMTYsLTQgMTYsLTEwYzAsLTUgOSwtMTAgMTksLTEwYzExLDAgMjMsLTQgMjYsLTEwYzgsLTEzIDEzMiwtMTMgMTQwLDBjMyw2IDE1LDEwIDI2LDEwYzMxLDAgMTEyLDg4IDEyNywxMzdjMzIsMTEwIC0yLDIwNyAtOTUsMjcxYy0xNywxMiAtMzgsMjIgLTQ3LDIyYy05LDAgLTE2LDUgLTE2LDEwYzAsNiAtMjgsMTAgLTY1LDEwYy0zNywwIC02NSwtNCAtNjUsLTEweiI+PC9wYXRoPjxwYXRoIGQ9Ik05OTAsNzAwYzAsLTUgLTcsLTEwIC0xNSwtMTBjLTE4LDAgLTUzLC0zMyAtNzQsLTcxYy0xNywtMjkgLTE2LC0xMzAgMSwtMTYxYzIwLC0zNCA1NiwtNjggNzIsLTY4YzgsMCAxOCwtNCAyMSwtMTBjOSwtMTUgOTgsLTEyIDEzMiw1YzE2LDggNDIsMjggNTcsNDVjODAsOTEgNTUsMjAzIC02MSwyNjhjLTI3LDE0IC0xMzMsMTYgLTEzMywyeiI+PC9wYXRoPjxwYXRoIGQ9Ik0xNDg1LDUwNWMtNDIsLTIxIC01NiwtNDggLTU3LC0xMDVjMCwtODQgNjAsLTEzOSAxMzcsLTEyN2MzOSw2IDg0LDM2IDg1LDU1YzAsNiA1LDEyIDEwLDEyYzYsMCAxMCwyNCAxMCw1M2MwLDQzIC01LDU4IC0yOSw4NmMtMjQsMjggLTM3LDM0IC03NywzOGMtMzIsMiAtNjAsLTIgLTc5LC0xMnoiPjwvcGF0aD48cGF0aCBkPSJNMTk1Miw1MDVjLTU2LC0yNSAtNjcsLTEwNiAtMjAsLTE0NmMyOSwtMjYgOTgsLTI2IDEyMCwwYzM0LDQwIDQwLDYxIDI4LDkxYy0yNCw1OCAtNzMsNzkgLTEyOCw1NXoiPjwvcGF0aD48L2c+PC9nPjwvZz48L3N2Zz4=

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
