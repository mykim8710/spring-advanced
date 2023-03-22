package io.spring.advanced.app.v5;

import io.spring.advanced.global.ThreadUtil;
import io.spring.advanced.trace.logtrace.LogTrace;
import io.spring.advanced.trace.template.AbstractTemplate;
import io.spring.advanced.trace.templatecallback.TraceTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepositoryV5 {
    private final TraceTemplate traceTemplate;

    public OrderRepositoryV5(LogTrace logTrace) {
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    public void save(String itemId) {
        traceTemplate.execute("OrderRepositoryV5.save()", () ->{
            // 저장로직
            if(itemId.equals("ex")) {
                throw new IllegalArgumentException("exception!!");
            }
            ThreadUtil.sleep(1000);
            return null;
        });
    }
}
