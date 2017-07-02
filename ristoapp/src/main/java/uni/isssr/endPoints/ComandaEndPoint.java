package uni.isssr.endPoints;


import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import uni.isssr.dto.ComandaItemDto;
import uni.isssr.dto.ComandaOrderDto;
import uni.isssr.service.ComandaService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ComandaEndPoint {

    @Autowired
    private ComandaService comandaService;

    /*
    @RequestMapping(value = "/comanda/addComanda", method = RequestMethod.POST)
    public void addComanda(@RequestBody ComandaOrderDto comandaOrderDto){
        comandaService.insertComanda(comandaOrderDto);
    }
    */

    @RequestMapping(value = "/comanda/tavolo/{numero}", method = RequestMethod.GET)
    public List<ComandaItemDto> getComandeAttive(@PathVariable Integer numero){
        return comandaService.getComandaByTavolo(numero);
    }

    @RequestMapping(value = "/comanda/updateComanda/{numeroTavolo}", method = RequestMethod.PUT)
    public boolean upComanda(@PathVariable Integer numeroTavolo){
        return comandaService.updateComanda(numeroTavolo);
    }



    /*  ------------------------- PUBLISH - SUBSCRIBE (STOMP OVER WEBSOCKET) ---------------------------
        Se un messaggio viene inviato alla destinazione /comanda/publishComanda allora viene chiamato
        il metodo nuovaComanda(). Il payload del messaggio è un oggetto di tipo ComandaOrderDto.
        Il valore di ritorno è mandato in broadcast a tutti i client che si sottoscrivono a
        /topic/comande
     */

    @MessageMapping("/comanda/publishComanda")
    @SendTo("/topic/comande")
    public ComandaOrderDto nuovaComanda(ComandaOrderDto comandaOrderDto) throws Exception {
        // Inserisce l'ordine nel database con lo stato a true di default cioè comanda attiva.
        comandaService.insertComanda(comandaOrderDto);
        // Restituisce l'ordine ai subscribers del topic in oggetto (ad esempio il cuoco)
        return comandaOrderDto;
    }

}