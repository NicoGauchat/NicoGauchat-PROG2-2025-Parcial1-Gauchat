package org.example.servicios;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.dtos.*;
import org.example.entidades.ContratoServicio;
import org.example.entidades.PagoServicio;
import org.example.enums.Estado;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


public class Logica {
    private static Logica instancia;

    private Logica() {
    }

    public static Logica getInstancia() {
        if (instancia == null){
            instancia = new Logica();
        }
        return instancia;
    }
    public NuevoPagoParametrosDTO nuevoPago(NuevoPagoParametrosDTO dto){
        if (dto == null){
            return null;
        }
        try (Session session = HibernateUtil.getSession()){
            ContratoServicio contrato = session.get(ContratoServicio.class, dto.getIdContrato());
            if (contrato == null){
                throw new RuntimeException("contrato no encontrado");
            }
            if (contrato.getEstado() == Estado.CANCELADO){
                throw new RuntimeException("el contarto se encuentra cancelado");
            }
            session.beginTransaction();
            PagoServicio pagoServicio = new PagoServicio();
            pagoServicio.setFechaPago(LocalDate.now());
            pagoServicio.setContrato(contrato);
            pagoServicio.setMonto(dto.getMonto());

            BigDecimal totalEsperado = contrato.getTarifaMensual().multiply(BigDecimal.valueOf(ChronoUnit.MONTHS.between(contrato.getFechaInicio(), contrato.getFechaFin())));
            if (dto.getMonto().compareTo(totalEsperado) >= 0){
                contrato.setEstado(Estado.CANCELADO);
                session.merge(contrato);
            }
            else{
                if (contrato.getFechaFin().isBefore(LocalDate.now())){
                    contrato.setEstado(Estado.VENCIDO);
                    session.merge(contrato);
                }
            }
            Estado estado = contrato.getEstado();
session.persist(pagoServicio);
session.getTransaction().commit();
return new NuevoPagoParametrosDTO(dto.getIdContrato(), dto.getMonto(), estado);
        }
    }
    public List<ContratoServicioDTO> obtenerContratosPorNombre(BuscarContratosPorNombreDeClienteDTO dto){
        try (Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ContratoServicio> query = cb.createQuery(ContratoServicio.class);
            Root<ContratoServicio> contratoRoot = query.from(ContratoServicio.class);
            Predicate predicate = cb.like(contratoRoot.get("nombreCliente"), "%"+dto.getNombreCliente()+"%");

            if (dto.getTipoServicio() != null){
                predicate = cb.and(predicate, cb.equal(contratoRoot.get("tipoServicio"), dto.getTipoServicio()));
            }
            if (dto.getFechaInicioDesde() != null && dto.getFechaInicioHasta() != null){
                predicate = cb.and(predicate, cb.between(contratoRoot.get("fechaInicio"), dto.getFechaInicioDesde(), dto.getFechaInicioHasta()));
            }
            if (dto.getTarifaMensualDesde() != null && dto.getTarifaMensualHasta() != null){
                predicate= cb.and(predicate, cb.between(contratoRoot.get("tarifaMensual"), dto.getTarifaMensualDesde(), dto.getTarifaMensualHasta()));
            }
            query.select(contratoRoot).orderBy(cb.desc(contratoRoot.get("fechaInicio")));
            query.where(predicate);
            List<ContratoServicio> resultado = session.createQuery(query).getResultList();
            return resultado.stream().map(ContratoServicioDTO::fromEntity).collect(Collectors.toList());
        }
public ResumenDTO obtenerResumenContratosCancelados(RangoDeFechasDTO dto){
            
        }

    }
}
