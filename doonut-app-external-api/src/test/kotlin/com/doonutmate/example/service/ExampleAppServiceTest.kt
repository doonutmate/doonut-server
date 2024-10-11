package com.doonutmate.example.service

import com.doonutmate.example.controller.dto.ExampleCreateReq
import com.doonutmate.example.model.Example
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ExampleAppServiceTest : BehaviorSpec({

    val exampleBusinessService = mockk<ExampleBusinessService>()
    val exampleAppService = ExampleAppService(exampleBusinessService)

    Given("이름이 주어지는 경우") {
        val req = ExampleCreateReq("test")
        every { exampleBusinessService.create(Example(null, req.name)) } returns 1L

        When("해당 이름으로 Example을 생성하면") {
            exampleAppService.create(req)

            Then("Example이 생성된다.") {
                verify(exactly = 1) { exampleBusinessService.create(any()) }
            }
        }
    }

    Given("ID가 주어지는 경우") {
        val id = 1L
        val expected = Example(id, "test")
        every { exampleBusinessService.get(id) } returns expected

        When("해당 ID로 Example을 조회하면") {
            val actual = exampleAppService.get(id)

            Then("Example을 확인할 수 있다") {
                actual shouldBe expected
            }
        }
    }
})

