package bg.petarh.interview.sirma.employees;

import bg.petarh.interview.sirma.employees.main.ConsoleApp;
import bg.petarh.interview.sirma.employees.main.EmployeeRunner;
import bg.petarh.interview.sirma.employees.main.FxApp;
import bg.petarh.interview.sirma.employees.utils.ArgumentsChecker;
import bg.petarh.interview.sirma.employees.utils.MainArgumentsBuilder;

public class MainConsole {

    public static void main(String[] args) {
        if (!ArgumentsChecker.argumentsCheck(args)) {
            System.exit(1);
        }
        MainArgumentsBuilder mab = new MainArgumentsBuilder(args);
        if (mab.getArguments().getWithUI()) {
            EmployeeRunner fxApp = new FxApp();
            fxApp.run(mab);
        } else {
            EmployeeRunner consoleApp = new ConsoleApp();
            consoleApp.run(mab);
        }
    }
}
