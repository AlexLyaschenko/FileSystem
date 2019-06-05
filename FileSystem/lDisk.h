#pragma once

#include "block.h"

struct LDisk
{
	static const int blocksAmount = 4;
	//static const int bytesAmount = cylindersAmount * Cylinder::bytesAmount;

	std::vector<Block> blocks;

	LDisk()
	{
		blocks.reserve(blocksAmount);
		for (int i = 0; i < blocksAmount; i++)
		{
			blocks.emplace_back(Block());
		}
	}
};