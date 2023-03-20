package io.spring.advanced.app.v1;

import io.spring.advanced.trace.TraceStatus;
import io.spring.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 helloTrace;

    public void orderItem(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = helloTrace.begin("OrderServiceV3.orderItem()");   // 메시지 이름 : 컨트롤러 이름 + 메서드 이름(수작업)
            orderRepository.save(itemId);
            helloTrace.end(traceStatus);
        }catch (Exception e) {
            helloTrace.exception(traceStatus, e);
            throw e;
        }
    }
}
