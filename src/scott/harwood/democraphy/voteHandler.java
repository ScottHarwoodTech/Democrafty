package scott.harwood.democraphy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class voteHandler implements CommandExecutor {
    //transfer obj for handling votes will have a public list of votes
    public int Yay;
    public int Neigh;
    public boolean task_running;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        //start the timeout possibly with a bar on the screen
        //check if vote running
        //when the time out
        return false;
    }

}
