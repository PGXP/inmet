/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.serpro.googlecep.entity;

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
 * @author 70744416353
 */
@Entity
@Table(catalog = "geo", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estacoes.findAll", query = "SELECT e FROM Estacoes e"),
    @NamedQuery(name = "Estacoes.findByCodigo", query = "SELECT e FROM Estacoes e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Estacoes.findByNome", query = "SELECT e FROM Estacoes e WHERE e.nome = :nome"),
    @NamedQuery(name = "Estacoes.findByLatitude", query = "SELECT e FROM Estacoes e WHERE e.latitude = :latitude"),
    @NamedQuery(name = "Estacoes.findByLongitude", query = "SELECT e FROM Estacoes e WHERE e.longitude = :longitude"),
    @NamedQuery(name = "Estacoes.findByAltitude", query = "SELECT e FROM Estacoes e WHERE e.altitude = :altitude"),
    @NamedQuery(name = "Estacoes.findById", query = "SELECT e FROM Estacoes e WHERE e.id = :id")})
public class Estacoes implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer codigo;
    @Column(length = 64)
    private String nome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 17, scale = 17)
    private Double latitude;
    @Column(precision = 17, scale = 17)
    private Double longitude;
    private Integer altitude;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;

    public Estacoes() {
    }

    public Estacoes(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estacoes)) {
            return false;
        }
        Estacoes other = (Estacoes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.serpro.googlecep.entity.Estacoes[ id=" + id + " ]";
    }
    
}
