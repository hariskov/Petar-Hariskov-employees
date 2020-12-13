package bg.petarh.interview.sirma.employees.utils;

public class ArgumentsChecker {
    private static final int ARGS_COUNT = 4;

    private ArgumentsChecker(){}

    public static boolean argumentsCheck(String[] args){
        if (args.length == 0) {
            System.out.println("please specify parameters for application launch or consult the manual via -man parameter");
            return false;
        }

        if (args[0].equals("man") || args[0].equals("-man")) {
            System.out.println("in order to run the application you need the following parameters :");
            System.out.println("-filePath argument followed by the path to the file (Mandatory)");
            System.out.println("-withUI boolean (Optional), defaults to false");
            return false;
        }

        if (args.length % ARGS_COUNT != 0 || args.length > ARGS_COUNT) {
            System.out.println("wrong argument count");
            return false;
        }

        return true;
    }
}
