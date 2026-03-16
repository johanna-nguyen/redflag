#!/usr/bin/env bash
set -euo pipefail

db_host="${DB_HOST:-mysql}"
db_port="${DB_PORT:-3306}"

echo "Waiting for MySQL at ${db_host}:${db_port}..."
for _ in $(seq 1 60); do
  if (echo >"/dev/tcp/${db_host}/${db_port}") >/dev/null 2>&1; then
    echo "MySQL is reachable, starting app."
    exec java -jar /app/app.jar
  fi
  sleep 2
done

echo "MySQL not reachable after 120s: ${db_host}:${db_port}" >&2
exit 1

