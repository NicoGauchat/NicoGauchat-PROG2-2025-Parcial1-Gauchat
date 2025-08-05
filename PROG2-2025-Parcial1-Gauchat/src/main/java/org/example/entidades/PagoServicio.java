package org.example.entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pago_servicio")
public class PagoServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_contrato")
    private  ContratoServicio contrato;
    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;
    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal monto;

    public PagoServicio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContratoServicio getContrato() {
        return contrato;
    }

    public void setContrato(ContratoServicio contrato) {
        this.contrato = contrato;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
