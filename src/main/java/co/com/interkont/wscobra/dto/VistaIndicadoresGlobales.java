/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.interkont.wscobra.dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "vista_indicadores_globales", catalog = "cobrauepruebas", schema = "appmobile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaIndicadoresGlobales.findByCodigocategoria", query = "SELECT v FROM VistaIndicadoresGlobales v WHERE v.codigocategoria = :codigocategoria"),
    })
public class VistaIndicadoresGlobales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private BigDecimal totalvalorproyectos;
    @Column(precision = 20, scale = 14)
    private String logototalvalorproyectos;
    private BigDecimal totalvalorejecutadoproyectos;
    @Column(precision = 20, scale = 14)
    private String logototalvalorejecutadoproyectos;
    private BigDecimal totalavanceproyectos;
    @Column(precision = 20, scale = 14)
    private String logototalavanceproyectos;
    private BigDecimal totalempleosdirectos;
    @Column(precision = 20, scale = 14)
    private String logototalempleosdirectos;
    private BigDecimal totalempleosindirectos;
    @Column(precision = 20, scale = 14)
    private String logototalempleosindirectos;
    private BigDecimal totalhabitantesbeneficiados;
    @Column(precision = 20, scale = 14)
    private String logototalhabitantesbeneficiados;
    private BigDecimal totalproyectos;
    @Column(precision = 20, scale = 14)
    private String logototalproyectos;
    private BigDecimal totalproyectosejecucion;
    @Column(precision = 20, scale = 14)
    private String logototalproyectosejecucion;
    private BigDecimal totalproyectosiniciar;
    @Column(precision = 20, scale = 14)
    private String logototalproyectosiniciar;
    private BigDecimal totalproyectosterminados;
    @Column(precision = 20, scale = 14)
    private String logototalproyectosterminados;
    private BigDecimal codigocategoria;
    @Column(precision = 20, scale = 14)
    private String nombrecategoria;
    @Column(precision = 20, scale = 14)
    private String colorcategoria;
    @Column(precision = 20, scale = 14)
    private String botoncategoriainactivo;
    @Column(precision = 20, scale = 14)
    private String botoncategoriaactivo;

    public VistaIndicadoresGlobales() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotalvalorproyectos() {
        return totalvalorproyectos;
    }

    public void setTotalvalorproyectos(BigDecimal totalvalorproyectos) {
        this.totalvalorproyectos = totalvalorproyectos;
    }

    public String getLogototalvalorproyectos() {
        return logototalvalorproyectos;
    }

    public void setLogototalvalorproyectos(String logototalvalorproyectos) {
        this.logototalvalorproyectos = logototalvalorproyectos;
    }

    public BigDecimal getTotalvalorejecutadoproyectos() {
        return totalvalorejecutadoproyectos;
    }

    public void setTotalvalorejecutadoproyectos(BigDecimal totalvalorejecutadoproyectos) {
        this.totalvalorejecutadoproyectos = totalvalorejecutadoproyectos;
    }

    public String getLogototalvalorejecutadoproyectos() {
        return logototalvalorejecutadoproyectos;
    }

    public void setLogototalvalorejecutadoproyectos(String logototalvalorejecutadoproyectos) {
        this.logototalvalorejecutadoproyectos = logototalvalorejecutadoproyectos;
    }

    public BigDecimal getTotalavanceproyectos() {
        return totalavanceproyectos;
    }

    public void setTotalavanceproyectos(BigDecimal totalavanceproyectos) {
        this.totalavanceproyectos = totalavanceproyectos;
    }

    public String getLogototalavanceproyectos() {
        return logototalavanceproyectos;
    }

    public void setLogototalavanceproyectos(String logototalavanceproyectos) {
        this.logototalavanceproyectos = logototalavanceproyectos;
    }

    public BigDecimal getTotalempleosdirectos() {
        return totalempleosdirectos;
    }

    public void setTotalempleosdirectos(BigDecimal totalempleosdirectos) {
        this.totalempleosdirectos = totalempleosdirectos;
    }

    public String getLogototalempleosdirectos() {
        return logototalempleosdirectos;
    }

    public void setLogototalempleosdirectos(String logototalempleosdirectos) {
        this.logototalempleosdirectos = logototalempleosdirectos;
    }

    public BigDecimal getTotalempleosindirectos() {
        return totalempleosindirectos;
    }

    public void setTotalempleosindirectos(BigDecimal totalempleosindirectos) {
        this.totalempleosindirectos = totalempleosindirectos;
    }

    public String getLogototalempleosindirectos() {
        return logototalempleosindirectos;
    }

    public void setLogototalempleosindirectos(String logototalempleosindirectos) {
        this.logototalempleosindirectos = logototalempleosindirectos;
    }

    public BigDecimal getTotalhabitantesbeneficiados() {
        return totalhabitantesbeneficiados;
    }

    public void setTotalhabitantesbeneficiados(BigDecimal totalhabitantesbeneficiados) {
        this.totalhabitantesbeneficiados = totalhabitantesbeneficiados;
    }

    public String getLogototalhabitantesbeneficiados() {
        return logototalhabitantesbeneficiados;
    }

    public void setLogototalhabitantesbeneficiados(String logototalhabitantesbeneficiados) {
        this.logototalhabitantesbeneficiados = logototalhabitantesbeneficiados;
    }

    public BigDecimal getTotalproyectos() {
        return totalproyectos;
    }

    public void setTotalproyectos(BigDecimal totalproyectos) {
        this.totalproyectos = totalproyectos;
    }

    public String getLogototalproyectos() {
        return logototalproyectos;
    }

    public void setLogototalproyectos(String logototalproyectos) {
        this.logototalproyectos = logototalproyectos;
    }

    public BigDecimal getTotalproyectosejecucion() {
        return totalproyectosejecucion;
    }

    public void setTotalproyectosejecucion(BigDecimal totalproyectosejecucion) {
        this.totalproyectosejecucion = totalproyectosejecucion;
    }

    public String getLogototalproyectosejecucion() {
        return logototalproyectosejecucion;
    }

    public void setLogototalproyectosejecucion(String logototalproyectosejecucion) {
        this.logototalproyectosejecucion = logototalproyectosejecucion;
    }

    public BigDecimal getTotalproyectosiniciar() {
        return totalproyectosiniciar;
    }

    public void setTotalproyectosiniciar(BigDecimal totalproyectosiniciar) {
        this.totalproyectosiniciar = totalproyectosiniciar;
    }

    public String getLogototalproyectosiniciar() {
        return logototalproyectosiniciar;
    }

    public void setLogototalproyectosiniciar(String logototalproyectosiniciar) {
        this.logototalproyectosiniciar = logototalproyectosiniciar;
    }

    public BigDecimal getTotalproyectosterminados() {
        return totalproyectosterminados;
    }

    public void setTotalproyectosterminados(BigDecimal totalproyectosterminados) {
        this.totalproyectosterminados = totalproyectosterminados;
    }

    public String getLogototalproyectosterminados() {
        return logototalproyectosterminados;
    }

    public void setLogototalproyectosterminados(String logototalproyectosterminados) {
        this.logototalproyectosterminados = logototalproyectosterminados;
    }

    public BigDecimal getCodigocategoria() {
        return codigocategoria;
    }

    public void setCodigocategoria(BigDecimal codigocategoria) {
        this.codigocategoria = codigocategoria;
    }

    public String getNombrecategoria() {
        return nombrecategoria;
    }

    public void setNombrecategoria(String nombrecategoria) {
        this.nombrecategoria = nombrecategoria;
    }

    public String getColorcategoria() {
        return colorcategoria;
    }

    public void setColorcategoria(String colorcategoria) {
        this.colorcategoria = colorcategoria;
    }

    public String getBotoncategoriainactivo() {
        return botoncategoriainactivo;
    }

    public void setBotoncategoriainactivo(String botoncategoriainactivo) {
        this.botoncategoriainactivo = botoncategoriainactivo;
    }

    public String getBotoncategoriaactivo() {
        return botoncategoriaactivo;
    }

    public void setBotoncategoriaactivo(String botoncategoriaactivo) {
        this.botoncategoriaactivo = botoncategoriaactivo;
    }
    
}
