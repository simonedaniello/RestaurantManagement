myApp.service('creaMenuService', function() {

    var pietanzeList = [
        {Name:"Carbonara",                   Prodotti:["uovo", "pasta", "guanciale"],       Category:"Pasta",       Chef:"Cracco", Tags:["cristiano"], Prezzo:18},
        {Name:"Amatriciana",                 Prodotti:["sugo", "pasta", "olio"],            Category:"Pasta",       Chef:"Cracco", Tags:["carne"], Prezzo:18},
        {Name:"Pasta con lo scoglio",        Prodotti:["scoglio", "pasta", "acqua marina"], Category:"Pasta",       Chef:"Cracco", Tags:["pesce"], Prezzo:18 },
        {Name:"Spaghetti agli scampi",       Prodotti:["spaghetti", "scampi"],              Category:"Pasta",       Chef:"Cracco", Tags:["pesce"], Prezzo:18 },
        {Name:"Lasagne",                     Prodotti:["sfoglia", "ragu", "mozzarella"],    Category:"Primo",       Chef:"Cracco", Tags:[], Prezzo:18 },
        {Name:"Krapfen",                     Prodotti:["crema pasticcera", "bo", "altro"],  Category:"Dolce",       Chef:"Cracco", Tags:[], Prezzo:18 },
        {Name:"Purea di patate",             Prodotti:["patate", "latte", "formaggio"],     Category:"Secondo",     Chef:"Cracco", Tags:[], Prezzo:18 },
        {Name:"Banana Split",                Prodotti:["banana", "cioccolato"],             Category:"Dolce",       Chef:"Cracco", Tags:[], Prezzo:18 },
        {Name:"Bollito di asparagi",         Prodotti:["asparagi", "spezie varie"],         Category:"Secondo",     Chef:"Cracco", Tags:[], Prezzo:18 },
        {Name:"Spremuta di arance",          Prodotti:["arancia"],                          Category:"Bevanda",     Chef:"Cracco", Tags:["veagno", "vegetariano"], Prezzo:18 },
        {Name:"Pollo alla romana",           Prodotti:["pollo", "roma", "romana"],          Category:"Secondo",     Chef:"Cracco", Tags:["piccante", "riso", "carne"], Prezzo:18 }
    ];


    var getPietanze = function(){
        return pietanzeList;
    };

    var categorieList = ["Primo", "Secondo", "Dolce", "Vino Bianco", "Antipasto"];

    var getCategorieList = function(){
        return categorieList;
    };

    var TagList = ["piccante", "veagno", "vegetariano", "riso", "pesce", "carne", "cristiano", "alaha"];

    var getTagList = function(){
        return TagList;
    };

    return {
        getCategorieList: getCategorieList,
        getTagList:getTagList,
        getPietanze: getPietanze
    };

});