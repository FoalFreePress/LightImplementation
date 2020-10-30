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

package org.sweetiebelle.lightimplementation.command;

import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.sweetiebelle.lightimplementation.API;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;

public class RegionCommand {

    public boolean delightRegion(Player player) {
        if (!API.API.checkPermissions(player)) {
            player.sendMessage(API.NO_PERMISSION);
            return true;
        }

        Optional<Region> oRegion = API.API.getWorldEditRegion(player);
        if (!oRegion.isPresent()) {
            player.sendMessage(ChatColor.RED + "You must have a WorldEdit selection.");
            return true;
        }
        Region region = oRegion.get();
        for (BlockVector3 block : region)
            API.API.deleteLight(player.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ());
        if (API.API.hasDebugger(player))
            player.sendMessage(ChatColor.GREEN + "Task complete.");
        return true;
    }

    public boolean lightRegion(Player player, int lightLevel) {
        if (!API.API.checkPermissions(player)) {
            player.sendMessage(API.NO_PERMISSION);
            return true;
        }

        Optional<Region> oRegion = API.API.getWorldEditRegion(player);
        if (!oRegion.isPresent()) {
            player.sendMessage(ChatColor.RED + "You must have a WorldEdit selection.");
            return true;
        }
        Region region = oRegion.get();
        for (BlockVector3 block : region)
            API.API.createLight(player.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ(), lightLevel);
        if (API.API.hasDebugger(player))
            player.sendMessage(ChatColor.GREEN + "Task complete.");
        return true;
    }
}
