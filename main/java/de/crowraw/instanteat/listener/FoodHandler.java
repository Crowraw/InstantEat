package de.crowraw.instanteat.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FoodHandler implements Listener {


    @EventHandler
    public void onEat(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(event.getPlayer().getItemInHand() == null || event.getPlayer().getItemInHand().getType()==Material.AIR){
                return;
            }
            if(event.getPlayer().getFoodLevel()>=20){
                return;
            }

            if(event.getPlayer().getItemInHand().getType().isEdible()){


                event.setCancelled(true);
                Material material = event.getPlayer().getItemInHand().getType();
                if(material==Material.GOLDEN_APPLE){
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,100,1));
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,20*60*2,0));
                }
                if(material==Material.ENCHANTED_GOLDEN_APPLE){
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,20*20,1));
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,20*60*2,3));
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,20*60*5,0));
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,20*60*5,0));
                }
                double current = getSaturationValue(material);
                event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
                if (((float) current + event.getPlayer().getFoodLevel()) > 20) {
                    event.getPlayer().setFoodLevel(20);
                }else {
                    event.getPlayer().setFoodLevel((int) (event.getPlayer().getFoodLevel()+current));
                }


                if (event.getPlayer().getItemInHand().getAmount() == 0) {
                    event.getPlayer().setItemInHand(new org.bukkit.inventory.ItemStack(Material.AIR));
                }
            }
        }


    }


    private double getSaturationValue(Material mat) {

        if (mat == Material.CAKE || mat == Material.COOKIE)
            return 0.4D;
        if (mat == Material.POTATO)
            return 0.6D;
        if (mat == Material.ROTTEN_FLESH)
            return 0.8D;
        if (mat == Material.MELON || mat == Material.POISONOUS_POTATO || mat == Material.CHICKEN || mat == Material.TROPICAL_FISH)
            return 1.2D;
        if (mat == Material.PORKCHOP || mat == Material.BEEF)
            return 1.8D;
        if (mat == Material.APPLE)
            return 2.4D;
        if (mat == Material.DRIED_KELP)
            return 2.4D;
        if (mat == Material.SPIDER_EYE)
            return 3.2D;
        if (mat == Material.CARROT || mat == Material.PUMPKIN_PIE)
            return 4.8D;
        if (mat == Material.BREAD || mat == Material.COOKED_SALMON || mat == Material.COOKED_COD)
            return 6D;
        if (mat == Material.BAKED_POTATO || mat == Material.COOKED_CHICKEN || mat == Material.MUSHROOM_STEW)
            return 7.2D;
        if (mat == Material.GOLDEN_APPLE)
            return 9.6D;
        if (mat == Material.COOKED_BEEF || mat == Material.COOKED_PORKCHOP)
            return 12.8D;
        if (mat == Material.GOLDEN_CARROT)
            return 14.4D;


        return 0;

    }


}
