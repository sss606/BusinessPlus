package ru.solomka.business.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.solomka.business.commands.utils.methods.message.Messages;
import ru.solomka.business.menu.Menu;
import ru.solomka.business.menu.enums.InventoryType;

public class OpenShopMenu implements CommandExecutor {

    private final Menu menu = new Menu();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(!p.hasPermission("Business.open")) {
            Messages.notHavePerm.replace("{permission}", "Business.open");
            return true;
        }
        menu.create(p,36, InventoryType.MAIN);
        return true;
    }
}