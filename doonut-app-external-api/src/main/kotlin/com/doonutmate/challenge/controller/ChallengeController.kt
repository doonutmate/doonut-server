package com.doonutmate.challenge.controller

import com.doonutmate.challenge.controller.dto.ChallengeListRequest
import com.doonutmate.challenge.controller.dto.ChallengeListResponse
import com.doonutmate.challenge.service.ChallengeService
import com.doonutmate.doonut.challenge.model.ChallengeType
import com.doonutmate.image.controller.dto.ImageUploadResponse
import com.doonutmate.oauth.configuration.Authorization
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@Tag(name = "Challenge", description = "챌린지 API")
@RequestMapping("/challenge")
class ChallengeController(
    private val service: ChallengeService,
) {

    @Operation(summary = "특정 년도 달에 해당하는 챌린지 목록 조회", description = "특정 연월을 입력으로 받으면 챌린지 목록을 반환한다.")
    @GetMapping
    fun getList(
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,

        @Valid @ModelAttribute
        req: ChallengeListRequest,
    ): List<ChallengeListResponse> {
        return service.getChallengeList(memberId, req)
    }

    @Operation(summary = "리사이징된 사진을 s3와 db에 저장", description = "")
    @PostMapping(
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun saveResizingChallengeImage(
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,

        @RequestPart("multipartFile") multipartFile: MultipartFile,
        @RequestParam type: ChallengeType,
    ): ImageUploadResponse {
        return service.saveResizingImage(multipartFile, type, memberId)
    }
}
