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

package com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl;

import org.locationtech.jts.geom.Envelope;

/**
 * Base class for generators that generate an Envelopable geometry that is based on a latitude and longitude.
 */
public class LatLonEnvelopableBaseGenerator {

    private Double inputLatitude;
    private Double inputLongitude;
    private Envelope inputEnvelope;

    /**
     * Default constructor.
     */
    public LatLonEnvelopableBaseGenerator() {}

    /**
     * Checks if a coordinate is missing.
     * @return true if a coordinate was not provided, false otherwise.
     */
    protected boolean coordinateMissing() {
        return inputLatitude == null || inputLongitude == null;
    }

    /**
     * Checks if an envelope has been provided.
     * @return true if an envelope has been provided, false otherwise.
     */
    protected boolean envelopeProvided() {
        return inputEnvelope != null;
    }

    /**
     * Returns the latitude input value.
     * @return latitude value
     */
    public Double getInputLatitude() {
        return inputLatitude;
    }

    /**
     * Sets the latitude input value.
     * @param inputLatitude latitude value
     */
    public void setInputLatitude(Double inputLatitude) {
        this.inputLatitude = inputLatitude;
    }

    /**
     * Returns the longitude input value.
     * @return longitude value
     */
    public Double getInputLongitude() {
        return inputLongitude;
    }

    /**
     * Sets the longitude input value.
     * @param inputLongitude longitude value
     */
    public void setInputLongitude(Double inputLongitude) {
        this.inputLongitude = inputLongitude;
    }

    /**
     * Returns the envelope input value.
     * @return envelope value
     */
    public Envelope getInputEnvelope() {
        return inputEnvelope;
    }

    /**
     * Sets the envelope input value.
     * @param inputEnvelope envelope value
     */
    public void setInputEnvelope(Envelope inputEnvelope) {
        this.inputEnvelope = inputEnvelope;
    }
}
