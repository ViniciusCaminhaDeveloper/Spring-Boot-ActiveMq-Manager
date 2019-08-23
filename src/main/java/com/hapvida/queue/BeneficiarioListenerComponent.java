package com.hapvida.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/queue")
public class BeneficiarioListenerComponent {

    @Autowired private JmsTemplate jmsTemplate;

    @JmsListener(destination = "queue.numeroBeneficiarios")
    public void onReceiverQueue(String str) {
        System.out.println( "Consumindo a fila para o Beneficiario : " +  str );
    }
    
    @PostMapping("/beneficiario")
    public String teste(@RequestBody String numeroBeneficiario) {
    	jmsTemplate.convertAndSend("queue.numeroBeneficiarios", "{numeroBeneficiario: " + numeroBeneficiario + "}");
	  
		return "Numero Beneficiario : " + numeroBeneficiario  + ", adicionado a fila";
    }



}
