/**
 * Copyright 2014 wildbits.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wildbits.hydro.impl;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.wildbits.hydro.SolidBuilder;
import org.wildbits.hydro.SolidBuilderFactory;

/**
 * Service to build {@code SolidBuilder} instances.
 */
@Service
@Component(metatype = false)
public class SolidBuilderFactoryImpl implements SolidBuilderFactory<BigDecimal> {

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public SolidBuilder<BigDecimal> getInstance() {
        return new SolidBuilderImpl();
    }
}
