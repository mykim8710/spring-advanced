package io.spring.advanced.app.v2;

import io.spring.advanced.trace.TraceStatus;
import io.spring.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    private final HelloTraceV2 helloTrace;

    @GetMapping("/v2/request")
    public String request(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = helloTrace.begin("OrderControllerV3.request()");   // 메시지 이름 : 컨트롤러 이름 + 메서드 이름(수작업)
            orderService.orderItem(traceStatus.getTraceId(), itemId);
            helloTrace.end(traceStatus);
            return "ok";
        } catch (Exception e) {
            helloTrace.exception(traceStatus, e);
            throw e;
            /**
             * 예외를 꼭 다시 던져주어야 한다.
             * 그렇지 않으면 여기서 예외를 먹어버리고, 이후에 정상 흐름으로 동작한다.
             * 로그는 애플리케이션에 흐름에 영향을 주면 안된다.
             * 로그 때문에 예외가 사라지면 안된다.
             */
        }
    }
}
