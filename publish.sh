#!/usr/bin/env bash
./gradlew clean assembleDebug

./gradlew :coroutine-animations:install
./gradlew :coroutine-animations:bintrayUpload