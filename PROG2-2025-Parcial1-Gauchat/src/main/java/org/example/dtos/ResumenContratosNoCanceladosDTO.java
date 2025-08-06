package org.example.dtos;

import java.math.BigDecimal;

public class ResumenContratosNoCanceladosDTO {
    private BigDecimal montoTotalEsperado;
    private BigDecimal montoPagado;

    public ResumenContratosNoCanceladosDTO(BigDecimal montoTotalEsperado, BigDecimal montoPagado) {
        this.montoTotalEsperado = montoTotalEsperado;
        this.montoPagado = montoPagado;
    }

    public BigDecimal getMontoTotalEsperado() {
        return montoTotalEsperado;
    }

    public void setMontoTotalEsperado(BigDecimal montoTotalEsperado) {
        this.montoTotalEsperado = montoTotalEsperado;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }
}
