#!/usr/bin/env bash

set -e

APP_NAME="javanmap"
INSTALL_DIR="/opt/$APP_NAME"
BIN_LINK="/usr/local/bin/$APP_NAME"

echo "Uninstalling $APP_NAME..."

if [ -L "$BIN_LINK" ] || [ -f "$BIN_LINK" ]; then
    sudo rm -f "$BIN_LINK"
    echo "Removed: $BIN_LINK"
else
    echo "Not found: $BIN_LINK"
fi

if [ -d "$INSTALL_DIR" ]; then
    sudo rm -rf "$INSTALL_DIR"
    echo "Removed: $INSTALL_DIR"
else
    echo "Not found: $INSTALL_DIR"
fi

echo
echo "$APP_NAME has been successfully uninstalled."