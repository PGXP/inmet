/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.serpro.googlecep.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(catalog = "geo", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clima.findAll", query = "SELECT c FROM Clima c"),
    @NamedQuery(name = "Clima.findById", query = "SELECT c FROM Clima c WHERE c.id = :id"),
    @NamedQuery(name = "Clima.findByEstacao", query = "SELECT c FROM Clima c WHERE c.estacao = :estacao"),
    @NamedQuery(name = "Clima.findByDia", query = "SELECT c FROM Clima c WHERE c.dia = :dia"),
    @NamedQuery(name = "Clima.findByHora", query = "SELECT c FROM Clima c WHERE c.hora = :hora"),
    @NamedQuery(name = "Clima.findByTempbulboseco", query = "SELECT c FROM Clima c WHERE c.tempbulboseco = :tempbulboseco"),
    @NamedQuery(name = "Clima.findByTempbulboumido", query = "SELECT c FROM Clima c WHERE c.tempbulboumido = :tempbulboumido"),
    @NamedQuery(name = "Clima.findByUmidaderelativa", query = "SELECT c FROM Clima c WHERE c.umidaderelativa = :umidaderelativa"),
    @NamedQuery(name = "Clima.findByPressaoatmestacao", query = "SELECT c FROM Clima c WHERE c.pressaoatmestacao = :pressaoatmestacao"),
    @NamedQuery(name = "Clima.findByDirecaovento", query = "SELECT c FROM Clima c WHERE c.direcaovento = :direcaovento"),
    @NamedQuery(name = "Clima.findByVelocidadeventonebulosidade", query = "SELECT c FROM Clima c WHERE c.velocidadeventonebulosidade = :velocidadeventonebulosidade")})
public class Clima implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private Integer estacao;
    @Temporal(TemporalType.DATE)
    private Date dia;
    @Column(length = 4)
    private String hora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 17, scale = 17)
    private Double tempbulboseco;
    @Column(precision = 17, scale = 17)
    private Double tempbulboumido;
    @Column(precision = 17, scale = 17)
    private Double umidaderelativa;
    @Column(precision = 17, scale = 17)
    private Double pressaoatmestacao;
    @Column(precision = 17, scale = 17)
    private Double direcaovento;
    @Column(precision = 17, scale = 17)
    private Double velocidadeventonebulosidade;

    public Clima() {
    }

    public Clima(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstacao() {
        return estacao;
    }

    public void setEstacao(Integer estacao) {
        this.estacao = estacao;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Double getTempbulboseco() {
        return tempbulboseco;
    }

    public void setTempbulboseco(Double tempbulboseco) {
        this.tempbulboseco = tempbulboseco;
    }

    public Double getTempbulboumido() {
        return tempbulboumido;
    }

    public void setTempbulboumido(Double tempbulboumido) {
        this.tempbulboumido = tempbulboumido;
    }

    public Double getUmidaderelativa() {
        return umidaderelativa;
    }

    public void setUmidaderelativa(Double umidaderelativa) {
        this.umidaderelativa = umidaderelativa;
    }

    public Double getPressaoatmestacao() {
        return pressaoatmestacao;
    }

    public void setPressaoatmestacao(Double pressaoatmestacao) {
        this.pressaoatmestacao = pressaoatmestacao;
    }

    public Double getDirecaovento() {
        return direcaovento;
    }

    public void setDirecaovento(Double direcaovento) {
        this.direcaovento = direcaovento;
    }

    public Double getVelocidadeventonebulosidade() {
        return velocidadeventonebulosidade;
    }

    public void setVelocidadeventonebulosidade(Double velocidadeventonebulosidade) {
        this.velocidadeventonebulosidade = velocidadeventonebulosidade;
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
        if (!(object instanceof Clima)) {
            return false;
        }
        Clima other = (Clima) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.serpro.googlecep.entity.Clima[ id=" + id + " ]";
    }
    
}
