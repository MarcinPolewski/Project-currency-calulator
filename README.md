# About project 

External JSON parser was used(https://github.com/FasterXML/jackson)
This currency api was used(https://exchangeratesapi.io/)
# Goals

# Design questions/problems
    - should LocalConnectionHandler class be static? In should not need any type of instance related variables. On the other hand if it needed not applying static keyword made implementing them much easier
    - handle better passing CurrencyCalculator(main logic class) to controller classes 
        - option 1. make LocalConnectionHandle not static 
        - option 2. (chosen one) LocalConnectionHandler has static property of CurrencyCaluclator class that is initialized in main
# Reflections
    - Name "LocalConnectionHandler" is not as understandable as i thourgh (memmoryHandler would be better )
    - use of some kind of DependencyInjection Framework would be really usefull 
        - in this project i had problem with injection CurrencyCalculator class into screen controller to start loading assets
    - Planning multithreading should also contain stopping the thread
        - stopping a thread is a major problem when for instance some manipulation on files is done. One solution for example when writing is writing to a temporary file then swaping their names and deleting previous version
    - Some kind of scene controller might be helpful(a class that handles switching scenes 
    - 