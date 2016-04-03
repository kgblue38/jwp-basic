# JWP-BASIC 중간 점검

1. 
Servlet Container가 초기화되면서, 먼저 ContextLoaderListener 클래스가 실행이 되면서 DB와 연결을 하고 jwp.sql에 있는 기본 퀴리를 실행시켜준다. 그리고, jsp 파일을 전부 servlet으로 변환시켜준다. 

2. 
localhost:8080에 접근하면 우선적으로 서블릿으로 가기전에 CharacterEncodingFilter와 ResourceFilter를 먼저 거친다. 인코딩 필터에서는 서버에서 한글 처리를 해주기 위한 인코딩 작업과 ResourceFilter는 정적인 자원과 동적인 자원의 요청을 구분하기 위한 작업을 미리한다. 이 후, 정적인 자원들은 서블릿에서 제공하는 default dispatcher가 자원을 불러주지만 루트로 접근했기 때문에 동적인 자원들이 이동하 dispatchServlet으로 이동한다. 초기에 requestmapping을 위한 인스턴스를 생성해준다. 그 다음에, RequestMapping에게 url("/")을 통한 컨트롤러를 요청한 다음 HomeController로 이동한다. HomeController에서 JstlView의 생성자 안에 이동할 url인 index.jsp를 넣어서 JstlView를 생성하고 그 JstlView를 가진 ModelAndView를 생성한다. 그리고 그 안에 questions라는 key를 가진 Question리스트를 model로 저장한다. 모델과 뷰를 넣어준 ModelAndView를 HomeController는 리턴하면 DispatcherServlet에서 ModelAndView에서 View를 반환하고 view는 render메서드를 통해서 redirect면 redirect해주지만 루트에 갈때는 forward형식이기 때문에 model에 있는 attribute의 key와 value를 꺼내온다음 request에 저장하고(사실 홈에 접근할때는 model에 어떠한 속성도 저장되어 있지 않음) 그 다음에 index.jsp로 이동하면, 사용자들은 화면을 보게 된다.

7.
서블릿을 싱글 인스턴스에서 멀티 쓰레드로 돌아간다. 그리고 인스턴스 영역의 데이터는 힙 영역에 저장된다. 쓰레드는 힙 영역은 공유하기 때문에 인스턴스 필드에 상태를 가지는 필드를 선언하면 안된다. Question, Answer는 ShowController에 접근하는 쓰레드마다 각각 다를 수 있는 값이다. 따라서 execute메서드 안에 넣어줘야한다. 메서드는 기본적으로 stack에 저장이 되기 때문에 쓰레드끼리 공유하지 않는다.