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
package org.wildbits.hydro;

import javax.annotation.Nonnull;

/**
 * The {@code SolidBuilder} allows to build {@code Solid} instances.
 */
public interface SolidBuilder<T> {

    /**
     * Add a volume/mass pair to the solid being built.
     * @param volume the volume to add in {@code m^-3}
     * @param mass the mass to add in {@code kg}
     * @return {@code this}
     */
    @Nonnull
    SolidBuilder<T> add(@Nonnull Number volume, @Nonnull Number mass);

    /**
     * @return a new {@link org.wildbits.hydro.Solid} instance containing the added volumes/masses.
     * @throws IllegalArgumentException if no volume/mass has been added
     */
    @Nonnull
    Solid<T> build() throws IllegalArgumentException;

}
