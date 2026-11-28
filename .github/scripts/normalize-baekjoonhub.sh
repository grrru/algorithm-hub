#!/usr/bin/env bash
# BaekjoonHub pushes to "백준/<tier>/<id>. <title>/" and "프로그래머스/<level>/...".
# This repo keeps them under "boj/" and "programmers/" instead.
set -euo pipefail

shopt -s nullglob dotglob

move_tree() {
  local src="$1" dest="$2"
  [[ -d "${src}" ]] || return 0

  # Walk files so existing problem directories are merged (resubmissions overwrite).
  while IFS= read -r -d '' file; do
    local rel="${file#"${src}"/}"
    mkdir -p "${dest}/$(dirname "${rel}")"
    mv -f "${file}" "${dest}/${rel}"
  done < <(find "${src}" -type f -print0)

  find "${src}" -depth -type d -empty -delete
}

move_tree "백준" "boj"
move_tree "프로그래머스" "programmers"
