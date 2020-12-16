/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.DocumentosBaja;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.MotivoBaja;
import com.pradettisanti.payroll.pojoh.SolicitudBaja;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import com.pradettisanti.payroll.pojoh.TipoDocumentoPK;
import com.pradettisanti.payroll.pojoh.TipoFiniquito;
import java.util.List;

/**
 *  Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.SolicitudBaja
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface SolicitudBajaDao {
    //Create
    public void setDocumentoBaja( DocumentosBaja documentosBaja );
    //Read
    public DocumentosBaja getDocumentoBaja( Integer idSolicitudBaja, Integer idTipoDocumento);
    public List<DocumentosBaja> getDocumentosBaja( SolicitudBaja solicitudBaja, TipoDocumentoPK tipoDocumentoPK );
    public List<DocumentosBaja> getDocumentosBaja( SolicitudBaja solicitudBaja );
    public List<DocumentosBaja> getDocumentosBaja( Integer idSolicitudBaja, Short idModulo);
    //--Modulo
    public Modulo getModulo( Short idModulo );
    public List<Modulo> getModulos();
    //--MotivoBaja
    public MotivoBaja getMotivoBaja( Short idMotivoBaja );
    public List<MotivoBaja> getMotivoBaja();
    //--TipoDocumento
    public TipoDocumento getTipoDocumento( TipoDocumentoPK tipoDocumentoPK );
    public List<TipoDocumento> getTipoDocumento( Modulo modulo );
    public List<TipoDocumento> getTipoDocumento( List<Modulo> modulo );
    public List<TipoDocumento> getTipoDocumentoSolicitudBaja( Integer idSolBaja, Short idModulo );
    //--TipoFiniquito
    public TipoFiniquito getTipoFiniquito( Short idTipoFiniquito );
    public List<TipoFiniquito> getTipoFiniquito();
    //Update
    //Delete
    public void deleteSolicitudBaja(SolicitudBaja solicitudBaja);
    public void deleteDocumentoBaja(DocumentosBaja documentobaja);
}
