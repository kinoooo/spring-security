package com.yunfeng.web.async;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-11
 */
@Component
@Getter
@Slf4j
public class MockQueue {

    // 下单
    private String placeOrder;
    // 订单完成
    private String completeOrder;

    public void setPlaceOrder(String placeOrder) throws Exception {
        new Thread(() -> {
            log.info("接到下单请求, " + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("下单请求处理完毕," + placeOrder);
        }).start();
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
