import events.Projectile;
import items.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import thirtyvirus.uber.UberItems;
import thirtyvirus.uber.UberMaterial;
import thirtyvirus.uber.helpers.AbilityType;
import thirtyvirus.uber.helpers.UberAbility;
import thirtyvirus.uber.helpers.UberCraftingRecipe;
import thirtyvirus.uber.helpers.UberRarity;

import java.util.Arrays;
import java.util.Collections;

public class TooManyPearls extends JavaPlugin {

    public void onEnable() {

        // enforce UberItems dependancy
        if (Bukkit.getPluginManager().getPlugin("UberItems") == null) {
            this.getLogger().severe("UberItems Addons requires UberItems! disabled because UberItems dependency not found");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // register events and UberItems
        registerEvents();
        registerUberMaterials();
        registerUberItems();

        // post confirmation in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been enabled");
    }
    public void onDisable() {
        // posts exit message in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been disabled");
    }
    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new Projectile(), this);
    }

    // NEW UBER ITEM CHECKLIST

    // - make a new class file, named with all lowercase lettering and underscores for spaces
    // - copy the UberItemTemplate class contents into the new class, extend UberItem
    // - make a putItem entry, follow the format of previous items and make sure to give a unique id
    // - write the unique item ability code in the appropriate method

    // - add the following line of code just after executing the item's ability:
    //      onItemUse(player, item); // confirm that the item's ability has been successfully used

    // - if the ability needs a cooldown, prefix it's code with a variation of the following line of code:
    //      if (!Utilities.enforceCooldown(getMain(), player, "name", 1, item, true)) return;

    // - if the item needs work done on create (like adding enchantments, adding other data) refer to onItemStackCreate
    // - if the item needs a prefix or suffix in its description,
    //   refer to the getSpecificLorePrefix and getSpecificLoreSuffix functions, then add the following:
    //      lore.add(ChatColor.RESET + "text goes here");

    // - if you need to store & retrieve ints and strings from items, you can use the following functions:
    //      Utilities.storeIntInItem(getMain(), item, 1, "number tag");
    //      if (Utilities.getIntFromItem(getMain(), item, "number tag") == 1) // { blah blah blah }
    //      (the same case for strings, just storeStringInItem and getStringFromItem)

    private void registerUberItems() {

        UberItems.putItem("crude_pearl", new crude_pearl(Material.ENDER_PEARL, "Crude Pearl", UberRarity.COMMON,
                true, true, true,
                Collections.singletonList(
                        new UberAbility("Unstable", AbilityType.RIGHT_CLICK, "Due to shoddy construction and excessive filler material, teleporting has strange side effects")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.ENDER_PEARL),
                        new ItemStack(Material.SAND),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.STRING),
                        new ItemStack(Material.GUNPOWDER),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR)), false, 4)));
        UberItems.putItem("healing_pearl", new healing_pearl(Material.ENDER_PEARL, "Healing Pearl", UberRarity.COMMON,
                true, true, false,
                Collections.singletonList(
                        new UberAbility("Safe Teleport", AbilityType.RIGHT_CLICK, "Doesn't hurt to throw, /newline gives 2s of regeneration II")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENDER_PEARL),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENDER_PEARL),
                        new ItemStack(Material.GHAST_TEAR),
                        new ItemStack(Material.ENDER_PEARL),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENDER_PEARL),
                        new ItemStack(Material.AIR)), false, 2)));
        UberItems.putItem("timewarp_pearl", new timewarp_pearl(Material.ENDER_PEARL, "Timewarp Pearl", UberRarity.UNCOMMON,
                true, true, false,
                Collections.singletonList(
                        new UberAbility("Timewarp", AbilityType.RIGHT_CLICK, "5s after this pearl lands, warp back to where you were when you threw it")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENDER_PEARL),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENDER_PEARL),
                        new ItemStack(Material.CLOCK),
                        new ItemStack(Material.ENDER_PEARL),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENDER_PEARL),
                        new ItemStack(Material.AIR)), false, 4)));
        UberItems.putItem("rocket_pearl", new rocket_pearl(Material.ENDER_PEARL, "Rocket Pearl", UberRarity.RARE,
                true, true, false,
                Collections.singletonList(
                        new UberAbility("ZOOOM", AbilityType.RIGHT_CLICK, "A really fast ender pearl")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENDER_PEARL, 4),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENDER_PEARL, 4),
                        UberItems.getMaterial("spark_dust").makeItem(16),
                        new ItemStack(Material.ENDER_PEARL, 4),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENDER_PEARL, 4),
                        new ItemStack(Material.AIR)), false, 4)));
        UberItems.putItem("infina_pearl", new infina_pearl(Material.ENDER_PEARL, "Infina-Pearl", UberRarity.EPIC,
                false, false, false,
                Collections.singletonList(
                        new UberAbility("Infinawarp", AbilityType.RIGHT_CLICK, "Throw an ender pearl that is never used up")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_ender_pearl").makeItem(16),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_ender_pearl").makeItem(16),
                        new ItemStack(Material.NETHER_STAR),
                        UberItems.getMaterial("enchanted_ender_pearl").makeItem(16),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_ender_pearl").makeItem(16),
                        new ItemStack(Material.AIR)), false, 1 )));

    }
    private void registerUberMaterials() {
        UberItems.putMaterial("enchanted_sponge", new UberMaterial(Material.SPONGE,
                "Enchanted Sponge", UberRarity.RARE, true, false, false,
                "idk why I chose sponge, but hey this demonstrates how to make a custom UberMaterial lol",
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.SPONGE, 32),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.SPONGE, 32),
                        new ItemStack(Material.SPONGE, 32),
                        new ItemStack(Material.SPONGE, 32),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.SPONGE, 32),
                        new ItemStack(Material.AIR)), false, 1)));
        UberItems.putMaterial("thumbnail", new UberMaterial(Material.GOLDEN_APPLE,
                "Titan Apple", UberRarity.MYTHIC, true, false, false,
                "" + ChatColor.GRAY + ChatColor.ITALIC + "look at what they /newline " + ChatColor.ITALIC + "need to mimic a /newline " + ChatColor.ITALIC + "fraction of our power",
                null));
    }
}