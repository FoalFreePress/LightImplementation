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

package org.sweetiebelle.lightimplementation.command.executor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sweetiebelle.lightimplementation.command.RegionCommand;

public class LightRegionCommandExecutor implements CommandExecutor {

    private RegionCommand regionCommand;

    public LightRegionCommandExecutor() {
        regionCommand = new RegionCommand();
    }

    @Override
    public boolean onCommand(CommandSender pSender, Command command, String label, String[] pArgs) {
        if (pArgs.length == 0)
            return false;
        if (pSender instanceof Player) {
            final Player player = (Player) pSender;
            if (pArgs.length > 2) {
                String subCommand = pArgs[0].toLowerCase();
                if (subCommand.equals("create")) {
                    if (pArgs.length != 2)
                        return false;
                    int lightLevel = Integer.parseInt(pArgs[1]);
                    return regionCommand.lightRegion(player, lightLevel);
                }
                if (subCommand.equals("delete"))
                    return regionCommand.delightRegion(player);
            }
            return false;
        }
        pSender.sendMessage(ChatColor.RED + "Sorry, you must be a player.");
        return true;

    }

}
