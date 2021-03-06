package ru.solomka.business.menu.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import ru.solomka.business.config.utils.CustomConfig;
import ru.solomka.business.menu.Menu;
import ru.solomka.business.menu.enums.InventoryType;

public class MainMenu implements Listener {

    private final Menu menu = new Menu();
    private final CustomConfig data = new CustomConfig("data");

    @EventHandler
    public void click(@NotNull InventoryClickEvent e) {
        Inventory click = e.getClickedInventory();
        Player p = (Player) e.getWhoClicked();
        int slot = e.getSlot();

        if (p.getOpenInventory().getTitle().contains("Главное меню")) {
            if (click == null) return;
            if (slot <= 36) e.setCancelled(true);
            switch (slot) {
                case 13 : {
                    menu.create(p, 36, InventoryType.CHANGE_CLASS);
                }
            }
        }
    }
}
