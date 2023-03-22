package io.spring.advanced.app.v0;

import io.spring.advanced.global.ThreadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {
    public void save(String itemId) {
        if(itemId.equals("ex")) {
            throw new IllegalArgumentException("exception!!");
        }

        ThreadUtil.sleep(1000);    //리포지토리는 상품을 저장하는데 약 1초 정도 걸리는 것으로 가정하기 위해 1초 지연(1000ms)
    }
}
