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
            this.getLogger().severe("TooManyPearls requires UberItems! disabled because UberItems dependency not found");
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
                        new ItemStack(Material.AIR)), false, 4)));
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
        UberItems.putItem("recall_pearl", new recall_pearl(Material.ENDER_PEARL, "Recall Pearl", UberRarity.EPIC,
                true, true, false,
                Collections.singletonList(
                        new UberAbility("Absolute Recall", AbilityType.LEFT_CLICK, "Save a location for later, throw to reappear at the saved location")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.REDSTONE),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.COMPARATOR),
                        UberItems.getMaterial("enchanted_ender_pearl").makeItem(1),
                        new ItemStack(Material.REPEATER),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.REDSTONE),
                        new ItemStack(Material.AIR)), false, 1 )));
        UberItems.putItem("infina_pearl", new infina_pearl(Material.ENDER_PEARL, "Infina-Pearl", UberRarity.LEGENDARY,
                false, false, false,
                Collections.singletonList(
                        new UberAbility("Infinawarp", AbilityType.RIGHT_CLICK, "Reappears in your inventory once it lands")),
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

    }
}