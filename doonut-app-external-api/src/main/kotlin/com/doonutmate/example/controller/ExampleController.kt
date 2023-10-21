package com.doonutmate.example.controller

import com.doonutmate.example.controller.dto.ExampleCreateReq
import com.doonutmate.example.service.ExampleAppService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/example")
class ExampleController(
    @Autowired var exampleAppservice: ExampleAppService,
) {

    @PostMapping
    fun create(@RequestBody req: ExampleCreateReq) {
        exampleAppservice.create(req)
    }
}