// Part of SourceAFIS: https://sourceafis.machinezoo.com
package com.danielogbuti.safe_t.sourceafis;

class JsonMinutia {
	int x;
	int y;
	double direction;
	String type;
	JsonMinutia(Minutia minutia) {
		x = minutia.position.x;
		y = minutia.position.y;
		direction = minutia.direction;
		type = minutia.type.json;
	}
}
