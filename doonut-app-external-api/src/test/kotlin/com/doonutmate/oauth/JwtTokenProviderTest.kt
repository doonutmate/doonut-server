package com.doonutmate.oauth

import com.doonutmate.exception.InvalidTokenException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class JwtTokenProviderTest : BehaviorSpec({

    val secretKey = "dasdc338hfhghsn21sdf1jvnu4ascasv21908fyhas2a"
    val validityInMilliseconds = 3600000L

    Given("payload가 주어지면") {
        val payload = "dummy_payload"
        val jwtTokenProvider = JwtTokenProvider(secretKey, validityInMilliseconds)
        When("JwtTokenProvider는") {
            val token = jwtTokenProvider.createToken(payload)
            Then("토큰을 생성한다.") {
                token shouldNotBe null
            }
        }
    }

    Given("토큰이 주어지면") {
        val expected = "dummy_payload"
        val jwtTokenProvider = JwtTokenProvider(secretKey, validityInMilliseconds)
        val token = jwtTokenProvider.createToken(expected)
        When("JwtTokenProvider는") {
            val actual = jwtTokenProvider.getPayload(token)
            Then("토큰 페이로드를 반환한다.") {
                actual shouldBe expected
            }
        }
    }

    Given("기간이 만료된 토큰이 주어지면") {
        val expected = "dummy_payload"
        val jwtTokenProvider = JwtTokenProvider(secretKey, 0)
        val token = jwtTokenProvider.createToken(expected)
        When("JwtTokenProvider는") {
            Then("에러가 발생한다.") {
                shouldThrow<InvalidTokenException> {
                    jwtTokenProvider.getPayload(token)
                }
            }
        }
    }
})
