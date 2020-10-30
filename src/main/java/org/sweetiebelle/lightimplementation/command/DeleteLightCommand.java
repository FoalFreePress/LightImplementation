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

import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sweetiebelle.lightimplementation.API;

public class DeleteLightCommand {

    private boolean onCommand(Player player, int x, int y, int z) {
        if (!API.API.checkPermissions(player)) {
            player.sendMessage(API.NO_PERMISSION);
            return true;
        }
        API.API.deleteLight(player.getWorld(), x, y, z);
        if (API.API.hasDebugger(player))
            player.sendMessage(ChatColor.GREEN + "Task complete.");
        return true;
    }

    public boolean onCommand(CommandSender sender, @Nullable String world, int x, int y, int z) {
        if (sender instanceof Player)
            return this.onCommand((Player) sender, x, y, z);
        if (world == null) {
            sender.sendMessage(ChatColor.RED + "As you are console, you must first specifiy world.");
            return false;
        }
        Optional<World> oWorld = API.API.getWorld(world);
        if (!oWorld.isPresent()) {
            sender.sendMessage(ChatColor.RED + world + " isn't loaded.");
            return true;
        }
        API.API.deleteLight(oWorld.get(), x, y, z);
        if (API.API.hasDebugger(sender))
            sender.sendMessage(ChatColor.GREEN + "Task complete.");
        return true;
    }
}
