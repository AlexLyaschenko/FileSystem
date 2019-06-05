#pragma once

#include <vector>

using namespace std;

struct Sector
{
	static const int bytesAmount = 64;

	vector<char> bytes;

	Sector()
	{
		bytes.resize(bytesAmount);
	}
};