package com.calibri.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "MESSAGES")
@Data
public class GptMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_generator")
    @SequenceGenerator(name = "message_generator", sequenceName = "messages_seq", allocationSize = 1)
    private Long id;

    @Column(name = "telegram_id")
    private Long telegramId;
    @Column(name = "content")
    private String content;
    @Column(name = "role")
    private String role;
    @Column(name = "finishReason")
    private String finishReason;
    @Column(name = "index")
    private Integer index;
}
