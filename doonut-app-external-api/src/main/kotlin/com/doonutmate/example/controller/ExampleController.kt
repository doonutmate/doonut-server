package com.doonutmate.example.controller

import com.doonutmate.example.controller.dto.ExampleCreateReq
import com.doonutmate.example.service.ExampleAppService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "example", description = "테스트용 API")
@RequestMapping("/example")
class ExampleController(
    @Autowired var exampleAppservice: ExampleAppService,
) {
    @PostMapping
    @Operation(summary = "테스트용 API", description = "=====API 설명=====")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "성공", content = [(Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = ExampleCreateReq::class)))))]),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Not Found", content = [Content()]),
        ],
    )
    fun create(@RequestBody req: ExampleCreateReq) {
        exampleAppservice.create(req)
    }
}
