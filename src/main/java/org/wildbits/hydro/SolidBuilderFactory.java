/**
 * Copyright wildbits org
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
 * The {@code SolidBuilderFactory} allows to build instances of {@code SolidBuilder}.
 */
public interface SolidBuilderFactory<T> {

    /**
     * @return a new instance of {@code SolidBuilder}
     */
    @Nonnull
    SolidBuilder<T> getInstance();

}