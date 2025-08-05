package org.example.dtos;

import org.example.enums.TipoServicio;

import java.math.BigDecimal;

public class ResumenDTO {
    private TipoServicio tipoServicio;
    private Long cantidadDeContratos;
    private BigDecimal montoTotalCobrado;

    public ResumenDTO(TipoServicio tipoServicio, Long cantidadDeContratos, BigDecimal montoTotalCobrado) {
        this.tipoServicio = tipoServicio;
        this.cantidadDeContratos = cantidadDeContratos;
        this.montoTotalCobrado = montoTotalCobrado;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Long getCantidadDeContratos() {
        return cantidadDeContratos;
    }

    public void setCantidadDeContratos(Long cantidadDeContratos) {
        this.cantidadDeContratos = cantidadDeContratos;
    }

    public BigDecimal getMontoTotalCobrado() {
        return montoTotalCobrado;
    }

    public void setMontoTotalCobrado(BigDecimal montoTotalCobrado) {
        this.montoTotalCobrado = montoTotalCobrado;
    }
}
