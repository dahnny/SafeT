// Part of SourceAFIS: https://sourceafis.machinezoo.com
package com.danielogbuti.safe_t.sourceafis;

class Parameters {
	static final int blockSize = 15;
	static final double dpiTolerance = 5;
	static final int histogramDepth = 256;
	static final double clippedContrast = 0.08;
	static final double minAbsoluteContrast = 17 / 255.0;
	static final double minRelativeContrast = 0.34;
	static final int relativeContrastSample = 168568;
	static final double relativeContrastPercentile = 0.49;
	static final int contrastVoteRadius = 9;
	static final double contrastVoteMajority = 0.86;
	static final int contrastVoteBorderDistance = 7;
	static final int maskVoteRadius = 7;
	static final double maskVoteMajority = 0.51;
	static final int maskVoteBorderDistance = 4;
	static final int blockErrorsVoteRadius = 1;
	static final double blockErrorsVoteMajority = 0.7;
	static final int blockErrorsVoteBorderDistance = 4;
	static final double maxEqualizationScaling = 3.99;
	static final double minEqualizationScaling = 0.25;
	static final double minOrientationRadius = 2;
	static final double maxOrientationRadius = 6;
	static final int orientationSplit = 50;
	static final int orientationsChecked = 20;
	static final int orientationSmoothingRadius = 1;
	static final int parallelSmoothinigResolution = 32;
	static final int parallelSmoothinigRadius = 7;
	static final double parallelSmoothinigStep = 1.59;
	static final int orthogonalSmoothinigResolution = 11;
	static final int orthogonalSmoothinigRadius = 4;
	static final double orthogonalSmoothinigStep = 1.11;
	static final int binarizedVoteRadius = 2;
	static final double binarizedVoteMajority = 0.61;
	static final int binarizedVoteBorderDistance = 17;
	static final int innerMaskBorderDistance = 14;
	static final double maskDisplacement = 10.06;
	static final int minutiaCloudRadius = 20;
	static final int maxCloudSize = 4;
	static final int maxMinutiae = 100;
	static final int sortByNeighbor = 5;
	static final int edgeTableRange = 490;
	static final int edgeTableNeighbors = 9;
	static final int thinningIterations = 26;
	static final int maxPoreArm = 41;
	static final int shortestJoinedEnding = 7;
	static final int maxRuptureSize = 5;
	static final int maxGapSize = 20;
	static final int gapAngleOffset = 22;
	static final int toleratedGapOverlap = 2;
	static final int minTailLength = 21;
	static final int minFragmentLength = 22;
	static final int maxDistanceError = 13;
	static final double maxAngleError = Math.toRadians(10);
	static final double maxGapAngle = Math.toRadians(45);
	static final int ridgeDirectionSample = 21;
	static final int ridgeDirectionSkip = 1;
	static final int maxTriedRoots = 70;
	static final int minRootEdgeLength = 58;
	static final int maxRootEdgeLookups = 1633;
	static final int minSupportingEdges = 1;
	static final double distanceErrorFlatness = 0.69;
	static final double angleErrorFlatness = 0.27;
	static final double pairCountScore = 0.032;
	static final double pairFractionScore = 8.98;
	static final double correctTypeScore = 0.629;
	static final double supportedCountScore = 0.193;
	static final double edgeCountScore = 0.265;
	static final double distanceAccuracyScore = 9.9;
	static final double angleAccuracyScore = 2.79;
	static final double thresholdMaxFMR = 8.48;
	static final double thresholdFMR2 = 11.12;
	static final double thresholdFMR10 = 14.15;
	static final double thresholdFMR100 = 18.22;
	static final double thresholdFMR1000 = 22.39;
	static final double thresholdFMR10_000 = 27.24;
	static final double thresholdFMR100_000 = 32.01;
}
