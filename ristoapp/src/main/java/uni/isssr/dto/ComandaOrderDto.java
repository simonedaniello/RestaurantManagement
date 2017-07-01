package uni.isssr.dto;

import java.util.List;

/**
 * Created by Antonio on 30/06/2017.
 */
public class ComandaOrderDto {

    private List<ComandaItemDto> comandaItems;
    private int tavolo;

    public ComandaOrderDto() {}

    public List<ComandaItemDto> getComandaItems() {
        return comandaItems;
    }

    public void setComandaItems(List<ComandaItemDto> comandaItems) {
        this.comandaItems = comandaItems;
    }

    public int getTavolo() {
        return tavolo;
    }

    public void setTavolo(int tavolo) {
        this.tavolo = tavolo;
    }
}
