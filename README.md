# About project 
Currency caluclator app that lets users convert currencies based on current exchange rates synced from server on start up. When sync is not possible, user can load archival data. 


External JSON parser was used(https://github.com/FasterXML/jackson)
This currency api was used(https://exchangeratesapi.io/)
# Goals
    - write first more complex javaFX app, that is not perfect, because it's writed to develop my understanding of this subject
    - learn the challangers of it, discover what to focus on more in the future and gain as much experience(from planning to technical applications) as possible
    - achieve automatically scaling and responsive UI (does not have to be visually pleasing)
# Usage
    1. get API key from website https://exchangeratesapi.io/
    2. paste your key in place of YOUR_API_KEY in config.properties located in /CurrencyCalculator/src/main/resources/com/example/currencycalculator/config.properties
    3. run main method in Main class
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
    - More focus on spreading responsibilities between classes. In case of this project classes of this type would be usefull:
        - WindowManager/ScreenController - class responsible for launching windows
        - PromptManager - class responsibl  for launching prompts
    - Focus more on reusability of code, think more about when something might be used. In this case, for instance loading exchange rates should be done in a way, that allows for syncing them during running the programm(currently only possible on start up)
    - better handle checking/sending request(they can be unsuccesful, preferably they should be run on other thread)
# Result 
![result1](result1.png?raw=true "Main Screen")
![result2](result2.png?raw=true "Start Screen")
