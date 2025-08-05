package org.example.entidades;

import jakarta.persistence.*;
import org.example.enums.Estado;
import org.example.enums.TipoServicio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "contrato_servicio")
public class ContratoServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private Long id;
    @Column(name = "nombre_cliente", length = 100, nullable = false)
    private String nombreCliente;
    @Column(name = "tipo_servicio", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoServicio tipoServicio;
    @Column(name = "tarifa_mesnual", precision = 12, scale = 2, nullable = false)
    private BigDecimal tarifaMensual;
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin", nullable = false)
    private  LocalDate fechaFin;
    @Column()
    private Estado estado = Estado.ACTIVO;
    @OneToMany(mappedBy = "contrato")
    private List<PagoServicio> pagos;

    public ContratoServicio() {
    }

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

    public List<PagoServicio> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoServicio> pagos) {
        this.pagos = pagos;
    }
}
