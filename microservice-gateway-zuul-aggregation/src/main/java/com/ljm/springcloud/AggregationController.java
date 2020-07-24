package com.ljm.springcloud;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;
import rx.functions.Func2;

import java.util.HashMap;

/**
 * @author liaojiamin
 * @Date:Created in 11:22 2020/7/10
 */
@RestController
public class AggregationController {
    public static final Logger logger = LoggerFactory.getLogger(AggregationController.class);
    @Autowired
    private AggregationService aggregationService;

    @GetMapping("/aggregation/{id}")
    public DeferredResult<HashMap<String, User>> aggregate(@PathVariable Long id) {
        Observable<HashMap<String, User>> result = this.aggregateObservable(id);
        return this.toDeferredResult(result);
    }

    public Observable<HashMap<String, User>> aggregateObservable(Long id) {
        // RXjava 合并两个或者多个Observables发射出的数据项，根据指定的函数变换它们
        return Observable.zip(
                aggregationService.getUserById(id),
                aggregationService.getBusinessUserByUserId(id),
                new Func2<User, User, HashMap<String, User>>() {
                    @Override
                    public HashMap<String, User> call(User user, User user2) {
                        HashMap<String, User> map = Maps.newHashMap();
                        map.put("user", user);
                        map.put("businessUser", user2);
                        return map;
                    }
                });
    }

    public DeferredResult<HashMap<String, User>> toDeferredResult(Observable<HashMap<String, User>> details) {
        DeferredResult<HashMap<String, User>> result = new DeferredResult<>();
        details.subscribe(
                new Observer<HashMap<String, User>>() {
                    @Override
                    public void onCompleted() {
                        logger.info("完成请求 ...");
                    }

                    @Override
                    public void onError(Throwable e) {
                        logger.error("请求错误....", e);
                    }

                    @Override
                    public void onNext(HashMap<String, User> stringUserHashMap) {
                        result.setResult(stringUserHashMap);
                    }
                }
        );
        return result;
    }

}
