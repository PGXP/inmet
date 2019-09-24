package br.gov.serpro.googlecep;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class WebCrawler {

//    public List<String> getSerieClima(String estacao) throws Exception {
//
//        String hostSent = "http://www.inmet.gov.br/projetos/rede/pesquisa/gera_serie_txt.php?&mRelEstacao="+estacao+"&btnProcesso=serie&mRelDtInicio=01/01/1900&mRelDtFim=19/09/2019&mAtributos=1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1";
//// 82294
//
//        URL url = new URL(hostSent);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Content-Type", "application/json");
//        connection.setRequestProperty("Accept", "application/json");
//        connection.setRequestProperty("User-Agent", "Demoiselle");
//        connection.setRequestProperty("charset", "utf-8");
//        //connection.setRequestProperty("Cookie", "__utmc=227935045; PHPSESSID=54vbqt7fm0cvsjki5d8dc1edt7; _ga=GA1.3.589124502.1568997472; __utmz=227935045.1569261006.4.3.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); __utma=227935045.589124502.1568997472.1569261006.1569319787.5");
//        connection.setDoOutput(true);
//        connection.setUseCaches(true);
//        connection.connect();
//
//        List<String> response = new ArrayList<>();
//
//        if (connection.getResponseCode() == 200) {
//
//            try (BufferedReader in = new BufferedReader(
//                    new InputStreamReader(connection.getInputStream()))) {
//                String inputLine;
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.add(inputLine);
//                }
//            }
//        }
//
//        connection.disconnect();
//
//        return response;
//    }
    @SuppressWarnings("empty-statement")
    public List<String> getSerieClima(String estacao) throws Exception {
        return FileUtils.readLines(new File("/opt/dados/83096.txt"), Charset.defaultCharset());
    }
}
