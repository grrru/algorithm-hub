#!/usr/bin/env bash
set -euo pipefail

mkdir -p leetcode

shopt -s nullglob dotglob
for src in [0-9][0-9][0-9][0-9]-*/; do
  src="${src%/}"
  dest="leetcode/${src}"

  if [[ -e "${dest}" ]]; then
    for item in "${src}"/*; do
      git mv -f "${item}" "${dest}/" 2>/dev/null || mv -f "${item}" "${dest}/"
    done
    rmdir "${src}"
  else
    git mv "${src}" "${dest}" 2>/dev/null || mv "${src}" "${dest}"
  fi
done

if [[ -f README.md ]]; then
  perl -0pi -e 's#(/tree/(?:main|master))/(?!leetcode/)([0-9]{4}-[A-Za-z0-9._-]+)#$1/leetcode/$2#g' README.md
fi
