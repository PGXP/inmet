package br.gov.serpro.googlecep;

import br.gov.serpro.googlecep.dao.ClimaJpaController;
import br.gov.serpro.googlecep.dao.EstacoesJpaController;
import br.gov.serpro.googlecep.runner.ClimaRunner;
import static java.lang.Runtime.getRuntime;
import static java.time.Duration.between;
import java.time.Instant;
import static java.time.Instant.now;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.MINUTES;
import java.util.logging.Level;
import static java.util.logging.Level.INFO;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 70744416353
 */
public class Treinar {

    private static final Logger LOG = getLogger(Treinar.class.getName());
    private static final int MAX_THREADS = getRuntime().availableProcessors();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LOG.log(INFO, "Clima init with {0} processors", MAX_THREADS);
        Instant start = now();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        ExecutorService executorGerador = newFixedThreadPool(MAX_THREADS);
        WebCrawler gc = new WebCrawler();

        ClimaJpaController climadao = new ClimaJpaController(emf);
        EstacoesJpaController estacoesdao = new EstacoesJpaController(emf);

        estacoesdao.findEstacoesEntities().forEach((estacao) -> {
            try {
                gc.login("", "");
                Thread.sleep(3333);
                ClimaRunner ir = new ClimaRunner();
                ir.setClimadao(climadao);
                ir.setEstacoesdao(estacoesdao);
                ir.setGc(gc);
                ir.setEstacao(estacao);
                executorGerador.execute(ir);

            } catch (InterruptedException ex) {
                Logger.getLogger(Treinar.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        executorGerador.shutdown();

        try {
            executorGerador.awaitTermination(720, MINUTES);
        } catch (InterruptedException ie) {
            LOG.severe(ie.getLocalizedMessage());
        }

//        emf.close();
        Instant finish = now();
        LOG.log(INFO, "Clima {0} seg", new Object[]{between(start, finish).getSeconds()});
    }

}
