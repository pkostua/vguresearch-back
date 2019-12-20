package com.vgu.research

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VGUApplication

fun main(args: Array<String>) {
    runApplication<VGUApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}