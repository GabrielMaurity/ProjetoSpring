package com.Infnet.JavaClass.JAvaAT.SpringBootApp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carro {
    private Long id;
    private String nome;
    private int numero;
    private String marca;

}