package com.doonutmate.image.controller

import com.doonutmate.image.controller.dto.ImageUploadResponse
import com.doonutmate.image.service.ImageAppService
import com.doonutmate.oauth.configuration.Authorization
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Tag(name = "이미지 업로드")
@RestController
@RequestMapping
class ImageController(
    private val imageAppService: ImageAppService,
) {
    @Operation(summary = "이미지 업로드", description = "이미지를 업로드하면 url을 반환한다.")
    @PostMapping(
        value = ["/image-upload"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun imageUpload(
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,

        @RequestPart("multipartFile") multipartFile: MultipartFile,
    ): ImageUploadResponse {
        return imageAppService.saveFile(multipartFile, memberId)
    }
}
