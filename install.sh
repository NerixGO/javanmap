#!/usr/bin/env bash

set -e

APP_NAME="javanmap"
INSTALL_DIR="/opt/$APP_NAME"
BIN_LINK="/usr/local/bin/$APP_NAME"

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

echo "Installing $APP_NAME..."

if ! command -v javac >/dev/null 2>&1; then
  echo "Error: javac not found."
  echo "Please install Java (JDK 8 or newer) and try again."
  exit 1
fi

sudo mkdir -p "$INSTALL_DIR"

sudo cp -a "$SCRIPT_DIR/." "$INSTALL_DIR/"

echo "Compiling Java sources..."
sudo rm -rf "$INSTALL_DIR/bin"
sudo mkdir -p "$INSTALL_DIR/bin"
sudo javac -d "$INSTALL_DIR/bin" "$INSTALL_DIR"/*.java

sudo chmod +x "$INSTALL_DIR/javanmap"

sudo ln -sf "$INSTALL_DIR/javanmap" "$BIN_LINK"

echo
echo "Installation completed successfully."
echo "Run the program with:"
echo
echo "    javanmap"
echo