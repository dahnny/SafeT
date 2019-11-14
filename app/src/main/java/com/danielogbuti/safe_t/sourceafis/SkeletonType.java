// Part of SourceAFIS: https://sourceafis.machinezoo.com
package com.danielogbuti.safe_t.sourceafis;

enum SkeletonType {
	RIDGES("ridges-"), VALLEYS("valleys-");
	final String prefix;
	SkeletonType(String prefix) {
		this.prefix = prefix;
	}
}
