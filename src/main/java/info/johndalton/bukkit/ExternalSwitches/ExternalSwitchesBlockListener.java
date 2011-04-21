
package com.dinnerbone.bukkit.sample;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

/**
 * Sample block listener
 * @author Dinnerbone
 */
public class SampleBlockListener extends BlockListener {
    private final SamplePlugin plugin;

    public SampleBlockListener(final SamplePlugin plugin) {
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
            System.out.println( "Lever at chunk " + b.getChunk() + " (" +b.getX()+","+b.getY()+","+b.getZ()+") is switched "
            					+ (b.isBlockPowered() ? "on" : "off"));
            System.out.println( "b is " + b + " and mat is " + mat );

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
