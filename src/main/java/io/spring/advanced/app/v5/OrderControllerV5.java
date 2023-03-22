package io.spring.advanced.app.v5;

import io.spring.advanced.trace.logtrace.LogTrace;
import io.spring.advanced.trace.templatecallback.TraceTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final TraceTemplate traceTemplate;  // 스프링 빈으로 등록하고 사용해도 된다.

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace) {
        this.orderService = orderService;
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {
        return traceTemplate.execute("OrderControllerV5.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }
}
