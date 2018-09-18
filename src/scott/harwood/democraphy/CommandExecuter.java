package scott.harwood.democraphy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandExecuter extends BukkitCommand {
    private double PassP;
    private double EvalP;
    private long EvalTO;
    private long NotEvalTO;
    private long votetime;
    private String ExecuteCmd;
    public voteHandler votes;
    private JavaPlugin m;
    public String name;
    public boolean LockedOut;
    private String cmdMessage;
    public CommandExecuter(String name, double PassPercentage, double EvaluationPercentage, long EvaluatedTimeOut, long NotEvaluatedTimeOut, String ExecuteCommand, voteHandler votes_, JavaPlugin master,long vTime,String cmdMessage)
    {
        super(name);
        PassP = PassPercentage;
        EvalP = EvaluationPercentage;
        EvalTO = EvaluatedTimeOut;
        NotEvalTO = NotEvaluatedTimeOut;
        ExecuteCmd = ExecuteCommand;
        votes = votes_;
        votetime = vTime;
        m = master;
        this.name = name;
        this.cmdMessage = cmdMessage;
    }
    @Override
    public boolean execute(CommandSender sender, String alias, String[] args)
    {
        if (!LockedOut)
        {
        if (votes.task_running)//checks if there is a prexisting task running
        {
            return false;
        }
        votes.task_running = true;//tells the voteing command that there is a poll going
            Bukkit.getServer().broadcastMessage(cmdMessage);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(m, new PollCalculator(this,PassP,EvalP,EvalTO,NotEvalTO,ExecuteCmd,votes,m),votetime);
        return true;
    }
    return false;
    }
}
