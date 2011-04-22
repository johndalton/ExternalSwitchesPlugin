
package info.johndalton.bukkit.externalswitches;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.material.Lever;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
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
    public void onBlockPhysics(BlockPhysicsEvent event) {
        Block block = event.getBlock();

        if ((block.getType() == Material.SAND) || (block.getType() == Material.GRAVEL)) {
            Block above = block.getFace(BlockFace.UP);
            if (above.getType() == Material.IRON_BLOCK) {
                event.setCancelled(true);
            }
        }
    }

    @Override
    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
       	Block b = event.getBlock();
        Material mat = b.getType();

        if (mat.equals(Material.LEVER)) {
	    Lever l = new Lever(mat);
            System.out.println( "Lever at (" + b.getX() + "," + b.getY() + "," + b.getZ() + ") switched "
	    			+ (event.getOldCurrent() < event.getNewCurrent() ? "on" : "off") );
        }
    }

    @Override
    public void onBlockCanBuild(BlockCanBuildEvent event) {
        Material mat = event.getMaterial();

        if (mat.equals(Material.CACTUS)) {
            event.setBuildable(true);
        }
    }
}
