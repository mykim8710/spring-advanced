package io.spring.advanced.app.v4;

import io.spring.advanced.global.ThreadUtil;
import io.spring.advanced.trace.TraceStatus;
import io.spring.advanced.trace.logtrace.LogTrace;
import io.spring.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace logTrace;

    public void save(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(logTrace) {
            @Override
            protected Void call() {
                // 저장로직
                if(itemId.equals("ex")) {
                    throw new IllegalArgumentException("exception!!");
                }
                ThreadUtil.sleep(1000);
                return null;
            }
        };

        template.execute("OrderRepositoryV4.save()");
    }
}
