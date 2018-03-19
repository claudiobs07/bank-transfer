package br.com.claudiobs.transacao.service.tax;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaxManager {

    @Autowired
    private List<TaxHandler> taxHandlers;


    public TaxHandler getTaxHandler(long daysToBankTransfer) {
        return null;
    }

}
