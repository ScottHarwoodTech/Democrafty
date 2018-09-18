package scott.harwood.democraphy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

public class CommandExecuter extends BukkitCommand {
    private double PassP;
    private double EvalP;
    private long EvalTO;
    private long NotEvalTO;
    private String ExecuteCmd;
    public voteHandler votes;
    public CommandExecuter(String name,double PassPercentage,double EvaluationPercentage,long EvaluatedTimeOut, long NotEvaluatedTimeOut, String ExecuteCommand,voteHandler votes_)
    {
        super(name);
        PassP = PassPercentage;
        EvalP = EvaluationPercentage;
        EvalTO = EvaluatedTimeOut;
        NotEvalTO = NotEvaluatedTimeOut;
        ExecuteCmd = ExecuteCommand;
        votes = votes_;
    }
    @Override
    public boolean execute(CommandSender sender, String alias, String[] args)
    {
        if (votes.task_running)//checks if there is a prexisting task running
        {
            return false;
        }
        votes.task_running = true;//tells the voteing command that there is a poll going

        return true;
    }
}
