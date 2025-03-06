# Dependencies

## Visual Paradigm Watermark Remover
[Visual Paradigm Watermark Remover](https://github.com/iamSlightlyWind/nixScript/blob/main/VPWMR/removeWatermark.sh)

Example:
```sh
~/Repositories/nixScript/VPWMR/removeWatermark.sh ./diagrams/*
```

## PlantUML
[PlantUML](https://plantuml.com/download)

Example:
```sh
java -jar plantuml.jar ./sequence_diagrams/*.puml -o ./output/
```

## Graphviz
[Graphviz](https://archlinux.org/packages/extra/x86_64/graphviz/)

Distro dependent. Required for class diagrams.

## CRC Diagram
[CRC Diagram](https://github.com/MarioKusek/CRCDiagram)

Example:
```sh
java -jar crcd.jar ./crc_diagrams/*.crc -o ./output/
```