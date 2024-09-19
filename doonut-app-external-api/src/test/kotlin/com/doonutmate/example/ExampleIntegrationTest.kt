package com.doonutmate.example

import com.doonutmate.example.entity.ExampleEntity
import com.doonutmate.example.repository.ExampleRepository
import com.doonutmate.example.service.ExampleAppService
import com.doonutmate.support.IntegrationTest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.longs.shouldNotBeZero
import io.kotest.matchers.shouldBe

@IntegrationTest
class ExampleIntegrationTest(
    private val exampleAppService: ExampleAppService,
    private val exampleRepository: ExampleRepository,
) : BehaviorSpec({

    Given("Example이 이미 생성되어 있는 경우") {

        val saved = exampleRepository.save(ExampleEntity(null, "test"))

        When("ID로 조회하면") {
            val actual = exampleAppService.get(saved.id)

            Then("생성된 Example이 조회된다.") {
                actual.id.shouldNotBeZero()
                actual.name shouldBe "test"
            }
        }
    }
})
