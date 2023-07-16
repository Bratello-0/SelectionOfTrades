<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fist run</title>
</head>
<#assign exts_flaot=[0.26501, 0.41501, 0.03501, 0.11001, 0.725005]/>
<#function stattrak skin>
    <#if skin.quality.id == 1>
        <#return 1>
    <#else>
        <#return 0>
    </#if>
</#function>
<body>
<answer>
    <#list contracts as contract>
        <div class="trade_contract collection_all collection_${contract.skin1.weapon.collection.tag} collection_${contract.skin10.weapon.collection.tag} rarity_${contract.skin1.weapon.rarity.id} stattrak_${stattrak(contract.skin1)}" data-price="${contract.price}" data-coeff="${contract.coefficient}"
             style="visibility: visible; display: none; opacity: 1;" data-skin="${contract.skin1.weapon.dataWeapon.weaponName} ${contract.skin1.weapon.name} ${contract.skin2.weapon.dataWeapon.weaponName} ${contract.skin2.weapon.name} ${contract.skin3.weapon.dataWeapon.weaponName} ${contract.skin3.weapon.name} ${contract.skin4.weapon.dataWeapon.weaponName} ${contract.skin4.weapon.name} ${contract.skin5.weapon.dataWeapon.weaponName} ${contract.skin5.weapon.name} ${contract.skin6.weapon.dataWeapon.weaponName} ${contract.skin6.weapon.name} ${contract.skin7.weapon.dataWeapon.weaponName} ${contract.skin7.weapon.name} ${contract.skin8.weapon.dataWeapon.weaponName} ${contract.skin8.weapon.name} ${contract.skin9.weapon.dataWeapon.weaponName} ${contract.skin9.weapon.name} ${contract.skin10.weapon.dataWeapon.weaponName} ${contract.skin10.weapon.name}">
            <div class="trade_info_contract" style="min-width: 150px;max-width: 150px;">
                <div>Коэффициент
                    <span>${contract.coefficient}</span>
                </div>
                <div>Стоимость
                    <span>${contract.price}$</span>
                </div>
            </div>

            <div id="allitems_edit" class="case panorama_scroll"
                 style="overflow:hidden; min-width: 780px;max-width: 780px; flex: 1;">
                <div style="margin-bottom:-32px; visibility: visible;  opacity: 1;" class="noselect case_item case_itemlist tradeEdit_item">
                    <a style="cursor: auto;">
                        <div class="case_item_img_div">
                            <img class="case_item_img"loading="lazy" src="https://community.akamai.steamstatic.com/economy/image/${contract.skin1.weapon.urlImg}">
                            <div class="trade_add_item">
                                <a target="_blank" class="hiddenLink"
                                   href="https://steamcommunity.com/market/search?appid=730&amp;q=${contract.skin1.weapon.dataWeapon.weaponName} ${contract.skin1.weapon.name}"
                                   style="height: 100%;position: absolute;right: 0;top: 0;padding-top: 4px;padding-right: 4px;box-sizing: border-box;opacity:0;transition:0.25s;z-index:100;">
                                    <img data-src="" style="height: 100%;transform: scale(1.5);" loading="lazy"
                                         src="../imgs/steam.png">
                                </a>
                            </div>
                        </div>
                        <div class="case_item_bg" style="transition: all 0.25s ease 0s;"></div>
                        <div class="case_item_descL case_item_level_nobox case_item_level${contract.skin1.weapon.rarity.id}"
                             style="background-image:none;"></div>
                        <div id="tradeEdit_price_1" class="trade_price">$${contract.skin1.price}</div>
                        <div id="tradeEdit_wear_1" class="trade_wear">${contract.skin1.exterior.exterior}</div>
                        <div id="tradeEdit_quantity_1" class="trade_quantity">${contract.skin1.quantity}</div>
                        <div id="tradeEdit_float_1" class="trade_float">${exts_flaot[contract.skin1.exterior.id-2]}</div>
                        <div id="tradeEdit_name_1" class="trade_name">${contract.skin1.weapon.name}</div>
                    </a>
                </div>

                <div style="margin-bottom:-32px; visibility: visible;  opacity: 1;" class="noselect case_item case_itemlist tradeEdit_item">
                    <a style="cursor: auto;">
                        <div class="case_item_img_div">
                            <img class="case_item_img"loading="lazy" src="https://community.akamai.steamstatic.com/economy/image/${contract.skin2.weapon.urlImg}">
                            <div class="trade_add_item">
                                <a target="_blank" class="hiddenLink"
                                   href="https://steamcommunity.com/market/search?appid=730&amp;q=${contract.skin2.weapon.dataWeapon.weaponName} ${contract.skin2.weapon.name}"
                                   style="height: 100%;position: absolute;right: 0;top: 0;padding-top: 4px;padding-right: 4px;box-sizing: border-box;opacity:0;transition:0.25s;z-index:100;">
                                    <img data-src="" style="height: 100%;transform: scale(1.5);" loading="lazy"
                                         src="../imgs/steam.png">
                                </a>
                            </div>
                        </div>
                        <div class="case_item_bg" style="transition: all 0.25s ease 0s;"></div>
                        <div class="case_item_descL case_item_level_nobox case_item_level${contract.skin2.weapon.rarity.id}"
                             style="background-image:none;"></div>
                        <div id="tradeEdit_price_2" class="trade_price">$${contract.skin2.price}</div>
                        <div id="tradeEdit_wear_2" class="trade_wear">${contract.skin2.exterior.exterior}</div>
                        <div id="tradeEdit_quantity_2" class="trade_quantity">${contract.skin2.quantity}</div>
                        <div id="tradeEdit_float_2" class="trade_float">${exts_flaot[contract.skin2.exterior.id-2]}</div>
                        <div id="tradeEdit_name_2" class="trade_name">${contract.skin2.weapon.name}</div>
                    </a>
                </div>

                <div style="margin-bottom:-32px; visibility: visible;  opacity: 1;" class="noselect case_item case_itemlist tradeEdit_item">
                    <a style="cursor: auto;">
                        <div class="case_item_img_div">
                            <img class="case_item_img"loading="lazy" src="https://community.akamai.steamstatic.com/economy/image/${contract.skin3.weapon.urlImg}">
                            <div class="trade_add_item">
                                <a target="_blank" class="hiddenLink"
                                   href="https://steamcommunity.com/market/search?appid=730&amp;q=${contract.skin3.weapon.dataWeapon.weaponName} ${contract.skin3.weapon.name}"
                                   style="height: 100%;position: absolute;right: 0;top: 0;padding-top: 4px;padding-right: 4px;box-sizing: border-box;opacity:0;transition:0.25s;z-index:100;">
                                    <img data-src="" style="height: 100%;transform: scale(1.5);" loading="lazy"
                                         src="../imgs/steam.png">
                                </a>
                            </div>
                        </div>
                        <div class="case_item_bg" style="transition: all 0.25s ease 0s;"></div>
                        <div class="case_item_descL case_item_level_nobox case_item_level${contract.skin3.weapon.rarity.id}"
                             style="background-image:none;"></div>
                        <div id="tradeEdit_price_3" class="trade_price">$${contract.skin3.price}</div>
                        <div id="tradeEdit_wear_3" class="trade_wear">${contract.skin3.exterior.exterior}</div>
                        <div id="tradeEdit_quantity_3" class="trade_quantity">${contract.skin3.quantity}</div>
                        <div id="tradeEdit_float_3" class="trade_float">${exts_flaot[contract.skin3.exterior.id-2]}</div>
                        <div id="tradeEdit_name_3" class="trade_name">${contract.skin3.weapon.name}</div>
                    </a>
                </div>

                <div style="margin-bottom:-32px; visibility: visible;  opacity: 1;" class="noselect case_item case_itemlist tradeEdit_item">
                    <a style="cursor: auto;">
                        <div class="case_item_img_div">
                            <img class="case_item_img"loading="lazy" src="https://community.akamai.steamstatic.com/economy/image/${contract.skin4.weapon.urlImg}">
                            <div class="trade_add_item">
                                <a target="_blank" class="hiddenLink"
                                   href="https://steamcommunity.com/market/search?appid=730&amp;q=${contract.skin4.weapon.dataWeapon.weaponName} ${contract.skin4.weapon.name}"
                                   style="height: 100%;position: absolute;right: 0;top: 0;padding-top: 4px;padding-right: 4px;box-sizing: border-box;opacity:0;transition:0.25s;z-index:100;">
                                    <img data-src="" style="height: 100%;transform: scale(1.5);" loading="lazy"
                                         src="../imgs/steam.png">
                                </a>
                            </div>
                        </div>
                        <div class="case_item_bg" style="transition: all 0.25s ease 0s;"></div>
                        <div class="case_item_descL case_item_level_nobox case_item_level${contract.skin4.weapon.rarity.id}"
                             style="background-image:none;"></div>
                        <div id="tradeEdit_price_4" class="trade_price">$${contract.skin4.price}</div>
                        <div id="tradeEdit_wear_4" class="trade_wear">${contract.skin4.exterior.exterior}</div>
                        <div id="tradeEdit_quantity_4" class="trade_quantity">${contract.skin4.quantity}</div>
                        <div id="tradeEdit_float_4" class="trade_float">${exts_flaot[contract.skin4.exterior.id-2]}</div>
                        <div id="tradeEdit_name_4" class="trade_name">${contract.skin4.weapon.name}</div>
                    </a>
                </div>

                <div style="margin-bottom:-32px; visibility: visible;  opacity: 1;" class="noselect case_item case_itemlist tradeEdit_item">
                    <a style="cursor: auto;">
                        <div class="case_item_img_div">
                            <img class="case_item_img"loading="lazy" src="https://community.akamai.steamstatic.com/economy/image/${contract.skin5.weapon.urlImg}">
                            <div class="trade_add_item">
                                <a target="_blank" class="hiddenLink"
                                   href="https://steamcommunity.com/market/search?appid=730&amp;q=${contract.skin5.weapon.dataWeapon.weaponName} ${contract.skin5.weapon.name}"
                                   style="height: 100%;position: absolute;right: 0;top: 0;padding-top: 4px;padding-right: 4px;box-sizing: border-box;opacity:0;transition:0.25s;z-index:100;">
                                    <img data-src="" style="height: 100%;transform: scale(1.5);" loading="lazy"
                                         src="../imgs/steam.png">
                                </a>
                            </div>
                        </div>
                        <div class="case_item_bg" style="transition: all 0.25s ease 0s;"></div>
                        <div class="case_item_descL case_item_level_nobox case_item_level${contract.skin5.weapon.rarity.id}"
                             style="background-image:none;"></div>
                        <div id="tradeEdit_price_5" class="trade_price">$${contract.skin5.price}</div>
                        <div id="tradeEdit_wear_5" class="trade_wear">${contract.skin5.exterior.exterior}</div>
                        <div id="tradeEdit_quantity_5" class="trade_quantity">${contract.skin5.quantity}</div>
                        <div id="tradeEdit_float_5" class="trade_float">${exts_flaot[contract.skin5.exterior.id-2]}</div>
                        <div id="tradeEdit_name_5" class="trade_name">${contract.skin5.weapon.name}</div>
                    </a>
                </div>

                <div style="margin-bottom:-32px; visibility: visible;  opacity: 1;" class="noselect case_item case_itemlist tradeEdit_item">
                    <a style="cursor: auto;">
                        <div class="case_item_img_div">
                            <img class="case_item_img"loading="lazy" src="https://community.akamai.steamstatic.com/economy/image/${contract.skin6.weapon.urlImg}">
                            <div class="trade_add_item">
                                <a target="_blank" class="hiddenLink"
                                   href="https://steamcommunity.com/market/search?appid=730&amp;q=${contract.skin6.weapon.dataWeapon.weaponName} ${contract.skin6.weapon.name}"
                                   style="height: 100%;position: absolute;right: 0;top: 0;padding-top: 4px;padding-right: 4px;box-sizing: border-box;opacity:0;transition:0.25s;z-index:100;">
                                    <img data-src="" style="height: 100%;transform: scale(1.5);" loading="lazy"
                                         src="../imgs/steam.png">
                                </a>
                            </div>
                        </div>
                        <div class="case_item_bg" style="transition: all 0.25s ease 0s;"></div>
                        <div class="case_item_descL case_item_level_nobox case_item_level${contract.skin6.weapon.rarity.id}"
                             style="background-image:none;"></div>
                        <div id="tradeEdit_price_6" class="trade_price">$${contract.skin6.price}</div>
                        <div id="tradeEdit_wear_6" class="trade_wear">${contract.skin6.exterior.exterior}</div>
                        <div id="tradeEdit_quantity_6" class="trade_quantity">${contract.skin6.quantity}</div>
                        <div id="tradeEdit_float_6" class="trade_float">${exts_flaot[contract.skin6.exterior.id-2]}</div>
                        <div id="tradeEdit_name_6" class="trade_name">${contract.skin6.weapon.name}</div>
                    </a>
                </div>

                <div style="margin-bottom:-32px; visibility: visible;  opacity: 1;" class="noselect case_item case_itemlist tradeEdit_item">
                    <a style="cursor: auto;">
                        <div class="case_item_img_div">
                            <img class="case_item_img"loading="lazy" src="https://community.akamai.steamstatic.com/economy/image/${contract.skin7.weapon.urlImg}">
                            <div class="trade_add_item">
                                <a target="_blank" class="hiddenLink"
                                   href="https://steamcommunity.com/market/search?appid=730&amp;q=${contract.skin7.weapon.dataWeapon.weaponName} ${contract.skin7.weapon.name}"
                                   style="height: 100%;position: absolute;right: 0;top: 0;padding-top: 4px;padding-right: 4px;box-sizing: border-box;opacity:0;transition:0.25s;z-index:100;">
                                    <img data-src="" style="height: 100%;transform: scale(1.5);" loading="lazy"
                                         src="../imgs/steam.png">
                                </a>
                            </div>
                        </div>
                        <div class="case_item_bg" style="transition: all 0.25s ease 0s;"></div>
                        <div class="case_item_descL case_item_level_nobox case_item_level${contract.skin7.weapon.rarity.id}"
                             style="background-image:none;"></div>
                        <div id="tradeEdit_price_7" class="trade_price">$${contract.skin7.price}</div>
                        <div id="tradeEdit_wear_7" class="trade_wear">${contract.skin7.exterior.exterior}</div>
                        <div id="tradeEdit_quantity_7" class="trade_quantity">${contract.skin7.quantity}</div>
                        <div id="tradeEdit_float_7" class="trade_float">${exts_flaot[contract.skin7.exterior.id-2]}</div>
                        <div id="tradeEdit_name_7" class="trade_name">${contract.skin7.weapon.name}</div>
                    </a>
                </div>

                <div style="margin-bottom:-32px; visibility: visible;  opacity: 1;" class="noselect case_item case_itemlist tradeEdit_item">
                    <a style="cursor: auto;">
                        <div class="case_item_img_div">
                            <img class="case_item_img"loading="lazy" src="https://community.akamai.steamstatic.com/economy/image/${contract.skin8.weapon.urlImg}">
                            <div class="trade_add_item">
                                <a target="_blank" class="hiddenLink"
                                   href="https://steamcommunity.com/market/search?appid=730&amp;q=${contract.skin8.weapon.dataWeapon.weaponName} ${contract.skin8.weapon.name}"
                                   style="height: 100%;position: absolute;right: 0;top: 0;padding-top: 4px;padding-right: 4px;box-sizing: border-box;opacity:0;transition:0.25s;z-index:100;">
                                    <img data-src="" style="height: 100%;transform: scale(1.5);" loading="lazy"
                                         src="../imgs/steam.png">
                                </a>
                            </div>
                        </div>
                        <div class="case_item_bg" style="transition: all 0.25s ease 0s;"></div>
                        <div class="case_item_descL case_item_level_nobox case_item_level${contract.skin8.weapon.rarity.id}"
                             style="background-image:none;"></div>
                        <div id="tradeEdit_price_8" class="trade_price">$${contract.skin8.price}</div>
                        <div id="tradeEdit_wear_8" class="trade_wear">${contract.skin8.exterior.exterior}</div>
                        <div id="tradeEdit_quantity_8" class="trade_quantity">${contract.skin8.quantity}</div>
                        <div id="tradeEdit_float_8" class="trade_float">${exts_flaot[contract.skin8.exterior.id-2]}</div>
                        <div id="tradeEdit_name_8" class="trade_name">${contract.skin8.weapon.name}</div>
                    </a>
                </div>

                <div style="margin-bottom:-32px; visibility: visible;  opacity: 1;" class="noselect case_item case_itemlist tradeEdit_item">
                    <a style="cursor: auto;">
                        <div class="case_item_img_div">
                            <img class="case_item_img"loading="lazy" src="https://community.akamai.steamstatic.com/economy/image/${contract.skin9.weapon.urlImg}">
                            <div class="trade_add_item">
                                <a target="_blank" class="hiddenLink"
                                   href="https://steamcommunity.com/market/search?appid=730&amp;q=${contract.skin9.weapon.dataWeapon.weaponName} ${contract.skin9.weapon.name}"
                                   style="height: 100%;position: absolute;right: 0;top: 0;padding-top: 4px;padding-right: 4px;box-sizing: border-box;opacity:0;transition:0.25s;z-index:100;">
                                    <img data-src="" style="height: 100%;transform: scale(1.5);" loading="lazy"
                                         src="../imgs/steam.png">
                                </a>
                            </div>
                        </div>
                        <div class="case_item_bg" style="transition: all 0.25s ease 0s;"></div>
                        <div class="case_item_descL case_item_level_nobox case_item_level${contract.skin9.weapon.rarity.id}"
                             style="background-image:none;"></div>
                        <div id="tradeEdit_price_9" class="trade_price">$${contract.skin9.price}</div>
                        <div id="tradeEdit_wear_9" class="trade_wear">${contract.skin9.exterior.exterior}</div>
                        <div id="tradeEdit_quantity_9" class="trade_quantity">${contract.skin9.quantity}</div>
                        <div id="tradeEdit_float_9" class="trade_float">${exts_flaot[contract.skin9.exterior.id-2]}</div>
                        <div id="tradeEdit_name_9" class="trade_name">${contract.skin9.weapon.name}</div>
                    </a>
                </div>

                <div style="margin-bottom:-32px; visibility: visible;  opacity: 1;" class="noselect case_item case_itemlist tradeEdit_item">
                    <a style="cursor: auto;">
                        <div class="case_item_img_div">
                            <img class="case_item_img" loading="lazy" src="https://community.akamai.steamstatic.com/economy/image/${contract.skin10.weapon.urlImg}">
                            <div class="trade_add_item">
                                <a target="_blank" class="hiddenLink"
                                   href="https://steamcommunity.com/market/search?appid=730&amp;q=${contract.skin10.weapon.dataWeapon.weaponName} ${contract.skin10.weapon.name}"
                                   style="height: 100%;position: absolute;right: 0;top: 0;padding-top: 4px;padding-right: 4px;box-sizing: border-box;opacity:0;transition:0.25s;z-index:100;">
                                    <img style="height: 100%;transform: scale(1.5);" loading="lazy"
                                         src="../imgs/steam.png">
                                </a>
                            </div>
                        </div>
                        <div class="case_item_bg" style="transition: all 0.25s ease 0s;"></div>
                        <div class="case_item_descL case_item_level_nobox case_item_level${contract.skin10.weapon.rarity.id}"
                             style="background-image:none;"></div>
                        <div id="tradeEdit_price_10" class="trade_price">$${contract.skin10.price}</div>
                        <div id="tradeEdit_wear_10" class="trade_wear">${contract.skin10.exterior.exterior}</div>
                        <div id="tradeEdit_quantity_10" class="trade_quantity">${contract.skin10.quantity}</div>
                        <div id="tradeEdit_float_10" class="trade_float">${exts_flaot[contract.skin10.exterior.id-2]}</div>
                        <div id="tradeEdit_name_10" class="trade_name">${contract.skin10.weapon.name}</div>
                    </a>
                </div>

            </div>
        </div>
    </#list>
    </answe>
</body>
</html>