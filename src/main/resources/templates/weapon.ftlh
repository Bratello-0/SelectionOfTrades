<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fist run</title>
</head>
<#function stattrak skins>
    <#list skins as skin>
        <#if skin.quality.id == 1>
            <#return 1>
        </#if>
        <#else>
            <#return 0>
    </#list>
    <#return 0>
</#function>

<#assign exts=['FT','WW','FN','MW','BS']/>
<#assign true_exts=['FN','MW','FT','WW','BS']/>
<#assign exts_num=[3, 2, 0, 1, 4]/>
<body>
<answer>
    <#list weapons as weapon>
    <div class="cv_tooltip noselect case_item case_itemlist collection_all collection_${weapon.collection.tag} rarity_${weapon.rarity.id} stattrak_${stattrak(weapon.skins)}"
         style="visibility: visible;opacity: 1; display: none;" data-skin="${weapon.dataWeapon.weaponName} ${weapon.name} ">
        <div class="cv_tooltip_block"
             style="min-width: unset;width: 114px;margin-top: 120px;font-size: 14px;font-weight: 500;pointer-events: none;">
            Prices:
            <#list weapon.skins?filter(x -> x.quality.id == 2)?sort_by(['exterior'],['floatExterior']) as skin>
                <br>${exts[skin.exterior.id-2]}:<span style="float:right">$${skin.price}</span>
            </#list>
            <#list weapon.skins?filter(x -> x.quality.id = 1)?sort_by(['exterior'],['floatExterior']) as skin>
                <br> <font style="color:#cf6a32">${exts[skin.exterior.id-2]}: <span style="float:right">$${skin.price}</span></font>
            </#list>
        </div>
            <a style="cursor: auto;">
                <div class="case_item_img_div">
                    <img class="case_item_img" loading="lazy" src="https://community.akamai.steamstatic.com/economy/image/${weapon.urlImg}">
                    <div class="trade_float_fast">
                        <#list true_exts as ext>
                            <div
                                <#if weapon.skins?filter(skin-> (skin.exterior.id-2) == exts_num[ext?index])?has_content>
                                    <#else>
                                    class="disabled"
                                </#if>>
                                ${ext}
                            </div>
                        </#list>
                    </div>
                </div>
                <div class="case_item_bg"></div>
                <div class="case_item_descL case_item_level_nobox case_item_level${weapon.rarity.id}"
                     style="background-image:none;">
            </div>
        </a>
        <div class="case_item_desc" style="position:relative;">
            <a style="cursor: auto;">
                <div class="case_item_desc_line1">${weapon.dataWeapon.weaponName}</div>
                <div class="case_item_desc_line2">${weapon.name}</div>
            </a>
            <a target="_blank" class="hiddenLink"
               href="https://steamcommunity.com/market/search?appid=730&amp;q=${weapon.dataWeapon.weaponName} | ${weapon.name}"
               style="height: 100%;position: absolute;right: 0;top: 0;padding-top: 4px;padding-right: 4px;box-sizing: border-box;opacity:0;transition:0.25s;z-index:100;">
                <img data-src style="height: 100%;transform: scale(1.5);" loading="lazy" src="../imgs/steam.png">
            </a>
        </div>
    </div>
    <#else>
    <h3>пусто</h3>
</#list>
</answe>
</body>
</html>