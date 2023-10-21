package com.doonutmate.example.service

import com.doonutmate.example.controller.dto.ExampleCreateReq
import com.doonutmate.example.model.Example
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class ExampleAppService(
    @Autowired var exampleBusinessService: ExampleBusinessService,
) {
    fun create(@RequestBody req: ExampleCreateReq) {
        exampleBusinessService.create(Example(null, req.name))
    }

    fun get(@RequestBody exampleId: Long) {
        exampleBusinessService.get(exampleId)
    }
}
