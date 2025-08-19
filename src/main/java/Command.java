public enum Command {
    LIST_TASK("list") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            return tm.viewList();
        }
    },

    ADD_TODO_TASK("todo") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                String[] result = validateAndFormatModifier(modifier[0], new String[]{});
                return tm.addTodoTask(result[0]);
            } catch (InvalidFormatException e) {
                return e.getMessage();
            }
        }
    },

    ADD_DEADLINE_TASK("deadline") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                String[] result = validateAndFormatModifier(modifier[0], new String[]{"/by"});
                return tm.addDeadlineTask(result[0], result[1]);
            } catch (InvalidFormatException e) {
                return e.getMessage();
            }
        }
    },

    ADD_EVENT_TASK("event") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                String[] result = validateAndFormatModifier(modifier[0], new String[]{"/from", "/to"});
                return tm.addEventTask(result[0], result[1], result[2]);
            } catch (InvalidFormatException e) {
                return e.getMessage();
            }
        }
    },

    DELETE_TASK("delete") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                String[] result = validateAndFormatModifier(modifier[0], new String[]{});
                int idx = Integer.parseInt(result[0]);
                return tm.deleteTask(idx);
            } catch (InvalidFormatException e) {
                return e.getMessage();
            } catch (NumberFormatException e) {
                return "that command isn't right: only numbers are allowed!";
            }
        }
    },

    MARK_TASK("mark") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                String[] result = validateAndFormatModifier(modifier[0], new String[]{});
                int idx = Integer.parseInt(result[0]);
                return tm.markTaskAsDone(idx);
            } catch (InvalidFormatException e) {
                return e.getMessage();
            } catch (NumberFormatException e) {
                return "that command isn't right: only numbers are allowed!";
            }
        }
    },

    UNMARK_TASK("unmark") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                String[] result = validateAndFormatModifier(modifier[0], new String[]{});
                int idx = Integer.parseInt(result[0]);
                return tm.unmarkTaskAsDone(idx);
            } catch (InvalidFormatException e) {
                return e.getMessage();
            } catch (NumberFormatException e) {
                return "that command isn't right: only numbers are allowed!";
            }
        }
    };

    private final String keyword;

    Command(String keyword) {
        this.keyword = keyword;
    }

    public abstract String execute(String[] modifier, TaskManager tm);

    /**
     * Compares user input with current valid commands and returns corresponding Command object if valid.
     * If invalid command, returns null.
     *
     * @param input User input as a String
     * @return Command object
     */
    public static Command validateCommand(String input) {
        for (Command c : values()) {
            if (c.keyword.equals(input)) {
                return c;
            }
        }
        return null;
    }

    //checks modifier is of correct format based on provided acceptedModifiers
    //returns the separated String[] version of the modifier if valid
    private static String[] validateAndFormatModifier(String modifier, String[] acceptedModifiers) throws InvalidFormatException {

        //check that modifier contains the accepted modifiers provided
        StringBuilder missing = new StringBuilder();
        for (String accepted: acceptedModifiers) {
            if (!modifier.contains(accepted)) {
                missing.append(" ").append(accepted);
            }
        }

        //throw if any missing modifiers
        if (!missing.isEmpty()) {
            throw new InvalidFormatException(String.format("missing the modifier(s):%s!", missing));
        }

        //format the modifier into its parameters based on the accepted modifiers
        String regex = String.join("|", acceptedModifiers);
        String[] params = modifier.split(regex, acceptedModifiers.length + 1);

        //ensure that none of the parameters are empty, otherwise trim leading and trailing whitespaces as well
        for (int i = 0; i< params.length; i++) {
            if (params[i].isEmpty()) {
                throw new InvalidFormatException("one of the parameters is missing!");
            } else {
                params[i] = params[i].trim();
            }
        }
        return params;
    }
}

