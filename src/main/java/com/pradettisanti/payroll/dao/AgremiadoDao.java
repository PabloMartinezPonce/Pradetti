/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.Utility.StatusQuery;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.DatosBancarios;
import com.pradettisanti.payroll.pojoh.DatosBeneficiario;
import com.pradettisanti.payroll.pojoh.DatosDomicilio;
import com.pradettisanti.payroll.pojoh.DatosLaborales;
import com.pradettisanti.payroll.pojoh.DatosPersonales;
import com.pradettisanti.payroll.pojoh.Documento;
import com.pradettisanti.payroll.pojoh.EsquemaCampo;
import com.pradettisanti.payroll.pojoh.EsquemaPago;
import com.pradettisanti.payroll.pojoh.Incidencia;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.RelacionContrato;
import com.pradettisanti.payroll.pojoh.SolicitudBaja;
import com.pradettisanti.payroll.pojoh.SolicitudRenovacionContrato;
import com.pradettisanti.payroll.pojoh.StatusAgremiado;
import com.pradettisanti.payroll.pojoh.TipoContrato;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import com.pradettisanti.payroll.pojoh.TipoNomina;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.Agremiado
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 2
 * @since 12/02/2018
 */
public interface AgremiadoDao {
    
    //Create
    public  Integer setAgremiado( Agremiado agremiado );
    public void setDatosBancarios( DatosBancarios datosBancarios );
    public void setDatosBeneficiario( DatosBeneficiario datosBeneficiario );
    public void setDatosLaborales( DatosLaborales datosLaborales );
    public void setDatosPersonales(DatosPersonales datosPersonales );
    public void setDocumento( Documento documento );
    public void setDocumento( List<Documento> documentos );
    public void setDatosDomicilio( DatosDomicilio datosDomicilio );
    public void setRecibo( Recibo recibo );
    public void setRelacionContrato( RelacionContrato relacionContrato );
    public void setSolicitudBaja( SolicitudBaja solicitudBaja );
    public void setSolicitudRenovacionContrato( SolicitudRenovacionContrato solicitudRenovacionContrato );
    public void setIncidencia ( Incidencia incidencia );
    //Read
    public SolicitudRenovacionContrato getLastSolRenovacion(Agremiado agremiado);
    public RelacionContrato getLastRelacionContrato(Agremiado agremiado);
    public SolicitudBaja getLastSolicitudBaja(Agremiado Agremiado);
    public DatosDomicilio getDatosDomicilio(Integer idAgremiado);
    public DatosBeneficiario getDatosBeneficiario(Integer idAgremiado);
    public DatosBancarios getDatosBancarios(Integer idAgremiado);
    public Agremiado getAgremiado(Integer idAgremiado);
    public Agremiado getAgremiado( Agremiado agremiado );
    @Deprecated
    public List<Agremiado> getAgremiado( StatusAgremiado statusAgremiado, Cliente cliente );
    public List<Agremiado> getAgremiados( StatusAgremiado statusAgremiado, Contratista contratista);
    public List<Agremiado> getAgremiados( StatusAgremiado statusAgremiado, Contratista contratista, Cliente cliente);
    public List<Agremiado> getAgremiados( StatusAgremiado statusAgremiado, Cliente cliente );
    public List<Agremiado> getAgremiados( String status, Integer idCliente);
    public Integer getAgremiadosCount( String status, Integer idCliente);
    public List<Agremiado> getAgremiadosContratosPorVencer(Integer idCliente);
    public Documento getDocumento( Agremiado agremiado, TipoDocumento tipoDocumento );
    public List<Documento> getDocumentoFA(Agremiado agremiado, TipoDocumento tipoDocumento);
    public Integer getFondoAhorroDisponible(String nombreContrato);
    public List<Documento> getDocumentos( Agremiado agremiado, Modulo modulo );
    public List<Integer> getDocumentosOblg( Agremiado agremiado, Short modulo );
    public List<Documento> getDocumentos( Agremiado agremiado, List<Modulo> modulos );
    public EsquemaPago getEsquemaPago (Short idEsquemaPago);
    public List<Agremiado> getAgremiadosBusqueda(String nombre, String curp, String rfc, Integer IdCliente);
    public List<StatusQuery> getAgremiadoPorStatus(List<Short> status);
    public List<StatusQuery> getAgremiadoPorStatus(List<Short> status, int idUsuario);
    public List<Agremiado> getAgremiadosPorVencer(int statusAgremiado, int idUsuario);
    public int getTotalAgremiadosPorVencer(int statusAgremiado, int idUsuario);
    /**
     * Metodo encargado de regresar una lista de tipo EsquemaCampo 
     * @param esquemaPago
     * @return List EsquemaCampo
     */
    public List<EsquemaCampo> getCamposDelEsquema(EsquemaPago esquemaPago);
    /**
     * Metodo encargado de regresar todos los esquemas de pago
     * @return List EsquemaPago
     */
    public List<EsquemaPago> getEsquemaDePagos();
    public DatosPersonales getifExistsCurp(String curp);
    public DatosPersonales getifExistsRFC(String rfc);
    //--Modulo
    public Modulo getModulo( Short idModulo );
    public List<Modulo> getModulos();
    //--TipoDocumento
    public TipoDocumento getTipoDocumento( Integer tipoDocumento );
    public List<Integer> getTipoDocumentosOblg( Short idModulo );
    public List<TipoDocumento> getTipoDocumento( Modulo modulo );
    public List<TipoDocumento> getTipoDocumento( List<Modulo> modulo );
    /**
     * Metodo encargado de regresar una lista de TipoDocumento en base a su modulo y esquema de pago
     * @param modulo
     * @param ep
     * @return List TipoDocumento
     */
    public List<TipoDocumento> getTipoDocumentoNoServicios( Short modulo, Short ep );
    public List<TipoDocumento> getTipoDocumentoServiciosByContrato( Short modulo, Short ep, Integer idCE );
    //--FondoDeAhorro
    public List<TipoDocumento> getTipoDocumentoFA( String nombreDocumento );
    //--TipoContrato
    public TipoContrato getTipoContrato( Short idTipoContrato );
    public List<TipoContrato> getTipoContrato();  
    //--TipoNomina
    public TipoNomina getTipoNomina( Short idTipoNomina );
    public List<TipoNomina> getTipoNomina();
    //--StatusAgremiado
    public StatusAgremiado getStatusAgremiado( Short idStatusAgremiado );
    public List<StatusAgremiado> getStatusAgremiado();
    //Update
    //Delete
    public void borrarDocumento(Documento documento);
    
}
