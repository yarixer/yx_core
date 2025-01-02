package org.yanix.yx_core.api;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class testCl {
    public void gamePlayerJoined(Player player){
        player.sendMessage(ChatColor.YELLOW + "Hey, welcome!");
    }
}
