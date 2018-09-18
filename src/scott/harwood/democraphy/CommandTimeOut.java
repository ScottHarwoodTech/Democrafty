package scott.harwood.democraphy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class CommandTimeOut implements Runnable {
    private CommandExecuter command;
    public CommandTimeOut(CommandExecuter command) {
        this.command = command;
    }

    @Override
    public void run() {
        this.command.LockedOut = false;
        Bukkit.getServer().broadcastMessage("Command: '" +ChatColor.GREEN.toString()  + command.name + "' Is now reset");
    }
}
