#!/usr/bin/env bash

set -e

APP_NAME="javanmap"
INSTALL_DIR="/opt/$APP_NAME"
BIN_LINK="/usr/local/bin/$APP_NAME"

echo "Installing..."

sudo rm -rf "$INSTALL_DIR"

sudo mkdir -p "$INSTALL_DIR"

sudo rsync -a --delete ./ "$INSTALL_DIR/"

sudo chmod +x "$INSTALL_DIR/javanmap"

sudo ln -sf "$INSTALL_DIR/javanmap" "$BIN_LINK"

echo "Done!"
echo "Run: javanmap"