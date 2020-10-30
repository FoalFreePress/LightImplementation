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
import org.sweetiebelle.lightimplementation.command.CreateLightCommand;

public class LightCreateLevelCommandExecutor implements CommandExecutor {

    private CreateLightCommand createLightCommand;

    public LightCreateLevelCommandExecutor() {
        createLightCommand = new CreateLightCommand();
    }

    @Override
    public boolean onCommand(CommandSender pSender, Command command, String label, String[] pArgs) {
        int x, y, z, lightLevel;
        if (pArgs.length == 4) {
            try {
                x = Integer.parseInt(pArgs[0]);
                y = Integer.parseInt(pArgs[1]);
                z = Integer.parseInt(pArgs[2]);
                lightLevel = Integer.parseInt(pArgs[3]);
            } catch (NumberFormatException ex) {
                pSender.sendMessage(ChatColor.RED + "String supplied, integer expected.");
                return false;
            }

            return createLightCommand.onCommand(pSender, null, x, y, z, lightLevel);
        }
        if (pArgs.length == 5) {
            try {
                x = Integer.parseInt(pArgs[1]);
                y = Integer.parseInt(pArgs[2]);
                z = Integer.parseInt(pArgs[3]);
                lightLevel = Integer.parseInt(pArgs[4]);
            } catch (NumberFormatException ex) {
                pSender.sendMessage(ChatColor.RED + "String supplied, integer expected.");
                return false;
            }

            return createLightCommand.onCommand(pSender, pArgs[0], x, y, z, lightLevel);
        }
        return false;

    }

}
