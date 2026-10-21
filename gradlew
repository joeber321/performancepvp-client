#!/usr/bin/env bash
set -e
if ! command -v gradle >/dev/null 2>&1; then
  echo "Gradle is not installed. Run: sudo apt update && sudo apt install gradle"
  exit 1
fi
gradle "$@"