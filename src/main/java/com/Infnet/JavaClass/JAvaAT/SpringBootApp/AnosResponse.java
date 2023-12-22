package com.Infnet.JavaClass.JAvaAT.SpringBootApp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnosResponse {
    private List<Ano> anos;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Ano {
        private String codigo;
        private String nome;
    }
}
