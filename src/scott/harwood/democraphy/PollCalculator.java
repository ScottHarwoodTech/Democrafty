package scott.harwood.democraphy;

import org.bukkit.Bukkit;

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
    public PollCalculator(double PassPercentage, double EvaluationPercentage, long EvaluatedTimeOut, long NotEvaluatedTimeOut, String ExecuteCommand, voteHandler votes_,JavaPlugin master) {
        PassP = PassPercentage;
        EvalP = EvaluationPercentage;
        EvalTO = EvaluatedTimeOut;
        NotEvalTO = NotEvaluatedTimeOut;
        ExecuteCmd = ExecuteCommand;
        votes = votes_;
        m = master;
    }

    @Override
    public void run() {//this will run when the voting time is stop.
        Logger Log = Bukkit.getLogger();
        Log.info("Stop Voting");
        int numVotes = votes.Neigh + votes.Yay;//calculate the total number of people that voted
        int numplayers = m.getServer().getOnlinePlayers().size();
        if (numVotes/numplayers >= EvalP)//if enough people voted
        {
            if (votes.Yay / numVotes >= PassP){//see if enough people voted yay;
                Log.info("Poll Passed");
            }
            else
            {
                Log.info("Poll Failed");
            }
        }
        else
        {
            Log.info("Poll did not get enough votes");
        }
        votes.task_running = false;
        votes.Neigh = 0;
        votes.Yay = 0;

    }
}
