find . -name "*.out" -type f -delete && find . -type f  ! -name "*.*"  -delete && cd ../ && fish gitPushAll.sh && cd IOT301x
