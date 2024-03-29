// Part of SourceAFIS: https://sourceafis.machinezoo.com
package com.danielogbuti.safe_t.sourceafis;

import java.util.List;

import java8.util.J8Arrays;
import java8.util.function.Function;
import java8.util.stream.Collectors;

class JsonPairing {
	JsonPair root;
	List<JsonEdge> tree;
	List<JsonEdge> support;
	JsonPairing(int count, MinutiaPair[] pairs, List<JsonEdge> supporting) {
		root = new JsonPair(pairs[0].probe, pairs[0].candidate);
		tree = J8Arrays.stream(pairs).limit(count).skip(1).map(new Function<MinutiaPair, JsonEdge>() {
			@Override
			public JsonEdge apply(MinutiaPair pair) {
				return new JsonEdge(pair);
			}
		}).collect(Collectors.<JsonEdge>toList());
		support = supporting;
	}
}
