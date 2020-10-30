/*
 * Modified MIT License (MIT)
 *
 * Copyright (c) 2015 shroomdog27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, and sublicense
 * the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.sweetiebelle.lightimplementation;

import java.util.ArrayList;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.session.SessionManager;

import ru.beykerykt.lightapi.LightAPI;
import ru.beykerykt.lightapi.LightType;

public final class API {
    public static final API API = new API();
    /**
     * Default message to provide to a player that they do not have permission to
     * execute a command.
     */
    public static final String NO_PERMISSION = ChatColor.RED + "You do not have permission to use this command.";
    private final ArrayList<CommandSender> debuggers;
    private final WorldEditPlugin worldEditPlugin;

    private API() {
        debuggers = new ArrayList<CommandSender>(0);
        worldEditPlugin = JavaPlugin.getPlugin(WorldEditPlugin.class);
    }

    public final boolean addDebugger(CommandSender sender) {
        return debuggers.add(sender);
    }

    public final boolean checkPermissions(Permissible sender) {
        return sender.hasPermission("light.use");
    }

    public final boolean createLight(Location location, int lightlevel) {
        return LightAPI.createLight(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), LightType.BLOCK, lightlevel, false);
    }

    public final boolean createLight(World world, int x, int y, int z, int lightLevel) {
        return LightAPI.createLight(world, x, y, z, LightType.BLOCK, lightLevel, false);
    }

    public final boolean deleteLight(Location location) {
        return LightAPI.deleteLight(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), LightType.BLOCK, false);
    }

    public final boolean deleteLight(World world, int x, int y, int z) {
        return LightAPI.deleteLight(world, x, y, z, LightType.BLOCK, false);
    }

    public final Optional<World> getWorld(String worldName) {
        return Optional.ofNullable(Bukkit.getWorld(worldName));
    }

    public final Optional<Region> getWorldEditRegion(Player player) {
        WorldEdit worldEdit = worldEditPlugin.getWorldEdit();
        SessionManager sessionManager = worldEdit.getSessionManager();
        BukkitPlayer worldEditPlayer = new BukkitPlayer(player);
        LocalSession localSession = sessionManager.getIfPresent(worldEditPlayer);
        BukkitWorld world = new BukkitWorld(player.getWorld());
        RegionSelector regionSelector = localSession.getRegionSelector(world);

        try {
            return Optional.ofNullable(regionSelector.getRegion());
        } catch (IncompleteRegionException e) {
            return Optional.empty();
        }
    }

    public final boolean hasDebugger(CommandSender sender) {
        return debuggers.contains(sender);
    }

    public final boolean removeDebugger(CommandSender sender) {
        return debuggers.remove(sender);
    }
}
