/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.Utility.DelContrato;
import com.pradettisanti.payroll.Utility.StatusQuery;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.CatalogoDocumental;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.ContratoEmpresas;
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
import com.pradettisanti.payroll.pojoh.SalarioUnicoProfesional;
import com.pradettisanti.payroll.pojoh.SolicitudBaja;
import com.pradettisanti.payroll.pojoh.SolicitudRenovacionContrato;
import com.pradettisanti.payroll.pojoh.StatusAgremiado;
import com.pradettisanti.payroll.pojoh.TipoContrato;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import com.pradettisanti.payroll.pojoh.TipoNomina;
import java.util.List;

public interface AgremiadoService {
    
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

    public SolicitudRenovacionContrato getLastSolRenovacion(Agremiado agremiado);

    public RelacionContrato getLastRelacionContrato(Agremiado agremiado);

    public SolicitudBaja getLastSolicitudBaja(Agremiado Agremiado);
 
    public DatosDomicilio getDatosDomicilio(Integer idAgremiado);
    
    public DatosBeneficiario getDatosBeneficiario(Integer idAgremiado);

    public DatosBancarios getDatosBancarios(Integer idAgremiado);
 
    public Agremiado getAgremiado(Integer idAgremiado);
 
    public Agremiado getAgremiado( Agremiado agremiado );

    public List<Agremiado> getAgremiado( StatusAgremiado statusAgremiado, Cliente cliente );
  
    public List<Agremiado> getAgremiado( String status, Integer idCliente );

    public Integer getAgremiadosCount( String status, Integer idCliente);
   
    public List<Agremiado> getAgremiadosContratosPorVencer(Integer idCliente);

    public Documento getDocumento( Agremiado agremiado, TipoDocumento tipoDocumento );
    
    public List<Documento> getDocumentoFA( Agremiado agremiado, TipoDocumento tipoDocumento );
    
    public Integer getFondoAhorroDisponible(String nombreContrato);

    public List<Documento> getDocumentos( Agremiado agremiado, Modulo modulo );
    
    public List<Integer> getDocumentosOblg( Agremiado agremiado, Short modulo );

    public List<Documento> getDocumentos( Agremiado agremiado, List<Modulo> modulos );

    public Modulo getModulo( Short idModulo );

    public List<Modulo> getModulos();
    
    public TipoDocumento getTipoDocumento( Integer tipoDocumentoPK );
    
    public List<Integer> getTipoDocumentosOblg( Short idModulo );
    
    public List<TipoDocumento> getTipoDocumento( Modulo modulo );
    
    public List<TipoDocumento> getTipoDocumentos( Modulo modulo, EsquemaPago ep, ContratoEmpresas  contratoEmpresas);
    
    public List<TipoDocumento> getTipoDocumentoFA( String nombreDocumento );

    public List<TipoDocumento> getTipoDocumento( List<Modulo> modulo );
    
    public EsquemaPago getEsquemaPago (Short idEsquemaPago);
  
    public List<Agremiado> getAgremiadosBusqueda(String nombre, String curp, String rfc, Integer IdCliente);
       
    public TipoContrato getTipoContrato( Short idTipoContrato );

    public List<TipoContrato> getTipoContrato();  

    public TipoNomina getTipoNomina( Short idTipoNomina );

    public List<TipoNomina> getTipoNomina();

    public StatusAgremiado getStatusAgremiado( Short idStatusAgremiado );

    public List<StatusAgremiado> getStatusAgremiado();
   
    public void borrarDocumento(Documento documento);
    
    public List<StatusQuery> getAgremiadoPorStatus(List<Short> status);
    public List<StatusQuery> getAgremiadoPorStatus(List<Short> status, int idUsuario);
    public List<Agremiado> getAgremiadosPorVencer(int statusAgremiado, int idUsuario);
    public int getTotalAgremiadosPorVencer(int statusAgremiado, int idUsuario);
    public List<EsquemaPago> getEsquemaDePagos();
    public List<EsquemaCampo> getCamposDelEsquema(EsquemaPago esquemaPago);
    public List<DelContrato> getContratosDelCliente(Integer idCliente);
    public List<SalarioUnicoProfesional> getSalariosDelContrato(Integer idContratoEmpresas);
    public SalarioUnicoProfesional getSalarioUnicoProfesional(Integer idSUP);
    public List<CatalogoDocumental> getDocumentosDelContrato(Integer idContratoEmpresas);
    public ContratoEmpresas getContratoEmpresasById(Integer id);
    public ContratoEmpresas getContratoEmpresasByIdName(String name);
    public DatosPersonales getifExistsCurp(String curp);
    public DatosPersonales getifExistsRFC(String rfc);
}
