package events;

import items.infina_pearl;
import org.bukkit.*;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import thirtyvirus.uber.UberItems;
import thirtyvirus.uber.helpers.Utilities;

import java.util.Random;

public class Projectile implements Listener {
    private Random rand = new Random();

    @EventHandler
    private void onProjectileThrow(ProjectileLaunchEvent event) {

        // cancel vanilla enderpearl throw if the thrown pearl is an UberItem
        Utilities.scheduleTask(()-> {
            if (event.getEntityType() == EntityType.ENDER_PEARL) {
                if (event.getEntity().getShooter() instanceof Player) {
                    Player thrower = (Player) event.getEntity().getShooter();
                    if (Utilities.isUber(thrower.getInventory().getItemInMainHand()) &&
                            thrower.getInventory().getItemInMainHand().getType() == Material.ENDER_PEARL &&
                            !Utilities.getEntityTag(event.getEntity(), "uberplayername").equals(thrower.getName()))
                        event.getEntity().remove();
                    if (Utilities.isUber(thrower.getInventory().getItemInOffHand()) &&
                            thrower.getInventory().getItemInOffHand().getType() == Material.ENDER_PEARL &&
                            !Utilities.getEntityTag(event.getEntity(), "uberplayername").equals(thrower.getName()))
                        event.getEntity().remove();
                }
            }
        }, 1);
    }

    @EventHandler
    private void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntityType() == EntityType.ENDER_PEARL) {
            // process infinipearl landing actions
            if (Utilities.getEntityTag(event.getEntity(), "infini").equals("a")) {
                Player player = Bukkit.getPlayer(Utilities.getEntityTag(event.getEntity(), "uberplayername"));
                if (player == null) return;
                player.getInventory().addItem(UberItems.getItem("infina_pearl").makeItem(1));
            }
            // process timewarp pearl landing actions
            else if (Utilities.getEntityTag(event.getEntity(), "timewarp").equals("a")) {
                Player player = Bukkit.getPlayer(Utilities.getEntityTag(event.getEntity(), "uberplayername"));
                if (player == null) return;
                Location loc = player.getLocation().clone();
                Utilities.scheduleTask(() -> {
                    player.teleport(loc);
                    player.playSound(player.getLocation(), Sound.ENTITY_SHULKER_TELEPORT, 1, 1);
                }, 100);
            }
            // process healing pearl landing actions
            else if (Utilities.getEntityTag(event.getEntity(), "healing").equals("a")) {
                Player player = Bukkit.getPlayer(Utilities.getEntityTag(event.getEntity(), "uberplayername"));
                if (player == null) return;
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 1));
                player.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1, 1);
            }

            // process crude pearl possibilities
            else if (Utilities.getEntityTag(event.getEntity(), "explode").equals("a")) {
                event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 2);
            }
            else if (Utilities.getEntityTag(event.getEntity(), "pearlgrenade").equals("a")) {
                Player player = Bukkit.getPlayer(Utilities.getEntityTag(event.getEntity(), "uberplayername"));
                if (player == null) return;
                pearlGrenade(player, 10);
            }
            else if (Utilities.getEntityTag(event.getEntity(), "chickengrenade").equals("a")) {
                Player player = Bukkit.getPlayer(Utilities.getEntityTag(event.getEntity(), "uberplayername"));
                if (player == null) return;
                chickenGrenade(player, 5);
            }
        }

    }

    private static void pearlGrenade(Player player, int counter) {

        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_EGG_THROW, 2, 3f);

        EnderPearl pearl = player.launchProjectile(EnderPearl.class);
        Utilities.tagEntity(pearl, player.getName(), "uberplayername");
        Utilities.tagEntity(pearl, "a", "smalldmg");
        pearl.setVelocity(getRandomDirection());

        if (counter > 0) Utilities.scheduleTask(()-> { pearlGrenade(player, counter - 1); }, 1);
    }

    private static void chickenGrenade(Player player, int counter) {
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_CHICKEN_EGG, 2, 1);

        Entity e = player.getWorld().spawnEntity(player.getLocation(), EntityType.CHICKEN);
        e.setVelocity(getRandomDirection());
        if (counter > 0) Utilities.scheduleTask(()-> { chickenGrenade(player, counter - 1); }, 2);
    }

    private static Vector getRandomDirection() {
        Vector direction = new Vector();
        direction.setX(Math.random()*2-1d);
        direction.setY(Math.random());
        direction.setZ(Math.random()*2-1d);
        return direction.normalize().multiply(0.6);
    }

    @EventHandler
    private void onDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.ENDER_PEARL) {
            if (Utilities.getEntityTag(event.getDamager(), "healing").equals("a")) {
                event.setCancelled(true);
            }
            if (Utilities.getEntityTag(event.getDamager(), "smalldmg").equals("a")) {
                event.setDamage(2);
            }
        }
    }

}
