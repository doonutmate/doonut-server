package com.doonutmate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
class DoonutAppExternalApiApplication

fun main(args: Array<String>) {
    runApplication<DoonutAppExternalApiApplication>(*args)
}
