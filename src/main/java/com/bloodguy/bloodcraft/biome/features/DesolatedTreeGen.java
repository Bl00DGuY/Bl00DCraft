package com.bloodguy.bloodcraft.biome.features;

import java.util.Random;

import com.bloodguy.bloodcraft.Main;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class DesolatedTreeGen extends WorldGenTrees {

	private final int field_48202_a;
	private final boolean field_48200_b;
	private final int field_48201_c;
	private final int field_48199_d;
	
	public DesolatedTreeGen(boolean flag) {
		this(flag, 4, 0, 0, false);
	}
	
	public DesolatedTreeGen(boolean flag, int i, int j, int k, boolean flag1) {
		super(flag);
		field_48202_a = i;
		field_48201_c = j;
		field_48199_d = k;
		field_48200_b = flag1;
	}
	
	@Override
	public boolean generate(World world, Random random, int i, int j, int k)
	{
		int l = random.nextInt(3) + field_48202_a;
		boolean flag = true;

		if (j < 1 || j + l + 1 > 256)
			return false;

		for (int i1 = j; i1 <= j + 1 + l; i1++)
		{
			byte byte0 = 1;

			if (i1 == j)
			{
				byte0 = 0;
			}

			if (i1 >= j + 1 + l - 2)
			{
				byte0 = 2;
			}

			for (int k1 = i - byte0; k1 <= i + byte0 && flag; k1++)
			{
				for (int i2 = k - byte0; i2 <= k + byte0 && flag; i2++)
				{
					if (i1 >= 0 && i1 < 256)
					{
						Block i3 = world.getBlock(k1, i1, i2);

						if (i3 != Blocks.air && i3 != Main.desolationLeaf && i3 != Blocks.dirt && i3 != Main.desolationLog)
						{
							flag = false;
						}
					}
					else
					{
						flag = false;
					}
				}
			}
		}

		if (!flag)
			return false;

		Block j1 = world.getBlock(i, j - 1, k);

		if (j1 != Blocks.dirt && j1 != Main.DesolatedDirt && j1 != Blocks.grass && j1 != Main.DesolatedGrass || j >= 256 - l - 1)
			return false;

		setBlockAndNotifyAdequately(world, i, j - 1, k, Main.desolationLog, l);
		byte byte1 = 3;
		int l1 = 0;

		for (int j2 = j - byte1 + l; j2 <= j + l; j2++)
		{
			int j3 = j2 - (j + l);
			int i4 = l1 + 1 - j3 / 2;

			for (int k4 = i - i4; k4 <= i + i4; k4++)
			{
				int i5 = k4 - i;

				for (int k5 = k - i4; k5 <= k + i4; k5++)
				{
					int l5 = k5 - k;

					if (Math.abs(i5) != i4 || Math.abs(l5) != i4 || random.nextInt(2) != 0 && j3 != 0)
					{
						Random rand=new Random(); 
						int x=rand.nextInt(1); 
						setBlockAndNotifyAdequately(world, k4, j2, k5, Main.desolationLeaf, 1);
					}
				}
			}
		}

		for (int k2 = 0; k2 < l; k2++)
		{
			Block k3 = world.getBlock(i, j + k2, k);

			if (k3 != Blocks.air && k3 != Main.desolationLeaf)
			{
				continue;
			}

			setBlockAndNotifyAdequately(world, i, j + k2, k, Main.desolationLog, 1);

			if (!field_48200_b || k2 <= 0)
			{
				continue;
			}

			if (random.nextInt(3) > 0 && world.isAirBlock(i - 1, j + k2, k))
			{
				setBlockAndNotifyAdequately(world, i - 1, j + k2, k, Main.DesolatedDirt, 8);
			}

			if (random.nextInt(3) > 0 && world.isAirBlock(i + 1, j + k2, k))
			{
				setBlockAndNotifyAdequately(world, i + 1, j + k2, k, Main.DesolatedDirt, 2);
			}

			if (random.nextInt(3) > 0 && world.isAirBlock(i, j + k2, k - 1))
			{
				setBlockAndNotifyAdequately(world, i, j + k2, k - 1, Main.DesolatedDirt, 1);
			}

			if (random.nextInt(3) > 0 && world.isAirBlock(i, j + k2, k + 1))
			{
				setBlockAndNotifyAdequately(world, i, j + k2, k + 1, Main.DesolatedDirt, 4);
			}
		}

		if (field_48200_b)
		{
			for (int l2 = j - 3 + l; l2 <= j + l; l2++)
			{
				int l3 = l2 - (j + l);
				int j4 = 2 - l3 / 2;

				for (int l4 = i - j4; l4 <= i + j4; l4++)
				{
					for (int j5 = k - j4; j5 <= k + j4; j5++)
					{
						if (world.getBlock(l4, l2, j5) != Main.desolationLog)
						{
							continue;
						}

						if (random.nextInt(4) == 0 && world.getBlock(l4 - 1, l2, j5) == Blocks.air)
						{
							func_48198_a(world, l4 - 1, l2, j5, 8);
						}

						if (random.nextInt(4) == 0 && world.getBlock(l4 + 1, l2, j5) == Blocks.air)
						{
							func_48198_a(world, l4 + 1, l2, j5, 2);
						}

						if (random.nextInt(4) == 0 && world.getBlock(l4, l2, j5 - 1) == Blocks.air)
						{
							func_48198_a(world, l4, l2, j5 - 1, 1);
						}

						if (random.nextInt(4) == 0 && world.getBlock(l4, l2, j5 + 1) == Blocks.air)
						{
							func_48198_a(world, l4, l2, j5 + 1, 4);
						}
					}
				}
			}
		}

		return true;
	}

	private void func_48198_a(World world, int i, int j, int k, int l)
	{
		setBlockAndNotifyAdequately(world, i, j, k, Main.DesolatedDirt, l);

		for (int i1 = 4; world.getBlockMetadata(i, --j, k) == 0 && i1 > 0; i1--)
		{
			setBlockAndNotifyAdequately(world, i, j, k, Main.DesolatedDirt, l);
		}
	}

	public void fertilize(World world, int x, int y, int z) {

	}

}
