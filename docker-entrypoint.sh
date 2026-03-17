#!/usr/bin/env bash
set -euo pipefail

db_host="${DB_HOST:-}"
db_port="${DB_PORT:-}"

# Render doesn't run docker-compose; there is no service DNS like "postgres".
# Prefer explicit DB_HOST/DB_PORT, otherwise derive from SPRING_DATASOURCE_URL.
if [[ -z "${db_host}" || -z "${db_port}" ]]; then
  jdbc_url="${SPRING_DATASOURCE_URL:-}"
  if [[ "${jdbc_url}" == jdbc:postgresql://* ]]; then
    # jdbc:postgresql://host:port/db?params
    hostport="${jdbc_url#jdbc:postgresql://}"
    hostport="${hostport%%/*}"
    if [[ "${hostport}" == *:* ]]; then
      db_host="${db_host:-${hostport%%:*}}"
      db_port="${db_port:-${hostport##*:}}"
    else
      db_host="${db_host:-${hostport}}"
      db_port="${db_port:-5432}"
    fi
  fi
fi

db_host="${db_host:-localhost}"
db_port="${db_port:-5432}"

echo "Waiting for database at ${db_host}:${db_port}..."
for _ in $(seq 1 60); do
  if (echo >"/dev/tcp/${db_host}/${db_port}") >/dev/null 2>&1; then
    echo "Database is reachable, starting app."
    exec java -jar /app/app.jar
  fi
  sleep 2
done

echo "Database not reachable after 120s: ${db_host}:${db_port}" >&2
exit 1
