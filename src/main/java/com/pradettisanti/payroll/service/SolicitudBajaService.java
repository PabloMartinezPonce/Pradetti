/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.DocumentosBaja;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.MotivoBaja;
import com.pradettisanti.payroll.pojoh.SolicitudBaja;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import com.pradettisanti.payroll.pojoh.TipoDocumentoPK;
import com.pradettisanti.payroll.pojoh.TipoFiniquito;
import java.util.List;

public interface SolicitudBajaService {
    
    public void setDocumentoBaja( DocumentosBaja documentosBaja );

    public List<DocumentosBaja> getDocumentosBaja( SolicitudBaja solicitudBaja, TipoDocumentoPK tipoDocumentoPK );

    public List<DocumentosBaja> getDocumentosBaja( SolicitudBaja solicitudBaja );

    public Modulo getModulo( Short idModulo );

    public List<Modulo> getModulos();

    public MotivoBaja getMotivoBaja( Short idMotivoBaja );

    public List<MotivoBaja> getMotivoBaja();

    public TipoDocumento getTipoDocumento( TipoDocumentoPK tipoDocumentoPK );

    public List<TipoDocumento> getTipoDocumento( Modulo modulo );

    public List<TipoDocumento> getTipoDocumento( List<Modulo> modulo );

    public TipoFiniquito getTipoFiniquito( Short idTipoFiniquito );

    public List<TipoFiniquito> getTipoFiniquito();
    
    public List<TipoDocumento> getTipoDocumentoSolicitudBaja( Integer idSolBaja, Short idModulo );
    
    public DocumentosBaja getDocumentoBaja(Integer idSolicitudBaja, Integer idTipoDocumento);
    
    public void deleteSolicitudBaja(SolicitudBaja solicitudBaja);
    
    public void deleteDocumentoBaja(DocumentosBaja documentobaja);
    
    public List<DocumentosBaja> getDocumentosBaja( Integer idSolicitudBaja, Short idModulo);
}
