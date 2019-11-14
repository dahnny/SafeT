// Part of SourceAFIS: https://sourceafis.machinezoo.com
package com.danielogbuti.safe_t.sourceafis;

class Range {
	static final Range zero = new Range(0, 0);
	final int start;
	final int end;
	Range(int start, int end) {
		this.start = start;
		this.end = end;
	}
	int length() {
		return end - start;
	}
}
