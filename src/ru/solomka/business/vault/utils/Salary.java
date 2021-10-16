package ru.solomka.business.vault.utils;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.solomka.business.Main;
import ru.solomka.business.config.utils.CustomConfig;
import ru.solomka.business.core.HandlerBusiness;
import ru.solomka.business.core.message.Messages;
import ru.solomka.business.vault.VaultHandler;

import static ru.solomka.business.config.ConfigManager.getInt;
import static ru.solomka.business.core.message.utils.ListHandler.buildList;

public class Salary {

    private int salary = 0;

    private final HandlerBusiness business = new HandlerBusiness();
    private final CustomConfig custom = new CustomConfig("messages");

    public int salary(Player p) {
        for(int i : business.getAllHaveBiz(p)) {
            salary += getInt("Business.Id." + i + ".Salary");
        }
        return salary;
    }

    public void give(Player p) {
        if(p == null) return;
        new BukkitRunnable() {
            @SneakyThrows
            @Override
            public void run() {
                if(p.getStatistic(Statistic.PLAY_ONE_TICK) < 72000) {
                    Messages.playTime.replace("{need_time}", "1 час").send(p);
                    return;
                }
                new VaultHandler().giveSalary(p, salary(p));
                buildList(custom.getList("Messages.Money.Payday"), p);
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0,3600 * 20L);
    }

    public void onStart() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    give(p);
                }
            }
        }.runTaskLaterAsynchronously(Main.getInstance(), 3600 * 20L);
    }
}