package com.doonutmate.challenge.controller

import com.doonutmate.challenge.controller.dto.ChallengeListRequest
import com.doonutmate.challenge.controller.dto.ChallengeListResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Challenge", description = "챌린지 API")
@RequestMapping("/challenge-mock")
class ChallengeMockController(
) {

    @Operation(summary = "특정 달에 해당하는 챌린지 목록 조회", description = "특정 연월을 입력으로 받으면 챌린지 목록을 반환한다.")
    @GetMapping
    fun getList(
        @Valid @ModelAttribute
        req: ChallengeListRequest,
    ): List<ChallengeListResponse> {
        return listOf(
            ChallengeListResponse(
                1,
                "https://fastly.picsum.photos/id/1074/46/46.jpg?hmac=POzNu16Rmops4n2cQcxfszwEAOcWv_P3ihbJbu_3sYU",
            ),
            ChallengeListResponse(
                2,
                "https://fastly.picsum.photos/id/955/46/46.jpg?hmac=VbG2wdEXhK0C1aNJV5rhL3ybV1iYxS3C2waNpmyvcxY",
            ),
            ChallengeListResponse(
                3,
                "https://fastly.picsum.photos/id/490/46/46.jpg?hmac=BqA1aZoipCc8Hk6Rrjn4kduAjCdpvse0LryS2XsZm0w",
            ),
            ChallengeListResponse(
                4,
                "https://fastly.picsum.photos/id/687/46/46.jpg?hmac=XRlqlaYOHBE-nWHdkV22Dq77XL706qFz7tLpdA30fso",
            ),
            ChallengeListResponse(
                5,
                "https://fastly.picsum.photos/id/292/46/46.jpg?hmac=eNcvgzsDa-rdoGKVNWIwL9gdcKqkdcpo6Akp2Y04s5Y",
            ),
            ChallengeListResponse(
                6,
                "https://fastly.picsum.photos/id/888/46/46.jpg?hmac=N9EGqNE2RnjztzoLp-IfJCqFprXdN-8Ji1K4Knz7byM",
            ),
            ChallengeListResponse(
                7,
                "https://fastly.picsum.photos/id/927/46/46.jpg?hmac=rOgBFvh_urJE9KvB6VJ56WiQk1VaJeuuLG2JjhY4idc",
            ),
            ChallengeListResponse(
                8,
                "https://fastly.picsum.photos/id/419/46/46.jpg?hmac=0WHe-HlRewOt6LYYVbnFLDGg1TJCcHNbPUF40_NWLRM",
            ),
            ChallengeListResponse(
                9,
                "https://fastly.picsum.photos/id/758/46/46.jpg?hmac=u8_5sxxUEQ0r5e0dbrkBmG3YlBmazcQz-KmnLutC1aQ",
            ),
            ChallengeListResponse(
                10,
                "https://fastly.picsum.photos/id/586/46/46.jpg?hmac=wvlBxTn_IIftiuRhRKjBmey5XPIKnwvL7Scojj6iw_s",
            ),
            ChallengeListResponse(
                11,
                "https://fastly.picsum.photos/id/1018/46/46.jpg?hmac=ClD0u5gEoUan0w8J2Qbpby-DIao2tDA2PIFQGnjVc0U",
            ),
            ChallengeListResponse(
                12,
                "https://fastly.picsum.photos/id/378/46/46.jpg?hmac=7RMdbZK41RUp5yywGZFTzrAwF9Sa2p1W3Ma7W6j-8HI",
            ),
            ChallengeListResponse(
                13,
                "https://fastly.picsum.photos/id/922/46/46.jpg?hmac=fyG7zlLpahIr6q6O0OPUyODjYyzQ0lN3tvDcA2ifdb4",
            ),
            ChallengeListResponse(
                14,
                "https://fastly.picsum.photos/id/264/46/46.jpg?hmac=gihVqG8x8_rfHie0P3BWnNoURjaptRISwYeje0YjV18",
            ),
            ChallengeListResponse(
                15,
                "https://fastly.picsum.photos/id/616/46/46.jpg?hmac=Oa1K4SgedEu1-bYoZcLkAn84chEkrb3s8p4VBXSnyxA",
            ),
            ChallengeListResponse(
                16,
                "https://fastly.picsum.photos/id/284/46/46.jpg?hmac=Zbkks_E3Qpc3cSnqGbcwu7bSrqUuV8N10Y73ZdW7-rA",
            ),
            ChallengeListResponse(
                17,
                "https://fastly.picsum.photos/id/65/46/46.jpg?hmac=e7XkzL-j-g15QLoWKAChxyRJZgiTRHaD0RtVVEl9zQc",
            ),
            ChallengeListResponse(
                18,
                "https://fastly.picsum.photos/id/702/46/46.jpg?hmac=m5VQXIXOfMezf6Rqu4PowGqNjH1N1v3Y5PvGWmVZPx0",
            ),
            ChallengeListResponse(
                19,
                "https://fastly.picsum.photos/id/334/46/46.jpg?hmac=q8e98w1rgmfk4tPm5qjQDZXgfEhzSFmJLNZlylXkPlI",
            ),
            ChallengeListResponse(
                20,
                "https://fastly.picsum.photos/id/0/46/46.jpg?hmac=G8ia9BJnOProeDIPL_bOdDLgxs0E3Wmk7NnFR_7JG0s",
            ),
            ChallengeListResponse(
                21,
                "https://fastly.picsum.photos/id/900/46/46.jpg?hmac=6Il4e8enFovBFmp1_5SWKAPBJzoadmkIYx20LACAje0",
            ),
            ChallengeListResponse(
                22,
                "https://fastly.picsum.photos/id/100/46/46.jpg?hmac=P58nzmrzpSb837Ynyl8Wz7MjB8WvGhLIlW9sXdZ1bIw",
            ),
            ChallengeListResponse(
                23,
                "https://fastly.picsum.photos/id/910/46/46.jpg?hmac=neV-xTQb5WL4eD993bN4nPFgKjMEpHVYW_-2_frQi4U",
            ),
            ChallengeListResponse(
                24,
                "https://fastly.picsum.photos/id/622/46/46.jpg?hmac=pwsV3BQxai-KtTzHO4u2D7hMfSmFOmFcg6ZyHIB0DEk",
            ),
            ChallengeListResponse(
                25,
                "https://fastly.picsum.photos/id/945/46/46.jpg?hmac=TG3DJx6BQyjdXRPYljffe10Tvv6_U8sWP5pj9O7_OeQ",
            ),
            ChallengeListResponse(
                26,
                "https://fastly.picsum.photos/id/7/46/46.jpg?hmac=wk4-OnILh5Qyjp1pLpxh96fQ188XEFMG9zgvQSySx6Q",
            ),
            ChallengeListResponse(
                27,
                "https://fastly.picsum.photos/id/721/46/46.jpg?hmac=l3p63zSvhCHj_K0kXsJ6YOAY0CDxKtEXwSq_GN5e2zI",
            ),
            ChallengeListResponse(
                28,
                "https://fastly.picsum.photos/id/604/46/46.jpg?hmac=EGqt6j0fJOuNkE9Y3fMyreQSvBh62zCxjHL9UoB7mM4",
            ),
            ChallengeListResponse(
                29,
                "https://fastly.picsum.photos/id/340/46/46.jpg?hmac=p63_o3vFpTb4aUa7aZGWosFs8mHiy0koKzrsJmKJPf4",
            ),
            ChallengeListResponse(
                30,
                "https://fastly.picsum.photos/id/574/46/46.jpg?hmac=dqpq1aIihcDuYLPqQR6y_EB2kBZI0_kBXqU2bsD_Ezo",
            ),
            ChallengeListResponse(
                31,
                "https://fastly.picsum.photos/id/302/46/46.jpg?hmac=RQuuucyDpkolEm2oOc8h_5DhDJZXz6VuEWKbuZeikKA",
            ),
        )
    }
}
