
package info.johndalton.bukkit.externalswitches;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.material.Lever;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handler for the /switchtoggle command.
 * @author John Dalton
 */
public class ExternalSwitchesToggleCommand implements CommandExecutor {
    private final ExternalSwitchesPlugin plugin;

    public ExternalSwitchesToggleCommand(ExternalSwitchesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {

	// Only Ops should be permitted to use this.
	if ( ! sender.isOp() ) {
	    sender.sendMessage("You don't have permission to use this command.");
	    return false;
	}

	// We need a world instance in order to be able to grab the block, I think.
	World world;
	if (sender instanceof Player) {
		Player player = (Player) sender;
		world = player.getWorld();
	} else {
		Server server = sender.getServer();
		// FIXME: hard-coded world name is horrible! How to deal with multiple worlds??
		world = server.getWorld("world");
	}

	// We expect 3 args: x y z
	if (split.length == 3) {
	    try {
		int x = Integer.parseInt(split[0]);
		int y = Integer.parseInt(split[1]);
		int z = Integer.parseInt(split[2]);

		Block b = world.getBlockAt(x, y, z);
		Material mat = b.getType();
		// We only care about Levers, for now.
		if (mat.equals(Material.LEVER)) {
		    //Lever l = new Lever(mat);
		    b.setData( (byte) (b.getData() ^ 0x08) );
		    sender.sendMessage("Toggling lever at ("+x+","+y+","+z+")");
		} else {
		    sender.sendMessage("There's no lever at those coordinates!");
		}
	    } catch (NumberFormatException ex) {
		sender.sendMessage("Those coordinates don't make any sense!");
	    }
	    return true;
	} else {
	    return false;
	}
    }
}
