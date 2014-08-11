package com.bloodguy.bloodcraft.block;

import java.util.Random;

import com.bloodguy.bloodcraft.Main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDesolatedGrass extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon iconGrassTop;
	@SideOnly(Side.CLIENT)
	private IIcon iconSnowOverlay;
	@SideOnly(Side.CLIENT)
	private static IIcon iconGrassSideOverlay;
	@SideOnly(Side.CLIENT)
	private IIcon iconGrassBottom;
	
	public BlockDesolatedGrass(Material p_i45394_1_) {
		super(p_i45394_1_);
		setTickRandomly(true);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	
	public IIcon getIcon(int par1, int par2)
	{
		return par1 == 1 ? iconGrassTop : par1 == 0 ? Blocks.dirt.getBlockTextureFromSide(par1) : blockIcon;
	}
	
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);

		if (par5Random.nextInt(10) == 0)
		{
			par1World.spawnParticle("depthsuspend", par2 + par5Random.nextFloat(), par3 + 1.1F, par4 + par5Random.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
	}
	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote)
		{
			if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && par1World.getBlockLightOpacity(par2, par3 + 1, par4) > 2)
			{
				par1World.setBlock(par2, par3, par4, Main.DesolatedDirt);
			}
			else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
			{
				for (int l = 0; l < 4; ++l)
				{
					int i1 = par2 + par5Random.nextInt(3) - 1;
					int j1 = par3 + par5Random.nextInt(5) - 3;
					int k1 = par4 + par5Random.nextInt(3) - 1;

					if (par1World.getBlock(i1, j1, k1) == Blocks.dirt && par1World.getBlockMetadata(i1, j1, k1) == 0 && par1World.getBlockLightValue(i1, j1 + 1, k1) >= 4 && par1World.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
					{
						par1World.setBlock(i1, j1, k1, Main.DesolatedGrass);
					}
				}
			}
		}
	}

	//	@Override
	//	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	//	{
	//		Block plant = plantable.getPlant(world, x, y + 1, z);
	//		if (plant == AbyssalCraft.DLTSapling)
	//		{
	//			return true;
	//		}
	//		return true;
	//	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return Blocks.dirt.getItemDropped(0, par2Random, par3);
	}

	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
	 */
	public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		if (par5 == 1)
			return iconGrassTop;
		else if (par5 == 0)
			return iconGrassBottom;
		else
		{
			Material material = par1IBlockAccess.getBlock(par2, par3 + 1, par4).getMaterial();
			return material != Material.snow && material != Material.craftedSnow ? blockIcon : iconSnowOverlay;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		blockIcon = par1IconRegister.registerIcon("Bl00DCraft:DesolatedGrassSide");
		iconGrassTop = par1IconRegister.registerIcon("Bl00DCraft:DesolatedGrassTop");
		iconGrassBottom = par1IconRegister.registerIcon("Bl00DCraft:DesolatedDirt");
		iconSnowOverlay = par1IconRegister.registerIcon("grass_side_snowed");
		//Darklandsgrass.iconGrassSideOverlay = par1IconRegister.registerIcon("Bl00DCraft:DesolatedGrassSide");
	}

	@SideOnly(Side.CLIENT)
	public static IIcon getIconSideOverlay()
	{
		return iconGrassSideOverlay;
	}

}
