package io.spring.advanced.app.v3;

import io.spring.advanced.trace.TraceStatus;
import io.spring.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
    private final LogTrace logTrace;

    public void save(String itemId) {
        TraceStatus traceStatus = null;

        try {
            traceStatus = logTrace.begin("OrderRepositoryV3.save()");   // 메시지 이름 : 컨트롤러 이름 + 메서드 이름(수작업)

            if(itemId.equals("ex")) {
                throw new IllegalArgumentException("exception!!");
            }
            sleep(1000);    //리포지토리는 상품을 저장하는데 약 1초 정도 걸리는 것으로 가정하기 위해 1초 지연(1000ms)

            logTrace.end(traceStatus);
        } catch (Exception e) {
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
