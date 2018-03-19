package br.com.claudiobs.transacao.service.tax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaxManager {

    @Autowired
    private List<TaxHandler> taxHandlers;


    public TaxHandler getTaxHandler(Long daysToBankTransfer) {
        return null;
    }

}
