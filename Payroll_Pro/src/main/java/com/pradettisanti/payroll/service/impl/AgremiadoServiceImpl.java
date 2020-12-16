/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.Utility.DelContrato;
import com.pradettisanti.payroll.Utility.StatusQuery;
import com.pradettisanti.payroll.dao.AgremiadoDao;
import com.pradettisanti.payroll.dao.ContratoEmpresasDao;
import com.pradettisanti.payroll.dao.SUPDao;
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
import com.pradettisanti.payroll.service.AgremiadoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgremiadoServiceImpl implements AgremiadoService{
    
    @Autowired
    private AgremiadoDao agremiadoDao;
    
    @Autowired
    private ContratoEmpresasDao contratoEmpresasDao;
    
    @Autowired
    private SUPDao sUPDao;

    @Override
    public Integer setAgremiado(Agremiado agremiado) {
        return agremiadoDao.setAgremiado(agremiado);
    }

    @Override
    public void setDatosBancarios(DatosBancarios datosBancarios) {
        agremiadoDao.setDatosBancarios(datosBancarios);
    }

    @Override
    public void setDatosBeneficiario(DatosBeneficiario datosBeneficiario) {
        agremiadoDao.setDatosBeneficiario(datosBeneficiario);
    }

    @Override
    public void setDatosLaborales(DatosLaborales datosLaborales) {
        agremiadoDao.setDatosLaborales(datosLaborales);
    }

    @Override
    public void setDatosPersonales(DatosPersonales datosPersonales) {
        agremiadoDao.setDatosPersonales(datosPersonales);
    }

    @Override
    public void setDocumento(Documento documento) {
        agremiadoDao.setDocumento(documento);
    }

    @Override
    public void setDocumento(List<Documento> documentos) {
        agremiadoDao.setDocumento(documentos);
    }

    @Override
    public void setDatosDomicilio(DatosDomicilio datosDomicilio) {
        agremiadoDao.setDatosDomicilio(datosDomicilio);
    }

    @Override
    public void setRecibo(Recibo recibo) {
        agremiadoDao.setRecibo(recibo);
    }

    @Override
    public void setRelacionContrato(RelacionContrato relacionContrato) {
        agremiadoDao.setRelacionContrato(relacionContrato);
    }

    @Override
    public void setSolicitudBaja(SolicitudBaja solicitudBaja) {
        agremiadoDao.setSolicitudBaja(solicitudBaja);
    }

    @Override
    public void setSolicitudRenovacionContrato(SolicitudRenovacionContrato solicitudRenovacionContrato) {
        agremiadoDao.setSolicitudRenovacionContrato(solicitudRenovacionContrato);
    }
    
    @Override
    public void setIncidencia ( Incidencia incidencia ){
        agremiadoDao.setIncidencia(incidencia);
    }
    
    @Override
    public SolicitudRenovacionContrato getLastSolRenovacion(Agremiado agremiado){
        return agremiadoDao.getLastSolRenovacion(agremiado);
    }
    
    @Override
    public RelacionContrato getLastRelacionContrato(Agremiado agremiado){
        return agremiadoDao.getLastRelacionContrato(agremiado);
    }
    
    @Override
    public SolicitudBaja getLastSolicitudBaja(Agremiado Agremiado){
        return agremiadoDao.getLastSolicitudBaja(Agremiado);
    }
    
    @Override
    public DatosDomicilio getDatosDomicilio(Integer idAgremiado){
        return agremiadoDao.getDatosDomicilio(idAgremiado);
    }
    
    @Override
    public DatosBeneficiario getDatosBeneficiario(Integer idAgremiado){
        return agremiadoDao.getDatosBeneficiario(idAgremiado);
    }
    
    @Override
    public DatosBancarios getDatosBancarios(Integer idAgremiado){
        return agremiadoDao.getDatosBancarios(idAgremiado);
    }
    
    @Override
    public Agremiado getAgremiado(Integer idAgremiado) {
        return agremiadoDao.getAgremiado(idAgremiado);
    }
    
    @Override
    public Agremiado getAgremiado(Agremiado agremiado) {
        return agremiadoDao.getAgremiado(agremiado);
    }

    @Override
    public List<Agremiado> getAgremiado(StatusAgremiado statusAgremiado, Cliente cliente) {
        return agremiadoDao.getAgremiados(statusAgremiado, cliente);
    }

    @Override
    public List<Agremiado> getAgremiado(String status, Integer idCliente) {
        return agremiadoDao.getAgremiados(status, idCliente);
    }
    
    @Override
    public Integer getAgremiadosCount( String status, Integer idCliente){
        return agremiadoDao.getAgremiadosCount(status, idCliente);
    }
    
    @Override
    public List<Agremiado> getAgremiadosContratosPorVencer(Integer idCliente){
        return agremiadoDao.getAgremiadosContratosPorVencer(idCliente);
    }

    @Override
    public Documento getDocumento(Agremiado agremiado, TipoDocumento tipoDocumento) {
        return agremiadoDao.getDocumento(agremiado, tipoDocumento);
    }

    @Override
    public List<Documento> getDocumentos(Agremiado agremiado, Modulo modulo) {
        return agremiadoDao.getDocumentos(agremiado, modulo);
    }
    
    @Override
    public List<Integer> getDocumentosOblg( Agremiado agremiado, Short modulo ){
        return agremiadoDao.getDocumentosOblg(agremiado, modulo);
    }

    @Override
    public List<Documento> getDocumentos(Agremiado agremiado, List<Modulo> modulos) {
        return agremiadoDao.getDocumentos(agremiado, modulos);
    }

    @Override
    public Modulo getModulo(Short idModulo) {
        return agremiadoDao.getModulo(idModulo);
    }

    @Override
    public List<Modulo> getModulos() {
        return agremiadoDao.getModulos();    
    }

    @Override
    public TipoDocumento getTipoDocumento(Integer tipoDocumentoPK) {
        return agremiadoDao.getTipoDocumento(tipoDocumentoPK);
    }
    
    @Override
    public List<Integer> getTipoDocumentosOblg( Short idModulo ){
        return agremiadoDao.getTipoDocumentosOblg(idModulo);
    }
    /**
     *  Metodo que se encargaba de devolver una lista de documentos segun su modulo
     * @param modulo
     * @return  List TipoDocumento
     * @deprecated Se remplaza por el metodo con la firma getTipoDocumento(Modulo modulo, EsquemaPago ep)
     */
    @Deprecated
    @Override
    public List<TipoDocumento> getTipoDocumento(Modulo modulo) {
        return agremiadoDao.getTipoDocumento(modulo);
    }

    @Override
    public List<TipoDocumento> getTipoDocumento(List<Modulo> modulo) {
        return agremiadoDao.getTipoDocumento(modulo);
    }
    
    @Override
    public EsquemaPago getEsquemaPago (Short idEsquemaPago){
        return agremiadoDao.getEsquemaPago(idEsquemaPago);
    }
    
    @Override
    public List<Agremiado> getAgremiadosBusqueda(String nombre, String curp, String rfc, Integer IdCliente){
        return agremiadoDao.getAgremiadosBusqueda( nombre,  curp,  rfc,  IdCliente);
    }

    @Override
    public TipoContrato getTipoContrato(Short idTipoContrato) {
        return agremiadoDao.getTipoContrato(idTipoContrato);
    }

    @Override
    public List<TipoContrato> getTipoContrato() {
        return agremiadoDao.getTipoContrato();
    }

    @Override
    public TipoNomina getTipoNomina(Short idTipoNomina) {
        return agremiadoDao.getTipoNomina(idTipoNomina);
    }

    @Override
    public List<TipoNomina> getTipoNomina() {
        return agremiadoDao.getTipoNomina();
    }

    @Override
    public StatusAgremiado getStatusAgremiado(Short idStatusAgremiado) {
        return agremiadoDao.getStatusAgremiado(idStatusAgremiado);
    }

    @Override
    public List<StatusAgremiado> getStatusAgremiado() {
        return agremiadoDao.getStatusAgremiado();
    }
    
    @Override
    public void borrarDocumento(Documento documento){
        agremiadoDao.borrarDocumento(documento);
    }

    @Override
    public List<StatusQuery> getAgremiadoPorStatus(List<Short> status) {
       return agremiadoDao.getAgremiadoPorStatus(status);
    }

    @Override
    public List<StatusQuery> getAgremiadoPorStatus(List<Short> status, int idUsuario) {
        return agremiadoDao.getAgremiadoPorStatus(status, idUsuario);
    }

    @Override
    public List<Agremiado> getAgremiadosPorVencer(int statusAgremiado, int idUsuario) {
        return agremiadoDao.getAgremiadosPorVencer(statusAgremiado, idUsuario);
    }

    @Override
    public int getTotalAgremiadosPorVencer(int statusAgremiado, int idUsuario) {
        return agremiadoDao.getTotalAgremiadosPorVencer(statusAgremiado, idUsuario);
    }

    @Override
    public List<EsquemaPago> getEsquemaDePagos() {
        return agremiadoDao.getEsquemaDePagos();
    }
    
    @Override
    public List<EsquemaCampo> getCamposDelEsquema(EsquemaPago esquemaPago) {
        return agremiadoDao.getCamposDelEsquema(esquemaPago);
    }

    @Override
    public List<DelContrato> getContratosDelCliente(Integer idCliente) {
        return contratoEmpresasDao.getContratosDelCliente(idCliente);
    }

    @Override
    public List<SalarioUnicoProfesional> getSalariosDelContrato(Integer idContratoEmpresas) {
        return contratoEmpresasDao.getContratoEmpresas(idContratoEmpresas).getSalarioUnicoProfesionalList();
    }

    @Override
    public List<CatalogoDocumental> getDocumentosDelContrato(Integer idContratoEmpresas) {
        return contratoEmpresasDao.getContratoEmpresas(idContratoEmpresas).getCatalogoDocumentalList();
    }

    @Override
    public ContratoEmpresas getContratoEmpresasById(Integer id) {
        return contratoEmpresasDao.getContratoEmpresas(id);
    }

    @Override
    public ContratoEmpresas getContratoEmpresasByIdName(String name) {
        return contratoEmpresasDao.getContratoEmpresasByIdName(name);
    }
    
    @Override
    public SalarioUnicoProfesional getSalarioUnicoProfesional(Integer idSUP){
        return sUPDao.getSalarioUnicoProfesional(idSUP);
    }

    @Override
    public List<TipoDocumento> getTipoDocumentos(Modulo modulo, EsquemaPago ep, ContratoEmpresas  contratoEmpresas) {
        List<TipoDocumento> documentos = new ArrayList<>();
        documentos.addAll(agremiadoDao.getTipoDocumentoNoServicios(modulo.getIdModulo(), ep.getIdEsquemaPago()));
        documentos.addAll(agremiadoDao.getTipoDocumentoServiciosByContrato(modulo.getIdModulo(), ep.getIdEsquemaPago(), contratoEmpresas.getIdContratoEmpresas()));
        return documentos;
    }

    @Override
    public List<TipoDocumento> getTipoDocumentoFA(String nombreDocumento) {
        return agremiadoDao.getTipoDocumentoFA(nombreDocumento);
    }

    @Override
    public List<Documento> getDocumentoFA(Agremiado agremiado, TipoDocumento tipoDocumento) {
        return agremiadoDao.getDocumentoFA(agremiado, tipoDocumento);
    }

    @Override
    public Integer getFondoAhorroDisponible(String nombreContrato) {
        return agremiadoDao.getFondoAhorroDisponible(nombreContrato);
    }

    @Override
    public DatosPersonales getifExistsCurp(String curp) {
        return agremiadoDao.getifExistsCurp(curp);
    }

    @Override
    public DatosPersonales getifExistsRFC(String rfc) {
        return agremiadoDao.getifExistsRFC(rfc);
    }

}
