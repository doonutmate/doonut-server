package com.doonutmate.member.service

import com.doonutmate.image.controller.dto.ImageUploadResponse
import com.doonutmate.image.service.ImageAppService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional(readOnly = true)
@Service
class MemberProfileFacadeService(
    private val imageAppService: ImageAppService,
    private val memberAppService: MemberAppService,
) {

    @Transactional
    fun updateMyPage(memberId: Long, name: String, multipartFile: MultipartFile): Long {
        val imageDto: ImageUploadResponse = imageAppService.saveFile(multipartFile, memberId)
        return memberAppService.updateProfile(memberId, name, imageDto.imageUrl)
    }
}
