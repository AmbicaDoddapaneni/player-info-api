package com.example.restservice.controller;

import com.example.restservice.model.PlayerInfo;
import com.example.restservice.service.PlayerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerPhoneController {

    @Autowired
    private PlayerInfoService playerInfoService;

    /**
     * Adds a phone for a player.
     *
     * @param playerId    The ID of the player.
     * @param phoneInput  The phone information to be added.
     * @return            The created phone information.
     */
    @PostMapping("/players/{playerId}/phones")
    public ResponseEntity<PlayerInfo> addPhone(@PathVariable Long playerId, @RequestBody PlayerInfo phoneInput) {
        PlayerInfo phone = playerInfoService.createPhoneForPlayer(playerId, phoneInput);
        return ResponseEntity.ok(phone);
    }

    /**
     * Retrieves all phones for a player.
     *
     * @param playerId    The ID of the player.
     * @return            The list of phone information for the player.
     */
    @GetMapping("/players/{playerId}/phones")
    public ResponseEntity<List<PlayerInfo>> getAllPhones(@PathVariable Long playerId) {
        try {
            List<PlayerInfo> phones = playerInfoService.getAllPhonesForPlayer(playerId);
            return ResponseEntity.ok(phones);
        } catch (Exception e) {
            System.out.println("Error getting phones" + e);
            return ResponseEntity.status(500).build(); // Return a 500 Internal Server Error response
        }
    }

    /**
     * Retrieves a phone by ID for a player.
     *
     * @param playerId    The ID of the player.
     * @param phoneId     The ID of the phone.
     * @return            The phone information.
     */
    @GetMapping("/players/{playerId}/phones/{phoneId}")
    public ResponseEntity<PlayerInfo> getPhoneById(@PathVariable Long playerId, @PathVariable Long phoneId) {
        try {
            PlayerInfo phone = playerInfoService.getPhoneForPlayer(playerId, phoneId);
            if (phone != null) {
                return ResponseEntity.ok(phone);
            } else {
                return ResponseEntity.notFound().build(); // Return a 404 Not Found response
            }
        } catch (Exception e) {
            System.out.println("Error getting phone" + e);
            return ResponseEntity.status(500).build(); // Return a 500 Internal Server Error response
        }
    }

    /**
     * Updates a phone for a player.
     *
     * @param playerId       The ID of the player.
     * @param phoneId        The ID of the phone.
     * @param updatedPhone   The updated phone information.
     * @return               The updated phone information.
     */
    @PutMapping("/players/{playerId}/phones/{phoneId}")
    public ResponseEntity<PlayerInfo> updatePhone(@PathVariable Long playerId, @PathVariable Long phoneId, @RequestBody PlayerInfo updatedPhone) {
        try {
            PlayerInfo phone = playerInfoService.updatePhoneForPlayer(playerId, phoneId, updatedPhone);
            if (phone != null) {
                return ResponseEntity.ok(phone);
            } else {
                return ResponseEntity.notFound().build(); // Return a 404 Not Found response
            }
        } catch (Exception e) {
            System.out.println("Error updating phone" + e);
            return ResponseEntity.status(500).build(); // Return a 500 Internal Server Error response
        }
    }

    /**
     * Deletes a phone for a player.
     *
     * @param playerId    The ID of the player.
     * @param phoneId     The ID of the phone.
     * @return            The response indicating the success or failure of the deletion.
     */
    @DeleteMapping("/players/{playerId}/phones/{phoneId}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long playerId, @PathVariable Long phoneId) {
        try {
            boolean deleted = playerInfoService.deletePhoneForPlayer(playerId, phoneId);
            if (deleted) {
                return ResponseEntity.noContent().build(); // Return a 204 No Content response
            } else {
                return ResponseEntity.notFound().build(); // Return a 404 Not Found response
            }
        } catch (Exception e) {
            System.out.println("Error deleting phone" + e);
            return ResponseEntity.status(500).build(); // Return a 500 Internal Server Error response
        }
    }
}

