package com.doonutmate.example.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LogService {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass.simpleName)
    fun log() {
        logger.trace("Trace")
        logger.debug("Debug")
        logger.info("Info")
        logger.warn("Warn")
        logger.error("Error")
    }
}
