package scott.harwood.democraphy;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class democraphy extends JavaPlugin {
    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable()
    {
            voteHandler votes = new voteHandler();
            //start parsing json
            JSONArray commands = loadData(".\\plugins\\Democrafty-config.json");
            try {
                Field spigotCM = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                spigotCM.setAccessible(true);
                CommandMap cm = (CommandMap) spigotCM.get(Bukkit.getServer());
                for (Object command : commands) {
                    JSONObject c = (JSONObject) command;
                    String IC = (String) c.get("InitiateCommand");//get the command that starts it all
                    double PassP = (double) c.get("PassPercentage");//gets the percentage of people that need to vote yes
                    double EP = (double) c.get("EvaluationPercentage");//gets the precentage of peeopl that need to vote to consider the vote
                    Long EvalTO = (Long) c.get("EvaluatedTimeOut");//gets how long to wait after command executed before can try again
                    Long NEvalTO = (Long) c.get("NotEvaluatedTimeOut");
                    String EC = (String) c.get("ExecuteCommand");//command executed on pass
                    cm.register(IC, new CommandExecuter(IC, PassP, EP, EvalTO, NEvalTO, EC, votes));

                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
    }
    private JSONArray loadData(String FileName)
    {
        if (new File(FileName).isFile()) {
            JSONParser parser = new JSONParser();
            try {
                Object object = parser.parse(new FileReader(FileName));
                JSONObject ConfigObj = (JSONObject) object;
                //iterate over each command and add a listener.
                return (JSONArray) ConfigObj.get("Commands");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        else{
            //create file
            Bukkit.getLogger().info("Did not find " + FileName + " Creating it!");
            JSONObject tempFile = new JSONObject();
            JSONArray EmptyArray = new JSONArray();
            tempFile.put("Commands",EmptyArray);
            try (FileWriter file = new FileWriter(FileName)) {

                file.write(tempFile.toJSONString());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return EmptyArray;
        }
        return new JSONArray();
    }

}
