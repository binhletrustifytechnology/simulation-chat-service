package com.example.chatservice.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Privilege {
    private UUID id;
    private String privilegeName;

}