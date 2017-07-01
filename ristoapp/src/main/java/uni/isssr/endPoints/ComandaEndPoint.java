package uni.isssr.endPoints;


import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import uni.isssr.dto.ComandaItemDto;
import uni.isssr.dto.ComandaOrderDto;
import uni.isssr.service.ComandaService;
import java.util.List;

@RestController
@CrossOrigin
public class ComandaEndPoint {

    @Autowired
    private ComandaService comandaService;


    @RequestMapping(value = "/comanda/addComanda", method = RequestMethod.POST)
    public void addComanda(@RequestBody ComandaOrderDto comandaOrderDto){
        comandaService.insertComanda(comandaOrderDto);
    }

    @RequestMapping(value = "/comanda/tavolo/{numero}", method = RequestMethod.GET)
    public List<ComandaItemDto> getComandeAttive(@PathVariable Integer numero){
        return comandaService.getComandaByTavolo(numero);
    }

    @RequestMapping(value = "/comanda/updateComanda/{numeroTavolo}", method = RequestMethod.PUT)
    public void upComanda(@PathVariable Integer numeroTavolo){
        comandaService.updateComanda(numeroTavolo);
    }

    @RequestMapping(value = "/comanda/tavolo/findAllAttivi", method = RequestMethod.GET)
    public List<Integer> findTavoliAttivi() {
        return comandaService.getComandeAttive();
    }



    /*
        Se un messaggio viene inviato alla destinazione /nuovaComanda allora viene chiamato
        il metodo nuovaComanda(). Il payload del messaggio rappresenta un oggetto di tipo ComandaItem.

        Dopo aver processato la comanda, il metodo nuovaComanda() restituisce la comanda. Il valore di
        ritorno è mandato in broadcast a tutti i client che si sottoscrivono a /topic/comande

     */

    /*@MessageMapping("/nuoveComande")
    @SendTo("/topic/comande")
    public List<ComandaItem> nuoveComanda(List<ComandaItem> comande) throws Exception {
        comandaRepository.save(comande);
        return comande;
    }*/

}