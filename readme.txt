This is a template project that can be used to generate a project for your TeamCity plugin.
It uses IntelliJ IDEA and Ant.

To generate your copy of the plugin with the required name, go to "generate" directory and run the command there:
ant -Dnew.name=<name of your plugin> -Dtarget.dir=<directory to create the project in>
(Ant 1.7 or later should be in the Path)