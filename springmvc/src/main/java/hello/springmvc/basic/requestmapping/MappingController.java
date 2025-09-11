package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

//@RestController
// - @Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
// - @RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메세지 바디에 바로 입력한다. 따라서 실행 결과로 ok 메세지를 받을 수 있다.
@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본 요청
     * 둘다 허용(스프링 부터 3.0 이전만) /hello-basic, /hello-basic/(스프링 부트 3.0 이후 부터는 지원x 하나만 허용)
     * 매핑: /hello-basic -> URL 요청: /hello-basic
     * 매핑: /hello-basic/ -> URL 요청: /hello-basic/ (스프링 부트 3.0 부터는 마지막의 /를 유지한다.)
     * HTTP 메서드 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE
     */

    // @RequestMapping("/hello-basic")
    // /hello-basic URL 호출이 오면 이 메서드가 실행되도록 매핑한다.
    // 대부분의 속성을 배열[]로 제공하므로 다중 설정이 가능하다. {"/hello-basic", "/hello-go"}
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * HTTP 메서드 매핑
     * method 특정 HTTP 메서드 요청만 허용
     * GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * HTTP 메서드 매핑 축약
     * 편리한 축약 애노테이션 (코드보기)
     * - @GetMapping
     * - @PostMapping
     * - @PutMapping
     * - @DeleteMapping
     * - @PatchMapping
     */
    //GetV1 방식보다 GetV2 방식 선호 -> 훨씬 더 직관적임
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable(경로 변수) 사용
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * - @PathVariable("userId") String userId -> @PathVariable String userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * PathVariable 사용 - 다중
     * PathVariable 사용 다중
     * - @PathVariable 의 이름과 파라미터 이름이 같으면 생략할 수 있다.
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 특정 파라미터 조건 매핑
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug", "data=good"}
     * 특정 파라미터가 있거나 없는 조건을 추가할 수 있다. 잘 사용하지는 않는다.
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더 조건 매핑
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     * 파라미터 매핑과 비슷하지만, HTTP 헤더를 사용한다.
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * 미디어 타입 조건 매핑 - HTTP 요청 Content-Type, consume
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * 미디어 타입 조건 매핑 - HTTP 요청 Accept, produce
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}
