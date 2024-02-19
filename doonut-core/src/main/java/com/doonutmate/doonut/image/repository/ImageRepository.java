package com.doonutmate.doonut.image.repository;

import com.doonutmate.doonut.image.entity.ImageEntity;
import com.doonutmate.doonut.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    @Query("""
                SELECT image
                FROM ImageEntity image
                WHERE image.deleted = false 
                AND image.memberId = :memberId
                AND FUNCTION('YEAR', image.createdAt) = :year
                AND FUNCTION('MONTH', image.createdAt) = :month
            """)
    Optional<List<ImageEntity>> findAllByMemberIdAndDate(
            @Param("memberId") String memberId,
            @Param("year") int year,
            @Param("month") int month
    );
}
