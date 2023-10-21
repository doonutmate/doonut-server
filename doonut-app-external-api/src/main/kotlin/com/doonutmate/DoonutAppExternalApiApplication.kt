package com.doonutmate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DoonutAppExternalApiApplication

fun main(args: Array<String>) {
    runApplication<DoonutAppExternalApiApplication>(*args)
}
