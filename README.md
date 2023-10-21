# doonut-server

## 멀티모듈 설계
서버간의 의존성 분리를 위한 멀티모듈 설계  
<img src="https://user-images.githubusercontent.com/52141636/277077892-2ddff744-a5c5-4f4c-84c6-92f628ee1dae.png" width="800" />


### doonut-app-external-api
- 애플리케이션 모듈 계층
- 외부에서 사용해야하는 애플리케이션 정의 (사용자 api)

### doonut-secret-submodule
- 내부 모듈 계층
- 전체 시스템에서 사용되는 외부 노출이 되지 않는 데이터 정의

### doonut-core
- 도메인 모듈 계층
- 시스템 중심 domain 및 repository 정의

### doonut-support
- 공통 모듈 계층
- 전체 모듈이 공통적으로 사용하는 Util, custom exception 정의