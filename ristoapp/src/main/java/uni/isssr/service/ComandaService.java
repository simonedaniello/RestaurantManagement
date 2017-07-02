package uni.isssr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isssr.dto.ComandaItemDto;
import uni.isssr.dto.ComandaOrderDto;
import uni.isssr.entities.ComandaItem;
import uni.isssr.entities.ComandaOrder;
import uni.isssr.repositories.ComandaOrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio on 30/06/2017.
 */
@Service
public class ComandaService {

    @Autowired
    private ComandaOrderRepository comandaOrderRepository;


    public void insertComanda(ComandaOrderDto comandaOrderDto) {
        ComandaOrder comandaOrderCurrent = this.comandaOrderDtoToComandaOrder(comandaOrderDto);

        int tavolo = comandaOrderCurrent.getTavolo();
        ComandaOrder comandaOrderOld = comandaOrderRepository.findComandaOrder(tavolo, true);
        if (comandaOrderOld != null) { /* C'è già una comanda attiva a quel tavolo */
            for (ComandaItem comandaItemCurrent : comandaOrderCurrent.getComandaItems()) {
                if (!updateQuantity(comandaItemCurrent, comandaOrderOld))
                    comandaOrderOld.addComandaItem(comandaItemCurrent);
            }
            comandaOrderRepository.save(comandaOrderOld);
        } else {
            comandaOrderRepository.save(comandaOrderCurrent);
        }
    }

    private boolean updateQuantity(ComandaItem comandaItemCurrent, ComandaOrder comandaOrderOld) {
        for (ComandaItem comandaItemOld : comandaOrderOld.getComandaItems()) {
            if (comandaItemCurrent.getNomePietanza().equals(comandaItemOld.getNomePietanza())) {
                comandaItemOld.setQuantita(comandaItemOld.getQuantita() + comandaItemCurrent.getQuantita());
                return true;
            }
        } return false;
    }

    // Passa lo stato da attivo a passivo (da true a false)
    public boolean updateComanda(int numero) {
        // Preleva l'ordinazione relativa a quel tavolo e che è attiva
        ComandaOrder comandaOrderDb = comandaOrderRepository.findComandaOrder(numero, true);
        if (comandaOrderDb != null) {
            comandaOrderDb.setActive(false); /* E' stato pagato il conto alla cassa */
            comandaOrderRepository.save(comandaOrderDb);
            return true;
        } return false;
    }

    /*
        Cerca la lista delle ordinazioni attive fatte da un tavolo cioè
        il conto associato è aperto e deve essere chiuso al pagamento
     */

    public List<ComandaItemDto> getComandaByTavolo(int numero) {
        List<ComandaItemDto> comandaItemDtos = new ArrayList<>();
        ComandaOrder comandaOrder = comandaOrderRepository.findComandaOrder(numero, true);
        if (comandaOrder == null)
            return comandaItemDtos;
        List<ComandaItem> comandaItems = comandaOrder.getComandaItems();
        for (ComandaItem comandaItem : comandaItems) {
            ComandaItemDto itemDto = this.comandaItemToComandaItemDto(comandaItem);
            comandaItemDtos.add(itemDto);
        }
        return comandaItemDtos;
    }

    private ComandaItemDto comandaItemToComandaItemDto(ComandaItem comandaItem) {
        ComandaItemDto comandaItemDto = new ComandaItemDto();
        comandaItemDto.setQuantita(comandaItem.getQuantita());
        comandaItemDto.setPietanza(comandaItem.getNomePietanza());
        comandaItemDto.setPrezzo(comandaItem.getPrezzoPietanza());
        return comandaItemDto;
    }

    private ComandaOrder comandaOrderDtoToComandaOrder(ComandaOrderDto comandaOrderDto) {
        ComandaOrder comandaOrder = new ComandaOrder();
        List<ComandaItem> comandaItems = new ArrayList<>();
        for (ComandaItemDto comandaItemDto : comandaOrderDto.getComandaItems()) {
            ComandaItem comandaItem = this.comandaItemDtoToComandaItem(comandaItemDto);
            comandaItems.add(comandaItem);
        }
        comandaOrder.setComandaItems(comandaItems);
        comandaOrder.setTavolo(comandaOrderDto.getTavolo());
        comandaOrder.setActive(true);
        return comandaOrder;
    }


    private ComandaItem comandaItemDtoToComandaItem(ComandaItemDto comandaDto) {
        ComandaItem comanda = new ComandaItem();
        comanda.setQuantita(comandaDto.getQuantita());
        comanda.setNomePietanza(comandaDto.getPietanza());
        comanda.setPrezzoPietanza(comandaDto.getPrezzo());
        return comanda;
    }

}
