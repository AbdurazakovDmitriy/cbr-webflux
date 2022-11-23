package com.currencyrate.cbr.service;

import com.currencyrate.cbr.mapper.RatesMapper;
import com.currencyrate.cbr.model.ValCurs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class CbrService {

    @Value("${cbr-daily.url}")
    private String cbrDaily;

    private final RatesMapper ratesMapper;

    @Cacheable("rates")
    public ValCurs getRates(String date) {
        String query = String.format("%s?data_req=%s", cbrDaily, date);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            // catch and swallow
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return ratesMapper.toValcursFromString(response.body());
    }

}
