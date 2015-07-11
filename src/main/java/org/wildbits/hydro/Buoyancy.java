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
import javax.annotation.Nullable;

/**
 * Buoyancy of a fully immersed solid.
 */
public interface Buoyancy<T> {

    /**
     * Return the buoyancy of a fully immersed solid in a liquid with the given density.
     * @param ρ the density of the liquid containing the solid in {@code kg•m^-3}.
     * @return the resulting buoyancy in {@code kg} or {@code null} if undefined.
     *         A positive resulting buoyancy indicates the solid is positively buoyant.
     */
    @Nullable
    T buoyancy(@Nonnull Number ρ);

}
