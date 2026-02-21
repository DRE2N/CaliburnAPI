# XLib Builder

XLib Builder is a tool made to streamline the building process of Bukkit/Spigot plugins. It installs proprietary server .jars to your local Maven repository that cannot safely legally be fetched from an online repository due to GPL violations.

Note that this process can take times of about an hour or longer on low end hardware.

## Requirements

All the Java Development Kits that the server versions themselves require need to be installed:

* JDK 8 for Minecraft 1.8 to 1.16.x
* JDK 17 for Minecraft 1.17.x to 1.19.x
* JDK 21 for Minecraft 1.20.x to 1.21.11
* JDK 25 for Minecaraft 26.1 and future versions

The builder itself requires JDK 25.

It is recommended to install the latest versions of each from Azul Zulu: https://www.azul.com/downloads

## Flags

| Flag, alias           | Values, **default**                      | What it does                                                                                                                              |
|-----------------------|------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| --clean, -c           | N/A                                      | Deletes all BuildTools and Maven target directories.                                                                                      |
| --goal, -g            | package, **install**                     | Sets the goal of the Maven build process.                                                                                                 |
| --mvnver, -mv         | Maven version                            | Sets the Maven version to download. By default, what the newest version is is fetched from the Apache servers.                            |
| --revision, --rev, -r | Minecraft version string (e.g. 1.21)     | Specifies the Spigot version to build. by default, all versions are built. Can be used multiple times to build multiple versions at once. |
| --threads, -t         | Integer, **number of versions to build** | Limits the number of threads used. Use if your PC isn't responsive during the process.                                                    |
| --verbose, -v         | N/A                                      | If enabled BuildTools messages are forwarded to console. This is very messy.                                                              |

## build.cfg

This file allows users to specify which JDK to use to build which servers.

```
8=C:\Program Files\Zulu\zulu-8\bin\java.exe|1.8,1.8.3,1.8.8,1.9.2,1.9.4,1.10.2,1.11.2,1.12.2,1.13,1.13.2,1.14.4,1.15.2,1.16.1,1.16.3,1.16.5
17=C:\Program Files\Zulu\zulu-17\bin\java.exe|1.17.1,1.18.1,1.18.2,1.19.2,1.19.3,1.19.4
21=C:\Program Files\Zulu\zulu-21\bin\java.exe|1.20.1,1.20.2,1.20.4,1.20.6,1.21.1,1.21.3,1.21.4,1.21.5,1.21.8,1.21.10,1.21.11
```

## NMS/OBC relocation versions

One server is installed per relocation - or, since the post relocation era seems to have begun, when changes make it necessary. For example, Paper 1.21.5-1.21.11 might not need a new build.

| Server software            | Relocation | Relocated packages | Latest MC version | Mappings      | JDK |
|----------------------------|------------|--------------------|-------------------|---------------|-----|
| Spigot, Paper              | 1_8_R1     | NMS, OBC           | 1.8               | Spigot        | 8   |
| Spigot, Paper              | 1_8_R2     | NMS, OBC           | 1.8.3             | Spigot        | 8   |
| Spigot, Paper              | 1_8_R3     | NMS, OBC           | 1.8.8             | Spigot        | 8   |
| Spigot, Paper              | 1_9_R1     | NMS, OBC           | 1.9.2             | Spigot        | 8   |
| Spigot, Paper              | 1_9_R2     | NMS, OBC           | 1.9.4             | Spigot        | 8   |
| Spigot, Paper              | 1_10_R1    | NMS, OBC           | 1.10.2            | Spigot        | 8   |
| Spigot, Paper              | 1_11_R1    | NMS, OBC           | 1.11.2            | Spigot        | 8   |
| Spigot, Paper              | 1_12_R1    | NMS, OBC           | 1.12.2            | Spigot        | 8   |
| Spigot, Paper              | 1_13_R1    | NMS, OBC           | 1.13              | Spigot        | 8   |
| Spigot, Paper              | 1_13_R2    | NMS, OBC           | 1.13.2            | Spigot        | 8   |
| Spigot, Paper              | 1_14_R1    | NMS, OBC           | 1.14.4            | Spigot        | 8   |
| Spigot, Paper              | 1_15_R1    | NMS, OBC           | 1.15.2            | Spigot        | 8   |
| Spigot, Paper              | 1_16_R1    | NMS, OBC           | 1.16.1            | Spigot        | 8   |
| Spigot, Paper              | 1_16_R2    | NMS, OBC           | 1.16.3            | Spigot        | 8   |
| Spigot, Paper              | 1_16_R3    | NMS, OBC           | 1.16.5            | Spigot        | 8   |
| Spigot, Paper              | 1_17_R1    | OBC                | 1.17.1            | SpecialSource | 17  |
| Spigot, Paper              | 1_18_R1    | OBC                | 1.18.1            | SpecialSource | 17  |
| Spigot, Paper              | 1_18_R2    | OBC                | 1.18.2            | SpecialSource | 17  |
| Spigot, Paper              | 1_19_R1    | OBC                | 1.19.2            | SpecialSource | 17  |
| Spigot, Paper              | 1_19_R2    | OBC                | 1.19.3            | SpecialSource | 17  |
| Spigot, Paper              | 1_19_R3    | OBC                | 1.19.4            | SpecialSource | 17  |
| Spigot, Paper              | 1_20_R1    | OBC                | 1.20.1            | SpecialSource | 21  |
| Spigot, Paper              | 1_20_R2    | OBC                | 1.20.2            | SpecialSource | 21  |
| Spigot, Paper              | 1_20_R3    | OBC                | 1.20.4            | SpecialSource | 21  |
| Spigot, Paper              | 1_20_R4    | OBC                | 1.20.6            | SpecialSource | 21  |
| Spigot, Paper              | 1_21_R1    | OBC                | 1.21.1            | SpecialSource | 21  |
| Spigot, Paper              | 1_21_R2    | OBC                | 1.21.3            | SpecialSource | 21  |
| Spigot                     | 1_21_R3    | OBC                | 1.21.4            | SpecialSource | 21  |
| Spigot                     | 1_21_R4    | OBC                | 1.21.5            | SpecialSource | 21  |
| Spigot                     | 1_21_R5    | OBC                | 1.21.8            | SpecialSource | 21  |
| Spigot                     | 1_21_R6    | OBC                | 1.21.10           | SpecialSource | 21  |
| Spigot                     | 1_21_R7    | OBC                | 1.21.11 (so far)  | SpecialSource | 21  |
| Paper                      | 1_21_R3    | OBC                | 1.21.4            | SpecialSource | 21  |
| Paper                      | none       | none               | 1.21.5            | Mojang        | 21  |
| Paper                      | none       | none               | 1.21.8            | Mojang        | 21  |
| Paper                      | none       | none               | 1.21.10           | Mojang        | 21  |
| Paper                      | none       | none               | 1.21.11           | Mojang        | 21  |
| future Spigot              | none       | ???                | 26.1              | Mojang        | 25  |
| future Paper               | none       | none               | 26.1              | Mojang        | 25  |

### Other server softwares

The other relevant servers for Vanilla clients (such as Purpur) nowadays are based on Paper and the same applies analogously. It is assumed that forks of legacy versions (such as 1.8.8) keep binary compatibility with Spigot 1.8.8.

Forge-based Bukkit hybrid servers (successors of MCPC+/Cauldron) are not CraftBukkit/Spigot/Paper forks and thus **none of this applies to them.** Functionality cannot be guaranteed.
