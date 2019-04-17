/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.interkont.wscobra.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonatan
 */
@Entity
@Table(name = "vista_contratistas_contrato_proyecto", catalog = "cobrauepruebas", schema = "appmobile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaContratistasContratoProyecto.findAll", query = "SELECT v FROM VistaContratistasContratoProyecto v"),
    @NamedQuery(name = "VistaContratistasContratoProyecto.findById", query = "SELECT v FROM VistaContratistasContratoProyecto v WHERE v.id = :id"),
    @NamedQuery(name = "VistaContratistasContratoProyecto.findByCodigoobra", query = "SELECT v FROM VistaContratistasContratoProyecto v WHERE v.codigoobra = :codigoobra"),
    @NamedQuery(name = "VistaContratistasContratoProyecto.findByNombreobra", query = "SELECT v FROM VistaContratistasContratoProyecto v WHERE v.nombreobra = :nombreobra"),
    @NamedQuery(name = "VistaContratistasContratoProyecto.findByCodigocontrato", query = "SELECT v FROM VistaContratistasContratoProyecto v WHERE v.codigocontrato = :codigocontrato"),
    @NamedQuery(name = "VistaContratistasContratoProyecto.findByNombrecontrato", query = "SELECT v FROM VistaContratistasContratoProyecto v WHERE v.nombrecontrato = :nombrecontrato"),
    @NamedQuery(name = "VistaContratistasContratoProyecto.findByCodigocontratista", query = "SELECT v FROM VistaContratistasContratoProyecto v WHERE v.codigocontratista = :codigocontratista"),
    @NamedQuery(name = "VistaContratistasContratoProyecto.findByNombrecontratista", query = "SELECT v FROM VistaContratistasContratoProyecto v WHERE v.nombrecontratista = :nombrecontratista")})
public class VistaContratistasContratoProyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private Integer codigoobra;
    @Column(length = 2147483647)
    private String nombreobra;
    private Integer codigocontrato;
    @Column(length = 2147483647)
    private String nombrecontrato;
    private Integer codigocontratista;
    @Column(length = 2147483647)
    private String nombrecontratista;

    public VistaContratistasContratoProyecto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoobra() {
        return codigoobra;
    }

    public void setCodigoobra(Integer codigoobra) {
        this.codigoobra = codigoobra;
    }

    public String getNombreobra() {
        return nombreobra;
    }

    public void setNombreobra(String nombreobra) {
        this.nombreobra = nombreobra;
    }

    public Integer getCodigocontrato() {
        return codigocontrato;
    }

    public void setCodigocontrato(Integer codigocontrato) {
        this.codigocontrato = codigocontrato;
    }

    public String getNombrecontrato() {
        return nombrecontrato;
    }

    public void setNombrecontrato(String nombrecontrato) {
        this.nombrecontrato = nombrecontrato;
    }

    public Integer getCodigocontratista() {
        return codigocontratista;
    }

    public void setCodigocontratista(Integer codigocontratista) {
        this.codigocontratista = codigocontratista;
    }

    public String getNombrecontratista() {
        return nombrecontratista;
    }

    public void setNombrecontratista(String nombrecontratista) {
        this.nombrecontratista = nombrecontratista;
    }
    
}
