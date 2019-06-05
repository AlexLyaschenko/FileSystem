#pragma once

#include "sector.h"

using namespace std;

struct Track
{
	static const int sectorsAmount = 8;
	//static const int bytesAmount = sectorsAmount * Sector::bytesAmount;

	vector<Sector> sectors;

	Track()
	{
		sectors.reserve(sectorsAmount);
		for (int i = 0; i < sectorsAmount; i++)
		{
			sectors.emplace_back(Sector());
		}
	}
};
