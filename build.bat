@echo off
setlocal enabledelayedexpansion
chcp 65001 >nul

:MAIN_MENU
cls
echo ╔══════════════════════════════════════╗
echo ║            XLib Builder              ║
echo ╠══════════════════════════════════════╣
echo ║  [1] Compile XLib                    ║
echo ║  [2] Install dependency stubs        ║
echo ║  [3] License                         ║
echo ║  [0] Quit                            ║
echo ╚══════════════════════════════════════╝
echo.
echo XLib dynamically links with proprietary Mojang binaries.
echo These are not provided automatically for compilation.
echo You can either install them manually through BuildTools
echo or compile XLib with stubs that provide the needed signatures
echo at compile time. Compiling with stubs makes no difference
echo for running XLib on a Bukkit server. It does however override
echo the Spigot servers installed to your local Maven repository.
echo If you are a developer who wants to compile other projects
echo against real Spigot servers, consider making a backup or
echo building server versions you're possibly missing with BuildTools.
echo.
echo Make sure JDK 8 or higher is installed. If not, you can get it here:
echo https://www.azul.com/downloads/?os=windows^&architecture=x86-64-bit^&package=jdk#zulu
echo.
set /p "CHOICE=Please choose [0-3]: "

if "%CHOICE%"=="1" goto COMPILE
if "%CHOICE%"=="2" goto INSTALL_STUBS
if "%CHOICE%"=="3" goto LICENSE
if "%CHOICE%"=="0" goto EXIT

echo [!] Invalid input. Please enter 1, 2 or 0.
timeout /t 2 >nul
goto MAIN_MENU

:COMPILE
cls
echo ╔══════════════════════════════════════╗
echo ║             Compiling...             ║
echo ╚══════════════════════════════════════╝
echo.
java -jar mvnbt.jar --goal install
echo.
XLib compilation finished.
echo.
pause
goto MAIN_MENU

:INSTALL_STUBS
cls
echo ╔══════════════════════════════════════╗
echo ║   Installing dependencies stubs...   ║
echo ╚══════════════════════════════════════╝
echo.
java -jar mvnbt.jar --dir nmsstub --goal install
echo.
Dependency stub installation finished.

:INSTALL_STUBS_DONE
echo.
echo ╔══════════════════════════════════════╗
echo ║      Proceed to compile XLib?        ║
echo ╠══════════════════════════════════════╣
echo ║  1. Compile XLib                     ║
echo ║  0. Quit                             ║
echo ╚══════════════════════════════════════╝
echo.
set /p "COMPILE_NOW=Please choose [0-1]: "

if "%COMPILE_NOW%"=="1" goto COMPILE
if "%COMPILE_NOW%"=="0" goto EXIT

echo [!] Invalid input. Please enter 1 or 0.
timeout /t 2 >nul
goto INSTALL_STUBS_DONE

:LICENSE
cls
type LICENSE
echo.
pause
goto MAIN_MENU

:EXIT
cls
echo Good bye!
timeout /t 1 >nul
exit /b 0
