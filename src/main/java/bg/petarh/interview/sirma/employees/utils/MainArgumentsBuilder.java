package bg.petarh.interview.sirma.employees.utils;

import java.util.Locale;

public class MainArgumentsBuilder {
    private ArgumentsHolder holder;

    public MainArgumentsBuilder(String[] args) {
        buildArguments(args);
    }

    // potentially more arguments ?!
    // overengineering doesnt hurt i guess
    private void buildArguments(String[] args) {

        String filePath = findValueInArgs("-filePath", args);
        boolean withUi = Boolean.TRUE.equals(Boolean.parseBoolean(findValueInArgs("-withUI", args)));
        this.holder = new ArgumentsHolder.Builder().setFilePath(filePath).setWithUi(withUi).build();
    }

    private String findValueInArgs(String param, String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].strip().toLowerCase(Locale.ROOT).equals(param.strip().toLowerCase(Locale.ROOT))) {
                try {
                    return args[i + 1];
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    System.out.println(indexOutOfBoundsException.getLocalizedMessage());
                }
            }
        }
        return null;
    }

    public ArgumentsHolder getArguments() {
        return holder;
    }
}
