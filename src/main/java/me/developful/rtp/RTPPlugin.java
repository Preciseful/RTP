package me.developful.rtp;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public final class RTPPlugin extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getCommand("rtp").setExecutor(this);
    }

    private float getRandomFloat(float min, float max) {
        Random rand = new Random();
        return rand.nextFloat((max - min) + 1) + min;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player))
            return false;

        var location = new Location(player.getWorld(), 0, -100, 0);

        while (location.y() == -100 || location.getBlock().isLiquid()) {
            var x = getRandomFloat(-500, 500);
            var z = getRandomFloat(-500, 500);
            location = new Location(player.getWorld(), x, 100, z);
            while (location.getBlock().isEmpty()
                    && !location.getBlock().isLiquid()
                    && location.y() > -100)
                location.subtract(0, 1, 0);
        }

        player.teleport(location.add(0, 1, 0));
        return true;
    }
}
