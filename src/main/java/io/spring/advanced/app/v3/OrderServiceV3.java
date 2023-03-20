package io.spring.advanced.app.v3;

import io.spring.advanced.trace.TraceStatus;
import io.spring.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final LogTrace logTrace;

    public void orderItem(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = logTrace.begin("OrderServiceV3.orderItem()");   // 메시지 이름 : 컨트롤러 이름 + 메서드 이름(수작업)
            orderRepository.save(itemId);
            logTrace.end(traceStatus);
        }catch (Exception e) {
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }
}
