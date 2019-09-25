package br.gov.serpro.googlecep;

import java.io.*;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebCrawler {

    private static String cookiesLocal;

    public void login(String usuario, String senha) {
        if (cookiesLocal == null) {

            try {

                URL url = new URL("http://www.inmet.gov.br/projetos/rede/pesquisa/inicio.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");

                conn.setInstanceFollowRedirects(true);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                String data = "mUsuario=&mGerModulo=&mCod=" + usuario + "&mSenha=" + senha + "&mGerModulo=PES&btnProcesso=+Acessar+";

                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(data);
                dos.flush();
                dos.close();

                String cookie = conn.getHeaderField("Set-Cookie");
                cookiesLocal = cookie.substring(0, cookie.indexOf(";"));
                System.out.println(cookie);

            } catch (MalformedURLException ex) {
                Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ProtocolException ex) {
                Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public List<String> getSerieClima(String estacao) throws Exception {

        String hostSent = "http://www.inmet.gov.br/projetos/rede/pesquisa/gera_serie_txt.php?&mRelEstacao=" + estacao + "&btnProcesso=serie&mRelDtInicio=01/01/1900&mRelDtFim=31/12/2019&mAtributos=1,1,,,1,1,,1,1,,,1,,,,,";
// 82294

        URL url = new URL(hostSent);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("User-Agent", "Demoiselle");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Cookie", cookiesLocal);
        connection.setDoOutput(true);
        connection.setUseCaches(true);
        connection.connect();

        List<String> response = new ArrayList<>();

        if (connection.getResponseCode() == 200) {

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.add(inputLine);
                }
            }
        }
        connection.disconnect();

        FileWriter writer = new FileWriter("/opt/inmet/" + estacao + ".txt");
        for (String str : response) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();

        return response;
    }

//    public List<String> getSerieClima(String estacao) throws Exception {
//        ArrayList<String> arr = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader("/opt/dados/83096.txt"))) {
//
//            String sCurrentLine;
//
//            while ((sCurrentLine = br.readLine()) != null) {
//                arr.add(sCurrentLine);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return arr;
//    }
}
