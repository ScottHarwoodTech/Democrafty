package scott.harwood.democraphy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.UUID;

public class voteHandler implements CommandExecutor {
    //transfer obj for handling votes will have a public list of votes
    public int Yay;
    public int Neigh;
    public boolean task_running;
    public ArrayList<UUID> PlayerVotes = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        String message;
        if (sender instanceof Player) {
            //start the timeout possibly with a bar on the screen
            //check if vote running
            Player p = (Player) sender;
            if (task_running) {

                UUID u = p.getUniqueId();
                if (PlayerVotes.contains(u))
                {
                    p.sendMessage(ChatColor.RED.toString() + "You have already voted in this poll, you cannot vote again.");
                    return false;
                }

                if (args[0].equals("Y") || args[0].equals("y")) {
                    Yay++;//one vote for yes
                    //message to say that you voted
                    message = ChatColor.GOLD.toString() + "You voted" + ChatColor.GREEN.toString() + " 'YES'";
                    PlayerVotes.add(u);
                } else if (args[0].equals("n") || args[0].equals("N")) {
                    Neigh++;
                    //message neg vote
                    message = ChatColor.GOLD.toString() + "You voted" + ChatColor.RED.toString() + " 'No'";
                    PlayerVotes.add(u);
                } else {
                    //message vote arg not right
                    message = ChatColor.RED.toString() + "I'm sorry that was not a valid answer";
                }
            } else {
                //message task not running
                message = ChatColor.DARK_PURPLE.toString() +"I'm sorry there is not currently a poll running.";
            }
            p.sendMessage(message);//send the message string to the player
            return true;
        }
        return false;
    }

}
