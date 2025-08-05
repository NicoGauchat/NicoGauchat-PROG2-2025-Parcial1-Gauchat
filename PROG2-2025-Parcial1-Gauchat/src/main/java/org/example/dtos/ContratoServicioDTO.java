package org.example.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.entidades.ContratoServicio;
import org.example.enums.Estado;
import org.example.enums.TipoServicio;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContratoServicioDTO {
    private Long id;
    private String nombreCliente;
    private TipoServicio tipoServicio;
    private BigDecimal tarifaMensual;
    private LocalDate fechaInicio;
    private  LocalDate fechaFin;
    private Estado estado = Estado.ACTIVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getTarifaMensual() {
        return tarifaMensual;
    }

    public void setTarifaMensual(BigDecimal tarifaMensual) {
        this.tarifaMensual = tarifaMensual;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public static ContratoServicioDTO fromEntity(ContratoServicio contrato){
        if (contrato == null){
                return null;
        }
        ContratoServicioDTO dto = new ContratoServicioDTO();
        dto.setId(contrato.getId());
        dto.setEstado(contrato.getEstado());
        dto.setFechaFin(contrato.getFechaFin());
        dto.setFechaInicio(contrato.getFechaInicio());
        dto.setTipoServicio(contrato.getTipoServicio());
        dto.setTarifaMensual(contrato.getTarifaMensual());
        dto.setNombreCliente(contrato.getNombreCliente());
        return dto;
    }
}
