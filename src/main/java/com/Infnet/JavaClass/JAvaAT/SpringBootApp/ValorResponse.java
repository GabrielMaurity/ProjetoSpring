package com.Infnet.JavaClass.JAvaAT.SpringBootApp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValorResponse {
    private int TipoVeiculo;
    private String Valor;
    private String Marca;
    private String Modelo;
    private int AnoModelo;
    private String Combustivel;
    private String CodigoFipe;
    private String MesReferencia;
    private String SiglaCombustivel;
}