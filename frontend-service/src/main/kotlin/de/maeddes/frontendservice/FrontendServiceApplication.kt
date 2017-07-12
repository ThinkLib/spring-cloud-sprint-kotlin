package de.maeddes.frontendservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableHystrixDashboard
@EnableCircuitBreaker
class FrontendServiceApplication : WebMvcConfigurerAdapter() {

    @Bean
    fun defaultSampler(): AlwaysSampler {
        return AlwaysSampler()
    }



}

fun main(args: Array<String>) {
    SpringApplication.run(FrontendServiceApplication::class.java, *args)
}
