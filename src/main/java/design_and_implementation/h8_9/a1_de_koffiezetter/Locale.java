package design_and_implementation.h8_9.a1_de_koffiezetter;

import java.io.Serializable;
import java.util.Objects;

public enum Locale implements Serializable {
    GENERIC_ON("aan"),
    GENERIC_OFF("uit"),
    GENERIC_CMD_STATUS("status"),
    GENERIC_CMD_SET("zet"),
    GENERIC_CMD_SELECT("select"),
    GENERIC_CMD_SHOW("toon"),
    MACHINE_MISSING("kies eerst een koffiezetter"),
    MACHINE_MISSING_POWER("de koffiezetter moet eerst aangezet worden"),
    MACHINE_MISSING_ITEM("de koffiezetter heeft geen item op slot '{0}'"),
    MACHINE_INFO_POWER("de koffiezetter is {0}"),
    MACHINE_INFO_SLOT("{0}: {1}"),
    MACHINE_UPDATE_POWER(MACHINE_INFO_POWER.message+" gezet"),
    ITEM_INFO("{0}: {1}")
    ;

    private final String message;
    Locale(String message) {
        this.message = message;
    }

    public String msg() {
        return message;
    }

    public String msg(Serializable... args) {
        String manip = message;

        for (int i = 0; i < args.length; i++) {
            String replacement = args[i].toString();
            manip = manip.replace("{" + i + "}", Objects.nonNull(replacement) ? replacement : "<?>");
        }
        return manip;
    }

    public void print() {
        System.out.println(msg());
    }

    public void print(Serializable... args) {
        System.out.println(msg(args));
    }

    @Override
    public String toString() {
        return msg();
    }
}
