package com.doonutmate.member.service

import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.image.controller.dto.ImageUploadResponse
import com.doonutmate.image.service.ImageAppService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional(readOnly = true)
@Service
class MemberProfileFacadeService(
    private val imageAppService: ImageAppService,
    private val memberBusinessService: MemberBusinessService,
) {

    @Transactional
    fun updateProfile(memberId: Long, name: String, multipartFile: MultipartFile): Long {
        val imageDto: ImageUploadResponse = imageAppService.saveProfileImage(multipartFile, memberId)

        return memberBusinessService.updateProfile(memberId, name, imageDto.imageUrl)
    }
}
