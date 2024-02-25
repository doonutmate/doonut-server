package com.doonutmate.doonut.image.repository;

import com.doonutmate.doonut.image.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
