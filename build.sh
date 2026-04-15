#!/bin/bash

cd "$(dirname "$0")"

main_menu() {
    clear
    echo "╔══════════════════════════════════════╗"
    echo "║            XLib Builder              ║"
    echo "╠══════════════════════════════════════╣"
    echo "║  [1] Compile XLib                    ║"
    echo "║  [2] Install dependency stubs        ║"
    echo "║  [3] License                         ║"
    echo "║  [0] Quit                            ║"
    echo "╚══════════════════════════════════════╝"
    echo
    echo "XLib dynamically links with proprietary Mojang binaries."
    echo "These are not provided automatically for compilation."
    echo "You can either install them manually through BuildTools"
    echo "or compile XLib with stubs that provide the needed signatures"
    echo "at compile time. Compiling with stubs makes no difference"
    echo "for running XLib on a Bukkit server. It does however override"
    echo "the Spigot servers installed to your local Maven repository."
    echo "If you are a developer who wants to compile other projects"
    echo "against real Spigot servers, consider making a backup or"
    echo "building server versions you're possibly missing with BuildTools."
    echo
    echo "Make sure JDK 8 or higher is installed. If not, you can get it here:"
    echo "https://www.azul.com/downloads/?os=linux&architecture=x86-64-bit&package=jdk#zulu"
    echo
    read -rp "Please choose [0-3]: " CHOICE

    case "$CHOICE" in
        1) compile ;;
        2) install_stubs ;;
        3) show_license ;;
        0) do_exit ;;
        *)
            echo "[!] Invalid input. Please enter 1, 2, 3 or 0."
            sleep 2
            main_menu
            ;;
    esac
}

compile() {
    clear
    echo "╔══════════════════════════════════════╗"
    echo "║             Compiling...             ║"
    echo "╚══════════════════════════════════════╝"
    echo
    java -jar mvnbt.jar --goal install
    echo
    echo "XLib compilation finished."
    echo
    read -rp "Press Enter to continue..."
    main_menu
}

install_stubs() {
    clear
    echo "╔══════════════════════════════════════╗"
    echo "║   Installing dependencies stubs...   ║"
    echo "╚══════════════════════════════════════╝"
    echo
    java -jar mvnbt.jar --dir nmsstub --goal install
    echo
    echo "Dependency stub installation finished."
    install_stubs_done
}

install_stubs_done() {
    echo
    echo "╔══════════════════════════════════════╗"
    echo "║      Proceed to compile XLib?        ║"
    echo "╠══════════════════════════════════════╣"
    echo "║  [1] Compile XLib                    ║"
    echo "║  [0] Quit                            ║"
    echo "╚══════════════════════════════════════╝"
    echo
    read -rp "Please choose [0-1]: " COMPILE_NOW

    case "$COMPILE_NOW" in
        1) compile ;;
        0) do_exit ;;
        *)
            echo "[!] Invalid input. Please enter 1 or 0."
            sleep 2
            install_stubs_done
            ;;
    esac
}

show_license() {
    clear
    cat LICENSE
    echo
    read -rp "Press Enter to continue..."
    main_menu
}

do_exit() {
    clear
    echo "Good bye!"
    sleep 1
    exit 0
}

main_menu
