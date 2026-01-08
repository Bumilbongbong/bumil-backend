package com.example.bumil_backend.repository;

import com.example.bumil_backend.entity.ChatRoom;
import com.example.bumil_backend.enums.ChatTags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    // 모든 채팅 조회 + 태그 필터
    Page<ChatRoom> findAllByTagAndIsDeletedFalse(
            ChatTags chatTag,
            Pageable pageable
    );
    // 모든 채팅 조회
    Page<ChatRoom> findAllByIsDeletedFalse(Pageable pageable);

    @Query("SELECT c FROM ChatRoom c WHERE :tag IS NULL OR c.tag = :tag")
    List<ChatRoom> findByTag(@Param("tag") String tag, Sort sort);
}


