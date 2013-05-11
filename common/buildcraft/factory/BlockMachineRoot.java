/**
 * Copyright (c) SpaceToad, 2011
 * http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package buildcraft.factory;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import buildcraft.core.CreativeTabBuildCraft;
import buildcraft.core.IMachine;

/**
 * BlockMachineRoot
 * 
 * Root of all machine type blocks
 * 
 * @author SpaceToad
 * @author BuildCraft team
 */
public abstract class BlockMachineRoot extends Block {

	protected BlockMachineRoot(int id, Material material) {
		super(id, material);
		this.setCreativeTab(CreativeTabBuildCraft.tabBuildCraft);
	}

	
    /**
     * How bright to render this block based on the light its receiving.
     */
	@Override
	public float getBlockBrightness(IBlockAccess world, int x, int y, int z) {
		for (int xPos = x - 1; xPos <= x + 1; xPos++) {
			for (int yPos = y - 1; yPos <= y + 1; yPos++) {
				for (int zPos = z - 1; zPos <= z + 1; zPos++) {
					TileEntity tile = world.getBlockTileEntity(xPos, yPos, zPos);

					if (tile instanceof IMachine && ((IMachine) tile).isActive()) {
						return super.getBlockBrightness(world, x, y, z) + 0.5F;
					}
				}
			}
		}
		return super.getBlockBrightness(world, x, y, z);
	}
	
	/**
	 * Returns a new instance of the TileEntity for this block.
	 */
	@Override
	public abstract TileEntity createTileEntity(World world, int meta);

	/**
	 * Returns true if this block has a TileEntity
	 */
	@Override
	public abstract boolean hasTileEntity(int meta);

}
