/*
 * Copyright 2025 Steven Gantz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stevenpg.instancio.locationtech.core.internal.generator.geom.utility;

/**
 * Record for storing bounds in a clearer way than pulling them from a geometry.
 * @param minLon minimum longitude of a bounding box
 * @param maxLon maximum longitude of a bounding box
 * @param minLat minimum latitude of a bounding box
 * @param maxLat maximum latitude of a bounding box
 */
public record BoundsRecord(double minLon, double maxLon, double minLat, double maxLat) {
}
