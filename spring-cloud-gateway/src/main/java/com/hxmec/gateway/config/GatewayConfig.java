package com.hxmec.gateway.config;

/**
 * 功能描述: 
 * @author  Trazen
 * @date  2020/7/1 20:14
 */
//@Configuration
//public class GatewayConfig {
//
//    @Bean
//    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes ()
//                .route (r -> r.path ("/provider/**")
//                        .filters (f -> f.stripPrefix (1))
//                        .uri ("lb://eureka-client-provider")
//                        .id ("eureka-client-provider")
//                )
//                .route (r -> r.path ("/consumer/**")
//                        .filters (f -> f.stripPrefix (1))
//                        .uri ("lb://eureka-client-consumer")
//                        .id ("eureka-client-consumer")
//                )
//                .build ();
//    }
//}
