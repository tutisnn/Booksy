package com.example.kutuphane.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder



public class GenericResponse<T> {
    private T data;
    private String message;
    public static <T> GenericResponse<T> error(String message){
        return GenericResponse.<T>builder()
                .message(message)
                .build();


    }
    public static <T> GenericResponse<T> success(T data){
        return GenericResponse.<T>builder()
                .message("İşlem Başarılı")
                .data(data
                ).build();

    }
}
