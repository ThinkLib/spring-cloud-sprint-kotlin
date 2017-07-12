package de.maeddes.frontendservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class FrontendServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(FrontendServiceApplication::class.java, *args)
}
