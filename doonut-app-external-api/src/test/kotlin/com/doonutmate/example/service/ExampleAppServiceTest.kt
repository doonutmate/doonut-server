package com.doonutmate.example.service

import com.doonutmate.example.controller.dto.ExampleCreateReq
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ExampleAppServiceTest : BehaviorSpec(
    {

        val exampleAppService: ExampleAppService = mockk()

        Given("이름을 담는 dto를 입력받는다면") {
            val exampleCreateReq = ExampleCreateReq("test")

            When("create 함수를 호출하면") {
                every { exampleAppService.create(exampleCreateReq) } returns Unit
                val createExample = exampleAppService.create(exampleCreateReq)

                Then("create함수는 Unit 을 반환해야한다.") {
                    createExample shouldBe Unit
                }

                And("create 함수가 정확히 한 번 호출되어야 합니다") {
                    verify(exactly = 1) { exampleAppService.create(exampleCreateReq) }
                }
            }
        }
    },
)
