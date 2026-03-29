package br.com.fiap.myday.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class QuoteService {

    public String getQuote() {
        RestTemplate rest = new RestTemplate();

        String url = "https://motivacional.top/api.php?acao=aleatoria";

        Map response = rest.getForObject(url, Map.class);

        List dados = (List) response.get("dados");
        Map frase = (Map) dados.get(0);

        return (String) frase.get("frase");
    }
}
