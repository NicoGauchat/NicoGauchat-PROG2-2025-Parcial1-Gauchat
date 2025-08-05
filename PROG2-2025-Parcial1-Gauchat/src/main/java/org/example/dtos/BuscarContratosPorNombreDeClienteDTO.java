package org.example.dtos;

import org.example.enums.TipoServicio;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BuscarContratosPorNombreDeClienteDTO {
    private String nombreCliente;
    private TipoServicio tipoServicio;
    private LocalDate fechaInicioDesde;
    private LocalDate FechaInicioHasta;
    private BigDecimal tarifaMensualDesde;
    private BigDecimal TarifaMensualHasta;

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public LocalDate getFechaInicioDesde() {
        return fechaInicioDesde;
    }

    public void setFechaInicioDesde(LocalDate fechaInicioDesde) {
        this.fechaInicioDesde = fechaInicioDesde;
    }

    public LocalDate getFechaInicioHasta() {
        return FechaInicioHasta;
    }

    public void setFechaInicioHasta(LocalDate fechaInicioHasta) {
        FechaInicioHasta = fechaInicioHasta;
    }

    public BigDecimal getTarifaMensualDesde() {
        return tarifaMensualDesde;
    }

    public void setTarifaMensualDesde(BigDecimal tarifaMensualDesde) {
        this.tarifaMensualDesde = tarifaMensualDesde;
    }

    public BigDecimal getTarifaMensualHasta() {
        return TarifaMensualHasta;
    }

    public void setTarifaMensualHasta(BigDecimal tarifaMensualHasta) {
        TarifaMensualHasta = tarifaMensualHasta;
    }
}
