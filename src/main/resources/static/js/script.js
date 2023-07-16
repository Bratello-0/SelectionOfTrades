var currentStattrak = -1;
var currentRarity = 0;
var currentTextInput = "";
var currentCase = "all";

var coeff_from = 0;
var coeff_to = 10;
var price_from = 0;
var price_to = 10000000;
var doubleFilter = false;

function doubleFilterSet() {
    doubleFilter = true;
}

function tradeFilter(tag, index) {
    if(currentRarity === index){
        return;
    }
    document.querySelectorAll('#allitems > .collection_all').forEach(function(node){
        node.style.display = 'none';
    });
    if(currentRarity === 0){
        currentRarity = index;
        document.querySelector("#trade_selector_rarities > .selector_rarity_"+currentRarity).classList.toggle('disabled');
        return;
    }
    if(tag === 'stattrak'){
        document.querySelector('#trade_selector_stattrak > .selector_stattrak').classList.toggle('disabled');
        if(currentStattrak == 0){
            currentStattrak = 1;
        }else{
            currentStattrak = 0;
        }
        setNullItems(this);
        return;
    }
    if(tag === 'rarity'){
        document.querySelector("#trade_selector_rarities > .selector_rarity_"+currentRarity).classList.toggle('disabled');

        var elementRarity = document.querySelector("#trade_selector_rarities > .selector_rarity_"+index);
        elementRarity.classList.toggle('disabled');

        currentRarity = index;
        setNullItems(this);
        return;
    }
    if(tag === 'search'){
        currentTextInput = index;
        setNullItems(this);
    }
    if(tag === 'collection'){
        currentCase = index;
        setNullItems(this);
    }
    if(tag === 'price_to'){
        if(index != null){
            price_to = parseFloat(index);
        }else{
            price_to = 1000000.0;
        }
        setNullItems(this);
    }
    if(tag === 'price_from'){
        if(index != null){
            price_from = parseFloat(index);
        }else{
            price_from = 0.0;
        }
        setNullItems(this);
    }
    if(tag === 'coeff_to'){
        if(index != null){
            coeff_to = parseFloat(index);
        }else{
            coeff_to = 10.0;
        }
        setNullItems(this);
    }
    if(tag === 'coeff_from'){
        if(index != null){
            coeff_from = parseFloat(index);
        }else{
            coeff_from = 0.0;
        }
        setNullItems(this);
    }
}
function setNullItems(data){
    var nodes = document.querySelectorAll('#allitems > div.rarity_'+data.currentRarity+'.stattrak_'
     + data.currentStattrak+'.collection_' + data.currentCase);
    var count = 0;
    if(nodes.length == 0){
        document.querySelector('#noItems').style.display = null;
    }
    else{
        nodes.forEach(function(node) {
            if(doubleFilter){
                var price = parseFloat(node.getAttribute("data-price").replace(',','.'));
                var coeff = parseFloat(node.getAttribute("data-coeff").replace(',','.'));
                if((price_from <= price && price <= price_to) && (coeff_from <= coeff && coeff <= coeff_to)){
                    if(node.getAttribute("data-skin").toLowerCase().includes(data.currentTextInput.toLowerCase())){
                        node.style.display = null;
                        count++;
                    }
                }
            }
            else{
                if(node.getAttribute("data-skin").toLowerCase().includes(data.currentTextInput.toLowerCase())){
                        node.style.display = null;
                        count++;
                    }
            }
        });
        if(count == 0){
            document.querySelector('#noItems').style.display = null;
        }
        else{
            document.querySelector('#noItems').style.display = 'none';
        }
    }
}