package com.doonutmate.example.controller

import com.doonutmate.example.controller.dto.ExampleCreateReq
import com.doonutmate.example.service.ExampleAppService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    @Operation(summary = "example 생성", description = "example을 생성하는 API입니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "성공", content = [(Content())]),
        ],
    )
    @PostMapping
    fun create(@RequestBody req: ExampleCreateReq) {
        exampleAppservice.create(req)
    }

    @Operation(summary = "example 조회", description = "example을 조회하는 API입니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "성공", content = [(Content())]),
        ],
    )
    @GetMapping("/{exampleId}")
    fun get(@PathVariable exampleId: Long) {
        return exampleAppservice.get(exampleId)
    }
}
