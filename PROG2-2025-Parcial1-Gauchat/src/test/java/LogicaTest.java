import org.example.dtos.BuscarContratosPorNombreDeClienteDTO;
import org.example.dtos.ContratoServicioDTO;
import org.example.dtos.NuevoPagoParametrosDTO;
import org.example.dtos.ResumenContratosNoCanceladosDTO;
import org.example.entidades.ContratoServicio;
import org.example.entidades.PagoServicio;
import org.example.enums.Estado;
import org.example.enums.TipoServicio;
import org.example.servicios.Logica;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogicaTest {
    private Logica logica;
    private Session session;

    private ContratoServicio contrato1;
    private ContratoServicio contrato2;
    private ContratoServicio contrato3;
    private ContratoServicio contrato4;
private  ContratoServicio contrato5;

    private PagoServicio pago1;
    private PagoServicio pago2;

    @BeforeAll
    void setUp(){
        logica = Logica.getInstancia();
        session = HibernateUtil.getSession();
        session.beginTransaction();
        contrato1 = new ContratoServicio();
        contrato2 = new ContratoServicio();
        contrato3 = new ContratoServicio();
        contrato4 = new ContratoServicio();
contrato5 = new ContratoServicio();

        pago1 = new PagoServicio();
        pago2 = new PagoServicio();


        contrato1.setNombreCliente("Nicolas Gauchat");
        contrato1.setFechaInicio(LocalDate.of(2025, 8, 1));
        contrato1.setFechaFin(LocalDate.of(2025, 8, 4));
        contrato1.setTarifaMensual(BigDecimal.valueOf(1000).setScale(2));
        contrato1.setTipoServicio(TipoServicio.ELECTRICIDAD);

        contrato5.setNombreCliente("Nicolas Gauchat");
        contrato5.setFechaInicio(LocalDate.of(2024, 8, 1));
        contrato5.setFechaFin(LocalDate.of(2024, 8, 4));
        contrato5.setTarifaMensual(BigDecimal.valueOf(1000).setScale(2));
        contrato5.setTipoServicio(TipoServicio.ELECTRICIDAD);

        contrato2.setNombreCliente("Nicolas Gauchat");
        contrato2.setFechaInicio(LocalDate.of(2025, 8, 1));
        contrato2.setFechaFin(LocalDate.of(2025, 8, 4));
        contrato2.setTarifaMensual(BigDecimal.valueOf(1000).setScale(2));
        contrato2.setTipoServicio(TipoServicio.AGUA);

        contrato3.setNombreCliente("Maxi Lovera");
        contrato3.setFechaInicio(LocalDate.of(2025, 8, 1));
        contrato3.setFechaFin(LocalDate.of(2025, 8, 4));
        contrato3.setTarifaMensual(BigDecimal.valueOf(1000).setScale(2));
        contrato3.setTipoServicio(TipoServicio.ELECTRICIDAD);
        contrato3.setEstado(Estado.CANCELADO);

        contrato4.setNombreCliente("Lio Messi");
        contrato4.setFechaInicio(LocalDate.of(2025, 8, 1));
        contrato4.setFechaFin(LocalDate.of(2025, 8, 4));
        contrato4.setTarifaMensual(BigDecimal.valueOf(1000).setScale(2));
        contrato4.setTipoServicio(TipoServicio.ELECTRICIDAD);
        contrato4.setEstado(Estado.CANCELADO);

        pago1.setMonto(BigDecimal.valueOf(1000));
        pago1.setFechaPago(LocalDate.of(2025, 8, 2));
        pago1.setContrato(contrato3);

        pago2.setMonto(BigDecimal.valueOf(1000));
        pago2.setFechaPago(LocalDate.of(2025, 8, 2));
        pago2.setContrato(contrato4);

        session.persist(contrato1);
        session.persist(contrato2);
        session.persist(contrato3);
        session.persist(contrato4);
        session.persist(contrato5);
        session.persist(pago1);
        session.persist(pago2);

        session.getTransaction().commit();
    }
    @AfterAll
    public void tearDown(){
        if (session != null && session.isOpen()){
            session.beginTransaction();
            session.createQuery("delete from PagoServicio").executeUpdate();
            session.createQuery("delete from ContratoServicio").executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }
    @Test
    void nuevoPagoTest(){
        NuevoPagoParametrosDTO dto = new NuevoPagoParametrosDTO(1L, BigDecimal.valueOf(1000), null);

        NuevoPagoParametrosDTO result = logica.nuevoPago(dto);

        assertEquals(Estado.CANCELADO, result.getEstadoActualizado());
        assertEquals(1L, result.getIdContrato());
    }
    @Test
    void ObtenerContratoPorNombreTest_SoloNombreCliente(){
        BuscarContratosPorNombreDeClienteDTO dto = new BuscarContratosPorNombreDeClienteDTO();
        dto.setNombreCliente("Nicolas Gauchat");
        List<ContratoServicioDTO> resultado = logica.obtenerContratosPorNombre(dto);

        assertEquals(3, resultado.size());
        assertEquals("Nicolas Gauchat",resultado.getFirst().getNombreCliente());
    }
    @Test
    void ObtenerContratoPorNombreTest_PorNombreClienteYTipoServicio(){
        BuscarContratosPorNombreDeClienteDTO dto = new BuscarContratosPorNombreDeClienteDTO();
        dto.setNombreCliente("Nicolas Gauchat");
        dto.setTipoServicio(TipoServicio.ELECTRICIDAD);
        List<ContratoServicioDTO> resultado = logica.obtenerContratosPorNombre(dto);

        assertEquals(2, resultado.size());
        assertEquals(TipoServicio.ELECTRICIDAD,resultado.getFirst().getTipoServicio());
    }

    @Test
    void ObtenerContratoPorNombreTest_PorNombreClienteTipoServicioYFechas(){
        BuscarContratosPorNombreDeClienteDTO dto = new BuscarContratosPorNombreDeClienteDTO();
        dto.setNombreCliente("Nicolas Gauchat");
        dto.setTipoServicio(TipoServicio.ELECTRICIDAD);
        dto.setFechaInicioDesde(LocalDate.of(2024,7,1));
        dto.setFechaInicioHasta(LocalDate.of(2024,9,5));
        List<ContratoServicioDTO> resultado = logica.obtenerContratosPorNombre(dto);

        assertEquals(1, resultado.size());
        assertEquals(resultado.getFirst().getFechaInicio(),LocalDate.of(2024,8,1));
    }

}
