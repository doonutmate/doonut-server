package com.doonutmate.example.controller

import com.doonutmate.example.controller.dto.ExampleCreateReq
import com.doonutmate.example.model.Example
import com.doonutmate.example.service.ExampleAppService
import com.doonutmate.oauth.JwtTokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@WebMvcTest(ExampleController::class)
class ExampleControllerTest {

    @MockkBean
    lateinit var exampleService: ExampleAppService

    @MockkBean
    lateinit var jwtTokenProvider: JwtTokenProvider

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `Example을 생성한다`() {
        val req = ExampleCreateReq("test")

        every { exampleService.create(any()) } returns Unit

        mockMvc.post("/example") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(req)
        }.andExpect {
            status { isOk() }
        }
    }

    @Test
    fun `Example을 조회한다`() {
        val res = Example(1L, "test")

        every { exampleService.get(any()) } returns res

        mockMvc.get("/example/1")
            .andExpect {
                status { isOk() }
                jsonPath("$.id") { value(res.id) }
                jsonPath("$.name") { value(res.name) }
            }
    }
}
