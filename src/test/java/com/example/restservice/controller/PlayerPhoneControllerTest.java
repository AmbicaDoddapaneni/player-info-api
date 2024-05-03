package com.example.restservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.restservice.model.PlayerInfo;
import com.example.restservice.service.PlayerInfoService;

@ExtendWith(MockitoExtension.class)
public class PlayerPhoneControllerTest {

    @Mock
    private PlayerInfoService playerInfoService;

    @InjectMocks
    private PlayerPhoneController playerPhoneController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPhone() {
        Long playerId = 1L;
        PlayerInfo phoneInput = new PlayerInfo();
        PlayerInfo phone = new PlayerInfo();
        when(playerInfoService.createPhoneForPlayer(playerId, phoneInput)).thenReturn(phone);

        ResponseEntity<PlayerInfo> response = playerPhoneController.addPhone(playerId, phoneInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(phone, response.getBody());
        verify(playerInfoService, times(1)).createPhoneForPlayer(playerId, phoneInput);
    }

    @Test
    public void testGetAllPhones() {
        Long playerId = 1L;
        List<PlayerInfo> phones = new ArrayList<>();
        when(playerInfoService.getAllPhonesForPlayer(playerId)).thenReturn(phones);

        ResponseEntity<List<PlayerInfo>> response = playerPhoneController.getAllPhones(playerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(phones, response.getBody());
        verify(playerInfoService, times(1)).getAllPhonesForPlayer(playerId);
    }

    @Test
    public void testGetPhoneById() {
        Long playerId = 1L;
        Long phoneId = 2L;
        PlayerInfo phone = new PlayerInfo();
        when(playerInfoService.getPhoneForPlayer(playerId, phoneId)).thenReturn(phone);

        ResponseEntity<PlayerInfo> response = playerPhoneController.getPhoneById(playerId, phoneId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(phone, response.getBody());
        verify(playerInfoService, times(1)).getPhoneForPlayer(playerId, phoneId);
    }

    @Test
    public void testGetPhoneById_NotFound() {
        Long playerId = 1L;
        Long phoneId = 2L;
        when(playerInfoService.getPhoneForPlayer(playerId, phoneId)).thenReturn(null);

        ResponseEntity<PlayerInfo> response = playerPhoneController.getPhoneById(playerId, phoneId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(playerInfoService, times(1)).getPhoneForPlayer(playerId, phoneId);
    }

    @Test
    public void testUpdatePhone() {
        Long playerId = 1L;
        Long phoneId = 2L;
        PlayerInfo updatedPhone = new PlayerInfo();
        PlayerInfo phone = new PlayerInfo();
        when(playerInfoService.updatePhoneForPlayer(playerId, phoneId, updatedPhone)).thenReturn(phone);

        ResponseEntity<PlayerInfo> response = playerPhoneController.updatePhone(playerId, phoneId, updatedPhone);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(phone, response.getBody());
        verify(playerInfoService, times(1)).updatePhoneForPlayer(playerId, phoneId, updatedPhone);
    }

    @Test
    public void testUpdatePhone_NotFound() {
        Long playerId = 1L;
        Long phoneId = 2L;
        PlayerInfo updatedPhone = new PlayerInfo();
        when(playerInfoService.updatePhoneForPlayer(playerId, phoneId, updatedPhone)).thenReturn(null);

        ResponseEntity<PlayerInfo> response = playerPhoneController.updatePhone(playerId, phoneId, updatedPhone);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(playerInfoService, times(1)).updatePhoneForPlayer(playerId, phoneId, updatedPhone);
    }

    @Test
    public void testDeletePhone() {
        Long playerId = 1L;
        Long phoneId = 2L;
        when(playerInfoService.deletePhoneForPlayer(playerId, phoneId)).thenReturn(true);

        ResponseEntity<Void> response = playerPhoneController.deletePhone(playerId, phoneId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(playerInfoService, times(1)).deletePhoneForPlayer(playerId, phoneId);
    }

    @Test
    public void testDeletePhone_NotFound() {
        Long playerId = 1L;
        Long phoneId = 2L;
        when(playerInfoService.deletePhoneForPlayer(playerId, phoneId)).thenReturn(false);

        ResponseEntity<Void> response = playerPhoneController.deletePhone(playerId, phoneId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(playerInfoService, times(1)).deletePhoneForPlayer(playerId, phoneId);
    }

    @Test
    public void testGetAllPhones_Error() {
        Long playerId = 1L;
        when(playerInfoService.getAllPhonesForPlayer(playerId)).thenThrow(new RuntimeException());

        ResponseEntity<List<PlayerInfo>> response = playerPhoneController.getAllPhones(playerId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(playerInfoService, times(1)).getAllPhonesForPlayer(playerId);
    }

    @Test
    public void testGetPhoneById_Error() {
        Long playerId = 1L;
        Long phoneId = 2L;
        when(playerInfoService.getPhoneForPlayer(playerId, phoneId)).thenThrow(new RuntimeException());

        ResponseEntity<PlayerInfo> response = playerPhoneController.getPhoneById(playerId, phoneId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(playerInfoService, times(1)).getPhoneForPlayer(playerId, phoneId);
    }

    @Test
    public void testUpdatePhone_Error() {
        Long playerId = 1L;
        Long phoneId = 2L;
        PlayerInfo updatedPhone = new PlayerInfo();
        when(playerInfoService.updatePhoneForPlayer(playerId, phoneId, updatedPhone)).thenThrow(new RuntimeException());

        ResponseEntity<PlayerInfo> response = playerPhoneController.updatePhone(playerId, phoneId, updatedPhone);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(playerInfoService, times(1)).updatePhoneForPlayer(playerId, phoneId, updatedPhone);
    }

    @Test
    public void testDeletePhone_Error() {
        Long playerId = 1L;
        Long phoneId = 2L;
        when(playerInfoService.deletePhoneForPlayer(playerId, phoneId)).thenThrow(new RuntimeException());

        ResponseEntity<Void> response = playerPhoneController.deletePhone(playerId, phoneId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(playerInfoService, times(1)).deletePhoneForPlayer(playerId, phoneId);
    }
}