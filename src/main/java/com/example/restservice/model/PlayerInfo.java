package com.example.restservice.model;

import java.time.LocalDateTime;

public class PlayerInfo {
    private Long playerId;
    private Long phoneId;
    private String phoneType;
    private String number;
    private String status;
    private LocalDateTime updated;

    public PlayerInfo(Long playerId, Long phoneId, String phoneType, String number, String status, LocalDateTime updated) {
        this.playerId = playerId;
        this.phoneId = phoneId;
        this.phoneType = phoneType;
        this.number = number;
        this.status = status;
        this.updated = updated;
    }

    public PlayerInfo() {

    }

    // Getters and setters
    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
