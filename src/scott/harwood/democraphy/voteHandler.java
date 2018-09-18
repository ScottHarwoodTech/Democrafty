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
        if (task_running)
        {
            if (args[0] == "Y" || args[0] == "y")
            {
                Yay++;//one vote for yes
                //message to say that you voted
                
            }
            else if (args[0] == "n" || args[0] == "N")
            {
                Neigh++;
                //message neg vote
            }
            else
            {
                //message vote arg not right

            }
        }
        else{
            //message task not running
        }
        return false;
    }

}
