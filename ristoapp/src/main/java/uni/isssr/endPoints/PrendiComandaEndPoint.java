package uni.isssr.endPoints;


import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import uni.isssr.entities.Comanda;
import uni.isssr.repositories.ComandaRepository;

import java.util.List;

@RestController
@CrossOrigin

/*@RequestMapping(path = "/comanda")*/
public class PrendiComandaEndPoint {

/*    private final Logger log = (Logger) LoggerFactory.getLogger(this.getClass());*/
    @Autowired
    private ComandaRepository comandaRepository;


/*    @RequestMapping(method = RequestMethod.POST)
    public void addComanda(@RequestBody List<Comanda> listaComande){

        for (Comanda aListaComande : listaComande) {
            comandaRepository.save(aListaComande);
        }
    }*/


/*    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Comanda> getAllComande() {
        System.out.println("----------- comande : " + comandaRepository.findAll());
        Iterable<Comanda> c =  comandaRepository.findAll();
        comandaRepository.deleteAll();
        return c;
    }*/


/*------------------------------------------------------*/

    /*
        Se un messaggio viene inviato alla destinazione /nuovaComanda allora viene chiamato
        il metodo nuovaComanda(). Il payload del messaggio rappresenta un oggetto di tipo Comanda.

        Dopo aver processato la comanda, il metodo nuovaComanda() restituisce la comanda. Il valore di
        ritorno è mandato in broadcast a tutti i client che si sottoscrivono a /topic/comande

     */

    /*@MessageMapping("/nuoveComande")
    @SendTo("/topic/comande")
    public List<Comanda> nuoveComanda(List<Comanda> comande) throws Exception {
        comandaRepository.save(comande);
        return comande;
    }*/

}