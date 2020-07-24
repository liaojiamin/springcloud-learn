package com.ljm.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

/**
 * @author liaojiamin
 * @Date:Created in 11:13 2020/7/10
 */
@Service
public class AggregationService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getUserById(Long id){
        // 创建一个被观察者
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                // 请求用户微服务的/{id}端点
                User user = restTemplate.getForObject("http://microservice-provider-user/{id}", User.class, id);
                subscriber.onNext(user);
                subscriber.onCompleted();
            }
        });
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getBusinessUserByUserId(Long id){
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                // 请求业务微服务的/user/{id}端点
                User movieUser = restTemplate.getForObject("http://microservice-consumer-business/user/{id}", User.class, id);
                subscriber.onNext(movieUser);
                subscriber.onCompleted();
            }
        });
    }

    public User fallback(Long id){
        User user = new User();
        user.setId(-1L);
        return user;
    }
}
