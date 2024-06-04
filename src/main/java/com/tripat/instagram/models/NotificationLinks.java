package com.tripat.instagram.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tripat.instagram.utils.LinkType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_notification_links")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationLinks {
    @Id
    @GeneratedValue(generator = "gen")
    private Long id;
    private int startPointer;
    private int endPointer;
    @Enumerated(EnumType.STRING)
    private LinkType type;
    private String typeId;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    @JsonBackReference
    private Notification notification;
}
