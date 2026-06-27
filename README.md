# Javanmap

Javanmap is a lightweight network scanner written from scratch in Java, inspired by Nmap.
It provides basic host discovery and port scanning functionality through a simple CLI interface.

---

## ⚠️ Disclaimer

This tool is intended for educational purposes only.
Do not use it on networks or systems you do not own or have explicit permission to scan.
The author is not responsible for any misuse.

---

## 🛠 Requirements

- Java 8 or higher (JDK required, not just JRE)
- Bash (for installation and CLI launcher)
- Git (for update functionality)

Check Java version:

```bash
java -version
javac -version
````

---

## 📥 Installation

Clone the repository:

```bash
git clone --depth 1 https://github.com/NerixGO/javanmap.git

sudo bash javanmap/install.sh

rm -rf javanmap
```

After installation, you can run:

```bash
javanmap
```

---

## 🚀 Usage

Basic scan:

```bash
javanmap 192.168.0.1
```

Scan specific port:

```bash
javanmap -p 80 192.168.0.1
```

Show help:

```bash
javanmap --help
```

---

## 🔄 Update

To update the program:

```bash
javanmap update
```

> Requires git-based installation

---

## ⛔ Uninstall

To remove the program:

```bash
javanmap uninstall
```

Or manually:

```bash
sudo bash /opt/javanmap/uninstall.sh
```

---

## 🧠 Features

* Host discovery (basic ping check)
* Top 1000 port scan
* Single port scan
* Simple CLI interface
* Fast TCP socket scanning

---

## 👨‍💻 Author

Created by NerixGO

GitHub: https://github.com/NerixGO