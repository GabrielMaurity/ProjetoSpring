package com.Infnet.JavaClass.JAvaAT.SpringBootApp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelosResponse {
    private List<AnoModelo> anos;
    private List<Modelo> modelos;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnoModelo {
        private String codigo;
        private String nome;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modelo {
        private int codigo;
        private String nome;
    }
}
