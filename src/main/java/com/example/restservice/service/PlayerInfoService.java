package com.example.restservice.service;

import com.example.restservice.model.PlayerInfo;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class PlayerInfoService {
    /**
     * We are using a hardcoded list of PlayerInfo objects to simulate a database. In a real-world application, you would
     * use a database to store and retrieve PlayerInfo objects.
     * 
     * For simplicity sake, this API assumes that each player can have only one phone of a given type (mobile, home, etc.).
     * In a real-world application, you would need to modify the API to handle multiple phones of the same type for a player.
     */
    private List<PlayerInfo> playerInfoList = new ArrayList<>(Arrays.asList(

            // Player 1234 has two phones (mobile and home)
            new PlayerInfo(1234L, 5678L, "mobile", "1234567890", "active", LocalDateTime.now()),
            new PlayerInfo(1234L, 6789L, "home", "0987654321", "active", LocalDateTime.now()),
            
            // Player 5678 has one phone (mobile)
            new PlayerInfo(5678L, 1234L, "mobile", "3334449999", "active", LocalDateTime.now())
    ));

    /**
     * Create a new phone for a player.
     * 
     * @param playerId The ID of the player.
     * @param playerInfo The PlayerInfo object representing the new phone.
     * @return The created PlayerInfo object.
     */
    public PlayerInfo createPhoneForPlayer(Long playerId, PlayerInfo playerInfo) {
        PlayerInfo player = getPlayerById(playerId);
        if (player == null) {
            // Create a new player with the given playerId
            player = new PlayerInfo(playerId, playerInfo.getPhoneId(), playerInfo.getPhoneType(), playerInfo.getNumber(), playerInfo.getStatus(), LocalDateTime.now());
            playerInfoList.add(player);
            return player;
        } else {
            boolean phoneExists = playerInfoList.stream()
                .anyMatch(phone -> phone.getPlayerId().equals(playerId) && phone.getPhoneId().equals(playerInfo.getPhoneId()));
            if (phoneExists) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone already exists for the given player");
            }

            playerInfo.setPlayerId(playerId);
            if(playerInfo.getUpdated() == null) {
                playerInfo.setUpdated(LocalDateTime.now());
            }
            playerInfoList.add(playerInfo);
            return playerInfo;
        }
    }

    /**
     * Get all phones for a player.
     * 
     * @param playerId The ID of the player.
     * @return A list of PlayerInfo objects representing all the phones of the player.
     */
    public List<PlayerInfo> getAllPhonesForPlayer(Long playerId) {
        PlayerInfo player = getPlayerById(playerId);
        if (player != null) {
            return playerInfoList.stream()
                .filter(phone -> phone.getPlayerId().equals(playerId))
                .toList();
        }
        return new ArrayList<>();
    }

    /**
     * Get a phone of a specific type for a player.
     * 
     * @param playerId The ID of the player.
     * @param phoneId The ID of the phone.
     * @return The PlayerInfo object representing the phone, or null if not found.
     */
    public PlayerInfo getPhoneForPlayer(Long playerId, Long phoneId) {
        PlayerInfo player = getPlayerById(playerId);
        if (player != null) {
            return playerInfoList.stream()
                .filter(phone -> phone.getPlayerId().equals(playerId) && phone.getPhoneId().equals(phoneId))
                .findFirst()
                .orElse(null);
        }
        return null;
    }

    /**
     * Update a phone for a player.
     * 
     * @param playerId The ID of the player.
     * @param phoneId The ID of the phone.
     * @param updatedPhone The updated PlayerInfo object representing the phone.
     * @return The updated PlayerInfo object, or null if not found.
     */
    public PlayerInfo updatePhoneForPlayer(Long playerId, Long phoneId, PlayerInfo updatedPhone) {
        PlayerInfo player = getPlayerById(playerId);
        if (player != null) {
            playerInfoList.stream()
                .filter(phone -> phone.getPlayerId().equals(playerId) && phone.getPhoneId().equals(phoneId))
                .findFirst()
                .ifPresent(phone -> {
                    phone.setNumber(updatedPhone.getNumber());
                    phone.setPhoneType(updatedPhone.getPhoneType());
                    phone.setStatus(updatedPhone.getStatus());
                    phone.setUpdated(LocalDateTime.now());
                });
            return getPhoneForPlayer(playerId, phoneId);
        }
        return null;
    }

    /**
     * Delete a phone for a player.
     * 
     * @param playerId The ID of the player.
     * @param phoneId The ID of the phone.
     * @return true if the phone was successfully deleted, false otherwise.
     */
    public boolean deletePhoneForPlayer(Long playerId, Long phoneId) {
        PlayerInfo player = getPlayerById(playerId);
        if (player != null) {
            boolean phoneDeleted = playerInfoList.removeIf(phone -> phone.getPlayerId().equals(playerId) && phone.getPhoneId().equals(phoneId));
            return phoneDeleted;
        }
        return false;
    }

    // Helper method to get a player by ID
    private PlayerInfo getPlayerById(Long playerId) {
        for (PlayerInfo player : playerInfoList) {
            if (player.getPlayerId().equals(playerId)) {
                return player;
            }
        }
        return null;
    }

    // Helper method to generate a unique phone ID
    // (for simplicity, we are maxing out at 100000 phone IDs)
    private Long generatePhoneId() {
        return (long) (Math.random() * 100000);
    }
}