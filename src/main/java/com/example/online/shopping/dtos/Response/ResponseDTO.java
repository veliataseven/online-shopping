package com.example.online.shopping.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseDTO {

    public String status;
    public String message;

//    @Builder
//    public ResponseDTO(String status, String message) {
//        this.status = status;
//        this.message = message;
//    }
}
