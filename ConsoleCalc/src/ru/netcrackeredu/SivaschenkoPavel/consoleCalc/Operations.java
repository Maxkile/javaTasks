package ru.netcrackeredu.SivaschenkoPavel.consoleCalc;

/**
 * This enum provides comfortable instrument to control properties.Enum constants represent operations' names which are used during program runtime.
 */
public enum Operations {
    SUM("Sum", "+"), SUBTRACTING("Subtracting", "-"), DIVISION("Division", "/"), MULTIPLYING("Multiplying", "*"),
    POW("Pow", "^");

    private String operationName;
    private String operationSign;

    /**
     * Constructor for Operations enum objects
     *
     * @param name "Name" - String variable which is similar to appropriate class name and key in properties file. This name is principal in getting
     *             instance of operation object.
     * @param sign "Sign" - String variable which is equivalent to that is using in user input. So, "sign" is just console representation of operation or function.
     */
    private Operations(String name, String sign) {
        this.operationName = name;
        this.operationSign = sign;
    }

    public String getName() {
        return operationName;
    }

    public String getSign() {
        return operationSign;
    }

    /**
     * Determines operationSign by operationName(enum Operations). If no operation is defined for this operationName than throws an exception.
     *
     * @param name Possible operationName.
     * @return operationSign
     */
    public static String getSign(String name) throws NotValidOperationException {
        for (Operations operation : Operations.values()) {
            if (name.equals(operation.getName())) return operation.getSign();
        }
        throw new NotValidOperationException(name);
    }
}
