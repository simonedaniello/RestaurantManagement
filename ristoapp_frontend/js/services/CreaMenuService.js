myApp.service('creaMenuService', function() {

    var pietanzeList = [
        {nome:"Carbonara",                   etichette:["Cristiano"], prezzo:18,
            ingredienti:[{nome:"uovo", quantita:15}, {nome:"pasta", quantita:15}, {nome:"guanciale", quantita:15}]},
        {nome:"Amatriciana",                 etichette:["Carne"], prezzo:18,
            ingredienti:[{nome:"sugo", quantita:15}, {nome:"pasta", quantita:15}, {nome:"olio", quantita:15}]},
        {nome:"Pasta con lo scoglio",        etichette:["Pesce"], prezzo:18,
            ingredienti:[{nome:"scoglio", quantita:15}, {nome:"pasta", quantita:15}, {nome:"acqua marina", quantita:15}, {nome:"sale marino", quantita:15}, {nome:"nemo", quantita:15}]},
        {nome:"Spaghetti agli scampi",       etichette:["Pesce"], prezzo:18,
            ingredienti:[{nome:"spaghetti", quantita:15}, {nome:"scampi", quantita:15}]},
        {nome:"Lasagne",                     etichette:[], prezzo:18,
            ingredienti:[{nome:"sfoglia", quantita:15}, {nome:"ragu", quantita:15}, {nome:"mozzarella", quantita:15}]},
        {nome:"Krapfen",                     etichette:[], prezzo:18,
            ingredienti:[{nome:"crema pasticcera", quantita:15}, {nome:"bo", quantita:15}, {nome:"altro", quantita:15}]},
        {nome:"Purea di patate",             etichette:[], prezzo:18 ,
            ingredienti:[{nome:"patate", quantita:15}, {nome:"latte", quantita:15}, {nome:"formaggio", quantita:15}]},
        {nome:"Banana Split",                etichette:["Vegano"], prezzo:18,
            ingredienti:[{nome:"banana", quantita:15}, {nome:"cioccolato", quantita:15}]},
        {nome:"Bollito di asparagi",         etichette:["Vegano", "Vegetariano"], prezzo:18,
            ingredienti:[{nome:"asparagi", quantita:15}, {nome:"spezie varie", quantita:15}]},
        {nome:"Spremuta di arance",          etichette:["Vegano", "Vegetariano"], prezzo:18,
            ingredienti:[{nome:"arancia", quantita:15}]},
        {nome:"Pollo alla romana",           etichette:["Piccante", "Riso", "Carne"], prezzo:18,
            ingredienti:[{nome:"pollo", quantita:15}, {nome:"roma", quantita:15}, {nome:"romana", quantita:15}]}
    ];


    var getPietanze = function(){
        return pietanzeList;
    };

    return {
        getPietanze: getPietanze
    };

});