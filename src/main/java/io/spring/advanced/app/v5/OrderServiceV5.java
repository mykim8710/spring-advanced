package io.spring.advanced.app.v5;

import io.spring.advanced.trace.logtrace.LogTrace;
import io.spring.advanced.trace.template.AbstractTemplate;
import io.spring.advanced.trace.templatecallback.TraceTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate traceTemplate;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace logTrace) {
        this.orderRepository = orderRepository;
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    public void orderItem(String itemId) {
        traceTemplate.execute("OrderServiceV5.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
