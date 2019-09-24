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
    @NamedQuery(name = "Clima.findAll", query = "SELECT c FROM Clima c order by c.estacao"),
    @NamedQuery(name = "Clima.findById", query = "SELECT c FROM Clima c WHERE c.id = :id"),
    @NamedQuery(name = "Clima.findByEstacao", query = "SELECT c FROM Clima c WHERE c.estacao = :estacao"),
    @NamedQuery(name = "Clima.findByDia", query = "SELECT c FROM Clima c WHERE c.dia = :dia"),
    @NamedQuery(name = "Clima.findByHora", query = "SELECT c FROM Clima c WHERE c.hora = :hora"),
    @NamedQuery(name = "Clima.findByPrecipitacao", query = "SELECT c FROM Clima c WHERE c.precipitacao = :precipitacao"),
    @NamedQuery(name = "Clima.findByTempbulboseco", query = "SELECT c FROM Clima c WHERE c.tempbulboseco = :tempbulboseco"),
    @NamedQuery(name = "Clima.findByTempbulboumido", query = "SELECT c FROM Clima c WHERE c.tempbulboumido = :tempbulboumido"),
    @NamedQuery(name = "Clima.findByTempmaxima", query = "SELECT c FROM Clima c WHERE c.tempmaxima = :tempmaxima"),
    @NamedQuery(name = "Clima.findByTempminima", query = "SELECT c FROM Clima c WHERE c.tempminima = :tempminima"),
    @NamedQuery(name = "Clima.findByUmidaderelativa", query = "SELECT c FROM Clima c WHERE c.umidaderelativa = :umidaderelativa"),
    @NamedQuery(name = "Clima.findByPressaoatmestacao", query = "SELECT c FROM Clima c WHERE c.pressaoatmestacao = :pressaoatmestacao"),
    @NamedQuery(name = "Clima.findByPressaoatmmar", query = "SELECT c FROM Clima c WHERE c.pressaoatmmar = :pressaoatmmar"),
    @NamedQuery(name = "Clima.findByDirecaovento", query = "SELECT c FROM Clima c WHERE c.direcaovento = :direcaovento"),
    @NamedQuery(name = "Clima.findByVelocidadevento", query = "SELECT c FROM Clima c WHERE c.velocidadevento = :velocidadevento"),
    @NamedQuery(name = "Clima.findByInsolacao", query = "SELECT c FROM Clima c WHERE c.insolacao = :insolacao"),
    @NamedQuery(name = "Clima.findByNebulosidade", query = "SELECT c FROM Clima c WHERE c.nebulosidade = :nebulosidade"),
    @NamedQuery(name = "Clima.findByEvaporacaopiche", query = "SELECT c FROM Clima c WHERE c.evaporacaopiche = :evaporacaopiche"),
    @NamedQuery(name = "Clima.findByTempcompmedia", query = "SELECT c FROM Clima c WHERE c.tempcompmedia = :tempcompmedia"),
    @NamedQuery(name = "Clima.findByUmidaderelativamedia", query = "SELECT c FROM Clima c WHERE c.umidaderelativamedia = :umidaderelativamedia"),
    @NamedQuery(name = "Clima.findByVelocidadeventomedia", query = "SELECT c FROM Clima c WHERE c.velocidadeventomedia = :velocidadeventomedia")})
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

    @Column(precision = 17, scale = 17)
    private Double precipitacao;
    @Column(precision = 17, scale = 17)
    private Double tempbulboseco;
    @Column(precision = 17, scale = 17)
    private Double tempbulboumido;
    @Column(precision = 17, scale = 17)
    private Double tempmaxima;
    @Column(precision = 17, scale = 17)
    private Double tempminima;
    @Column(precision = 17, scale = 17)
    private Double umidaderelativa;
    @Column(precision = 17, scale = 17)
    private Double pressaoatmestacao;
    @Column(precision = 17, scale = 17)
    private Double pressaoatmmar;
    @Column(precision = 17, scale = 17)
    private Double direcaovento;
    @Column(precision = 17, scale = 17)
    private Double velocidadevento;
    @Column(precision = 17, scale = 17)
    private Double insolacao;
    @Column(precision = 17, scale = 17)
    private Double nebulosidade;
    @Column(precision = 17, scale = 17)
    private Double evaporacaopiche;
    @Column(precision = 17, scale = 17)
    private Double tempcompmedia;
    @Column(precision = 17, scale = 17)
    private Double umidaderelativamedia;
    @Column(precision = 17, scale = 17)
    private Double velocidadeventomedia;

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

    public Double getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(Double precipitacao) {
        this.precipitacao = precipitacao;
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

    public Double getTempmaxima() {
        return tempmaxima;
    }

    public void setTempmaxima(Double tempmaxima) {
        this.tempmaxima = tempmaxima;
    }

    public Double getTempminima() {
        return tempminima;
    }

    public void setTempminima(Double tempminima) {
        this.tempminima = tempminima;
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

    public Double getPressaoatmmar() {
        return pressaoatmmar;
    }

    public void setPressaoatmmar(Double pressaoatmmar) {
        this.pressaoatmmar = pressaoatmmar;
    }

    public Double getDirecaovento() {
        return direcaovento;
    }

    public void setDirecaovento(Double direcaovento) {
        this.direcaovento = direcaovento;
    }

    public Double getVelocidadevento() {
        return velocidadevento;
    }

    public void setVelocidadevento(Double velocidadevento) {
        this.velocidadevento = velocidadevento;
    }

    public Double getInsolacao() {
        return insolacao;
    }

    public void setInsolacao(Double insolacao) {
        this.insolacao = insolacao;
    }

    public Double getNebulosidade() {
        return nebulosidade;
    }

    public void setNebulosidade(Double nebulosidade) {
        this.nebulosidade = nebulosidade;
    }

    public Double getEvaporacaopiche() {
        return evaporacaopiche;
    }

    public void setEvaporacaopiche(Double evaporacaopiche) {
        this.evaporacaopiche = evaporacaopiche;
    }

    public Double getTempcompmedia() {
        return tempcompmedia;
    }

    public void setTempcompmedia(Double tempcompmedia) {
        this.tempcompmedia = tempcompmedia;
    }

    public Double getUmidaderelativamedia() {
        return umidaderelativamedia;
    }

    public void setUmidaderelativamedia(Double umidaderelativamedia) {
        this.umidaderelativamedia = umidaderelativamedia;
    }

    public Double getVelocidadeventomedia() {
        return velocidadeventomedia;
    }

    public void setVelocidadeventomedia(Double velocidadeventomedia) {
        this.velocidadeventomedia = velocidadeventomedia;
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
        return "Clima{" + "id=" + id + ", estacao=" + estacao + ", dia=" + dia + ", hora=" + hora + ", precipitacao=" + precipitacao + ", tempbulboseco=" + tempbulboseco + ", tempbulboumido=" + tempbulboumido + ", tempmaxima=" + tempmaxima + ", tempminima=" + tempminima + ", umidaderelativa=" + umidaderelativa + ", pressaoatmestacao=" + pressaoatmestacao + ", pressaoatmmar=" + pressaoatmmar + ", direcaovento=" + direcaovento + ", velocidadevento=" + velocidadevento + ", insolacao=" + insolacao + ", nebulosidade=" + nebulosidade + ", evaporacaopiche=" + evaporacaopiche + ", tempcompmedia=" + tempcompmedia + ", umidaderelativamedia=" + umidaderelativamedia + ", velocidadeventomedia=" + velocidadeventomedia + '}';
    }

}
