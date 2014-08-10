/**
 * Copyright T. Maret (timothee.maret@gmail.com)
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
 * A solid represented by its hydrostatic characteristics.
 */
public interface Solid<T> extends Density<T>, Buoyancy<T> {

    /**
     * @return the volume of the solid in {@code m^-3}
     */
    @Nonnull
    T volume();

    /**
     * @return the mass of the solid in {@code kg}
     */
    @Nonnull
    T mass();

}
