package io.spring.advanced.app.v2;

import io.spring.advanced.trace.TraceId;
import io.spring.advanced.trace.TraceStatus;
import io.spring.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 helloTrace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = helloTrace.beginSync(traceId,"OrderServiceV3.orderItem()");   // 메시지 이름 : 컨트롤러 이름 + 메서드 이름(수작업)
            orderRepository.save(traceStatus.getTraceId(), itemId);
            helloTrace.end(traceStatus);
        }catch (Exception e) {
            helloTrace.exception(traceStatus, e);
            throw e;
        }
    }
}
