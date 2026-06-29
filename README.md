# Javanmap

Javanmap is a lightweight network scanner written from scratch in Java, inspired by Nmap.
It provides basic host discovery and port scanning functionality through a simple CLI interface.

---

## ⚠️ Disclaimer

This tool is intended for educational and authorized security testing only.
Do not use it on networks or systems you do not own or have explicit permission to scan.

The author is not responsible for any misuse or damage caused by this tool.

---

## 🛠 Requirements

- Java 8 or higher (JDK required, not just JRE)
- Bash (for installation and CLI launcher)
- Git (only required for update functionality)

Check Java installation:

```bash
java -version
javac -version
```

---

## 📥 Installation

Clone the repository:

```bash
git clone --depth 1 https://github.com/NerixGO/javanmap.git

chmod +x javanmap/install.sh

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

You can also use domain names:

```bas
javanmap youtube.com
```

NOTE:
Some domains may not respond to ICMP ping due to firewall or provider restrictions.

---

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

NOTE:
Requires git-based installation (repository cloned via git).

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

* Host discovery (ICMP reachability check via Java)
* TCP port scanning (connect-based)
* UDP probe scanning (basic response detection)
* Top 1000 most common ports scan
* Single port scan mode
* Simple CLI interface
* Multithreaded scanning for performance

---

## LIMITATIONS

* ICMP ping may not work on all systems (OS and firewall dependent)
* UDP scanning is not fully reliable (depends on service behavior)
* Some operations may require elevated permissions on Linux/macOS

---

## 👨‍💻 Author

Created by NerixGO

GitHub: https://github.com/NerixGO