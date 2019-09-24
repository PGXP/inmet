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

            //List<String> lista = gc.getSerieClima(estacao.getCodigo().toString());
            List<String> lista = gc.getSerieClima("");

            try {
                //processa(endereco);
            } catch (Exception e) {
                getLogger(ClimaRunner.class.getName()).log(SEVERE, "", e);
            }

            for (int i = 49; i < lista.size() - 1; i++) {
                String[] linha = lista.get(i).split(";");

                Clima clima = new Clima();
                clima.setEstacao(Integer.valueOf(linha[0]));
                clima.setDia((Date) formatter.parse(linha[1]));
                clima.setHora(linha[2]);
                clima.setPrecipitacao(linha[3].isEmpty() ? null : Double.valueOf(linha[3]));
                clima.setTempbulboseco(linha[4].isEmpty() ? null : Double.valueOf(linha[4]));
                clima.setTempbulboumido(linha[5].isEmpty() ? null : Double.valueOf(linha[5]));
                clima.setTempmaxima(linha[6].isEmpty() ? null : Double.valueOf(linha[6]));
                clima.setTempminima(linha[7].isEmpty() ? null : Double.valueOf(linha[7]));
                System.out.println(clima.toString());

            }

            Instant finish = now();
            LOG.log(Level.INFO, "Work {0} -> {1}", new Object[]{between(start, finish).getNano(), lista.size()});
        } catch (Exception ex) {
            Logger.getLogger(ClimaRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processa(String endereco) {
        try {

//            Geocep geo = atualiza(results);
//
//            if (geo.getCep() != null && !cep.getCep().equalsIgnoreCase(geo.getCep())) {
//                LOG.log(Level.INFO, "Error : {0} -> {1} -> {2}", new Object[]{cep.getCep(), geo.getCep(), cep.getCidade()});
//            }
//
//            if (cep.getAtualizar() != null) {
//                cepdao.edit(cep);
//            }
        } catch (Exception ex) {
            Logger.getLogger(Treinar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private Geocep atualiza(GeocodingResult[] results) {
//        Geocep geo = new Geocep();
//        boolean br = false;
//        boolean uf = false;
//        boolean cidade = false;
//
//        if (results != null && results.length > 0) {
//
//            try {
//
//                for (AddressComponent addressComponent : results[0].addressComponents) {
//                    for (AddressComponentType type : addressComponent.types) {
//
//                        if (type.equals(AddressComponentType.POSTAL_CODE)) {
//                            geo.setGep(addressComponent.longName.replace("-", ""));
//                        }
//
//                        if (type.equals(AddressComponentType.STREET_NUMBER)) {
//                            geo.setNumero(addressComponent.longName);
//                        }
//
//                        if (type.equals(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1)) {
//                            if (addressComponent.shortName.equalsIgnoreCase(cep.getUf())) {
//                                uf = true;
//                            } else {
//                                LOG.log(Level.INFO, "Error Estado : {0} - {1}", new Object[]{addressComponent.shortName, cep.toString()});
//                            }
//                        }
//
//                        if (type.equals(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_2)) {
//                            Features muni = gc.getPointInMunicipio(results[0].geometry.location.lat, results[0].geometry.location.lng, cep.getCidade());
//                            String tem = muni.getFeatures().get(0).getProperties().getTags().get("PointInMunicipio");
//                            boolean latlng = Boolean.getBoolean(tem == null ? "false" : tem);
//                            if (addressComponent.shortName.equalsIgnoreCase(cep.getCidade()) && latlng) {
//                                cidade = true;
//                            } else {
//                                LOG.log(Level.INFO, "Error Cidade : {0} - {1} - {2}", new Object[]{latlng, addressComponent.shortName, cep.getCidade()});
//                            }
//                        }
//
//                        if (type.equals(AddressComponentType.COUNTRY)) {
//                            if (addressComponent.shortName.equalsIgnoreCase("BR")) {
//                                br = true;
//                            } else {
//                                LOG.log(Level.INFO, "Error Pais : {0} - {1}", new Object[]{addressComponent.shortName, cep.toString()});
//                            }
//                        }
//
//                    }
//                }
//
//                if (geo.getGep() == null || geo.getGep().isEmpty()) {
//                    geo.setGep(cep.getCep());
//                }
//
//                geo.setCep(cep.getCep());
//                geo.setLatitude(results[0].geometry.location.lat);
//                geo.setLongitude(results[0].geometry.location.lng);
//
//                cep.setAtualizar(new Date());
//
//                if (br && uf && cidade) {
//                    geodao.create(geo);
//                }
//
//            } catch (Exception ex) {
//                Logger.getLogger(CepGoogle.class
//                        .getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return geo;
//    }
//
//    private String formata(Cep cep) {
//        Random rd = new Random();
//        Integer numero = rd.nextInt((200 - 100) + 1) + 100;
//        StringBuilder sb = new StringBuilder();
//
//        sb.append(numero).append(" ");
//        sb.append(cep.getLogradouro()).append(", ");
//        sb.append(cep.getCidade()).append(", ");
//        sb.append(cep.getUf()).append(", ");
//        sb.append("Brasil");
//
//        return sb.toString().replace(" ", "+");
//    }
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
