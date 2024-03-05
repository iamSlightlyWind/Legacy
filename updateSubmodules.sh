#!/bin/bash

git submodule update --init --recursive

for submodule in $(git submodule status | awk '{print $2}'); do
    echo "Updating $submodule"
    cd $submodule
    git checkout main
    git pull
    cd ..
done
