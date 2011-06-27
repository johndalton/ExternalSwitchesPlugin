
package info.johndalton.bukkit.externalswitches;

import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRedstoneEvent;

/**
 * ExternalSwitches block listener
 * @author John Dalton
 */
public class ExternalSwitchesBlockListener extends BlockListener {
    private final ExternalSwitchesPlugin plugin;

    public ExternalSwitchesBlockListener(final ExternalSwitchesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
       	Block b = event.getBlock();
	Material mat = b.getType();
 
	if (mat.equals(Material.LEVER)) {
            System.out.println( "Lever at (" + b.getX() + "," + b.getY() + "," + b.getZ() + ") switched "
	    			+ (event.getOldCurrent() < event.getNewCurrent() ? "on" : "off") );
        }
    }

}
