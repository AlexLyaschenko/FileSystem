#pragma once

#include "track.h"

using namespace std;

struct Block
{
	static const int tracksAmount = 3;
	//static const int bytesAmount = tracksAmount * Track::bytesAmount;

	vector<Track> tracks;

	Block()
	{
		tracks.reserve(tracksAmount);
		for (int i = 0; i < tracksAmount; i++)
		{
			tracks.emplace_back(Track());
		}
	}
};