package org.example.dtos;

import java.time.LocalDate;

public class RangoDeFechasDTO {
    private LocalDate fechaInicioDesde;
    private LocalDate FechaInicioHasta;

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
}
