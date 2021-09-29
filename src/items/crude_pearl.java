package items;

import java.util.List;
import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import org.bukkit.util.Vector;
import thirtyvirus.uber.UberItem;
import thirtyvirus.uber.helpers.*;

public class crude_pearl extends UberItem {
    private Random rand = new Random();

    public crude_pearl(Material material, String name, UberRarity rarity, boolean stackable, boolean oneTimeUse, boolean hasActiveEffect, List<UberAbility> abilities, UberCraftingRecipe craftingRecipe) {
        super(material, name, rarity, stackable, oneTimeUse, hasActiveEffect, abilities, craftingRecipe);
    }
    public void onItemStackCreate(ItemStack item) { }
    public void getSpecificLorePrefix(List<String> lore, ItemStack item) { }
    public void getSpecificLoreSuffix(List<String> lore, ItemStack item) { }

    public boolean leftClickAirAction(Player player, ItemStack item) { return false; }
    public boolean leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }
    public boolean rightClickAirAction(Player player, ItemStack item) {
        if (Utilities.enforceCooldown(player, "pearlThrow", 1, item, false)) return false;

        EnderPearl pearl = player.launchProjectile(EnderPearl.class);
        Utilities.tagEntity(pearl, player.getName(), "uberplayername");
        Utilities.tagEntity(pearl, "a", "smalldmg");
        if (item.getAmount() == 1) destroy(item, 1);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EGG_THROW, 2, 1.5f);
        pearl.setVelocity(player.getEyeLocation().getDirection().multiply(1.5));

        switch (rand.nextInt(10) + 1) {
            case 1:
                pearl.setVelocity(player.getEyeLocation().getDirection().multiply(0.3));
                break;
            case 2:
                Utilities.tagEntity(pearl, "a", "explode");
                break;
            case 3:
                Utilities.tagEntity(pearl, "a", "pearlgrenade");
                break;
            case 4:
                Utilities.tagEntity(pearl, "a", "chickengrenade");
                break;
            case 5:
                pearl.remove();
                player.setVelocity(player.getEyeLocation().getDirection().multiply(5));
                break;
            default:

        }

        return false;
    }
    public boolean rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return rightClickAirAction(player, item); }
    public boolean shiftLeftClickAirAction(Player player, ItemStack item) { return false; }
    public boolean shiftLeftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }
    public boolean shiftRightClickAirAction(Player player, ItemStack item) { return false; }
    public boolean shiftRightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }

    public boolean middleClickAction(Player player, ItemStack item) { return false; }
    public boolean hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity target, ItemStack item) { return false; }
    public boolean breakBlockAction(Player player, BlockBreakEvent event, Block block, ItemStack item) { return false; }
    public boolean clickedInInventoryAction(Player player, InventoryClickEvent event, ItemStack item, ItemStack addition) { return false; }
    public boolean activeEffect(Player player, ItemStack item) { return false; }

}
