# xtm-base-frame
Spring Cloud &amp; Spring Cloud Alibaba 基础框架，内置了 Cat 监控,公司落地 Spring Cloud 架构参考

组件列表
xtm-spring-cloud-starter-cat：Cat 监控组件。

xtm-spring-cloud-starter-web：spring-boot-starter-web的封装，会对请求的Url进行Cat埋点，会对一些通用信息进行接收透传，会对RestTemplate的调用进行Cat埋点。

xtm-spring-cloud-starter-dubbo：Dubbo组件，调用监控，Cat监控信息传递。


xtm-spring-cloud-starter-feign：Feign组件, 内置Cat调用监控，Cat监控信息传递。


xtm-spring-cloud-starter-id：ID发射器客户端，基于美团Leaf。


xtm-spring-cloud-starter-lock：分布式锁组件（内置幂等注解），基于Redisson的Redis锁和Mysql的锁。


xtm-spring-cloud-starter-mybatis：基于Mybatis-Plus，内置Cat调用监控。

xtm-spring-cloud-starter-nacos：Nacos组件，内置Cat调用监控。

xtm-spring-cloud-starter-redis：Redis客户端，内置Cat调用监控。

xtm-spring-cloud-starter-sentinel：熔断限流。




