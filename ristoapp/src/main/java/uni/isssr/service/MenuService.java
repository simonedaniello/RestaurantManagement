package uni.isssr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isssr.dto.*;
import uni.isssr.entities.*;
import uni.isssr.entities.Menu;
import uni.isssr.repositories.MenuRepository;
import uni.isssr.repositories.PietanzaRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Antonio on 29/06/2017.
 */

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private PietanzaRepository pietanzaRepository;
    /*
        Search di tutti i menu presenti nel database
     */

    public List<MenuSearchDto> searchAllMenu() {
        List<MenuSearchDto> menuDtos = new ArrayList<>();

        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList)          //trasformazione dei Menu in MenuDto
            menuDtos.add(this.menuToMenuSearchDto(menu));

        return menuDtos;
    }

    private MenuSearchDto menuToMenuSearchDto(Menu menu) {
        MenuSearchDto menuSearchDto = new MenuSearchDto();
        menuSearchDto.setNome(menu.getNome());
        menuSearchDto.setDescrizione(menu.getDescrizione());
        menuSearchDto.setImmagine("imgFiles/menu_background.jpg"); /* Da cambiare !!! */
        Date date = menu.getDataCreazione();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = sdf.format(date);
        menuSearchDto.setData(stringDate);
        return menuSearchDto;
    }

    /*
        Search di un menù per nome, restituito in una lista per compatibilità
        con le altre search che restiuiscono una lista.
     */

    public List<MenuSearchDto> searchMenuByName(String nomeMenu) {
        List<MenuSearchDto> menuSearchDtos = new ArrayList<>();
        Menu menu = menuRepository.findOne(nomeMenu);
        if (menu == null)
            return menuSearchDtos;
        menuSearchDtos.add(this.menuToMenuSearchDto(menu));
        return menuSearchDtos;
    }

    /*
        Search dei menù che contengono un ingrediente specificato
     */

    public List<MenuSearchDto> searchMenuByIngrediente(String nomeIngrediente) {
        List<MenuSearchDto> menuDtos = new ArrayList<>();

        List<Menu> menuList = menuRepository.findMenuByIngrediente(nomeIngrediente);
        for (Menu menu : menuList)
            menuDtos.add(this.menuToMenuSearchDto(menu));

        return menuDtos;
    }

    /*
        Search dei menù che contengono un'etichetta specificata
     */

    public List<MenuSearchDto> searchMenuByEtichetta(String nomeEtichetta) {
        List<MenuSearchDto> menuDtos = new ArrayList<>();

        List<Menu> menuList = menuRepository.findMenuByEtichetta(nomeEtichetta);
        for (Menu menu : menuList)
            menuDtos.add(this.menuToMenuSearchDto(menu));

        return menuDtos;
    }

    /*
        Search del singolo menu per nome per visualizzare i dettagli
     */

    public MenuItemDto searchMenu(String nomeMenu) {
        MenuItemDto menuItemDto = new MenuItemDto();
        Menu menu = menuRepository.findOne(nomeMenu);
        if (menu == null)
            return menuItemDto;
        return this.menuToMenuItemDto(menu);
    }

    private MenuItemDto menuToMenuItemDto(Menu menu) {
        MenuItemDto menuItemDto = new MenuItemDto();
        menuItemDto.setNomeMenu(menu.getNome());
        menuItemDto.setImmagineMenu("imgFiles/menu_background.jpg");
        List<CategoriaDto> categorie = new ArrayList<>();
        for (Categoria categoria : menu.getCategorie())
            categorie.add(this.categoriaToCategoriaDto(categoria));
        menuItemDto.setCategorie(categorie);
        return menuItemDto;
    }

    private CategoriaDto categoriaToCategoriaDto(Categoria categoria) {
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setNomeCategoria(categoria.getNome());
        List<PietanzaDto> pietanze = new ArrayList<>();
        for (Pietanza pietanza : categoria.getPietanze())
            pietanze.add(this.pietanzaToPietanzaDto(pietanza));
        categoriaDto.setPietanze(pietanze);
        return categoriaDto;
    }

    private PietanzaDto pietanzaToPietanzaDto(Pietanza pietanza) {
        PietanzaDto pietanzaDto = new PietanzaDto();
        pietanzaDto.setNome(pietanza.getNome());
        pietanzaDto.setPrezzo(pietanza.getPrezzo());
        List<String> etichette = new ArrayList<>();
        for (Etichetta etichetta : pietanza.getEtichette())
            etichette.add(etichetta.getClassificatore());
        List<IngredienteDto> ingredienti = new ArrayList<>();
        for (Ingrediente ingrediente : pietanza.getIngredienti())
            ingredienti.add(this.ingredienteToIngredienteDto(ingrediente));
        pietanzaDto.setEtichette(etichette);
        pietanzaDto.setIngredienti(ingredienti);
        return pietanzaDto;
    }

    public IngredienteDto ingredienteToIngredienteDto(Ingrediente ingrediente) {
        IngredienteDto ingredienteDto = new IngredienteDto();
        ingredienteDto.setNome(ingrediente.getProdotto().getNome());
        ingredienteDto.setProdottoId(ingrediente.getProdotto().getId());
        ingredienteDto.setQuantita(ingrediente.getQuantita());
        return ingredienteDto;
    }



    //salvataggio del menu
    private Menu unmarshallMenuDto(MenuDto menuDto){
        Menu menu = new Menu(menuDto.getNome(), menuDto.getDescrizione());
        menu.setIsActive(menuDto.getIsActive());
        List<Categoria> categorie = new ArrayList<>();
        Integer posizioneCategoria = new Integer(1);
        for (CategoriaMenuDto categoriaDto:menuDto.getCategorie()) {
            Categoria categoria = new Categoria(categoriaDto.getNomeCategoria());
            categoria.setPosizione(posizioneCategoria);
            posizioneCategoria+=1;
            List<Long> pietanzeId = new ArrayList<>();
            for (PietanzaMenuDto p:categoriaDto.getPietanze()) {
                PietanzaMenuDto pietanzaMenuDto = (PietanzaMenuDto)p;
                pietanzeId.add(pietanzaMenuDto.getId());
            }
            categoria.setPietanze(pietanzaRepository.findAll(pietanzeId));
            categorie.add(categoria);
        }
        menu.setCategorie(categorie);
        return menu;
    }

    public List<CategoriaMenuDto> searchMenuAttivo() {
        Menu menuAttivo = menuRepository.findOneByIsActive(true);
        List<CategoriaMenuDto> categoriaMenuDtos = new ArrayList<>();
        if (menuAttivo == null){
            return categoriaMenuDtos;
        }
        for (Categoria categoria : menuAttivo.getCategorie()) {
            CategoriaMenuDto categoriaMenuDto = new CategoriaMenuDto();
            categoriaMenuDto.setNomeCategoria(categoria.getNome());
            List<PietanzaMenuDto> pietanzaMenuDtos = new ArrayList<>();
            for (Pietanza pietanza : categoria.getPietanze()) {
                List<IngredienteDto> ingredientiDtos = new ArrayList<>();
                for (Ingrediente ingrediente : pietanza.getIngredienti()) {
                    IngredienteDto ingredienteDto = new IngredienteDto(ingrediente.getProdotto().getNome(), ingrediente.getQuantita(), ingrediente.getId());
                    ingredientiDtos.add(ingredienteDto);
                }
                    PietanzaMenuDto pietanzaMenuDto = new PietanzaMenuDto(pietanza.getId(), pietanza.getNome(), pietanza.getPrezzo(), ingredientiDtos);
                pietanzaMenuDtos.add(pietanzaMenuDto);
            }
            categoriaMenuDto.setPietanze(pietanzaMenuDtos);
            categoriaMenuDto.setPosizione(categoria.getPosizione());

            categoriaMenuDtos.add(categoriaMenuDto);
        }
        return categoriaMenuDtos;

    }


    public void saveMenu(MenuDto menuDto){
        Menu menu = this.unmarshallMenuDto(menuDto);
        if (menu.getIsActive()) {
            this.disattivaMenu();
        }
        menuRepository.save(menu);
    }

    //elimina il menu col nome dato
    public void deleteMenu(String nome){
        menuRepository.delete(nome);
    }


    //rende attivo il menu (ne setta l'attributo a true) il cui nome è passato per parametro e rende inattivo il menu correntemente attivo se presente
    public void attivaMenu(String nome){
        this.disattivaMenu();
        Menu menuAttivo= menuRepository.findOne(nome);
        menuAttivo.setIsActive(true);
        menuRepository.save(menuAttivo);
    }


    //disattiva il menu attivo
    private void disattivaMenu(){
        Menu menuAttivo = menuRepository.findOneByIsActive(true);
        if (menuAttivo != null){
            menuAttivo.setIsActive(false);
            menuRepository.save(menuAttivo);
        }
    }

    //per dettagli vedi findByNAme
    public List<MenuSearchDto> getMenuAttivo(){
        Menu menu = menuRepository.findOneByIsActive(true);
        List<MenuSearchDto> menuSearchDtos = new ArrayList<MenuSearchDto>();
        if (menu == null)
            return menuSearchDtos;
        menuSearchDtos.add(this.menuToMenuSearchDto(menu));
        return menuSearchDtos;
    }

}
