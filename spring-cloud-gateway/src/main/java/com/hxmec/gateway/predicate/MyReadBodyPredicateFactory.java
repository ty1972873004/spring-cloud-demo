package com.hxmec.gateway.predicate;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 功能描述:
 * 自定义ReadBodyPredicateFactory，copy之ReadBodyPredicateFactory
 * 实现request body为空时也可以匹配如有成功。
 * @author  Trazen
 * @date  2020/7/2 14:38
 */
@Component
@Slf4j
public class MyReadBodyPredicateFactory extends AbstractRoutePredicateFactory<MyReadBodyPredicateFactory.Config> {

    private static final String TEST_ATTRIBUTE = "read_body_predicate_test_attribute";

    private static final String CACHE_REQUEST_BODY_OBJECT_KEY = "cachedRequestBodyObject";

    private static final List<HttpMessageReader<?>> messageReaders = HandlerStrategies
            .withDefaults().messageReaders();

    public MyReadBodyPredicateFactory() {
        super(MyReadBodyPredicateFactory.Config.class);
    }

    public MyReadBodyPredicateFactory(Class<MyReadBodyPredicateFactory.Config> configClass) {
        super(configClass);
    }


    @Override
    @SuppressWarnings("unchecked")
    public AsyncPredicate<ServerWebExchange> applyAsync(MyReadBodyPredicateFactory.Config config) {
        return new AsyncPredicate<ServerWebExchange>() {
            @Override
            public Publisher<Boolean> apply(ServerWebExchange exchange) {
                Class inClass = config.getInClass();

                Object cachedBody = exchange.getAttribute(CACHE_REQUEST_BODY_OBJECT_KEY);
                Mono<?> modifiedBody;
                // We can only read the body from the request once, once that happens if
                // we try to read the body again an exception will be thrown. The below
                // if/else caches the body object as a request attribute in the
                // ServerWebExchange so if this filter is run more than once (due to more
                // than one route using it) we do not try to read the request body
                // multiple times
                if (cachedBody != null) {
                    try {
                        boolean test = config.predicate.test(cachedBody);
                        exchange.getAttributes().put(TEST_ATTRIBUTE, test);
                        return Mono.just(test);
                    } catch (ClassCastException e) {
                        if (log.isDebugEnabled()) {
                            log.debug("Predicate test failed because class in predicate "
                                    + "does not match the cached body object", e);
                        }
                    }
                    return Mono.just(false);
                } else {
                    return ServerWebExchangeUtils.cacheRequestBodyAndRequest(exchange,
                            (serverHttpRequest) -> ServerRequest
                                    .create(exchange.mutate().request(serverHttpRequest)
                                            .build(), messageReaders)
                                    .bodyToMono(inClass)
                                    .doOnNext(objectValue -> exchange.getAttributes().put(
                                            CACHE_REQUEST_BODY_OBJECT_KEY,
                                            //去除多余符号
                                            objectValue.toString().replaceAll("[\n\t\r'']", "")))
                                    .map(objectValue -> config.getPredicate()
                                            .test(objectValue))
                                    //总是返回true
                                    .thenReturn(true));
                }
            }

            @Override
            public String toString() {
                return String.format("ReadBody: %s", config.getInClass());
            }
        };
    }

    @Override
    @SuppressWarnings("unchecked")
    public Predicate<ServerWebExchange> apply(MyReadBodyPredicateFactory.Config config) {
        throw new UnsupportedOperationException(
                "GwReadBodyPredicateFactory is only async.");
    }



    public static class Config {

        private Class inClass;

        private Predicate predicate;

        private Map<String, Object> hints;

        public Class getInClass() {
            return inClass;
        }

        public MyReadBodyPredicateFactory.Config setInClass(Class inClass) {
            this.inClass = inClass;
            return this;
        }

        public Predicate getPredicate() {
            return predicate;
        }

        public MyReadBodyPredicateFactory.Config setPredicate(Predicate predicate) {
            this.predicate = predicate;
            return this;
        }

        public <T> MyReadBodyPredicateFactory.Config setPredicate(Class<T> inClass, Predicate<T> predicate) {
            setInClass(inClass);
            this.predicate = predicate;
            return this;
        }

        public Map<String, Object> getHints() {
            return hints;
        }

        public MyReadBodyPredicateFactory.Config setHints(Map<String, Object> hints) {
            this.hints = hints;
            return this;
        }

    }

}
