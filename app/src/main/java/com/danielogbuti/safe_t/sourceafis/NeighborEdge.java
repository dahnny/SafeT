// Part of SourceAFIS: https://sourceafis.machinezoo.com
package com.danielogbuti.safe_t.sourceafis;

class NeighborEdge extends EdgeShape {
	final int neighbor;
	NeighborEdge(Minutia[] minutiae, int reference, int neighbor) {
		super(minutiae[reference], minutiae[neighbor]);
		this.neighbor = neighbor;
	}
}
