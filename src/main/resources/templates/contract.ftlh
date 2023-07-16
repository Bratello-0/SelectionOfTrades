<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Craft skins CS GO</title>
    <link href="/style.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/js/script.js"></script>
</head>
<body class="case">
<div class="cv_nav2">
    <div class="cv_nav2_inside">
        <div class="cv_nav2_button">
            <div class="cv_nav2_name w_drop">
                Предметы
            </div>
            <div class="cv_nav2_drop">
                <a target="_self" onclick="pl_show();" href="http://localhost:8080/trade/weapon" class="cv_nav2_dropel">
                    Все предметы
                    <div class="bg"></div>
                </a>
            </div>
        </div>
        <div class="cv_nav2_button">
            <div class="cv_nav2_name w_drop">
                Контракты
            </div>
            <div class="cv_nav2_drop">
                <a target="_self" onclick="pl_show();" href="http://localhost:8080/trade/contract"
                   class="cv_nav2_dropel">
                    Лучшие контракты
                    <div class="bg"></div>
                </a>
            </div>
        </div>
        <div class="cv_nav2_button search">
            <form onkeydown="return event.key != 'Enter';" class="navform">
                <input oninput="tradeFilter('search',this.value);" id="tradeup_search_input" class="ts1px"
                       type="navsearch" name="extSteam"
                       placeholder="Поиск по определённому имени скина (в англ. варианте)">
            </form>
        </div>
    </div>
</div>
<script>
let selectorActive = false;
function selector_activate(e)
{
	if(selectorActive == true)
	{
		clearDropdown(document.getElementsByClassName("dropdown"));
		clearDropdown(document.getElementsByClassName("selector"));
		selectorActive = false;
		hideAllSearchDrops();
	}
	else if(selectorActive == false)
	{
		selectorActive = true;
		document.getElementById(e.id).classList.toggle("show");
		document.getElementById(e.id + '_dropdown').classList.toggle("show");
	}
}
function selector(info,e)
{
	var parent = e.parentElement.id.replace('_dropdown','');
	var result = parent + '_dropresult';
	var options = document.getElementsByClassName(parent + '_options');
	for(option of options)
	{
		option.style.background = "";
		option.style.borderColor = "";
	}
	e.style.background = "#ffffff16";
	e.style.borderColor = "#fff6";

	lastSelector = e.id;

	document.getElementById(result).innerHTML = e.innerHTML;
	document.getElementById(result).setAttribute('data',info);
	selectorActive = false;

	switch (parent) {
		case "trade_filter_collections":
			tradeFilter('collection',info);
			break;
		default:
			console.log(parent + " id is not found");
	}
}
function clearDropdown(items)
{
	for (var i = 0; i < items.length; i++)
	{
		var item = items[i];
		if (item.classList.contains('show'))
		{
			item.classList.remove('show');
		}
	}
}
function hideAllSearchDrops()
{
	var searchDrops = document.getElementsByClassName("search_drop");

	for(entry of searchDrops)
	{
		entry.classList.add('hid');
	}
}
window.onclick = function(event)
{
	if(event.target.tagName == 'path' || event.target.tagName == 'svg' || event.target.classList[0] == 'dropSvg' || event.target.classList[0] == 'drop' || event.target.classList[0] == 'text' || event.target.classList[1] == 'drop' || event.target.classList[1] == 'text')
	{
		customMeaning = 'selector'
	}
	else if(selectorActive == true)
	{
		clearDropdown(document.getElementsByClassName("dropdown"));
		clearDropdown(document.getElementsByClassName("selector"));
		selectorActive = false;
		hideAllSearchDrops();
	}
	else
	{
		customMeaning = ''
	}
}

window.addEventListener("keydown", function (event) {
	if (event.defaultPrevented || customMeaning == '')
	{
		return;
	}

	if(customMeaning == 'selector')
	{
		switch (event.key)
		{
			case "Down":
			case "ArrowDown":
			case "Right":
			case "ArrowRight":
				if(document.getElementById(lastSelector).nextElementSibling != null)
				{
					document.getElementById(lastSelector).nextElementSibling.click();
				}
				break;
			case "Up":
			case "ArrowUp":
			case "Left":
			case "ArrowLeft":
				if(document.getElementById(lastSelector).previousElementSibling != null)
				{
					document.getElementById(lastSelector).previousElementSibling.click();
				}
				break;
			case "Enter":

				break;
			case "Esc":
			case "Escape":

				break;
			default:
				return;
		}
	}

	event.preventDefault();
}, true);










</script>
<main class="w960 ts1px">
    <div id="tradeAddDialog" class="bB1" style="background: #567;position: relative;height: 800px;transition:0.25s;">
        <div class="case_bg_div" style="height:100%;">
            <div id="case_bg_overlay" class="case_bg_overlay"></div>
        </div>
        <div id="tradeShowCase" class="case_showcase noselect" style="height: 700px;transition:0.25s; top: 100px;">
            <h2 class="case_itemsmight" style="color: #9990;text-shadow: none;">Добро пожаловать в CS:GO симулятор
                Trade-Up контрактов!</h2>
            <div class="trade_selectors" style="width:100%; top:-100px;">
                <div id="trade_selector_stattrak" class="trade_selector_entry">
                    <span>StatTrak</span>
                    <div class="selector_trade selector_stattrak" onclick="tradeFilter('stattrak')"></div>
                </div>
                <div id="trade_selector_rarities" class="trade_selector_entry">
                    <span>Редкость</span>
                    <div class="cv_tooltip selector_trade selector_rarity selector_rarity_5 disabled"
                         onclick="tradeFilter('rarity',5)">
                        <div style="font-size: 14px;font-weight: 500;width: max-content;min-width: unset;margin-top: 130%;"
                             class="cv_tooltip_block"><font style="color:#b0c3d9">Consumer</font></div>
                    </div>
                    <div class="cv_tooltip selector_trade selector_rarity selector_rarity_1 disabled"
                         onclick="tradeFilter('rarity',1)">
                        <div style="font-size: 14px;font-weight: 500;width: max-content;min-width: unset;margin-top: 130%;"
                             class="cv_tooltip_block"><font style="color:#5e98d9">Industrial</font></div>
                    </div>
                    <div class="cv_tooltip selector_trade selector_rarity selector_rarity_6 disabled"
                         onclick="tradeFilter('rarity',6)">
                        <div style="font-size: 14px;font-weight: 500;width: max-content;min-width: unset;margin-top: 130%;"
                             class="cv_tooltip_block"><font style="color:#4b69fe">Mil-Spec</font></div>
                    </div>
                    <div class="cv_tooltip selector_trade selector_rarity selector_rarity_2 disabled"
                         onclick="tradeFilter('rarity',2)">
                        <div style="font-size: 14px;font-weight: 500;width: max-content;min-width: unset;margin-top: 130%;"
                             class="cv_tooltip_block"><font style="color:#8847ff">Restricted</font></div>
                    </div>
                    <div class="cv_tooltip selector_trade selector_rarity selector_rarity_4 disabled"
                         onclick="tradeFilter('rarity',4)">
                        <div style="font-size: 14px;font-weight: 500;width: max-content;min-width: unset;margin-top: 130%;"
                             class="cv_tooltip_block"><font style="color:#d32ce6">Classified</font></div>
                    </div>
                </div>
                <div id="trade_selector_collection" class="trade_selector_entry" style="flex:1;">
                    <span style="text-align:right;">Коллекция</span>
                    <div class="selector" style="min-width: 370px;margin-right: 0px;" id="trade_filter_collections"
                         onclick="selector_activate(this);">
                        <div id="trade_filter_collections_dropresult" data="all" class="text noselect">Все коллекции
                            <div class="bg"></div>
                        </div>
                        <div id="trade_filter_collections_dropdown" class="dropdown test_scroll">
                            <a id="trade_filter_collections_all_option"
                               class="trade_filter_collections_options noselect textWrap" onclick="selector('all',this)"
                               style="background: rgba(255, 255, 255, 0.086); border-color: rgba(255, 255, 255, 0.4);">
                                Все коллекции
                                <div class="bg"></div>
                            </a>
                            <#list collections?sort_by(['caseName']) as collection>
                            <a id="trade_filter_collections_${collection.tag}"
                               class="trade_filter_collections_options noselect textWrap"
                               onclick="selector('${collection.tag}',this)">${collection.caseName}
                                <div style="float:right;padding-right:8px;pointer-events: none;">
                                    <div style="display:inline-block;" class="trade_rarities_in_collections_0">
                                        ${weapons?filter(weapon-> weapon.rarity.id == 5 && weapon.collection.id ==
                                        collection.id)?size}
                                    </div>
                                    <div style="display:inline-block;" class="trade_rarities_in_collections_1">
                                        ${weapons?filter(weapon-> weapon.rarity.id == 1 && weapon.collection.id ==
                                        collection.id)?size}
                                    </div>
                                    <div style="display:inline-block;" class="trade_rarities_in_collections_2">
                                        ${weapons?filter(weapon-> weapon.rarity.id == 6 && weapon.collection.id ==
                                        collection.id)?size}
                                    </div>
                                    <div style="display:inline-block;" class="trade_rarities_in_collections_3">
                                        ${weapons?filter(weapon-> weapon.rarity.id == 2 && weapon.collection.id ==
                                        collection.id)?size}
                                    </div>
                                    <div style="display:inline-block;" class="trade_rarities_in_collections_4">
                                        ${weapons?filter(weapon-> weapon.rarity.id == 4 && weapon.collection.id ==
                                        collection.id)?size}
                                    </div>
                                    <div style="display:inline-block;" class="trade_rarities_in_collections_5">
                                        ${weapons?filter(weapon-> weapon.rarity.id == 3 && weapon.collection.id ==
                                        collection.id)?size}
                                    </div>
                                </div>
                                <div class="bg"></div>
                            </a>
                        </#list>
                        </div>
                    </div>
            </div>
            <div id="trade_selector_price" class="trade_selector_entry" style="flex:1;">
                <span style="text-align:right;">Цена крафта</span>
                <input oninput="tradeFilter('price_from',this.value);" id="trade_price_from_input" class="ts1px"
                       name="extSteam" type="number"
                       placeholder="От (в долларах)" style="background:#345; box-shadow: 0px 0px 4px #0006;">
                <input oninput="tradeFilter('price_to',this.value);" id="trade_price_to_input" class="ts1px"
                       name="extSteam" type="number"
                       placeholder="До (в долларах)" style="background:#345; box-shadow: 0px 0px 4px #0006;">
            </div>
            <div id="trade_selector_coeff" class="trade_selector_entry">
                <span style="text-align:right;">Коэффициент</span>
                <input oninput="tradeFilter('coeff_from',this.value);" id="trade_coeff_from_input" class="ts1px"
                       name="extSteam" type="number"
                       placeholder="От" style="background:#345; box-shadow: 0px 0px 4px #0006;">
                <input oninput="tradeFilter('coeff_to',this.value);" id="trade_coeff_to_input" class="ts1px"
                       name="extSteam" type="number"
                       placeholder="До" style="background:#345; box-shadow: 0px 0px 4px #0006;">
            </div>
        </div>
        <div id="case_welcome" class="case_welcome"></div>
        <div style="width:100%;position: relative;padding-right: 16px;overflow:hidden;z-index:1;">
            <div id="allitems" class="case_allitems panorama_scroll"
                 style="transition:0.5s;position:relative;top:0%;height:700px;">
                <div style="width: 100%; text-align: center; height: 450px; line-height: 450px; box-sizing: border-box; font-size: 25px; font-weight: 500; font-style: italic; display: none;"
                     id="noItems">No items eligible for craft found with selected filters
                </div>
            </div>
            <div id="allitems_result" class="case_allitems panorama_scroll"
                 style="transition:0.5s;position:relative;top: 0%;"> results here test
            </div>
        </div>
    </div>
    </div>
    <div class="tip_div" style="margin: 8px;"></div>
    <div class="tip_div" style="margin: 8px;"></div>
</main>
<script>
    function reqListener() {
        var element = document.querySelector('#noItems');
        element.insertAdjacentHTML('afterend', new DOMParser().parseFromString(this.responseText, "text/html").querySelector("answer").innerHTML);
        tradeFilter("rarity",4);
	    tradeFilter('stattrak');
	    doubleFilterSet();
    }
    const req = new XMLHttpRequest();
    req.addEventListener("load", reqListener);
    req.open("GET", "http://localhost:8080/contract");
    req.send();
</script>
</body>
</html>