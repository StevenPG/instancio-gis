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

    // TODO - review
    protected boolean coordinateProvided() {
        return inputLatitude != null || inputLongitude != null;
    }

    protected boolean envelopeProvided() {
        return inputEnvelope != null;
    }

    public Double getInputLatitude() {
        return inputLatitude;
    }

    public void setInputLatitude(Double inputLatitude) {
        this.inputLatitude = inputLatitude;
    }

    public Double getInputLongitude() {
        return inputLongitude;
    }

    public void setInputLongitude(Double inputLongitude) {
        this.inputLongitude = inputLongitude;
    }

    public Envelope getInputEnvelope() {
        return inputEnvelope;
    }

    public void setInputEnvelope(Envelope inputEnvelope) {
        this.inputEnvelope = inputEnvelope;
    }
}
