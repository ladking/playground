package com.currencyconverter.services;

import com.currencyconverter.dto.ConversionDTO;
import com.currencyconverter.repository.HistoryRepository;
import com.currencyconverter.utils.HttpUtils;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class ConversionService {
        private String exchangeRateBaseUrl;
        private HistoryRepository historyRepo;


        @Autowired
        public ConversionService(HistoryRepository hittoryRepo) {
            this.historyRepo = historyRepo;
            this.exchangeRateBaseUrl = "https://api.frankfurter.dev/v2/";
        }


        public List<Rates> getRates(String baseCurrency)throws Exception{
            HttpUtils.Response  resp = HttpUtils.get(Strings.format("%s?base=%s", exchangeRateBaseUrl, baseCurrency), Rates.class);
            if(resp.statusCode != 200){
                throw new Exception("exchange service down. please try again later");
            }
            return resp.body;
        }

        public ConversionDTO.conversionResponse convertCurrency(ConversionDTO.convertCurrency payload) throws Exception {
            try{
              List<Rates> rates = HttpUtils.get(Strings.format("%s", exchangeRateBaseUrl, payload.currency));
              Double rate = 0.00;
              for(Rates itm: rates){
                if(itm.Quote.equals(payload.targetCurrency)){
                    rate = itm.rate;
                    break;
                }   
              }
              Double conversion = payload.amount * rate;

              ConversionDTO.conversionResponse resp = new ConversionDTO.conversionResponse(
                payload.baseCurrency,
                payload.quoteCurrency,
                conversion,
                payload.amount,
                rate
               );
                return resp;
            }catch(Exception e){
                throw new Exception("server error");
            }
        }



        public record Rates(
            String date,
            String base,
            String Quote,
            Double rate
        ){}
}   