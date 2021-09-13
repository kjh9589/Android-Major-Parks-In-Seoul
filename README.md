<a href='https://play.google.com/store/apps/details?id=com.teamnoyes.majorparksinseoul'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height="70" width="180"/></a>

# 서울시 우리 동네 공원
<br><br>
![banner](https://user-images.githubusercontent.com/47181654/111105781-0d063a80-8597-11eb-99a8-6d0759db8c03.png)

◆ 서울시 주요 공원현황API를 기반으로 한 우리 동네 공원 리스트

◆ 집에만 있기에 갑갑할 때는 우리 동네 근처 공원에서 산책을 하는 것은 어떨까요?

◆ 구별로 나눠져 있는 정보
서울시 지역구 별로 보기 쉽게 구성되어 있습니다.

◆ 해당 공원에 대한 상세한 정보
공원까지 오시는 길과 주요 시설을 표시해 놓아 공원의 접근성과 이용 목적에 맞게 정보를 알려드립니다.

◆ 즐겨찾기
* 자주 가는 공원을 즐겨찾기에 등록하여 더 빠르게 이용해보세요.

◆ 길찾기
* 휴대폰에 지도 어플이 있다면 해당 공원까지 길을 찾을 수 있도록 좌표를 제공합니다.

# 기술 스택

|Category| - |
| --- | --- |
|Language|Kotlin|
|Network|Retrofit2|
|Jetpack|DataBinding|
||LiveData|
||Room|
||Navigation|

# 1.1
 * Navigation + BottomNavigationView
 * Repository 패턴 적용

# 간단 설명
 * SharedViewModel
  전체 공원 정보들을 가지고 있는 클래스
  MainActivity, ParkListFragment, DetailParkFragment에 사용
 * ParkRepository
  공공데이터 API 서버에서 공원 정보 불러오기, 로컬 데이터 베이스(Room)에서 정보 불러오기 및 asset 폴더에서 구별 정보 불러오기
 * NetworkLiveData
  사용자의 네트워크 상태를 체크하기 위한 object, WIFI 연결과 모바일 통신을 감지
  
# 개발 시 고려사항
  * 사용자가 어플을 사용 시 네트워크가 끊어져 있다면?
    * 이 어플은 사용자의 네트워크가 연결되어 있지 않다면 즐겨찾기에 저장된 공원들을 제외하고는 각 구별 공원을 볼 수가 없다.
    * 공원 정보는 어플 사용시에 한 번 전부 받아오는 상황이다. 받아오는 시점은 사용자가 처음 어플에 들어올 시 받아온다.
    * 안전하게 모든 공원 정보를 방아오기 위해 사용자에게 Logo 화면을 보여주면서 네트워크가 연결되어 있고, 공원 정보를 전부 받았을 시 메인 화면으로 이동하는 방식을 해결책으로 생각했다.
    * 네트워크를 감지하기 위해 ConnectivityManger를 사용하고, 이를 LiveData와 결합하여 네트워크 변경 상황을 알 수 있게 했다(NetworkLiveData).
    * NetworkLiveData가 true 상태일 때 공공데이터 API 서버에 공원 정보를 요청하고 공원 정보들을 완전히 받은 경우에 메인 화면으로 넘어간다.
    * 만약 NetworkLiveData가 true인 상태에서 공원정보를 받는 도중 NetworkLiveData가 false가 되면 공원 정보를 완전히 받지 않았기에 메인 화면으로 넘어가지 않고 다시 네트워크가 연결되기를 기다린다.
   <br>
  * API 키를 어떻게 숨길까?
    * 이 어플은 Naver Map API와 서울시 공공데이터 서울시 주요 공원현황 API를 사용한다.
    * 프로젝트를 GitHub에 올리기 때문에 API 키를 그대로 사용 시 타인에게 노출될 우려가 있다.
    * 이를 해결하기 위해 gitignore에 등록되어 있는 local.properties를 이용하여 해당 파일 내에 API키들을 입력해놓았다.
    * Naver Map API 키는 Manifest 파일에 meta-data에서 사용되므로 build.gradle(app)에서 manifestPlaceholders를 이용하여 local.properties에 있는 키를 불러와 사용한다.
    * 서울시 공공데이터 서울시 주요 공원현황 API는 Retrofit2를 이용하여 호출하기에 키를 build.gradle(app)에서 buildConfigField를 이용하여 local.properties에 있는 키를 불러와 사용한다.

# 개발 의의
 * 집에만 있는 요즘 기분 전환을 위한 산책 정보 및 집 근처 녹지 정보 제공
 * Jetpack을 실서비스에 적용
 * MVVM 아키텍쳐 패턴 적용
 * Naver Cloud Platform(Maps)
 

# 스크린샷

![screenshot1](https://user-images.githubusercontent.com/85272794/133020976-6b11d9b4-b788-4bd2-b36e-5419af9e87bc.jpg)
![screenshot2](https://user-images.githubusercontent.com/85272794/133020979-1df3466f-bb90-4215-8836-be59d904945c.jpg)
![screenshot3](https://user-images.githubusercontent.com/85272794/133020982-7f550379-9e97-4704-a015-7325d03de352.jpg)
![screenshot4](https://user-images.githubusercontent.com/85272794/133020983-dccc146c-06ae-4063-9c69-5f91295df9d1.jpg)

# 동영상
![video1](https://user-images.githubusercontent.com/85272794/133025893-157da8cf-142b-4ed8-a3ec-f928fbebdc28.gif)
