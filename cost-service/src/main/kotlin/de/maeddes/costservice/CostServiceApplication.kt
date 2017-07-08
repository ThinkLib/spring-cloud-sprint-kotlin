package de.maeddes.costservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CostServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(CostServiceApplication::class.java, *args)
}
