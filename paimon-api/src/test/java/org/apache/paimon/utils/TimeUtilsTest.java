/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.paimon.utils;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

/** Tests for {@link TimeUtils}. */
class TimeUtilsTest {

    @Test
    void testFormatWithHighestUnit() {
        assertThat(TimeUtils.formatWithHighestUnit(Duration.ZERO)).isEqualTo("0 ms");
        assertThat(TimeUtils.formatWithHighestUnit(Duration.ofMillis(60000))).isEqualTo("1 min");
        assertThat(TimeUtils.formatWithHighestUnit(Duration.ofHours(1).plusSeconds(1)))
                .isEqualTo("3601 s");
        assertThat(TimeUtils.formatWithHighestUnit(Duration.ofHours(23))).isEqualTo("23 h");
        assertThat(TimeUtils.formatWithHighestUnit(Duration.ofHours(36))).isEqualTo("36 h");
        assertThat(TimeUtils.formatWithHighestUnit(Duration.ofDays(1))).isEqualTo("1 d");
        assertThat(TimeUtils.formatWithHighestUnit(Duration.ofDays(7))).isEqualTo("7 d");
    }

    @Test
    void testFormattedDurationParsesBack() {
        for (Duration duration :
                new Duration[] {
                    Duration.ZERO, Duration.ofMinutes(90), Duration.ofDays(1), Duration.ofDays(30)
                }) {
            assertThat(TimeUtils.parseDuration(TimeUtils.formatWithHighestUnit(duration)))
                    .isEqualTo(duration);
        }
    }
}
