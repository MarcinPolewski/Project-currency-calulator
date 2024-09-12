# About the Project
Currency calculator app that lets users convert currencies based on current exchange rates, synced from the server at startup. When syncing is not possible, the user can load archival data. Furthermore, the user is presented with exchange rates from the selected base currency to all other currencies.

# Goals
- Write my first more complex JavaFX app. It's not perfect, as it's primarily built to develop my understanding of JavaFX.
- Learn the challenges of working with JavaFX, discover areas to focus on in the future, and gain as much experience as possible (from planning to technical application).
- Achieve automatic scaling and responsive UI (visual appeal is not a priority).

# Usage
1. Get an API key from [Exchange Rates API](https://exchangeratesapi.io/).
2. Paste your key in place of `YOUR_API_KEY` in the `config.properties` file located at `/CurrencyCalculator/src/main/resources/com/example/currencycalculator/config.properties`.
3. Run the main method in the `Main` class.

# Design Questions/Problems
- Should the `LocalConnectionHandler` class be static? It doesn’t seem to need instance-related variables. On the other hand, if it did, omitting the static keyword would make implementing them much easier.
- Better handling is needed for passing the `CurrencyCalculator` (main logic class) to controller classes:
  - Option 1: Make `LocalConnectionHandler` non-static.
  - Option 2 (chosen): `LocalConnectionHandler` has a static property of the `CurrencyCalculator` class that is initialized in `Main`.

# Reflections
- The name "LocalConnectionHandler" is not as clear as I thought; `MemoryHandler` would be a better name.
- Using some kind of Dependency Injection framework would have been really useful.
  - In this project, I had trouble injecting the `CurrencyCalculator` class into the screen controller to start loading assets.
- Planning for multithreading should also include stopping the thread.
  - Stopping a thread can be challenging, especially when manipulating files. One solution is to write to a temporary file, then swap their names and delete the previous version.
- More focus is needed on distributing responsibilities between classes. For this project, the following types of classes would be useful:
  - `WindowManager`/`ScreenController` - responsible for managing windows.
  - `PromptManager` - responsible for handling prompts.
- Focus more on code reusability. For example, loading exchange rates should be implemented in a way that allows syncing them during runtime (currently, it's only possible on startup).
- Better handling of request checking/sending is needed (requests can fail and should preferably run on a separate thread).

# Result
![Main Screen](result1.png?raw=true "Main Screen")
![Start Screen](result2.png?raw=true "Start Screen")

# External Libraries/APIs
- [Jackson](https://github.com/FasterXML/jackson) - External JSON parser.
- [Exchange Rates API](https://exchangeratesapi.io/) - Currency exchange rate data provider.
