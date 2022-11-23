package com.currencyrate.cbr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValCurs {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Valute")
    private List<CurrencyRate> valute = new ArrayList<>();
}
