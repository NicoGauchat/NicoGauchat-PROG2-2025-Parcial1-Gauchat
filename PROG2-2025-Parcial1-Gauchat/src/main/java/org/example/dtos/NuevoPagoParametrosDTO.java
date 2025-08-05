package org.example.dtos;

import org.example.enums.Estado;

import javax.lang.model.util.ElementScanner6;
import java.math.BigDecimal;

public class NuevoPagoParametrosDTO {
    private Long idContrato;
    private BigDecimal monto;
    private Estado estadoActualizado;

    public NuevoPagoParametrosDTO(Long idContrato, BigDecimal monto, Estado estadoActualizado) {
        this.idContrato = idContrato;
        this.monto = monto;
        this.estadoActualizado = estadoActualizado;
    }

    public Estado getEstadoActualizado() {
        return estadoActualizado;
    }

    public void setEstadoActualizado(Estado estadoActualizado) {
        this.estadoActualizado = estadoActualizado;
    }

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
