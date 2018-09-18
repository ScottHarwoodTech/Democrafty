package scott.harwood.democraphy;

import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class PollCalculator implements Runnable {
    private double PassP;
    private double EvalP;
    private long EvalTO;
    private long NotEvalTO;
    private String ExecuteCmd;
    public voteHandler votes;
    private JavaPlugin m;
    private CommandExecuter command;
    public PollCalculator(CommandExecuter name, double PassPercentage, double EvaluationPercentage, long EvaluatedTimeOut, long NotEvaluatedTimeOut, String ExecuteCommand, voteHandler votes_, JavaPlugin master) {
        PassP = PassPercentage;
        EvalP = EvaluationPercentage;
        EvalTO = EvaluatedTimeOut;
        NotEvalTO = NotEvaluatedTimeOut;
        ExecuteCmd = ExecuteCommand;
        votes = votes_;
        m = master;
        this.command = name;
    }

    @Override
    public void run() {//this will run when the voting time is stop.
        int numVotes = votes.Neigh + votes.Yay;//calculate the total number of people that voted
        int numplayers = m.getServer().getOnlinePlayers().size();
        if (numVotes/numplayers >= EvalP)//if enough people voted
        {
            if (votes.Yay / numVotes >= PassP){//see if enough people voted yay;
                Bukkit.getServer().broadcastMessage(ChatColor.GREEN.toString() + "Poll Passed");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), ExecuteCmd);
            }
            else
            {
                Bukkit.getServer().broadcastMessage(ChatColor.RED.toString() +"Poll Failed");

            }
            //set time out
            command.LockedOut = true; //lock out this vote
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(m,new CommandTimeOut(command),EvalTO);
            Bukkit.getServer().broadcastMessage(ChatColor.RED.toString() + "This command cannot be ran for another " + String.valueOf(EvalTO/20) + " Seconds");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.YELLOW.toString() +"Poll Failed");
            command.LockedOut = true; //lock out this vote
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(m,new CommandTimeOut(command),NotEvalTO);
            Bukkit.getServer().broadcastMessage(ChatColor.RED.toString() + "This command cannot be ran for another " + String.valueOf(NotEvalTO/20) + " Seconds");
            //set time out
        }
        votes.task_running = false;
        votes.Neigh = 0;
        votes.Yay = 0;
        votes.PlayerVotes.removeAll(votes.PlayerVotes);
    }
}
