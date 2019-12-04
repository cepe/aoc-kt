#!/usr/bin/env bash

if [[ $# -ne 1 ]]; then
  echo "Usage: init.sh [year]"
  exit 1
fi

if ! [[ $1 =~ ^20[0-9]{2}$ ]]; then
  echo "Year should be in [2000, 2099]"
  exit 1
fi

YEAR=$1
SCRIPT_LOCATION="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
SOLUTIONS_LOCATION="$SCRIPT_LOCATION/src/main/kotlin/ml/szloch/aoc/e$YEAR"
TESTS_LOCATION="$SCRIPT_LOCATION/src/test/kotlin/ml/szloch/aoc/e$YEAR"
INPUT_FILES_LOCATION="$SCRIPT_LOCATION/src/main/resources/ml/szloch/aoc/e$YEAR"

function create_directories_for_inputs() {
  for day in {01..25}; do
    mkdir -pv "$INPUT_FILES_LOCATION/day$day"
  done
}

function create_directories_for_solutions() {
  for day in {01..25}; do
    mkdir -pv "$SOLUTIONS_LOCATION/day$day"
    SOLUTION_FILE="$SOLUTIONS_LOCATION/day$day/Day$day.kt"
    if [[ -f "$SOLUTION_FILE" ]]; then
      echo "Skipping file $SOLUTION_FILE"
    else
      echo "Creating file $SOLUTION_FILE"
      cat >"$SOLUTION_FILE" <<EOF
package ml.szloch.aoc.e$YEAR.day$day

import ml.szloch.aoc.AoC

class Day$day : AoC<Int, Int> {

    override fun firstStar(): Int = TODO()

    override fun secondStar(): Int  = TODO()

}

fun main() {
    Day$day().solve()
}
EOF
    fi
  done
}

function create_directories_for_tests() {
    for day in {01..25}; do
    mkdir -pv "$TESTS_LOCATION/day$day"
    TEST_FILE="$TESTS_LOCATION/day$day/Day${day}Test.kt"
    if [[ -f "$TEST_FILE" ]]; then
      echo "Skipping file $TEST_FILE"
    else
      echo "Creating file $TEST_FILE"
      cat >"$TEST_FILE" <<EOF
package ml.szloch.aoc.e$YEAR.day$day

import org.assertj.core.api.Assertions
import org.junit.Test

class Day${day}Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day${day}().firstStar()).isEqualTo(0)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day${day}().secondStar()).isEqualTo(0)
    }
}
EOF
    fi
  done
}

create_directories_for_solutions
create_directories_for_tests
create_directories_for_inputs
