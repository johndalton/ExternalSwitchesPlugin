
package info.johndalton.bukkit.externalswitches;

import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handler for the /debug sample command.
 * @author SpaceManiac
 */
public class SampleDebugCommand implements CommandExecutor {
    private final ExternalSwitchesPlugin plugin;

    public SampleDebugCommand(ExternalSwitchesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            plugin.setDebugging(player, !plugin.isDebugging(player));

            return true;
        } else {
            return false;
        }
    }
}
