/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.serpro.googlecep.runner;

import br.gov.serpro.googlecep.Treinar;
import br.gov.serpro.googlecep.WebCrawler;

import br.gov.serpro.googlecep.dao.ClimaJpaController;
import br.gov.serpro.googlecep.dao.EstacoesJpaController;
import br.gov.serpro.googlecep.entity.Clima;
import br.gov.serpro.googlecep.entity.Estacoes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static java.time.Duration.between;
import java.time.Instant;
import static java.time.Instant.now;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Serpro
 */
public class ClimaRunner implements Runnable {

    private static final Logger LOG = getLogger(ClimaRunner.class.getName());

    private WebCrawler gc;
    private ClimaJpaController climadao;
    private EstacoesJpaController estacoesdao;
    private Estacoes estacao;

    /**
     *
     */
    @Override
    public void run() {
        try {
            Instant start = now();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            List<String> lista = gc.getSerieClima(estacao.getCodigo().toString());
            //List<String> lista = gc.getSerieClima("83096");

            try {
                //processa(endereco);
            } catch (Exception e) {
                getLogger(ClimaRunner.class.getName()).log(SEVERE, "", e);
            }

            for (int i = 50; i < lista.size() - 1; i++) {
                String[] linha = lista.get(i).split(";");

                Clima clima = new Clima();
                clima.setEstacao(Integer.valueOf(linha[0]));
                clima.setDia((Date) formatter.parse(linha[1]));
                clima.setHora(linha[2]);
                clima.setTempbulboseco(linha[3].isEmpty() ? null : Double.valueOf(linha[3]));
                clima.setTempbulboumido(linha[4].isEmpty() ? null : Double.valueOf(linha[4]));
                clima.setUmidaderelativa(linha[5].isEmpty() ? null : Double.valueOf(linha[5]));
                clima.setPressaoatmestacao(linha[6].isEmpty() ? null : Double.valueOf(linha[6]));
                clima.setDirecaovento(linha[7].isEmpty() ? null : Double.valueOf(linha[7]));
                clima.setVelocidadevento(linha[8].isEmpty() ? null : Double.valueOf(linha[8]));
                clima.setNebulosidade(linha[9].isEmpty() ? null : Double.valueOf(linha[9]));

                climadao.create(clima);
                System.out.println(clima.toString());

            }
            Instant finish = now();
            LOG.log(Level.INFO, "Work {0} -> {1} -> {2}", new Object[]{estacao.getCodigo().toString(), between(start, finish).getNano(), lista.size()});
        } catch (Exception ex) {
            Logger.getLogger(ClimaRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public WebCrawler getGc() {
        return gc;
    }

    public void setGc(WebCrawler gc) {
        this.gc = gc;
    }

    public ClimaJpaController getClimadao() {
        return climadao;
    }

    public void setClimadao(ClimaJpaController climadao) {
        this.climadao = climadao;
    }

    public EstacoesJpaController getEstacoesdao() {
        return estacoesdao;
    }

    public void setEstacoesdao(EstacoesJpaController estacoesdao) {
        this.estacoesdao = estacoesdao;
    }

    public Estacoes getEstacao() {
        return estacao;
    }

    public void setEstacao(Estacoes estacao) {
        this.estacao = estacao;
    }

}
