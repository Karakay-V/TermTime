# Simple application that allows you to set timers

> The application was created while studying the basics of java SE8.

In this application, you are given the opportunity to set timers for a specified period of time in seconds or minutes or hours by specifying the appropriate symbol at the end of the numbers.

You can change the time region by specifying it's name or the time difference from Greenwich Mean Time (GMT).


## Create a jar package file with [ant](https://ant.apache.org) build tool.

> [!NOTE]
> For graphical notifications to be displayed, you need to have the ```libnotify``` package installed on your system.

```bash
ant jar
```

The built package will be placed in ```./build/jar/``` directory.

### Possible ant commands

```bash
# Remove build directory.
ant clean

# Compile the source.
ant compile

# Compile the source and create a package.
ant jar

# Create and run a package.
ant run
```

To see the list of availible commands, call ```help``` as argument for application.
