package br.gov.serpro.googlecep;

import br.gov.serpro.googlecep.runner.ClimaRunner;
import static java.lang.Runtime.getRuntime;
import static java.time.Duration.between;
import java.time.Instant;
import static java.time.Instant.now;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.logging.Level.INFO;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

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
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        ExecutorService executorGerador = newFixedThreadPool(1);
        WebCrawler gc = new WebCrawler();

        //ClimaJpaController climadao = new ClimaJpaController(emf);
        //EstacoesJpaController estacoesdao = new EstacoesJpaController(emf);
        ClimaRunner ir = new ClimaRunner();
        ir.setClimadao(null);
        ir.setEstacoesdao(null);
        ir.setGc(gc);
        ir.setEstacao(null);
        executorGerador.execute(ir);

//        estacoesdao.findEstacoesEntities(1, 1).forEach((estacao) -> {
//            try {
//
//                ClimaRunner ir = new ClimaRunner();
//                ir.setClimadao(climadao);
//                ir.setEstacoesdao(estacoesdao);
//                ir.setGc(gc);
//                ir.setEstacao(estacao);
//                executorGerador.execute(ir);
//
//                Thread.sleep(333);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Treinar.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
        executorGerador.shutdown();

        try {
            executorGerador.awaitTermination(720, MINUTES);
        } catch (InterruptedException ie) {
            LOG.severe(ie.getLocalizedMessage());
        }

        // emf.close();
        Instant finish = now();
        LOG.log(INFO, "Clima {0} seg", new Object[]{between(start, finish).getSeconds()});
    }

}
