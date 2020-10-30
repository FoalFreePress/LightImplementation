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

import org.bukkit.plugin.java.JavaPlugin;
import org.sweetiebelle.lightimplementation.command.executor.LightCreateCommandExecutor;
import org.sweetiebelle.lightimplementation.command.executor.LightCreateLevelCommandExecutor;
import org.sweetiebelle.lightimplementation.command.executor.LightDebugCommandExecutor;
import org.sweetiebelle.lightimplementation.command.executor.LightDeleteCommandExecutor;
import org.sweetiebelle.lightimplementation.command.executor.LightRegionCommandExecutor;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import ru.beykerykt.lightapi.LightAPI;

public class LightImplementation extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!hasNecessaryPlugins()) {
            getLogger().severe("Dependent plugins not found!!! Are they not installed?");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getLogger().info("I found my friends WorldEdit and LightAPI :D");
        getCommand("lightcreate").setExecutor(new LightCreateCommandExecutor());
        getCommand("lightdelete").setExecutor(new LightDeleteCommandExecutor());
        getCommand("lightcreatelevel").setExecutor(new LightCreateLevelCommandExecutor());
        getCommand("lightregion").setExecutor(new LightRegionCommandExecutor());
        getCommand("lightdebug").setExecutor(new LightDebugCommandExecutor());
    }

    private boolean hasNecessaryPlugins() {
        try {
            JavaPlugin.getPlugin(WorldEditPlugin.class);
            JavaPlugin.getPlugin(LightAPI.class);
            return true;
        } catch (Throwable ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
