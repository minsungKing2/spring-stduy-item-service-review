package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
//@Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
//@RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다. 따라서 실행 결과로 ok 메세지를 받을 수 있다.
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {

        String name = "Spring";

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        //시간, 로그 레벨, 프로세스 ID, 쓰레드 명, 클래스명, 로그 메시지
        //2025-09-04T16:23:24.019+09:00  INFO 154556 --- [springmvc] [nio-8080-exec-8] h.springmvc.basic.LogTestController      :  info log=Spring
        //2025-09-04T16:23:24.019+09:00  WARN 154556 --- [springmvc] [nio-8080-exec-8] h.springmvc.basic.LogTestController      :  warn log=Spring
        //2025-09-04T16:23:24.019+09:00 ERROR 154556 --- [springmvc] [nio-8080-exec-8] h.springmvc.basic.LogTestController      : error log=Spring

        //로그 LEVEL: TRACE > DEBUG > INFO > WARN > ERROR -> 개발 서버는 debug 출력, 운영 서버는 info 출력

        //로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 X
        log.debug("String concat log=" + name);
        return "ok";
    }
}
