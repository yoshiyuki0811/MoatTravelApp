package com.example.moattravel.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name="verification_tokens")
@Data
public class VerificationToken {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;    

    private String token; // @Columnは省略してシンプルに

    // データベース側で自動設定（DEFAULT CURRENT_TIMESTAMP）するため、Java側からは挿入・更新しない設定
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt; // TimestampからLocalDateTimeに変更

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

}
